CREATE DATABASE pizzeria DEFAULT CHARACTER SET utf8;

GRANT SELECT, INSERT, UPDATE, DELETE
    ON pizzeria.*
    TO pizzer@localhost
    IDENTIFIED BY 'abcdefg';

