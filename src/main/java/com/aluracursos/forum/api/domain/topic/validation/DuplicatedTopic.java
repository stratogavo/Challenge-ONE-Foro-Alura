package com.aluracursos.forum.api.domain.topic.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aluracursos.forum.api.domain.topic.TopicCreationData;
import com.aluracursos.forum.api.domain.topic.TopicRepository;
import com.aluracursos.forum.api.domain.topic.TopicUpdateDataWithId;
import com.aluracursos.forum.api.infra.errors.IntegrityValidation;

@Component
public class DuplicatedTopic implements TopicCreateValidator, TopicUpdateValidator {

	@Autowired
	private TopicRepository topic;
	
	@Override
	public void validate(TopicCreationData topicCreationData) {
		var duplicatedTopic = topic.existsByTitleAndMessage(
				topicCreationData.title(), topicCreationData.message());
		checkDuplicatedTopic(duplicatedTopic);
	}

	@Override
	public void validate(TopicUpdateDataWithId topicUpdateData) {
		var duplicatedTopic = topic.existsByTitleAndMessageAndIdIsNot(
				topicUpdateData.title(), topicUpdateData.message(), topicUpdateData.id());
		checkDuplicatedTopic(duplicatedTopic);
	}
	
	private void checkDuplicatedTopic(Boolean topicIsDuplicated) {
		if (topicIsDuplicated) {
			throw new IntegrityValidation("El tópico ya se había creado con anterioridad");
		}
	}

}
