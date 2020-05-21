package com.geek.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.ms.mapper.ScoreMapper;
import com.geek.ms.pojo.po.Class;
import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.po.Years;
import com.geek.ms.pojo.vo.CourseClassStudent;
import com.geek.ms.pojo.vo.ScoreByTeacher;
import com.geek.ms.pojo.vo.ScoreByYears;
import com.geek.ms.pojo.vo.ScoreUpHelp;
import com.geek.ms.pojo.vo.UserInfo;
import com.geek.ms.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private ScoreMapper scoreMapper;
	
	@Override
	public Years queryStudentYears(int id) {
		return scoreMapper.queryStudentYears(id);
	}

	@Override
	public List<Years> loadAllYears() {
		return scoreMapper.loadAllYears();
	}

	@Override
	public List<ScoreByYears> queryScore(int id) {
		return scoreMapper.queryScore(id);
	}

	@Override
	public List<CourseClassStudent> queryCourseClassStudents(int id, int courseId, int yearsId) {
		return scoreMapper.queryCourseClassStudents(id, courseId, yearsId);
	}

	@Override
	public List<Course> queryAllMustCourseByUserId(int id) {
		return scoreMapper.queryAllMustCourseByUserId(id);
	}

	@Override
	public Integer haveScoreByCourse(int courseId) {
		return scoreMapper.haveScoreByCourse(courseId);
	}

	@Override
	public int insertScore(int courseId, int userId, int score) {
		return scoreMapper.insertScore(courseId, userId, score);
	}

	@Override
	public List<ScoreByTeacher> getMustScoreByClassAndTeacherAndCourse(ScoreUpHelp sh) {
		return scoreMapper.getMustScoreByClassAndTeacherAndCourse(sh);
	}

	@Override
	public List<Class> getClassesByCourseAndTeacher(ScoreUpHelp sh) {
		return scoreMapper.getClassesByCourseAndTeacher(sh);
	}

	@Override
	public List<ScoreByTeacher> getXuanScoreByTeacherAndCourrseAndTime(ScoreUpHelp sh) {
		return scoreMapper.getXuanScoreByTeacherAndCourrseAndTime(sh);
	}

	@Override
	public List<UserInfo> getStudentsByClassId(Integer courseId) {
		return scoreMapper.getStudentsByClassId(courseId);
	}

	@Override
	public Integer getScoreFlag(ScoreUpHelp sh) {
		return scoreMapper.getScoreFlag(sh);
	}

	@Override
	public int updateScoreFlag(ScoreUpHelp sh) {
		return scoreMapper.updateScoreFlag(sh);
	}

	@Override
	public int getciidByCidAndUid(ScoreUpHelp sh) {
		return scoreMapper.getciidByCidAndUid(sh);
	}

	@Override
	public List<UserInfo> getStudentByCourseId(ScoreUpHelp sh) {
		return scoreMapper.getStudentByCourseId(sh);
	}

	@Override
	public void updateSciStateByUIAndCiId(ScoreUpHelp sh) {
		scoreMapper.updateSciStateByUIAndCiId(sh);
		
	}
	
}
