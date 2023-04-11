create table TodoDB.Todo
(
    id          bigint auto_increment
        primary key,
    title       varchar(255)                                         not null,
    content     varchar(255)                                         null,
    status      enum ('INCOMPLETE', 'COMPLETE') default 'INCOMPLETE' not null,
    complete_at datetime                                             null,
    created_at  datetime                                             not null,
    update_at   datetime                                             null
)
    collate = utf8mb4_unicode_ci;