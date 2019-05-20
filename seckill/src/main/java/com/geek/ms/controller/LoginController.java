package com.geek.ms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.ms.pojo.vo.User;
import com.geek.ms.service.SeckillCouresService;
import com.geek.ms.service.SpeechService;

@Controller
public class LoginController {
	
	@Autowired
	SeckillCouresService seckillCouresService;
	
	@Autowired
	private SpeechService speechService;

	
	@RequestMapping(value= {"index"})
	public String index(Model model) {
		if(seckillCouresService.isOpen()) {
			model.addAttribute("isOpen", true);
		}else {
			model.addAttribute("isOpen", false);
		}
		return "index";
	}
	
	//@Async
	@RequestMapping(value= {"/login",""})
	public String login(HttpServletRequest request, User user) throws Exception{
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
        	subject.login(token);
        	speechService.speech(user.getUsername() + ",欢迎登录极客教务系统，我叫小G，是您可爱的小助手。");
        	//user.getUsername() + "欢迎登录极客教务系统，我叫小G，是您可爱的小助手，我将竭诚为您服务。"
            return "redirect:index";
        }catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }
	
	@RequestMapping("/logout")
	public String logout() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if(user != null) {
			SecurityUtils.getSubject().logout();
		}
		return "login";
	}
	
	@RequestMapping("/403")
    public String forbidden(){
        return "403";
    }
	
}
