CREATE TABLE IF NOT EXISTS post (
   id SERIAL PRIMARY KEY,
   name varchar(255),
   description varchar(255),
   created timestamp
);

CREATE TABLE IF NOT EXISTS candidate (
   id SERIAL PRIMARY KEy,
   name varchar(255),
   description varchar(255),
   created timestamp
);