create table users
(
    id bigint not null auto_increment,
    username varchar(20),
    firstname varchar(50),
    lastname varchar(50),
    phone varchar(50),
    email varchar(50),
    gender varchar(50),
    password varchar(120),
    imageURL varchar(255),
    primary key (id)
);
create table roles
(
    id bigint not null auto_increment,
    name varchar(20),
    primary key (id)
);
create table posts
(
    id bigint not null auto_increment,
    username varchar (255),
    start_point varchar(255),
    end_point varchar(255),
    start_point_date varchar(50),
    end_point_date varchar(50),
    passengers bigint not null ,
    primary key (id)
);
create table user_posts
(
    user_id bigint not null,
    post_id bigint not null,
    primary key (user_id, post_id)
);
create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);
alter table user_posts add constraint FKh8ciramu9cc9q3ccqiv4ue8a6 foreign key (post_id) references posts (id);
alter table user_posts add constraint FKhfh9dx7w3ubfggo1vdev94g3f foreign key (user_id) references users (id);
alter table users add constraint UKr43af9ap4edm43mmtq01oddj6 unique (username);
alter table users add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table user_roles add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles (id);
alter table user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users (id);