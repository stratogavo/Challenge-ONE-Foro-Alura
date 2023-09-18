package com.aluracursos.forum.api.domain.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluracursos.forum.api.domain.course.CourseRepository;
import com.aluracursos.forum.api.domain.topic.validation.TopicUpdateValidator;
import com.aluracursos.forum.api.domain.user.UserRepository;

@Service
public class topicUpdateService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private List<TopicUpdateValidator> topicValidators;

	public TopicUpdateResponse update(TopicUpdateDataWithId topicUpdateData) {
		topicValidators.forEach(v -> v.validate(topicUpdateData));

		var topic = topicRepository.getReferenceById(topicUpdateData.id());
		var author = userRepository.getReferenceById(topicUpdateData.authorId());
		var course = courseRepository.getReferenceById(topicUpdateData.courseId());
		
		topic.setTitle(topicUpdateData.title());
		topic.setMessage(topicUpdateData.message());
		topic.setStatus(topicUpdateData.status());
		topic.setAuthor(author);
		topic.setCourse(course);
		
		return new TopicUpdateResponse(topic);
	}

}
