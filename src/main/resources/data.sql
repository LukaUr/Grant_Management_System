insert into roles(name) VALUES('ROLE_ADMIN');
insert into roles(name) VALUES('ROLE_USER');
insert into users(name, password) values('admin', '$2a$10$w5aMkLgMSXX8FGoCv8YcvOJ9wDrh5qolNt47/0OsbBZw42FI9fd/.');
insert into users(name, password) values('user', '$2a$10$VZVkz4rWmoJIjlD/ieYjWOXnrNxNNhyQU9zajtETnXkLJ6ws.DF8O');
insert into users_roles(user_id, role_id) values (1, 1);
insert into users_roles(user_id, role_id) values (1, 2);
insert into users_roles(user_id, role_id) values (2, 2);
