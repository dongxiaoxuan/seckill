package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.vo.CourseModel;
import com.geek.ms.pojo.vo.StudentCourseInfo;

public interface StudentCourseInfoMapper {

	int addAllStudentCoures(List<StudentCourseInfo> list);
	
	List<CourseModel> loadCourse(Integer start, Integer length, Integer id);
	
	Integer getCourseCount();
	
	Integer isOpen();
	
	int openOrClose(Integer open);
	
	Integer dropCourseById(Integer userId, Integer ciid);
	
	List<Integer> loadAllCourseId();
	
	List<StudentCourseInfo> queryAllSciByTime(String start, int id);
}
