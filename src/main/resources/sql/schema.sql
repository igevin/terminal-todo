create table todo_user
(
    id       bigint auto_increment
        primary key,
    username varchar(128) not null,
    password varchar(256) not null,
    constraint to_user_username_uindex
        unique (username)
);