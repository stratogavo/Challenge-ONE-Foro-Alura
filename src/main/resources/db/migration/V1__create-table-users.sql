create table users(
	id bigint not null auto_increment,
	name varchar(100) not null,
	email varchar(100) not null unique,
	password varchar(300) not null,
	active boolean not null,
	
	primary key(id)
);