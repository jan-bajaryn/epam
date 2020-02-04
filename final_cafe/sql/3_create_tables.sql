use pizzeria;

CREATE TABLE user
(
    id            bigint auto_increment,
    username      varchar(20),
    password      varchar(100),
    role_id       bigint,
    name          varchar(100),
    surname       varchar(100),
    birth_date    date,
    creation_date date,
    address       varchar(100),
    phone         varchar(100),

    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role (id)
);



CREATE TABLE pizz_order
(
    id              bigint auto_increment,
    creation        date,
    price           int,
    order_status_id bigint,
    payment_type_id bigint,
    CONSTRAINT pk_order PRIMARY KEY (id),
    FOREIGN KEY (order_status_id) REFERENCES order_status (id),
    FOREIGN KEY (payment_type_id) REFERENCES payment_type (id)
);

CREATE TABLE delivery_inf
(
    id            bigint auto_increment,
    delivery_time date,
    client_name   varchar(100),
    address       varchar(100),
    phone         varchar(100),
    email         varchar(100),
    order_id      bigint,
    CONSTRAINT pk_delivery_inf PRIMARY KEY (id),
    CONSTRAINT fk_delivery_inf_pizz_order FOREIGN KEY (order_id) references pizz_order (id),
    CONSTRAINT unique_order_id UNIQUE (order_id)
);

CREATE TABLE product
(
    id              bigint auto_increment,
    name            varchar(100),
    description     text(500),
    photo_name      varchar(200),
    price           int,
    product_type_id bigint,
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