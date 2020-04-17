package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Profession;
import com.geek.ms.pojo.vo.CourseModel;
import com.geek.ms.pojo.vo.StudentCourseInfo;

public interface StudentCourseInfoMapper {

	int addAllStudentCoures(List<StudentCourseInfo> list);
	
	List<CourseModel> loadCourse(Integer start, Integer length, Integer id);
	
	List<CourseModel> loadUpdateCourse(Integer start, Integer length);
	
	Integer getCourseCount();
	
	Integer isOpen();
	
	int openOrClose(Integer open);
	
	Integer dropCourseById(Integer userId, Integer ciid);
	
	List<Integer> loadAllCourseId();
	
	List<StudentCourseInfo> queryAllSciByTime(String start, int id);
	
	int deleteCourse(int id);
	
	int updateCourse(int id, String name);
	
	int isTeacher(String teacherName);
	
	int updateTeahcer(String name, int id);
	
	int addCourse0(Course course);
	
	int addCourse(Course course);
	
	int addCourseInfo(String courseName, String teacherName);
	
	List<Profession> selectProfession();
}
