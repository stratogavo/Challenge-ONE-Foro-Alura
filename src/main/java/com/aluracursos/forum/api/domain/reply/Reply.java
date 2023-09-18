package com.aluracursos.forum.api.domain.reply;

import java.time.LocalDateTime;

import com.aluracursos.forum.api.domain.topic.Topic;
import com.aluracursos.forum.api.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "replies")
@Entity(name = "Reply")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topic;
	private LocalDateTime creationDate = LocalDateTime.now();
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User author;
	private boolean solution = false;
	private boolean active = true;

}
