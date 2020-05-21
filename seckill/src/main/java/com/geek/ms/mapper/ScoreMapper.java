package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.po.Class;
import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Years;
import com.geek.ms.pojo.vo.CourseClassStudent;
import com.geek.ms.pojo.vo.ScoreByTeacher;
import com.geek.ms.pojo.vo.ScoreByYears;
import com.geek.ms.pojo.vo.ScoreUpHelp;
import com.geek.ms.pojo.vo.UserInfo;

public interface ScoreMapper {

	Years queryStudentYears(int id);
	
	List<Years> loadAllYears();
	
	List<ScoreByYears> queryScore(int id);
	
	List<CourseClassStudent> queryCourseClassStudents(int id, int courseId, int yearsId);
	
	List<Course> queryAllMustCourseByUserId(int id);
	
	Integer haveScoreByCourse(int courseId);
	
	int insertScore(int courseId, int userId, int score);
	
	List<ScoreByTeacher> getMustScoreByClassAndTeacherAndCourse(ScoreUpHelp sh);
	
	List<Class> getClassesByCourseAndTeacher(ScoreUpHelp sh);
	
	List<ScoreByTeacher> getXuanScoreByTeacherAndCourrseAndTime(ScoreUpHelp sh);
	
	List<UserInfo> getStudentsByClassId(Integer courseId);
	
	Integer getScoreFlag(ScoreUpHelp sh);
	
	int getciidByCidAndUid(ScoreUpHelp sh);
	
	int updateScoreFlag(ScoreUpHelp sh);
	
	List<UserInfo> getStudentByCourseId(ScoreUpHelp sh);
	
	void updateSciStateByUIAndCiId(ScoreUpHelp sh);
	
}
