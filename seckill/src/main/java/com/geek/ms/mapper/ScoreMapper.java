package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Years;
import com.geek.ms.pojo.vo.CourseClassStudent;
import com.geek.ms.pojo.vo.ScoreByYears;

public interface ScoreMapper {

	Years queryStudentYears(int id);
	
	List<Years> loadAllYears();
	
	List<ScoreByYears> queryScore(int id);
	
	List<CourseClassStudent> queryCourseClassStudents(int id, int courseId, int yearsId);
	
	List<Course> queryAllMustCourseByUserId(int id);
	
	Integer haveScoreByCourse(int courseId);
	
	int insertScore(int courseId, int userId, int score);
	
}
