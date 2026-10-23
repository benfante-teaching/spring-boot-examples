create schema people;

create table people.City (
  city_id SERIAL PRIMARY KEY,
  name varchar(50) not null,
  external_id UUID NOT NULL UNIQUE DEFAULT gen_random_uuid()
);

create table people.Person (
  person_id SERIAL PRIMARY KEY,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  email varchar(50) not null,
  city_id BIGINT,
  external_id UUID NOT NULL UNIQUE DEFAULT gen_random_uuid()
);

ALTER TABLE ONLY people.Person
    ADD CONSTRAINT people_city_fkey FOREIGN KEY (city_id) REFERENCES people.City(city_id);