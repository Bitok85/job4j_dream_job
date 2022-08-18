CREATE TABLE IF NOT EXISTS post (
   id SERIAL PRIMARY KEY,
   name text,
   description text,
   created timestamp,
   visible boolean,
   city_id integer,
   city_name text
);

CREATE TABLE IF NOT EXISTS candidate (
   id SERIAL PRIMARY KEy,
   name text,
   description text,
   created timestamp
   visible boolean,
   photo bytea[]
);