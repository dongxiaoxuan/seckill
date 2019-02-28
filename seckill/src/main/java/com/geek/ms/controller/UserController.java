package com.geek.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.model.Role;
import com.geek.ms.model.User;
import com.geek.ms.service.UserService;
import com.geek.ms.util.PasswordHelper;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping
	public String getAllUsers(
			@RequestParam(required = false, defaultValue = "0") int start, 
			@RequestParam(required = false, defaultValue = "10") int length,
			Model model) {
		List<User> users = userService.selectPageAll(start, length);
		List<User> allUser = userService.selectAll();
		int page = (int) Math.ceil((double)allUser.size() / length);
		List<Role> roles = userService.selectAllRole();
		model.addAttribute("users", users);
		model.addAttribute("page", page);
		model.addAttribute("roles", roles);
		return "users";
	}
	
	@RequestMapping("/allrole")
	@ResponseBody
	public String selectAllRole(Model model) {
		
		return "success";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addUser(User user) {
		User u = userService.selectByUsername(user.getUsername());
		if(u != null) {
			return "error";
		}
		new PasswordHelper().encryptPassword(user);
		user.setEnable(1);
		userService.addUser(user);
		return "success";
	}
	
	@RequestMapping("/enable")
	@ResponseBody
	public String setEnable(User user) {
		userService.setEnable(user);
		return "success";
	}
	
	@RequestMapping("/userrole")
	@ResponseBody
	public String setUserRole(Integer userId, Integer roleId) {
		if(userService.selectUserRoleByUserId(userId) > 0) {
			userService.updateUserRole(userId, roleId);
		}else {
			System.out.println(userId + ":" + roleId);
			userService.setUserRole(userId, roleId);
		}
		return "success";
	}
}
