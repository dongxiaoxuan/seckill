package com.geek.ms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.pojo.vo.Resources;
import com.geek.ms.service.ResourcesService;
import com.geek.ms.shiro.ShiroService;

@Controller
@RequestMapping("/resources")
public class RosourcesController {

	@Autowired
	private ResourcesService resourcesService;
	@Autowired
	private ShiroService shiroService;

	@RequestMapping
	public String getAllResources(@RequestParam(required = false, defaultValue = "0") int start, 
			@RequestParam(required = false, defaultValue = "10") int length,
			Model model) {
		List<Resources> resources = resourcesService.selectPageAll(start, length);
		List<Resources> allResources = resourcesService.queryAll();
		int page = (int) Math.ceil((double)allResources.size() / length);
		model.addAttribute("resources", resources);
		model.addAttribute("page", page);
		return "resources";
	}
	
	@RequestMapping("/loadMenu")
	@ResponseBody
	public List<Resources> loadMenu() {
		Map<String, Object> map = new HashMap<>();
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		map.put("type", 1);
		map.put("userId", userId);
		List<Resources> resourcesList = resourcesService.loadUserResources(map);
		return resourcesList;
	}
	
	@RequestMapping("/add")
	public String addResources(Resources resources) {
		/*
		 * 1. service.add()
		 * 2.更新权限↓
		 */
		shiroService.updatePermission();
		return "";
	}
	
	@RequestMapping("/delete")
	public String deleteResources(Resources resources) {
		/*
		 * 1. service.delete()
		 * 2.更新权限↓
		 */
		shiroService.updatePermission();
		return "";
	}
	
}
