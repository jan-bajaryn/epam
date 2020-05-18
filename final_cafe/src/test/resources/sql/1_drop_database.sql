use pizzeria_test;
-- DROP DATABASE IF EXISTS pizzeria_test;
DROP TRIGGER IF EXISTS for_order_insert;
DROP TRIGGER IF EXISTS for_order_update;

DROP TABLE IF EXISTS order_product;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_group;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS delivery_inf;

