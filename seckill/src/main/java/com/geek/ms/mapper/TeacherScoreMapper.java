package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.po.Course;

public interface TeacherScoreMapper {

	List<Course> selectAllCourse(Integer id, Integer ismust);
	
}
