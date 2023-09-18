create table courses(
	id bigint not null auto_increment,
	name varchar(300) not null unique,
	category varchar(100),
	active boolean not null,
	
	primary key(id)
);