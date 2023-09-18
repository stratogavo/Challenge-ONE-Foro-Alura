package com.aluracursos.forum.api.domain.topic.validation;

import com.aluracursos.forum.api.domain.topic.TopicCreationData;

public interface TopicCreateValidator {

	public void validate(TopicCreationData topicCreationData);
	
}
