package com.aluracursos.forum.api.domain.topic;

public record TopicUpdateDataWithId(
		Long id, 
		String title, 
		String message, 
		TopicStatus status, 
		Long authorId, 
		Long courseId) {
	
	public TopicUpdateDataWithId(Long id, TopicUpdateData topicUpdateData) {
		this(id, topicUpdateData.title(), topicUpdateData.message(), 
				topicUpdateData.status(), topicUpdateData.authorId(),
				topicUpdateData.courseId());
	}
	
}
