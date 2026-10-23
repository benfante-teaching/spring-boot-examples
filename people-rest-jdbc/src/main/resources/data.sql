delete from Person;
delete from City;

insert into City(city_id, name) values (10001, 'Rome');
insert into City(city_id, name) values (10002,'Milan');
insert into City(city_id, name) values (10003, 'Naples');

insert into Person(first_name, last_name, email, city_id)
  values ('Lucio', 'Benfante', 'lucio.benfante@email.it', 10001);
insert into Person(first_name, last_name, email, city_id)
  values ('Mario', 'Rossi', 'mario.rossi@email.it', 10002);
insert into Person(first_name, last_name, email, city_id)
  values ('Giovanna', 'Verdi', 'giovanna.verdi@email.it', 10003);