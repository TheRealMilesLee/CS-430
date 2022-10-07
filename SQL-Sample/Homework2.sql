/**
 * @file Homework2.sql
 * @author Hengyi Li (hl3265@truman.edu)
 * @brief This is the SQL file for Homework2 problem
 * @version 0.1
 * @date 2022-09-11
 * @copyright Copyright (c) 2022. Hengyi Li. All rights reserve
 */
use assignment2_2022;

# Question 1.1
select distinct parts.pid, parts.pname
from parts
join catalog
where catalog.pid = parts.pid and parts.color = "blue";

# Question 1.2
select distinct parts.pid as "pids", parts.pname as "pnames", 
max(catalog.cost) as "maximum cost", suppliers.sname as "Supplier"
from parts
inner join catalog on catalog.pid = parts.pid
inner join suppliers on suppliers.sid = catalog.sid
group by suppliers.sname;

# Question 1.3
select distinct suppliers.sid as "sids", suppliers.sname as "snames"
from suppliers
inner join catalog on catalog.sid = suppliers.sid
inner join parts on catalog.pid = parts.pid
where parts.color = "red" or parts.color = "green" 
order by suppliers.sid asc;


# Question 1.4.1
select count(suppliers.address) from suppliers where suppliers.address = "Ames";

# Question 1.4.2
select distinct count(suppliers.address) from suppliers;

# Question 1.5
select catalog.cost as "Max cost", suppliers.sname as "Supplier"
from catalog
inner join suppliers on suppliers.sid = catalog.sid
where catalog.cost = (select max(cost) from catalog);


# Question 2
drop table if exists Student;
create table Student(
	mn int not null,
	name varchar(255),
	email varchar(255),
	primary key (mn)
);

drop table if exists Takes;
create table Takes(
	mn int not null,
	mark boolean,
	code int not null
);

drop table if exists Courses;
create table Courses(
	code int not null,
	name varchar(255),
	year varchar(255),
	primary key (code)
);
