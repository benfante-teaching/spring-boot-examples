create schema people;

create table people.City (
  city_id BIGINT PRIMARY KEY IDENTITY(1, 1),
  name varchar(50) not null
);

create table people.Person (
  person_id BIGINT PRIMARY KEY IDENTITY(1, 1),
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  email varchar(50) not null,
  city_id BIGINT,
  foreign key (city_id) references people.City(city_id)
);