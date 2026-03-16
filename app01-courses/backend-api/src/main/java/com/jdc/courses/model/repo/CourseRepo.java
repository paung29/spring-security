package com.jdc.courses.model.repo;

import java.util.Optional;

import com.jdc.courses.model.BaseRepository;
import com.jdc.courses.model.entity.Course;

public interface CourseRepo  extends BaseRepository<Course, Integer>{
	
	Optional<Course> findOneByName(String name);
	
	Long countByName(String name);

}
