create table categories
(
    id   serial primary key,
    name varchar(30) not null
);

create table products
(
    id          serial primary key,
    category_id int4        not null,
    name        varchar(50) not null,
    price       decimal     not null,
    foreign key (category_id) references categories (id)
);

create table characteristics
(
    id          serial primary key,
    category_id int4        not null,
    name        varchar(30) not null,
    foreign key (category_id) references categories (id)
);

create table options
(
    id                 serial primary key,
    products_id        int4        not null,
    characteristics_id int4        not null,
    option             varchar(50) not null,
    foreign key (products_id) references products (id),
    foreign key (characteristics_id) references characteristics (id)
);

create table roles
(
    id           serial primary key,
    service_name varchar(20) not null,
    display_name varchar(30) not null
);

create table users
(
    id       serial primary key,
    login    varchar(20)  not null,
    password varchar(200) not null,
    name     varchar(50)  not null,
    surname  varchar(50)  not null,
    date_reg timestamp    not null,
    role_id  int4         not null,
    foreign key (role_id) references roles (id)
);

create table statuses
(
    id     serial primary key,
    status varchar(20) not null
);

create table orders
(
    id              serial primary key,
    user_id         int4        not null,
    status_id       int4        not null,
    delivery_adress varchar(40) not null,
    date_reg        timestamp   not null,
    foreign key (user_id) references users (id),
    foreign key (status_id) references statuses (id)
);

create table order_products
(
    id         serial primary key,
    product_id int4 not null,
    order_id   int4,
    quantity   int2 not null,
    foreign key (product_id) references products (id),
    foreign key (order_id) references orders (id)
);

create table feedbacks
(
    id         serial primary key,
    user_id    int4      not null,
    product_id int4      not null,
    posted     boolean   not null,
    estimate   int4      not null,
    feedback   text      not null,
    date_red   timestamp not null,
    foreign key (user_id) references users (id),
    foreign key (product_id) references products (id)
);

create table cart_items(
    id serial primary key,
    user_id int4 not null,
    product_id int4 not null,
    quantity int2 not null,
    foreign key (product_id) references products(id),
    foreign key (user_id) references users(id)
);
