delete from Person;
delete from City;

insert into City(city_id, name)
    values (1, 'Rome');
insert into City(city_id, name)
    values (2, 'Milan');
insert into City(city_id, name)
    values (3, 'Naples');

insert into Person(first_name, last_name, email, city_id)
  values ('Lucio', 'Benfante', 'lucio.benfante@email.it', 1);
insert into Person(first_name, last_name, email, city_id)
  values ('Mario', 'Rossi', 'mario.rossi@email.it', 2);
insert into Person(first_name, last_name, email, city_id)
  values ('Giovanna', 'Verdi', 'giovanna.verdi@email.it', 3);