package com.geek.ms.service;

import java.util.List;

import com.geek.ms.model.CourseModel;

public interface SeckillCouresService {

	void saveStudentCouresInfo(int couresInfoId);
	
	int seckillCoures(int couresInfoId, int studentId);
	
	List<CourseModel> loadCourse(int start, int length);
	
	Integer getCourseCount();
	
	boolean isOpen();
	
	int openOrClose(Integer open);
	
}
