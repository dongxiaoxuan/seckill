package com.geek.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.ms.mapper.CouresInfoMapper;
import com.geek.ms.mapper.CourseMapper;
import com.geek.ms.mapper.StudentCourseInfoMapper;
import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Festivals;
import com.geek.ms.pojo.po.Week;
import com.geek.ms.pojo.vo.CourseInfo;
import com.geek.ms.pojo.vo.CourseTable;
import com.geek.ms.pojo.vo.StudentCourseInfo;
import com.geek.ms.pojo.vo.StudentInfoToTeacher;
import com.geek.ms.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private CouresInfoMapper courseInfoMapper;
	
	@Autowired
	private StudentCourseInfoMapper studentCourseInfoMapper;

	@Override
	public List<Week> loadWeek() {
		return courseMapper.loadWeek();
	}

	@Override
	public List<Festivals> loadFestivals() {
		return courseMapper.loadFestivals();
	}

	@Override
	public List<CourseTable> queryCourseTable(int id) {
		return courseMapper.queryCourseTable(id);
	}

	@Override
	public List<StudentInfoToTeacher> queryStudentNumberToTeacher(int id) {
		return courseInfoMapper.queryStudentNumberToTeacher(id);
	}

	@Override
	public List<StudentCourseInfo> queryAllSciByTime(String start, int id) {
		return studentCourseInfoMapper.queryAllSciByTime(start,id);
	}

	@Override
	public List<CourseInfo> getAllMustCourse() {
		return courseInfoMapper.getAllMustCourse();
	}

	@Override
	public int updateTeacherByCourseId(CourseInfo courseInfo) {
		return courseInfoMapper.updateTeacherByCourseId(courseInfo);
	}

	@Override
	public int updateCourseNameById(Course course) {
		return courseInfoMapper.updateCourseNameById(course);
	}

}
