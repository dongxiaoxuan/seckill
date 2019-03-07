package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.po.Festivals;
import com.geek.ms.pojo.po.Week;
import com.geek.ms.pojo.vo.CourseTable;

public interface CourseMapper {
	
	List<Week> loadWeek();
	
	List<Festivals> loadFestivals();
	
	List<CourseTable> queryCourseTable(int id);

}
