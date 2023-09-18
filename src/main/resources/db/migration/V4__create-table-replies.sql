create table replies(
	id bigint not null auto_increment,
	message varchar(5000) not null,
	topic_id bigint not null,
	creationDate datetime not null,
	author_id bigint not null,
	solution boolean,
	active boolean not null,
	
	primary key(id),
	constraint fk_replies_author_id foreign key(author_id) references users(id),
	constraint fk_replies_topic_id foreign key(topic_id) references topics(id)
);