package com.geek.ms.service;

import java.util.List;

import com.geek.ms.pojo.vo.CourseTable;
import com.geek.ms.pojo.vo.Festivals;
import com.geek.ms.pojo.vo.StudentCourseInfo;
import com.geek.ms.pojo.vo.StudentInfoToTeacher;
import com.geek.ms.pojo.vo.Week;

public interface CourseService {
	
	List<Week> loadWeek();
	
	List<Festivals> loadFestivals();
	
	List<CourseTable> queryCourseTable(int id);
	
	List<StudentInfoToTeacher> queryStudentNumberToTeacher(int id);
	
	List<StudentCourseInfo> queryAllSciByTime(String start,int id);
}
