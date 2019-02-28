package com.geek.ms.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.ms.model.User;

@Controller
public class LoginController {

	@GetMapping(value= {"/login"})
	public String login() {
		return "login";
	}
	
	
	@RequestMapping(value= {"index"})
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value= {""})
	public String index2(Model model) {
		return "redirect:index";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, User user) throws Exception{
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
        	subject.login(token);
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
