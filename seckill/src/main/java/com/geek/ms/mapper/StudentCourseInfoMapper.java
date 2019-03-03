package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.model.CourseModel;
import com.geek.ms.model.StudentCouresInfo;

public interface StudentCourseInfoMapper {

	int addAllStudentCoures(List<StudentCouresInfo> list);
	
	List<CourseModel> loadCourse(Integer start, Integer length, Integer id);
	
	Integer getCourseCount();
	
	Integer isOpen();
	
	int openOrClose(Integer open);
	
	Integer dropCourseById(Integer userId, Integer ciid);
	
	List<Integer> loadAllCourseId();
	
	List<StudentCouresInfo> queryAllSciByTime(String start, String end);
}
