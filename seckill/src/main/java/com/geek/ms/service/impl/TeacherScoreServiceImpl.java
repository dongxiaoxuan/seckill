package com.geek.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.ms.mapper.TeacherScoreMapper;
import com.geek.ms.pojo.po.Course;
import com.geek.ms.service.TeacherScoreService;

@Service
public class TeacherScoreServiceImpl implements TeacherScoreService{

	@Autowired
	private TeacherScoreMapper teacherScoreMapper;

	@Override
	public List<Course> selectAllCourse(Integer id, Integer ismust) {
		return teacherScoreMapper.selectAllCourse(id, ismust);
	}
	
	
}
