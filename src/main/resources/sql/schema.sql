create table todo_user
(
    id       bigint auto_increment
        primary key,
    username varchar(128) not null,
    password varchar(256) not null,
    constraint to_user_username_uindex
        unique (username)
);

create table todo_list
(
    id         bigint auto_increment
        primary key,
    username   varchar(128)                       not null,
    createTime datetime default CURRENT_TIMESTAMP not null
);

create table todo_task
(
    id          bigint                             not null
        primary key,
    content     varchar(256)                       not null,
    checked     tinyint                            not null,
    list_id     bigint                             not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null
);