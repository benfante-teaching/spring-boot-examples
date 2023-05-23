create table if not exists Userdata (
  id identity,
  username varchar(50) not null,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  age integer
);