CREATE TABLE IF NOT EXISTS post (
   id SERIAL PRIMARY KEY,
   name text,
   description text,
   created timestamp,
   visiblea boolean,
   city_id integer,
   city_name text
);

CREATE TABLE IF NOT EXISTS candidate (
   id SERIAL PRIMARY KEy,
   name text,
   description text,
   created timestamp,
   visiblea boolean,
   photo bytea
);

CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   email varchar(255) unique,
   password text
);
