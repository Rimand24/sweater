insert into usr (id, username, password, active)
 values (1, 'admin', '10', true);
-- $2a$08$5Gb0G87B01Z0TVaeYU3lseU7tOMMiEhu3H9ktmQKiigvs8W/HFTDK

insert into user_role (user_id, roles)
 VALUES (1, 'USER'), (1, 'ADMIN');