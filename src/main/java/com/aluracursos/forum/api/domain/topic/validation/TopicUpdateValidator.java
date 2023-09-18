package com.aluracursos.forum.api.domain.topic.validation;

import com.aluracursos.forum.api.domain.topic.TopicUpdateDataWithId;

public interface TopicUpdateValidator {

	public void validate(TopicUpdateDataWithId topUpdateDataWithId);
	
}
