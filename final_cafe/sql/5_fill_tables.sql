use pizzeria;
INSERT INTO delivery_inf (id, comments, delivery_time, email, floor, house, phone, porch, room, street)
VALUES (3, NULL, '2020-03-13 15:55:52.611265', 'uuu@uuu.com', 2, '1', '294567651', 1, '1', 'Vatutina'),
       (5, NULL, '2020-03-13 15:55:52.611265', 'some@gmail.com', 2, '1', '999111222', 1, '1', 'Ватутина'),
       (9, NULL, '2020-03-13 15:55:52.611265', 'viviva5679@ymail365.com', 1, '1', '777444333', 1, '1', 'Беляева'),
       (21, NULL, '2020-03-13 15:55:52.611265', 'andreygordenevskiy@gmail.com', 5, '1', '294444455', 2,
        '1', 'Edwarda Wittiga'),
       (38, NULL, '2020-03-13 15:55:52.611265', 'asdasd@gmail.com', 2, '2', '294444455', 2, '4', 'Барадино'),
       (40, NULL, '2020-03-13 15:55:52.611265', 'semenov@gmail.com', 1, '1', '294444455', 1, '1', 'Семенова'),
       (42, NULL, '2020-03-13 15:55:52.611265', 'myemail@mail.ru', 1, '1', '294444455', 1, '1', 'Майова');

INSERT INTO user (id, creation, name, password, phone, role, surname, username, email, floor, house, porch, room,
                  street, is_blocked)
VALUES (12, '2020-03-13 15:55:52.611265', 'Anatoliy',
        '29609ba0cfb2fc501a3f3ae31d0e4178334d06eacfd99a6cf389e3889867a09f', '338762323', 1, 'Chichivarkin', 'Alex',
        'toha@gmail.com', NULL, NULL, NULL, NULL, NULL, FALSE),
       (16, '2020-03-13 20:27:24.898557', 'Георгий', '29609ba0cfb2fc501a3f3ae31d0e4178334d06eacfd99a6cf389e3889867a09f',
        '444444444', 2, 'Станкевич', 'Avalon',
        'georgiy@uuu.com', 2, '2', 2, '2', 'Михайловский переулок', FALSE),
       (17, '2020-03-13 20:56:41.920967', 'Алина', '29609ba0cfb2fc501a3f3ae31d0e4178334d06eacfd99a6cf389e3889867a09f',
        '333333333', 2, 'Скубарева', 'Alinochka',
        'abcdefoo@gmail.com', 5, '5', 5, '5', 'Васьняцова', FALSE),
       (18, '2020-03-14 03:27:34.701067', 'Михаил', '29609ba0cfb2fc501a3f3ae31d0e4178334d06eacfd99a6cf389e3889867a09f',
        '333444555', 2, 'Семенов', 'nick',
        'my_new_email@gmail.com', NULL, NULL, NULL, NULL, NULL, FALSE),
       (20, '2020-03-14 04:08:03.960329', 'Афанасий',
        '29609ba0cfb2fc501a3f3ae31d0e4178334d06eacfd99a6cf389e3889867a09f', '294443234', 2, 'Аркадьевич', 'Alarak',
        'kkk@kkk.kkk', 1, '23', 1,
        '18', 'Ленина', FALSE);

INSERT INTO `order` (id, creation, payment_type, price, status, delivery_inf_id, user_id, client_name)
VALUES (4, '2020-03-13 15:55:52.611265', 1, 0, 4, 3, NULL, 'Генадий'),
       (6, '2020-03-13 15:55:52.611265', 0, 0, 2, 5, NULL, 'Антон'),
       (10, '2020-03-13 15:55:52.611265', 0, 2020, 4, 9, NULL, 'Андрей'),
       (22, '2020-03-13 15:55:52.611265', 1, 17776, 2, 21, NULL, 'Виктор'),
       (39, '2020-03-13 15:55:52.611265', 0, 13610, 1, 38, NULL, 'Екатерина'),
       (41, '2020-03-13 15:55:52.611265', 0, 2008, 0, 40, NULL, 'Настя'),
       (43, '2020-03-13 15:55:52.611265', 0, 2020, 0, 42, 16, 'Торин');

INSERT INTO product_group (id, description, name, photo_name, type, disabled)
VALUES (1, 'Сливочный соус, смесь сыров пармезан и чеддер, сыр блю чиз, моцарелла', 'Четыре сыра', 'chetyre_syra.jpg',
        0, FALSE),
       (2, 'Томатный соус, цыпленок филе, пикантная пепперони, красный лук, моцарелла, бекон', 'Дон Бекон',
        'don_bekon.jpg', 0, FALSE),
       (3, 'Томаты свежие, острая чоризо, моцарелла, соус чипотле', 'Испанские колбаски чоризо', 'kolbaski.jpeg', 0,
        FALSE),
       (4, 'Сливочный соус, смесь сыров пармезан и чеддер, сыр блю чиз, моцарелла', 'Четыре сыра 2', 'krevetki.jpg', 0,
        FALSE),
       (5, 'Черный кунжут, шампиньоны, креветки, моцарелла, кисло-сладкий соус', 'Креветки по-азиатски',
        'chetyre_syra.jpg', 0, FALSE),
       (6, 'Сладкий перец, пепперони, моцарелла, томатный соус', 'Пепперони Фреш с перцем', 'pepperony.jpeg', 0,
        FALSE),
       (26, 'Паста, мясной соус болоньезе, моцарелла, итальянские травы', 'Куриные кусочки с сыром',
        'kur_kus.jpeg', 2, FALSE),
       (27, 'Освежающий напиток 7up, его все любят', '7 Up', '7up.jpg', 1,
        FALSE),
       (28, 'Освежающий напиток pepsi', 'Pepsi', 'pepsi.jpg', 1, FALSE),
       (29, 'Сытные палочки с курицей', 'Сытные палочки', 'sytnyje.jpeg', 2,
        FALSE),
       (30, 'Сытные рулетики с моцареллой и сырным соусом, 16 шт', 'Рулетики с сыром, 16 шт., 16 шт',
        'ruletiki.jpg', 2, FALSE),
       (31, 'Ароматный запеченный картофель с итальянскими травами', 'Горячий картофель из печи',
        'kartofel.jpg', 2, FALSE);

INSERT INTO product (id, price, weight, product_group_id)
VALUES (1, 1004, 239, 1),
       (2, 3423, 244, 3),
       (3, 1120, 244, 4),
       (4, 1820, 244, 5),
       (5, 1110, 244, 6),
       (6, 1000, 244, 2),
       (7, 1212, 500, 2),
       (8, 3333, 500, 3),
       (9, 3355, 500, 4),
       (10, 2211, 500, 5),
       (11, 2222, 500, 6),
       (12, 2020, 700, 1),
       (13, 2020, 700, 2),
       (14, 4444, 700, 3),
       (15, 4444, 700, 4),
       (16, 3030, 700, 5),
       (17, 3030, 700, 6),
       (18, 1212, 500, 1),
       (23, 2341, 200, 5),
       (24, 2241, 345, NULL),
       (32, 1010, 200, 31),
       (33, 2010, 400, 30),
       (34, 1212, 300, 29),
       (35, 300, 500, 28),
       (36, 300, 500, 27),
       (37, 2020, 500, 26);


INSERT INTO order_product (order_id, product_id, count)
VALUES (10, 12, 1),
       (22, 15, 4),
       (39, 35, 1),
       (39, 10, 2),
       (39, 15, 2),
       (41, 1, 2),
       (43, 13, 1),
       (4, 1, 2),
       (6, 1, 2);