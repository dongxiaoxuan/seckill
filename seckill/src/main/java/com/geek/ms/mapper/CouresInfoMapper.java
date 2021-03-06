package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.po.Course;
import com.geek.ms.pojo.vo.CourseInfo;
import com.geek.ms.pojo.vo.StudentInfoToTeacher;

public interface CouresInfoMapper {

	int desAllAmount(int length, int couresInfoId);
	
	int clearAllAmount();
	
	List<StudentInfoToTeacher> queryStudentNumberToTeacher(int id);
	
	List<CourseInfo> getAllMustCourse();
	
	int updateTeacherByCourseId(CourseInfo courseInfo);
	
	int updateCourseNameById(Course course);
	
}
