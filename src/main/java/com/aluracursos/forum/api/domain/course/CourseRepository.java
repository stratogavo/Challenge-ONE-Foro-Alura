package com.aluracursos.forum.api.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	Boolean existsByIdAndActive(Long idCourse, Boolean isCourseActive);
	
}
