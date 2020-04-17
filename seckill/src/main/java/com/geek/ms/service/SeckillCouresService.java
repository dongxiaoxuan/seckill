package com.geek.ms.service;

import java.util.List;

import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Profession;
import com.geek.ms.pojo.vo.CourseModel;

public interface SeckillCouresService {

	void saveStudentCouresInfo(int couresInfoId);
	
	int seckillCoures(int couresInfoId, int studentId);
	
	List<CourseModel> loadCourse(Integer start, Integer length, Integer id);
	
	List<CourseModel> loadUpdateCourse(Integer start, Integer length);
	
	Integer getCourseCount();
	
	boolean isOpen();
	
	int openOrClose(Integer open);
	
	Integer dropCourseById(Integer userId, Integer ciid);
	
	void saveDataWhenClose(int couresInfoId);
	
	String getAmountFromRedis(int courseId);
	
	void setAmountToRedis(int courseId, int amount);
	
	List<?> getCourseStudentListFromRedis(int courseId);
	
	List<Integer> loadAllCourseId();
	
	int clearAllAmount();
	
	int deleteCourse(int id);
	
	int updateCourse(int id, String name);
	
	int isTeacher(String teacherName);
	
	int updateTeahcer(String name, int id);
	
	int addCourse0(Course course);
	
	int addCourse(Course course);
	
	int addCourseInfo(String courseName, String teacherName);
	
	List<Profession> selectProfession();
}
