CREATE DATABASE IF NOT EXISTS pizzeria_test DEFAULT CHARACTER SET utf8;

CREATE USER IF NOT EXISTS 'pizza_worker'@'localhost' IDENTIFIED BY 'abcde';

GRANT SELECT, INSERT, UPDATE, DELETE ON pizzeria_test.* TO 'pizza_test_worker'@'localhost';
