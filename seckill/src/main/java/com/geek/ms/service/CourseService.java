package com.geek.ms.service;

import java.util.List;

import com.geek.ms.model.CourseTable;
import com.geek.ms.model.Festivals;
import com.geek.ms.model.StudentCourseInfo;
import com.geek.ms.model.StudentInfoToTeacher;
import com.geek.ms.model.Week;

public interface CourseService {
	
	List<Week> loadWeek();
	
	List<Festivals> loadFestivals();
	
	List<CourseTable> queryCourseTable(int id);
	
	List<StudentInfoToTeacher> queryStudentNumberToTeacher(int id);
	
	List<StudentCourseInfo> queryAllSciByTime(String start,int id);
}
