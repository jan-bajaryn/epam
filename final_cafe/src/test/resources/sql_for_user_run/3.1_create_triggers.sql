use pizzeria_test;

DELIMITER $$
CREATE TRIGGER for_order_insert
    BEFORE INSERT
    ON `order`
    FOR EACH ROW
BEGIN
    IF new.status = 0 THEN
        BEGIN
            DECLARE c INT;
            select count(status) into c from `order` where user_id = NEW.user_id and status = 0;
            if c > 0 then
                signal sqlstate '45000';
            end if;
        end;
    end if;
end;
$$
DELIMITER $$
CREATE TRIGGER for_order_update
    BEFORE UPDATE
    ON `order`
    FOR EACH ROW
BEGIN
    IF new.status = 0 THEN
        BEGIN
            DECLARE c INT;
            select count(status) into c from `order` where user_id = NEW.user_id and status = 0;
            if c > 0 then
                signal sqlstate '45000';
            end if;
        end;
    end if;
end;
$$
DELIMITER ;
