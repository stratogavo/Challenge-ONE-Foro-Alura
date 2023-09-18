package com.aluracursos.forum.api.domain.topic;

public record TopicUpdateResponse(
		Long id,
		String title,
		String message, 
		TopicStatus status, 
		String authorName, 
		String courseName) {
	
	public TopicUpdateResponse(Topic topic) {
		this(topic.getId(), topic.getTitle(), topic.getMessage(), 
				topic.getStatus(), topic.getAuthor().getName(), 
				topic.getCourse().getName());
	}
	
}
