package com.aluracursos.forum.api.domain.topic;

import java.time.LocalDateTime;

public record TopicCreationResponse(
		Long id, 
		String title,
		String message, 
		LocalDateTime creationDate, 
		TopicStatus status, 
		String authorName, 
		String courseName) {
	
	public TopicCreationResponse(Topic topic) {
		this(topic.getId(), topic.getTitle(), topic.getMessage(),
				topic.getCreationDate(), topic.getStatus(),
				topic.getAuthor().getName(), topic.getCourse().getName());
	}
	
}
