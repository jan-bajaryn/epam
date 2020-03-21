use pizzeria;

CREATE TABLE delivery_inf
(
    id            bigint NOT NULL,
    comments      varchar(255),
    delivery_time datetime(6),
    email         varchar(255),
    floor         int,
    house         varchar(10),
    phone         varchar(255),
    porch         int,
    room          varchar(10),
    street        varchar(255),
    CONSTRAINT pk_delivery_inf PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         bigint NOT NULL,
    creation   datetime(6),
    name       varchar(255),
    password   varchar(255),
    phone      varchar(255),
    role       int,
    surname    varchar(255),
    username   varchar(255),
    email      varchar(255),
    floor      int,
    house      varchar(10),
    porch      int,
    room       varchar(10),
    street     varchar(255),
    is_blocked BOOL   NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id              bigint NOT NULL,
    client_name     varchar(255),
    creation        date,
    payment_type    int,
    price           int,
    status          int,
    delivery_inf_id bigint,
    user_id         bigint,
    CONSTRAINT pk_order PRIMARY KEY (id),
    CONSTRAINT fk_order_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_order_delivery_inf_id FOREIGN KEY (delivery_inf_id) REFERENCES delivery_inf (id)
);

CREATE TABLE product_group
(
    id          bigint NOT NULL,
    description varchar(255),
    name        varchar(255),
    photo_name  varchar(255),
    type        int,
    disabled    BOOL   NOT NULL,
    CONSTRAINT pk_product_group PRIMARY KEY (id)
);

CREATE TABLE product
(
    id               bigint NOT NULL,
    price            int,
    weight           int,
    product_group_id bigint,
    CONSTRAINT pk_product PRIMARY KEY (id),
    UNIQUE KEY uk_product_product_group_id_weight (product_group_id, weight),
    CONSTRAINT fk_product_product_group FOREIGN KEY (product_group_id) REFERENCES product_group (id)
);

CREATE TABLE order_product
(
    order_id   bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT fk_order_product_order FOREIGN KEY (order_id) REFERENCES `order` (id),
    CONSTRAINT fk_order_product_product FOREIGN KEY (product_id) REFERENCES product (id)
);


