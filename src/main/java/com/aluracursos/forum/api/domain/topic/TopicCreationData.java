package com.aluracursos.forum.api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreationData(
		@NotBlank(message = "{topic.title.required}") 
		String title,
		@NotBlank(message = "{topic.message.required}") 
		String message, 
		@NotNull(message = "{topic.author.required}")
		Long authorId, 
		@NotNull(message = "{topic.course.required}")
		Long courseId
		) {}
