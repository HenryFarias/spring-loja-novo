insert into user (username, password, name) VALUES 
('user1', '$2a$10$GinoZ5nJordq6K/kUUfypuJsw2zw2AqLEug1mlx7/plL8DfKSvGxS', 'user1');

-- senha é user1

insert into role (name) VALUES ('ROLE_ADMIN');
insert into role (name) VALUES ('ROLE_USER');

insert into user_role (user_id, role_id) VALUES (1, 1);