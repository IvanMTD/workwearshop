create table if not exists subjects (
    id long primary key auto_increment,
    username varchar(32) not null unique,
    password text not null,
    birthday date not null,
    placed_at date not null,
    role text not null,
    enabled boolean
);

create table if not exists category (
    id long primary key auto_increment,
    name varchar(32) not null,
    description text not null,
    internal boolean not null,
    image blob not null
);