package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.vo.CourseTable;
import com.geek.ms.pojo.vo.Festivals;
import com.geek.ms.pojo.vo.Week;

public interface CourseMapper {
	
	List<Week> loadWeek();
	
	List<Festivals> loadFestivals();
	
	List<CourseTable> queryCourseTable(int id);

}
