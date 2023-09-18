package com.aluracursos.forum.api.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	Page<Topic> findByActiveTrue(Pageable page);
	
	Boolean existsByTitleAndMessage(String title, String message);
	
	Boolean existsByTitleAndMessageAndIdIsNot(String title, String message, Long id);
	
}
