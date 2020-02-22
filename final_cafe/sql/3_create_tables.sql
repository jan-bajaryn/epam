use pizzeria;

--  Roles:
--   1 - admin
--  2 - operator
--  3 - client

CREATE TABLE user
(
    id         bigint auto_increment,
    username   varchar(20),
    password   varchar(100),
    role       tinyint,
    name       varchar(100),
    surname    varchar(100),
    birth_date date,
    creation   datetime,
    address    varchar(100),
    phone      varchar(100),

    CONSTRAINT pk_user PRIMARY KEY (id)
);


--  Order statuses:
--  1 - Waiting
--  2- Ready
--  3 - Delivering
--  4 - Done

--  Payment types:
--  1 - Cash
--  2 - Credit Card

CREATE TABLE `order`
(
    id           bigint auto_increment,
    creation     datetime,
    price        int,
    status tinyint,
    payment_type tinyint,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE delivery_inf
(
    id            bigint auto_increment,
    delivery_time datetime,
    client_name   varchar(100),
    address       varchar(100),
    phone         varchar(100),
    email         varchar(100),
    order_id      bigint,
    CONSTRAINT pk_delivery_inf PRIMARY KEY (id),
    CONSTRAINT fk_delivery_inf_order FOREIGN KEY (order_id) references `order` (id),
    CONSTRAINT unique_order_id UNIQUE (order_id)
);

CREATE TABLE product_type
(
    id   int auto_increment,
    name varchar(50),
    CONSTRAINT pk_product_type PRIMARY KEY (id),
    CONSTRAINT unique_product_type_name UNIQUE (name)
);

-- Product sizes:
-- null - not measured
-- 1- small
-- 2 - middle
-- 3 - big
-- 4 - extra big

CREATE TABLE product
(
    id              bigint auto_increment,
    name            varchar(100),
    description     text(500),
    photo_name      varchar(200),
    price           int,
    product_type_id int,
    product_size    tinyint,
    CONSTRAINT pk_product PRIMARY KEY (id),
    CONSTRAINT fk_product_product_type FOREIGN KEY (product_type_id) REFERENCES product_type (id)
);

CREATE TABLE ingredient
(
    id   bigint auto_increment,
    name varchar(100),
    CONSTRAINT pk_ingredient PRIMARY KEY (id),
    CONSTRAINT unique_ingredient_name UNIQUE (name)
);

CREATE TABLE product_ingredient
(
    product_id    bigint,
    ingredient_id bigint,
    weight        int NOT NULL,
    CONSTRAINT pk_product_ingredient PRIMARY KEY (product_id, ingredient_id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);

CREATE TABLE order_product
(
    id         bigint not null auto_increment,
    product_id bigint,
    order_id   bigint,
    CONSTRAINT pk_order_product PRIMARY KEY (id),
    CONSTRAINT fk_order_product_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_order_product_order FOREIGN KEY (order_id) REFERENCES `order` (id)
)