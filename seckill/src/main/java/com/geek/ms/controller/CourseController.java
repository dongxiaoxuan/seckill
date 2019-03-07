package com.geek.ms.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.ms.pojo.po.Festivals;
import com.geek.ms.pojo.po.Week;
import com.geek.ms.pojo.vo.CourseTable;
import com.geek.ms.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@RequiresRoles(value={"student"})
	@RequestMapping
	public String into(Model model) {
		
		
		List<Week> weeks = courseService.loadWeek();
		List<Festivals> festivals = courseService.loadFestivals();
		List<CourseTable> courses = courseService.queryCourseTable(
				(int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
		model.addAttribute("weeks", weeks);
		model.addAttribute("festivals", festivals);
		model.addAttribute("courses", courses);
		return "course";
	}
	
}
