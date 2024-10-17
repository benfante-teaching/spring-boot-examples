USE master;
GO

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = N'people')
BEGIN
    exec('create schema people;');

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

    insert into people.City(name) values ('Rome'); -- city_id should be 1

    insert into people.City(name) values ('Milan'); -- city_id should be 2

    insert into people.City(name) values ('Naples'); -- city_id should be 3

    insert into people.Person(first_name, last_name, email, city_id)
        values ('Lucio', 'Benfante', 'lucio.benfante@email.it', 1);

    insert into people.Person(first_name, last_name, email, city_id)
        values ('Mario', 'Rossi', 'mario.rossi@email.it', 2);

    insert into people.Person(first_name, last_name, email, city_id)
        values ('Giovanna', 'Verdi', 'giovanna.verdi@email.it', 3);

END
