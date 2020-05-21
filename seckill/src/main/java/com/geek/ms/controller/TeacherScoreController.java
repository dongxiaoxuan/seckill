package com.geek.ms.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.pojo.po.Class;
import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.vo.ScoreByTeacher;
import com.geek.ms.pojo.vo.ScoreUpHelp;
import com.geek.ms.service.ScoreService;
import com.geek.ms.service.TeacherScoreService;

@Controller
@RequestMapping("/teacherScore")
public class TeacherScoreController {

	@Autowired
	private TeacherScoreService teacherScoreService;
	@Autowired
	private ScoreService scoreService;
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping
	public String into(Model model) {
		return "teacherScore";
	}
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping("/getCourse")
	@ResponseBody
	public List<Course> getCourse(int ismust, Model model) {
		int userId = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		List<Course> courseList = teacherScoreService.selectAllCourse(userId, ismust);
		return courseList;
	}
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping("/getClasses")
	@ResponseBody
	public List<Class> getClasses(ScoreUpHelp sh, Model model) {
		int userId = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		sh.setUserId(userId);
		List<Class> classes = scoreService.getClassesByCourseAndTeacher(sh);
		model.addAttribute("classes", classes);
		return classes;
	}
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping("/getScore")
	@ResponseBody
	public List<ScoreByTeacher> getScore(ScoreUpHelp sh, Model model) {
		int userId = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		sh.setUserId(userId);
		List<ScoreByTeacher> scores = scoreService.getMustScoreByClassAndTeacherAndCourse(sh);
		return scores;
	}
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping("/getXuanScore")
	@ResponseBody
	public List<ScoreByTeacher> getXuanScore(ScoreUpHelp sh, Model model) {
		int userId = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		sh.setUserId(userId);
		List<ScoreByTeacher> scores = scoreService.getXuanScoreByTeacherAndCourrseAndTime(sh);
		return scores;
	}
	
}
