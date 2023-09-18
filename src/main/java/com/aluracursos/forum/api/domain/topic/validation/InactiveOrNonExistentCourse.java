package com.aluracursos.forum.api.domain.topic.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aluracursos.forum.api.domain.course.CourseRepository;
import com.aluracursos.forum.api.domain.topic.TopicCreationData;
import com.aluracursos.forum.api.domain.topic.TopicUpdateDataWithId;
import com.aluracursos.forum.api.infra.errors.IntegrityValidation;

@Component
public class InactiveOrNonExistentCourse implements TopicCreateValidator, TopicUpdateValidator {

	@Autowired
	private CourseRepository course;
	
	@Override
	public void validate(TopicCreationData topicCreationData) {
		if (topicCreationData.courseId() == null) {
			throw new IntegrityValidation("El id del curso es nulo");
		}
		if (!course.existsByIdAndActive(topicCreationData.courseId(), true)) {
			throw new IntegrityValidation("El curso no está disponible en el sistema");
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
