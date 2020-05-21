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

import com.alibaba.fastjson.JSON;
import com.geek.ms.annotation.CacheLock;
import com.geek.ms.annotation.CacheParam;
import com.geek.ms.annotation.Limit;
import com.geek.ms.pojo.vo.CourseModel;
import com.geek.ms.pojo.vo.SeckillStat;
import com.geek.ms.pojo.vo.StudentCourseInfo;
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
	
	private List<String> studentCounts = new ArrayList<String>();
	
	@RequiresRoles(value={"student","admin"},logical=Logical.OR)
	@RequestMapping
	public String into(
			@RequestParam(required = false, defaultValue = "0") int start, 
			@RequestParam(required = false, defaultValue = "5") int length,
			Model model) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		//学生登录
		if(SecurityUtils.getSubject().hasRole("student")) {
			//选课功能开启时
			if(seckillCouresService.isOpen()) {
				//获取选课信息和数据库中该课程学生选课情况
				List<CourseModel> cms = seckillCouresService.loadCourse(start, length,userId);
				//获取所有课程id
				List<Integer> courseIds = seckillCouresService.loadAllCourseId();
				List<Integer> amounts = new ArrayList<Integer>(courseIds.size());
				List<SeckillStat> csLists = new ArrayList<>();
				int i = 0;
				for(Integer courseId : courseIds) {
					//获取课程余量
					amounts.add(i, Integer.parseInt(seckillCouresService.getAmountFromRedis(courseId)));
					//获取学生选课链表
					List<?> csList = seckillCouresService.getCourseStudentListFromRedis(courseId);
					//判断redis对应选课链表中是否存在当前用户，结果写入model并返回给视图
					boolean haveCurrentUserId = false;
					for(Object redisUserId : csList) {
						if(Integer.parseInt((String) redisUserId) == userId) {
							haveCurrentUserId = true;
						}
					}
					SeckillStat ss = new SeckillStat();
					ss.setCiid(courseId);
					ss.setHaveCurrentUserId(haveCurrentUserId);
					csLists.add(ss);
					i ++;
				}
				int page = (int)Math.ceil((double) seckillCouresService.getCourseCount() / length);
				
				
				
				model.addAttribute("page", page);
				model.addAttribute("cms", cms);
				model.addAttribute("amounts", amounts);
				model.addAttribute("csLists", csLists);
				model.addAttribute("isOpen", true);
			}else {
				//选课功能关闭时
				model.addAttribute("isOpen", false);
			}
		}else {
			//老师登录
			//选课开启时
			if(seckillCouresService.isOpen()) {
				model.addAttribute("isOpen", true);
			}else {
				List<CourseModel> cms = seckillCouresService.loadCourse(start, seckillCouresService.getCourseCount(),userId);
				model.addAttribute("cms", cms);
				model.addAttribute("isOpen", false);
			}
		}
		return "select";
	}
	
	@RequestMapping("/open")
	@ResponseBody
	public String open(Integer open, String amounts) {
		int result = seckillCouresService.openOrClose(open);
		List<Integer> courseIds = seckillCouresService.loadAllCourseId();
		//执行关闭选课时
		if(open == 0) {
			for(Integer courseId : courseIds) {
				seckillCouresService.saveDataWhenClose(courseId);
			}
			//删除所有学生本次选课数量
			
			for(String hashName : studentCounts) {
				System.out.println(hashName);
				seckillCouresService.removeOwnCourse(hashName);
			}
		}else {
			//开启选课时
			List<String> amountsList = JSON.parseArray("[" + amounts + "]", String.class);
			int i = 0;
			for(Integer courseId : courseIds) {
				int amount = Integer.parseInt(amountsList.get(i));
				//设置redis课余量
				seckillCouresService.setAmountToRedis(courseId.intValue(), amount);
				i ++;
			}
			seckillCouresService.clearAllAmount();
		}
		return "" + result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Integer ciid) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		Integer result = seckillCouresService.dropCourseById(userId, ciid);
		seckillCouresService.removeUserFromRedisList(ciid, userId);
		/*
		 * 1.redis课余量hset + 1
		 * 2.reids学生选课hset删除指定数据
		 */
		return "" + result;
	}
	
	@CacheLock(prefix = "limit")
	@RequestMapping("/limit")
	@ResponseBody
	public int limit(@CacheParam(name = "s") @RequestParam(value = "s") List<String> s, StudentCourseInfo sc) {
		logger.info("进入limit成功");
		List<Integer> result = new ArrayList<Integer>();
		for(String str : s) {
			result.add(Integer.parseInt(str.replace("[", "").replace("\"", "").replace("]", "")));
		}
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
	public Map<String, Object> seckill(@CacheParam(name = "s") @RequestParam(value = "s", defaultValue = "") List<String> s, StudentCourseInfo sc) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(seckillCouresService.isOpen()) {
			List<Integer> result = new ArrayList<Integer>();
			for(String str : s) {
				result.add(Integer.parseInt(str.replace("[", "").replace("\"", "").replace("]", "")));
			}
			Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
			sc.setUserId(userId);
			if(seckillCouresService.getOwnCourseCount(userId) + result.size() <= 3) {
				for(Integer i : result) {
					sc.setCouresInfoId(i);
					try {
						logger.info("生产端:开始入队");
						rabbitTemplate.setExchange(env.getProperty("seckill.exchange.name"));
						rabbitTemplate.convertAndSend(MessageBuilder.withBody(BeanString.beanToString(sc).getBytes("UTF-8")).build());
						resultMap.put("课程" + i, "成功");
					}catch (Exception e) {
						resultMap.put("课程" + i, "失败");
						e.printStackTrace();
					}
				}
				studentCounts.add("student_count_" + userId);
			}else {
				resultMap.put("选课失败", "本次选课已达到选修数量的上限");
			}
		}else {
			resultMap.put("message", "请勿非法访问！");
		}
		
		return resultMap;
	}
	//压力测试用例
	@RequestMapping(value = "/test")
	@ResponseBody
	public String test(String s) {
		try {
			rabbitTemplate.setExchange(env.getProperty("seckill.exchange.name"));
			rabbitTemplate.convertAndSend(MessageBuilder.withBody(s.getBytes("UTF-8")).build());
			//System.out.println("2020-04-28 17:03:42.156  WARN 8972 --- [cTaskExecutor-1] o.s.a.s.c.Jackson2JsonMessageConverter   : Could not convert incoming message with content-type [application/octet-stream], 'json' keyword missing.");
			//System.out.println(s);
		}catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		
		return "success";
	}
	
}
