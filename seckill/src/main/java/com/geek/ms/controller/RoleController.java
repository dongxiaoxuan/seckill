package com.geek.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.model.Resources;
import com.geek.ms.model.Role;
import com.geek.ms.service.RoleService;

@Controller
@RequestMapping("roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping
	public String getAllResources(@RequestParam(required = false, defaultValue = "0") int start, 
			@RequestParam(required = false, defaultValue = "10") int length,
			Model model) {
		List<Role> roles = roleService.selectPageAll(start, length);
		List<Role> allRoles = roleService.selectAll();
		int page = (int) Math.ceil((double)allRoles.size() / length);
		List<Resources> allResources = roleService.queryAll();
		model.addAttribute("roles", roles);
		model.addAttribute("page", page);
		model.addAttribute("resources", allResources);
		return "roles";
	}
	
	@RequestMapping("/otherUrl")
	@ResponseBody
	public List<Resources> getAllResources(int id) {
		List<Resources> otherUrl = roleService.selectOtherUrl(id);
		if(otherUrl.size() < 0) {
			return null;
		}
		System.out.println(otherUrl.size());
		return otherUrl;
	}
	
}
