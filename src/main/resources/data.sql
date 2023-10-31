create table if not exists subjects (
    id long primary key auto_increment,
    username varchar(32) not null unique,
    password text not null,
    birthday date not null,
    placed_at date not null,
    role text not null,
    enabled boolean
);