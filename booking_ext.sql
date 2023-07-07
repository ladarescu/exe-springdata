DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id BIGINT PRIMARY KEY,
  name VARCHAR
) WITH "template=partitioned, backups=1, CACHE_NAME=user, VALUE_TYPE=exe.springdata.model.user";

DROP TABLE IF EXISTS house;

CREATE TABLE house (
  id BIGINT PRIMARY KEY,
  idowner BIGINT,
) WITH "template=partitioned, backups=1, CACHE_NAME=house, VALUE_TYPE=exe.springdata.model.house";

DROP TABLE IF EXISTS booking;

CREATE TABLE booking (
  id BIGINT PRIMARY KEY,
  iduser BIGINT,
  idhouse BIGINT,
  dateop TIMESTAMP,
  datestart TIMESTAMP,
  dateend TIMESTAMP
) WITH "template=partitioned, backups=1, CACHE_NAME=booking, VALUE_TYPE=exe.springdata.model.booking";

CREATE TABLE block (
  id BIGINT PRIMARY KEY,
  idhouse BIGINT,
  dateop TIMESTAMP,
  datestart TIMESTAMP,
  dateend TIMESTAMP
) WITH "template=partitioned, backups=1, CACHE_NAME=block, VALUE_TYPE=exe.springdata.model.block";


SET STREAMING ON;

INSERT INTO user(id, name) VALUES (1,'aaaa1');
INSERT INTO user(id, name) VALUES (2,'aaaa2');
INSERT INTO user(id, name) VALUES (3,'aaaa3');
INSERT INTO user(id, name) VALUES (4,'aaaa4');
INSERT INTO user(id, name) VALUES (5,'aaaa5');
INSERT INTO user(id, name) VALUES (6,'aaaa6');
INSERT INTO user(id, name) VALUES (7,'aaaa7');
INSERT INTO user(id, name) VALUES (8,'aaaa8');
INSERT INTO user(id, name) VALUES (9,'aaaa9');
INSERT INTO user(id, name) VALUES (10,'aaaa10');


INSERT INTO house(id, idowner) VALUES (1,1);
INSERT INTO house(id, idowner) VALUES (2,1);
INSERT INTO house(id, idowner) VALUES (3,2);
INSERT INTO house(id, idowner) VALUES (4,2);
INSERT INTO house(id, idowner) VALUES (5,3);

INSERT INTO booking(id, iduser, idhouse, dateop, datestart, dateend) VALUES (1, 4, 1, '2023-07-06 13:00:00', '2023-07-06 13:00:00', '2023-07-09 13:00:00');
INSERT INTO booking(id, iduser, idhouse, dateop, datestart, dateend) VALUES (2, 5, 1, '2023-07-06 13:01:00', '2023-07-09 13:00:00', '2023-07-10 13:00:00');
INSERT INTO booking(id, iduser, idhouse, dateop, datestart, dateend) VALUES (3, 6, 1, '2023-07-06 13:02:00', '2023-07-08 13:00:00', '2023-07-12 13:00:00');
INSERT INTO booking(id, iduser, idhouse, dateop, datestart, dateend) VALUES (4, 7, 2, '2023-07-06 13:03:00', '2023-07-07 13:00:00', '2023-07-09 13:00:00');
INSERT INTO booking(id, iduser, idhouse, dateop, datestart, dateend) VALUES (5, 8, 3, '2023-07-06 13:04:00', '2023-07-10 13:00:00', '2023-07-11 13:00:00');
INSERT INTO booking(id, iduser, idhouse, dateop, datestart, dateend) VALUES (6, 9, 4, '2023-07-06 13:05:00', '2023-07-11 13:00:00', '2023-07-12 13:00:00');


INSERT INTO block(id, idhouse, dateop, datestart, dateend) VALUES (4, 5, '2023-07-06 13:03:00', '2023-07-07 13:00:00', '2023-07-09 13:00:00');
INSERT INTO block(id, idhouse, dateop, datestart, dateend) VALUES (5, 4, '2023-07-06 13:04:00', '2023-07-10 13:00:00', '2023-07-11 13:00:00');
INSERT INTO block(id, idhouse, dateop, datestart, dateend) VALUES (6, 5, '2023-07-06 13:05:00', '2023-07-11 13:00:00', '2023-07-12 13:00:00');

SET STREAMING OFF;
