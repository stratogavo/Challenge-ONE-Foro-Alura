package com.aluracursos.forum.api.domain.topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aluracursos.forum.api.domain.course.Course;
import com.aluracursos.forum.api.domain.reply.Reply;
import com.aluracursos.forum.api.domain.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	@Enumerated(EnumType.STRING)
	private TopicStatus status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private List<Reply> replies = new ArrayList<>();
	private boolean active;

	public Topic(String title, String message, User user, Course course) {
		this.title = title;
		this.message = message;
		this.creationDate = LocalDateTime.now();
		this.status = TopicStatus.NO_RESPONDIDO;
		this.author = user;
		this.course = course;
		this.active = true;
	}
	
}
