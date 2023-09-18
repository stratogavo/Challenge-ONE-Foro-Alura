package com.aluracursos.forum.api.domain.topic.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aluracursos.forum.api.domain.topic.TopicCreationData;
import com.aluracursos.forum.api.domain.topic.TopicUpdateDataWithId;
import com.aluracursos.forum.api.domain.user.UserRepository;
import com.aluracursos.forum.api.infra.errors.IntegrityValidation;

@Component
public class InactiveOrNonExistentUser implements TopicCreateValidator, TopicUpdateValidator {

	@Autowired
	private UserRepository user;
	
	@Override
	public void validate(TopicCreationData topicCreationData) {
		if (topicCreationData.authorId() == null) {
			throw new IntegrityValidation("El id del usuario es nulo");
		}
		if (!user.existsByIdAndActive(topicCreationData.authorId(), true)) {
			throw new IntegrityValidation("El usuario no está disponible en el sistema");
		}
	}

	@Override
	public void validate(TopicUpdateDataWithId topicUpdateData) {
		var updatedTopicData = new TopicCreationData(
				topicUpdateData.title(),topicUpdateData.message(),
				topicUpdateData.authorId(), topicUpdateData.courseId());
		validate(updatedTopicData);
	}

}
