package com.geek.ms.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.ms.pojo.vo.StudentCourseInfo;
import com.geek.ms.pojo.vo.StudentInfoToTeacher;
import com.geek.ms.service.CourseService;
import com.geek.ms.service.SeckillCouresService;
import com.geek.ms.util.FormatTime;

@Controller
@RequestMapping("/teacherCourse")
public class TeacherCourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SeckillCouresService seckillCouresService;
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping
	public String into(Model model) {
		if(seckillCouresService.isOpen()) {
			model.addAttribute("isOpen", true);
		}else{
			Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
			List<StudentInfoToTeacher> sitts = courseService.queryStudentNumberToTeacher(userId);
			List<StudentCourseInfo> students = courseService.queryAllSciByTime(FormatTime.getFormatTime(), userId);
			model.addAttribute("sitts", sitts);
			model.addAttribute("students", students);
			model.addAttribute("isOpen", false);
		}
		return "teacherCourse";
	}
}
