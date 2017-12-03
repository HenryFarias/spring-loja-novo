insert into user (username, password, name) VALUES ('user1', 'user1', 'user1');

insert into role (name) VALUES ('ROLE_ADMIN');
insert into role (name) VALUES ('ROLE_USER');

insert into user_role (user_id, role_id) VALUES (1, 1);