package com.geek.ms.service;

import java.util.List;

import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Festivals;
import com.geek.ms.pojo.po.Week;
import com.geek.ms.pojo.vo.CourseInfo;
import com.geek.ms.pojo.vo.CourseTable;
import com.geek.ms.pojo.vo.StudentCourseInfo;
import com.geek.ms.pojo.vo.StudentInfoToTeacher;

public interface CourseService {
	
	List<Week> loadWeek();
	
	List<Festivals> loadFestivals();
	
	List<CourseTable> queryCourseTable(int id);
	
	List<StudentInfoToTeacher> queryStudentNumberToTeacher(int id);
	
	List<StudentCourseInfo> queryAllSciByTime(String start,int id);
	
	List<CourseInfo> getAllMustCourse();
	
	int updateTeacherByCourseId(CourseInfo courseInfo);
	
	int updateCourseNameById(Course course);
}
