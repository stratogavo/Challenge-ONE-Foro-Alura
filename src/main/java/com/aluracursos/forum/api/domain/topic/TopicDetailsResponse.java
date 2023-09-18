package com.aluracursos.forum.api.domain.topic;

import java.time.LocalDateTime;

public record TopicDetailsResponse(
		String title,
		String message,
		LocalDateTime creationDate,
		TopicStatus status,
		String authorName,
		String courseName) {
	
	public TopicDetailsResponse(Topic topic) {
		this(topic.getTitle(), topic.getMessage(),
				topic.getCreationDate(), topic.getStatus(),
				topic.getAuthor().getName(), topic.getCourse().getName());
	}
	
}
