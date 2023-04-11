create table TodoDB.Users
(
    id          bigint auto_increment
        primary key,
    username    varchar(255)                                         not null,
    role      enum ('ADMIN', 'USER') default 'USER' not null,
    created_at  datetime                                             not null,
    update_at   datetime                                             null
)
    collate = utf8mb4_unicode_ci;