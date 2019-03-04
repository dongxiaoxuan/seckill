package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.model.CourseTable;
import com.geek.ms.model.Festivals;
import com.geek.ms.model.Week;

public interface CourseMapper {
	
	List<Week> loadWeek();
	
	List<Festivals> loadFestivals();
	
	List<CourseTable> queryCourseTable(int id);

}
