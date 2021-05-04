DROP TABLE IF EXISTS person;

CREATE TABLE person (
  id INT(10) AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  patronymic VARCHAR(250) DEFAULT NULL,
  last_name VARCHAR(250) NOT NULL,
  birthday DATE NOT NULL
);

INSERT INTO person (first_name, patronymic, last_name,  birthday) VALUES
  ('Петр', 'Сергеевич', 'Иванов', parsedatetime('17.09.2012 00:00', 'dd.MM.yyyy HH:mm') ),
  ('Иван', 'Петрович', 'Кузнецов', parsedatetime('12.01.1957 00:00', 'dd.MM.yyyy HH:mm') );