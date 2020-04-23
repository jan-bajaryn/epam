use pizzeria;
INSERT INTO delivery_inf (id, comments, delivery_time, email, floor, house, phone, porch, room, street)
VALUES (3, NULL, NULL, 'uuu@uuu.com', 2, '1', 'sdf', 1, '1', 'sdf'),
       (5, NULL, NULL, 'g@sdf.sdf', 2, '1', 'g', 1, '1', 'g'),
       (9, NULL, NULL, 'viviva5679@ymail365.com', 1, '1', 'z', 1, '1', 'z'),
       (21, NULL, NULL, 'andreygordenevskiy@gmail.com', 5, '1', '+375 29 444 44 55', 2,
        '1', 'Edwarda Wittiga'),
       (38, NULL, NULL, 'asdasd@gmail.com', 2, '2', '+375 29 444 44 55', 2, '4', 'Барадино'),
       (40, NULL, NULL, 'semenov@gmail.com', 1, '1', '+375 29 444 44 55', 1, '1', 'Семенова'),
       (42, NULL, NULL, 'A@A.com', 1, '1', '+375 29 444 44 55', 1, '1', 'A');

INSERT INTO user (id, creation, name, password, phone, role, surname, username, email, floor, house, porch, room,
                  street, is_blocked)
VALUES (12, '2020-03-13 15:55:52.611265', 'Anatoliy', '1234', '+375 33 333 33 33', 1, 'Chichivarkin', 'Alex',
        'toha@gmail.com', NULL, NULL, NULL, NULL, NULL, FALSE),
       (16, '2020-03-13 20:27:24.898557', 'Георгий', 'asdf', '+48 444 444 444', 2, 'Станкевич', 'Avalon',
        'georgiy@uuu.com', 2, '2', 2, '2', 'Михайловский переулок', FALSE),
       (17, '2020-03-13 20:56:41.920967', 'Алина', '12345', '+375 33 333 33 33', 2, 'Скубарева', 'Alinochka',
        'abcdefoo@gmail.com', 5, '5', 5, '5', 'Васьняцова', FALSE),
       (18, '2020-03-14 03:27:34.701067', 'Михаил', 'abcdefg', '+48 333 444 555', 2, 'Семенов', 'nick',
        'my_new_email@gmail.com', NULL, NULL, NULL, NULL, NULL, FALSE),
       (20, '2020-03-14 04:08:03.960329', 'Афанасий', 'password', '+375 29 444 32 34', 2, 'Аркадьевич', 'Alarak',
        'kkk@kkk.kkk', 1, '23', 1,
        '18', 'Ленина', FALSE);

INSERT INTO `order` (id, creation, payment_type, price, status, delivery_inf_id, user_id, client_name)
VALUES (4, NULL, 1, 0, 4, 3, NULL, 'Генадий'),
       (6, NULL, 0, 0, 2, 5, NULL, 'Антон'),
       (10, NULL, 0, 2020, 4, 9, NULL, 'Андрей'),
       (22, NULL, 1, 17776, 2, 21, NULL, 'Виктор'),
       (39, NULL, 0, 13610, 1, 38, NULL, 'Екатерина'),
       (41, NULL, 0, 2008, 0, 40, NULL, 'Настя'),
       (43, NULL, 0, 2020, 0, 42, 16, 'Торин');

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
        '333e4481-c0de-4205-aa14-cdf6e20fb624.Куриныекусочкиссыром.jpeg', 2, FALSE),
       (27, 'Освежающий напиток 7up, его все любят', '7 Up', 'b1dd0d59-9c44-4308-bb7e-447e34c56e7b.7up.jpg', 1,
        FALSE),
       (28, 'Освежающий напиток pepsi', 'Pepsi', '3c2c67d2-522d-41b6-af8f-cbecd343dd6b.pepsi.jpg', 1, FALSE),
       (29, 'Сытные палочки с курицей', 'Сытные палочки', '3154076d-0d0d-4bc1-8434-5d2217693438.Caтные палочки.jpeg', 2,
        FALSE),
       (30, 'Сытные рулетики с моцареллой и сырным соусом, 16 шт', 'Рулетики с сыром, 16 шт., 16 шт',
        '06c647ef-af16-4ee4-844b-3f37394c1cea.Рулетики с сыром.jpg', 2, FALSE),
       (31, 'Ароматный запеченный картофель с итальянскими травами', 'Горячий картофель из печи',
        '18a74641-b7d8-41e8-883a-a061add5e5fd.Картофель из печи.jpg', 2, FALSE);

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


INSERT INTO order_product (order_id, product_id)
VALUES (10, 12),
       (22, 15),
       (22, 15),
       (22, 15),
       (22, 15),
       (39, 35),
       (39, 10),
       (39, 15),
       (39, 10),
       (39, 15),
       (41, 1),
       (41, 1),
       (43, 13);