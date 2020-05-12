insert into usr (id, username, password, active, email)
 values (1, 'admin', '123', true, 'example@mail.com'), (2, 'user', '123', true, 'example2@mail.com');

insert into user_role (user_id, roles)
 VALUES (1, 'USER'), (1, 'ADMIN'), (2, 'USER');