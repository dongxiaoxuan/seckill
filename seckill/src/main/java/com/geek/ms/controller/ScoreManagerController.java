package com.geek.ms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.vo.CourseClassStudent;
import com.geek.ms.pojo.vo.ScoreUpHelp;
import com.geek.ms.pojo.vo.StudentCourseInfo;
import com.geek.ms.pojo.vo.StudentInfoToTeacher;
import com.geek.ms.pojo.vo.UserInfo;
import com.geek.ms.service.CourseService;
import com.geek.ms.service.ScoreService;
import com.geek.ms.util.FormatTime;
import com.google.gson.Gson;

@Controller
@RequestMapping("/ScoreManager")
public class ScoreManagerController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ScoreService scoreService;

	@RequestMapping
	public String into(Model model) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		List<StudentInfoToTeacher> sitts = courseService.queryStudentNumberToTeacher(userId);
		List<StudentCourseInfo> students = courseService.queryAllSciByTime(FormatTime.getFormatTime(), userId);
		List<Course> courses = scoreService.queryAllMustCourseByUserId(userId);
		List<List<CourseClassStudent>> allCstudents = new ArrayList<List<CourseClassStudent>>();
		//必修
		List<Integer> haveScores = new ArrayList<Integer>();
		
		for(Course c : courses) {
			List<CourseClassStudent> cstudents = scoreService.queryCourseClassStudents(userId, c.getId(), c.getYearsId());
			allCstudents.add(cstudents);
			haveScores.add(scoreService.haveScoreByCourse(c.getId()));
		}
		//选修
		List<Integer> haveScores2 = new ArrayList<Integer>();
		for(StudentInfoToTeacher s : sitts) {
			haveScores2.add(scoreService.haveScoreByCourse(s.getId()));
		}
		model.addAttribute("sitts", sitts);
		model.addAttribute("students", students);
		model.addAttribute("allCstudents", allCstudents);
		model.addAttribute("courses", courses);
		model.addAttribute("haveScores", haveScores);
		model.addAttribute("haveScores2", haveScores2);
		return "scoreManager";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/update")
	@ResponseBody
	public Integer update(@RequestParam("courseId") Integer courseId, @RequestParam("classesId") Integer classesId, @RequestParam String json) {
		Gson gson = new Gson();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		list = gson.fromJson(json, list.getClass());
		Map<Integer, Integer> resultMap = new HashMap<>();
		for(Map<String, String> map : list) {
			int i = 1;
			Integer rkey = 0;
			Integer rvalue = 0;
			for(String key : map.keySet()){
			   String value = map.get(key);
			   if(i % 2 == 0) {
				   rvalue = Integer.parseInt(value);
				   resultMap.put(rkey, rvalue);
			   }else {
				   rkey = Integer.parseInt(value);
			   }
			   i ++;
			}
		}
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		ScoreUpHelp sh = new ScoreUpHelp();
		sh.setUserId(userId); sh.setClassesId(classesId); sh.setCourseId(courseId);
		Integer courseInfoId = scoreService.getciidByCidAndUid(sh);
		sh.setCourseId(courseInfoId);
		scoreService.updateScoreFlag(sh);
		Integer result = 0;
		for(int key : resultMap.keySet()){
			int value = resultMap.get(key);
			result = scoreService.insertScore(courseId, key, value);
		}
		return result;
	}
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping("/getStudents")
	@ResponseBody
	public List<?> getStudents(ScoreUpHelp sh, Model model) {
		int userId = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		sh.setUserId(userId);
		if(scoreService.getScoreFlag(sh) == 0) {
			List<UserInfo> students = scoreService.getStudentsByClassId(sh.getClassesId());
			return students;
		}else {
			List<String> flag = new ArrayList<String>();
			flag.add("alreadyHave");
			return flag;
		}
		
	}
	
	@RequiresRoles(value={"teacher"})
	@RequestMapping("/getStudents2")
	@ResponseBody
	public List<?> getStudents2(ScoreUpHelp sh, Model model) {
		int userId = (int) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		sh.setUserId(userId);
		List<UserInfo> students = scoreService.getStudentByCourseId(sh);
		return students;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/update2")
	@ResponseBody
	public Integer update2(@RequestParam("courseId") Integer courseId, @RequestParam String json) {
		Gson gson = new Gson();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		list = gson.fromJson(json, list.getClass());
		Map<Integer, Integer> resultMap = new HashMap<>();
		for(Map<String, String> map : list) {
			int i = 1;
			Integer rkey = 0;
			Integer rvalue = 0;
			for(String key : map.keySet()){
			   String value = map.get(key);
			   if(i % 2 == 0) {
				   rvalue = Integer.parseInt(value);
				   resultMap.put(rkey, rvalue);
			   }else {
				   rkey = Integer.parseInt(value);
			   }
			   i ++;
			}
		}
		System.out.println(resultMap);
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
		ScoreUpHelp sh = new ScoreUpHelp();
		sh.setUserId(userId);  sh.setCourseId(courseId);
		Integer courseInfoId = scoreService.getciidByCidAndUid(sh);
		sh.setCourseInfoId(courseInfoId);
		Integer result = 0;
		for(int key : resultMap.keySet()){
			int value = resultMap.get(key);
			result = scoreService.insertScore(courseId, key, value);
			sh.setUserId(key);
			scoreService.updateSciStateByUIAndCiId(sh);
		}
		return result;
	}
	
}
