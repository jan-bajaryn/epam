use pizzeria;

INSERT INTO role (id, name)
VALUES (1, 'admin');

INSERT INTO role (id, name)
VALUES (2, 'operator');

INSERT INTO role (id, name)
VALUES (3, 'client');

INSERT INTO user (id, username, password, role_id, name, surname, birth_date, creation_date, address, phone)
VALUES (1, 'admin', '12345', 1, 'Antuan', 'Andreev', '1999-3-3', 'DATE: Manual Date', 'Lenina 3/4, 5',
        '+375 29 333 33 33');

INSERT INTO ingredient (id, name)
VALUES (1, 'Сыр');
INSERT INTO ingredient (id, name)
VALUES (2, 'Бекон');
INSERT INTO ingredient (id, name)
VALUES (3, 'Помидоры');

INSERT INTO product_type (id, name)
VALUES (1, 'Маленькая пицца');
INSERT INTO product_type (id, name)
VALUES (1, 'Средняя пицца');
INSERT INTO product_type (id, name)
VALUES (1, 'Большая пицца');

INSERT INTO product (id, name, description, photo_name, price, product_type_id)
VALUES (1, 'Маргарита', 'Великолепная, сочная, вкусная пицца из сыра и помидоров.', 'Маргарита.jpg', 12, 1);

INSERT INTO product (id, name, description, photo_name, price, product_type_id)
VALUES (2, 'Везувий', 'Красивая пицца, которую любит каждый.', 'Везувий1.jpg', 15, 2);

INSERT INTO product (id, name, description, photo_name, price, product_type_id)
VALUES (3, 'Вкусная', 'Идеально подходит для большой компании для просмотра футбола.', 'Вкусная_обложка.jpg', 25, 3);

INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (1, 1, 29);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (1, 3, 30);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (2, 1, 35);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (2, 2, 27);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (3, 1, 50);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (3, 2, 60);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (3, 3, 37);

INSERT INTO order_status (id, name)
VALUES (1, 'Waiting');
INSERT INTO order_status (id, name)
VALUES (2, 'Ready');
INSERT INTO order_status (id, name)
VALUES (3, 'Delivering');
INSERT INTO order_status (id, name)
VALUES (4, 'Done');

INSERT INTO payment_type (id, name)
VALUES (1, 'Cash');
INSERT INTO payment_type (id, name)
VALUES (2, 'Credit card');

INSERT INTO pizz_order (id, creation, price, order_status_id, payment_type_id)
VALUES (1, 'DATE: Manual Date', 25, 1, 1);
INSERT INTO pizz_order(id, creation, price, order_status_id, payment_type_id)
VALUES (2, 'DATE: Manual Date', 30, 3, 2);
INSERT INTO pizz_order(id, creation, price, order_status_id, payment_type_id)
VALUES (3, 'DATE: Manual Date', 30, 3, 1);



