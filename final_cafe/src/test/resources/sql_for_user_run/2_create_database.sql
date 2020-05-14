CREATE DATABASE IF NOT EXISTS pizzeria_test DEFAULT CHARACTER SET utf8;

DROP USER IF EXISTS 'pizza_worker_test'@'localhost';
CREATE USER IF NOT EXISTS 'pizza_worker_test'@'localhost' IDENTIFIED BY 'abcde';

GRANT SELECT, INSERT, UPDATE, DELETE ON pizzeria_test.* TO 'pizza_worker_test'@'localhost';

FLUSH PRIVILEGES;
