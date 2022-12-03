use hl3265;

drop table if exists username;
drop table if exists user_password;

create table user_password
(
	id int unsigned not null auto_increment,
	user_password longtext not null,
	primary key(id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;


create table username
(
	id int unsigned not null auto_increment,
	username varchar(255) not null,
	screen_name longtext not null,
  password_id int unsigned not null,
  is_administrator boolean not null,
	primary key(id),
	constraint password_fk foreign key(password_id) references user_password(id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
