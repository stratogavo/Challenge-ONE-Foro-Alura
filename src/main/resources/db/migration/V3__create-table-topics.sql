create table topics(
	id bigint not null auto_increment,
	title varchar(200) not null,
	message varchar(5000) not null,
	creationDate datetime not null,
	status varchar(30) not null,
	author_id bigint not null,
	course_id bigint not null,
	active boolean not null,
	
	primary key(id),
	constraint fk_topics_author_id foreign key(author_id) references users(id),
	constraint fk_topics_course_id foreign key(course_id) references courses(id)
);