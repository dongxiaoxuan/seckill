package com.geek.ms.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Profession;
import com.geek.ms.pojo.vo.CourseInfo;
import com.geek.ms.pojo.vo.CourseModel;
import com.geek.ms.pojo.vo.UserInfo;
import com.geek.ms.service.CourseService;
import com.geek.ms.service.SeckillCouresService;
import com.geek.ms.service.UserInfoService;

@Controller
@RequestMapping("/updateCourse")
@RequiresRoles(value={"admin"})
public class CourseUpdateController {
	
	@Autowired
	private SeckillCouresService seckillCouresService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserInfoService userInfoSerivce;

	@RequestMapping
	public String update(
			@RequestParam(required = false, defaultValue = "0") int start, 
			@RequestParam(required = false, defaultValue = "5") int length,
			Model model) {
		
		List<CourseModel> cms2 = seckillCouresService.loadUpdateCourse(start, length);
		List<Profession> pro = seckillCouresService.selectProfession();
		List<CourseInfo> mustCourses = courseService.getAllMustCourse();
		List<UserInfo> teacheres = userInfoSerivce.selectAllTeacher();
		model.addAttribute("cms2", cms2);
		model.addAttribute("pro", pro);
		model.addAttribute("mustCourses", mustCourses);
		model.addAttribute("teacheres", teacheres);
		return "courseUpdate";
		
	}
	
	@RequestMapping("/delete")
	public String delete(int id) {
		
		seckillCouresService.deleteCourse(id);
		
		return "courseUpdate";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String delete(@RequestParam("a[]") List<String> a) {
		
		int id = Integer.valueOf(a.get(0));
		String name = a.get(1);
		String teacherName = a.get(2);
		
		if(seckillCouresService.isTeacher(teacherName) > 0) {
			seckillCouresService.updateCourse(id, name);
			seckillCouresService.updateTeahcer(teacherName, id);
			return "success";
		}else {
			return "信息有误，请重新输入。";
		}
		
	}
	
	@RequestMapping("/update2")
	@ResponseBody
	public String delete(CourseInfo courseInfo, String name) {
		Course course = new Course();
		course.setId(courseInfo.getCouresId());
		course.setName(name);
		System.out.println(course);
		System.out.println(courseInfo);
			courseService.updateCourseNameById(course);
			courseService.updateTeacherByCourseId(courseInfo);
		return "success";
	}
	
	@RequestMapping("/add0")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED)
	public String add0(Course course, @RequestParam("teacherName") String teacherName) throws Exception {
		if(seckillCouresService.isTeacher(teacherName) > 0) {
			if(seckillCouresService.addCourse0(course) > 0 && seckillCouresService.addCourseInfo(course.getName(), teacherName) > 0) {
				return "success";
			}else {
				throw new Exception("事务回滚");
			}
		}else {
			return "信息有误，请重新输入。";
		}
	}
	
	@RequestMapping("/add1")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED)
	public String add1(Course course,
			   @RequestParam("teacherName") String teacherName
		) throws Exception {
		System.out.println(course.toString() + ',' + teacherName);
		if(seckillCouresService.isTeacher(teacherName) > 0) {
			if(seckillCouresService.addCourse(course) > 0 && seckillCouresService.addCourseInfo(course.getName(), teacherName) > 0) {
				return "success";
			}else {
				throw new Exception("事务回滚");
			}
		}else {
			return "信息有误，请重新输入。";
		}
	}
	
}
