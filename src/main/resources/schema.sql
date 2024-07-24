create TABLE IF NOT EXISTS USERS
(
    USER_ID   INT PRIMARY KEY AUTO_INCREMENT,
    EMAIL     VARCHAR(255) NOT NULL,
    LOGIN     VARCHAR(255) NOT NULL,
    USER_NAME VARCHAR(255) NOT NULL,
    mpa_id INT,
    BIRTHDAY  DATE
);

create TABLE IF NOT EXISTS FRIENDS
(
    USER_ID   INT,
    FRIEND_ID INT
);


create table if not exists MPA
(
    mpa_id int not null primary key auto_increment,
    name varchar(255) not null,
    constraint MPA_PK primary key (mpa_id)
);

create table if not exists GENRE
(
    genre_id int not null primary key auto_increment,
    name varchar(255) not null,
    constraint GENRE_PK primary key (genre_id)
);