package com.aluracursos.forum.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.forum.api.domain.topic.TopicCreationData;
import com.aluracursos.forum.api.domain.topic.TopicCreationService;
import com.aluracursos.forum.api.domain.topic.TopicDetailsResponse;
import com.aluracursos.forum.api.domain.topic.TopicListResponse;
import com.aluracursos.forum.api.domain.topic.TopicCreationResponse;
import com.aluracursos.forum.api.domain.topic.TopicRepository;
import com.aluracursos.forum.api.domain.topic.TopicUpdateData;
import com.aluracursos.forum.api.domain.topic.TopicUpdateDataWithId;
import com.aluracursos.forum.api.domain.topic.TopicUpdateResponse;
import com.aluracursos.forum.api.domain.topic.topicUpdateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private TopicCreationService topicCreationService;

	@Autowired
	private topicUpdateService topicUpdateService;
	
	@PostMapping
	public ResponseEntity<TopicCreationResponse> createTopic(
			@RequestBody @Valid TopicCreationData topicCreationData,
			UriComponentsBuilder uriComponentsBuilder) {
		var topicPostResponse = topicCreationService.create(topicCreationData);
		URI url = uriComponentsBuilder.path("/topicos/{id}")
				.buildAndExpand(topicPostResponse.id()).toUri();
		return ResponseEntity.created(url).body(topicPostResponse);
	}
	
	@GetMapping
	public ResponseEntity<Page<TopicListResponse>> getTopicList(
			@PageableDefault(size = 8) Pageable page) {
		return ResponseEntity.ok(topicRepository.findByActiveTrue(page)
				.map(TopicListResponse::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailsResponse> getTopicDetails(@PathVariable Long id) {
		var topic = topicRepository.getReferenceById(id);
		return ResponseEntity.ok(new TopicDetailsResponse(topic));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicUpdateResponse> updateTopic(@PathVariable Long id,
			@RequestBody @Valid TopicUpdateData topicUpdateData) {
		var topicUpdateResponse = topicUpdateService
				.update(new TopicUpdateDataWithId(id, topicUpdateData));
		return ResponseEntity.ok(topicUpdateResponse);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
		var topic = topicRepository.getReferenceById(id);
		topic.setActive(false);
		return ResponseEntity.noContent().build();
	}
	
}
