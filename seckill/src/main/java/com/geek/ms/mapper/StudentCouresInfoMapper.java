package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.model.CourseModel;
import com.geek.ms.model.StudentCouresInfo;

public interface StudentCouresInfoMapper {

	int addAllStudentCoures(List<StudentCouresInfo> list);
	
	List<CourseModel> loadCourse(int start, int length);
	
	Integer getCourseCount();
	
	Integer isOpen();
	
	int openOrClose(Integer open);
}
