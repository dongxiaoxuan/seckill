package com.geek.ms.service;

import java.util.List;

import com.geek.ms.pojo.po.Course;

public interface TeacherScoreService {

	List<Course> selectAllCourse(Integer id, Integer ismust);
	
}
