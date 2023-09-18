package com.aluracursos.forum.api.domain.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluracursos.forum.api.domain.course.CourseRepository;
import com.aluracursos.forum.api.domain.topic.validation.TopicCreateValidator;
import com.aluracursos.forum.api.domain.user.UserRepository;

@Service
public class TopicCreationService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private List<TopicCreateValidator> topicValidators;
	
	public TopicCreationResponse create(TopicCreationData topicCreationData) {
		topicValidators.forEach(v -> v.validate(topicCreationData));
		var title = topicCreationData.title();
		var message = topicCreationData.message();
		var author = userRepository.findById(topicCreationData.authorId()).get();
		var course = courseRepository.findById(topicCreationData.courseId()).get();
		var topic = topicRepository.save(new Topic(title, message, author, course));
		return new TopicCreationResponse(topic);
	}
	
}
