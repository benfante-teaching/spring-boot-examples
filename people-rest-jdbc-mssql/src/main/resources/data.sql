delete from Person;
delete from City;

insert into City(name) values ('Rome'); -- city_id should be 1
insert into City(name) values ('Milan'); -- city_id should be 2
insert into City(name) values ('Naples'); -- city_id should be 3

insert into Person(first_name, last_name, email, city_id)
  values ('Lucio', 'Benfante', 'lucio.benfante@email.it', 1);
insert into Person(first_name, last_name, email, city_id)
  values ('Mario', 'Rossi', 'mario.rossi@email.it', 2);
insert into Person(first_name, last_name, email, city_id)
  values ('Giovanna', 'Verdi', 'giovanna.verdi@email.it', 3);