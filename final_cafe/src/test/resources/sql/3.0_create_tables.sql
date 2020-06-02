use pizzeria_test;

CREATE TABLE delivery_inf
(
    id            integer      NOT NULL auto_increment,
    comments      varchar(255),
    delivery_time datetime(6),
    email         varchar(255),
    floor         integer,
    phone         varchar(255),
    street        varchar(255) NOT NULL,
    house         varchar(10)  NOT NULL,
    room          varchar(10),
    porch         integer,
    CONSTRAINT pk_delivery_inf PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         integer NOT NULL auto_increment,
    creation   datetime(6),
    name       varchar(255),
    password   varchar(64),
    phone      varchar(255),
    role       integer,
    surname    varchar(255),
    username   varchar(255),
    email      varchar(255),
    floor      integer,
    house      varchar(10),
    porch      integer,
    room       varchar(10),
    street     varchar(255),
    is_blocked BOOL    NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT uk UNIQUE (username),
    CONSTRAINT uk_email UNIQUE (email),
    INDEX (username, password)
);

CREATE TABLE `order`
(
    id              integer NOT NULL auto_increment,
    client_name     varchar(255),
    creation        date,
    payment_type    integer,
    price           integer,
    status          integer,
    delivery_inf_id integer,
    user_id         integer,
    CONSTRAINT pk_order PRIMARY KEY (id),
    CONSTRAINT fk_order_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_order_delivery_inf_id FOREIGN KEY (delivery_inf_id) REFERENCES delivery_inf (id)
);


CREATE TABLE product_group
(
    id          integer NOT NULL auto_increment,
    description varchar(255),
    name        varchar(255),
    photo_name  varchar(255),
    type        integer,
    disabled    BOOL    NOT NULL,
    CONSTRAINT pk_product_group PRIMARY KEY (id)
);

CREATE TABLE product
(
    id               integer NOT NULL auto_increment,
    price            integer,
    weight           integer,
    product_group_id integer,
    CONSTRAINT pk_product PRIMARY KEY (id),
    UNIQUE KEY uk_product_product_group_id_weight (product_group_id, weight),
    CONSTRAINT fk_product_product_group FOREIGN KEY (product_group_id) REFERENCES product_group (id)
);

CREATE TABLE order_product
(
    order_id   integer NOT NULL,
    product_id integer NOT NULL,
    count      integer NOT NULL,
    CONSTRAINT uk_order_product UNIQUE (order_id, product_id),
    CONSTRAINT fk_order_product_order FOREIGN KEY (order_id) REFERENCES `order` (id) ON DELETE CASCADE,
    CONSTRAINT fk_order_product_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT ch_count CHECK ( count > 0 )
);


