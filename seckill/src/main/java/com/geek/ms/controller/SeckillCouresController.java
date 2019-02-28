package com.geek.ms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.annotation.CacheLock;
import com.geek.ms.annotation.CacheParam;
import com.geek.ms.annotation.Limit;
import com.geek.ms.model.CourseModel;
import com.geek.ms.model.StudentCouresInfo;
import com.geek.ms.service.SeckillCouresService;
import com.geek.ms.util.BeanString;

@Controller
@RequestMapping("/select")
public class SeckillCouresController {
	
	private Logger logger = LoggerFactory.getLogger(SeckillCouresController.class);
	//原子性自增
	private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

	@Autowired
	SeckillCouresService seckillCouresService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Environment env;
	
	@RequiresRoles(value={"student","admin"},logical=Logical.OR)
	@RequestMapping
	public String into(
			@RequestParam(required = false, defaultValue = "0") int start, 
			@RequestParam(required = false, defaultValue = "5") int length,
			Model model) {
		if(SecurityUtils.getSubject().hasRole("student")) {
			if(seckillCouresService.isOpen()) {
				List<CourseModel> cms = seckillCouresService.loadCourse(start, length);
				int page = (int)Math.ceil((double) seckillCouresService.getCourseCount() / length);
				model.addAttribute("page", page);
				model.addAttribute("cms", cms);
				model.addAttribute("isOpen", true);
			}else {
				model.addAttribute("isOpen", false);
			}
		}else {
			if(seckillCouresService.isOpen()) {
				model.addAttribute("isOpen", true);
			}else {
				model.addAttribute("isOpen", false);
			}
		}
		return "select";
	}
	
	@RequestMapping("open")
	@ResponseBody
	public String open(Integer open) {
		int result = seckillCouresService.openOrClose(open);
		return ""+result;
	}
	
	@CacheLock(prefix = "limit")
	@RequestMapping("/limit")
	@ResponseBody
	public int limit(@CacheParam(name = "s") @RequestParam(value = "s") List<String> s, StudentCouresInfo sc) {
		logger.info("进入limit成功");
		List<Integer> result = new ArrayList<Integer>();
		for(String str : s) {
			result.add(Integer.parseInt(str.replace("[", "").replace("\"", "").replace("]", "")));
		}
		sc.setUserId((int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
		for(Integer i : result) {
			sc.setCouresInfoId(i);
			System.out.println(sc);
		}
		return ATOMIC_INTEGER.incrementAndGet();
	}
	
	@RequiresRoles(value={"student"})
	@Limit(key = "test", period = 1, count = 5000)
	@RequestMapping(value = "/seckill")
	@ResponseBody
	@CacheLock(prefix = "limit")
	public Map<String, Object> seckill(@CacheParam(name = "s") @RequestParam(value = "s", defaultValue = "") List<String> s, StudentCouresInfo sc) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(seckillCouresService.isOpen()) {
			List<Integer> result = new ArrayList<Integer>();
			for(String str : s) {
				result.add(Integer.parseInt(str.replace("[", "").replace("\"", "").replace("]", "")));
			}
			sc.setUserId((int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			for(Integer i : result) {
				sc.setCouresInfoId(i);
				try {
					logger.info("生产端:开始入队");
					rabbitTemplate.setExchange(env.getProperty("seckill.exchange.name"));
					System.out.println(sc);
					rabbitTemplate.convertAndSend(MessageBuilder.withBody(BeanString.beanToString(sc).getBytes("UTF-8")).build());
					resultMap.put("message" + i, "success");
				}catch (Exception e) {
					resultMap.put("message" + i, "error");
					e.printStackTrace();
				}
			}
		}else {
			resultMap.put("message", "请勿非法访问！");
		}
		
		return resultMap;
	}
	
}
