use pizzeria;

INSERT INTO user (id, username, password, role, name, surname, birth_date, creation, address, phone)
VALUES (2, 'Alina', 'my password', 2, 'Alina', 'Stankevich', '1995-7-3', NOW(), 'Mayakouskaha 3, 5',
        '+375 29 453 43 43');

INSERT INTO user (id, username, password, role, name, surname, birth_date, creation, address, phone)
VALUES (3, 'Vasia', 'util4345', 3, 'Vasilisa', 'Romanova', '1998-1-4', NOW(), 'Maksima Bahdanovicha 7, 1',
        '+375 33 545 55 55');

INSERT INTO ingredient (id, name)
VALUES (1, 'Cheese');
INSERT INTO ingredient (id, name)
VALUES (2, 'Bacon');
INSERT INTO ingredient (id, name)
VALUES (3, 'Tomato');
INSERT INTO ingredient (id, name)
VALUES (4, 'Potato');
INSERT INTO ingredient (id, name)
VALUES (5, 'Chicken');
INSERT INTO ingredient (id, name)
VALUES (6, 'Milk');
INSERT INTO ingredient (id, name)
VALUES (7, 'Chocolate');

INSERT INTO product_type (id, name)
VALUES (1, 'Pizza');
INSERT INTO product_type (id, name)
VALUES (2, 'Hot dish');
INSERT INTO product_type (id, name)
VALUES (3, 'Dessert');

INSERT INTO product (id, name, description, photo_name, price, product_type_id, product_size)
VALUES (1, 'Margarita', 'Great, juicy, delicious pizza made of cheese and tomatoes.', 'Margarita.jpg', 12, 1, 1);
INSERT INTO product (id, name, description, photo_name, price, product_type_id)
VALUES (2, 'Potato with chicken', 'Delicious chicken with potatoes', 'Potato_with_chicken.jpg', 15, 2);
INSERT INTO product (id, name, description, photo_name, price, product_type_id)
VALUES (3, 'Chocolate ice cream', 'Cold, delicious ice cream', 'Chocolate_ice_cream.jpg', 25, 3);

INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (1, 1, 29);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (1, 3, 30);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (2, 4, 35);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (2, 5, 27);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (3, 6, 50);
INSERT INTO product_ingredient (product_id, ingredient_id, weight)
VALUES (3, 7, 60);



INSERT INTO p_order (id, creation, price, order_status, payment_type)
VALUES (1, NOW(), 25, 1, 1);
INSERT INTO p_order(id, creation, price, order_status, payment_type)
VALUES (2, NOW(), 30, 3, 2);
INSERT INTO p_order(id, creation, price, order_status, payment_type)
VALUES (3, NOW(), 30, 3, 1);
