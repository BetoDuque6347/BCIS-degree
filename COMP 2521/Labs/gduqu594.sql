--Lab 6
--Author: Beto Duque
--Instructor: Shoba Ittyipe
--Due date: March 24, 2026


--======PROCEDURES AND FUNCTIONS======--


--Create procedure add_author
DROP PROCEDURE add_author;
delimiter $$

CREATE PROCEDURE add_author
(
    IN id CHAR(11),
    IN last VARCHAR(40),
    IN first VARCHAR(20)
)
BEGIN
INSERT INTO author (au_id, au_lname, au_fname) VALUES
(
    id,
    last,
    first
);
END$$
delimiter ;

--Test add_author
CALL add_author('300', 'Collins', 'Suzanne');
CALL add_author('400', 'Ittyipe', 'Shoba');

--Write a SELECT statement to retrieve the rows inserted.
SELECT *
FROM author
WHERE au_id = '300' OR au_id = '400';

--Create procedure add_title
DROP PROCEDURE add_title;
delimiter $$
CREATE PROCEDURE add_title
(
    IN tit_id CHAR(6),
    IN tit_name VARCHAR(80),
    IN publisher CHAR(4)
)
BEGIN
INSERT INTO title (title_id, title, pub_id)
VALUES (tit_id, tit_name, publisher);
END$$
delimiter ;

--Test add_title
CALL add_title ("123", "About Life", "0877");
CALL add_title ("789", "Udacity", "1389");

--Write a SELECT statement to retrieve the rows inserted.
SELECT *
FROM title
WHERE contract IS NULL;

--Create find_title()
DROP FUNCTION find_title;
delimiter $$

CREATE FUNCTION find_title(titleName CHAR(80))
    RETURNS char(6)
BEGIN
    DECLARE id CHAR(6);
    SELECT title_id
    INTO id
    FROM title WHERE title = titleName;
    return id;
END$$
delimiter ;

--Test find_title()
SELECT find_title("About Life") as id;

--Create addAuthorTitle
DROP PROCEDURE addAuthorTitle;
delimiter $$

CREATE PROCEDURE addAuthorTitle
(
    IN auNbr CHAR(11),
    IN titleName VARCHAR(80),
    IN ordering DECIMAL(3, 0),
    IN royalty DECIMAL(6, 2)
)
BEGIN
    DECLARE aid INT;
    INSERT INTO author_title(au_id, title_id, au_ord, royaltyshare) VALUES (
        auNbr,
        find_title(titleName),
        ordering,
        royalty
    );
END$$

delimiter ;

--Test addAuthorTitle
CALL addAuthorTitle(300, "About Life", 1, 0.6);
CALL addAuthorTitle(400, "About Life", 2, 0.4);

--Write a SELECT statement to retrieve the author's last name, first name, and the title's name,
--the author order, and the royalty share of each author for the title "About Life"
SELECT a.au_lname, a.au_fname, t.title, at.au_ord, at.royaltyshare
FROM author a
JOIN author_title at ON a.au_id = at.au_id
JOIN title t ON at.title_id = t.title_id
WHERE t.title = "About Life";

--Create add_author_check
DROP PROCEDURE add_author_check;
delimiter $$

CREATE PROCEDURE add_author_check
(
    IN id CHAR(11),
    IN last VARCHAR(40),
    IN first VARCHAR(20),
    IN a VARCHAR(50),
    OUT b VARCHAR(20)
)
BEGIN
IF a LIKE 'Justin Beiber%' THEN
    SET b = 'Invalid Entry!';
ELSE
    INSERT INTO author (au_id, au_lname, au_fname, address) VALUES 
    (
        id,
        last,
        first,
        a
    );
END IF;
END$$
delimiter ;

--Test add_author_check
CALL add_author_check('11', 'Gomez', 'Selena', 'Justin Beiber', @just);


--======TRIGGERS======--


--Create book_price_audit
CREATE TABLE book_price_audit (
    title_id CHAR(6),
    type CHAR(12),
    old_price NUMERIC(6, 2),
    new_price NUMERIC(6, 2)
);

--Create trigger for when the price of a book increases by 10%
DROP TRIGGER audit_book_price_BUR;
delimiter $$

CREATE TRIGGER audit_book_price_BUR
BEFORE UPDATE
ON title
FOR EACH ROW
BEGIN
    IF (new.price / old.price >= 1.1) THEN
        INSERT INTO book_price_audit (title_id, type, old_price, new_price)
    VALUES (new.title_id, new.type, old.price, new.price);
END IF;
END$$

delimiter ;

--Test trigger by changing PC8888 to $20.50
UPDATE title
SET price = 20.50
WHERE title_id = 'PC8888';

--Test trigger by changing BU1032 to $25.00
UPDATE title
SET PRICE = 25.00
WHERE title_id = 'BU1032';

--Add audit_nbr column into book_price_audit
ALTER TABLE book_price_audit
ADD audit_nbr INT

--Create generate_audit_nbr_BIR
DROP TRIGGER generate_audit_nbr_BIR;
delimiter $$

CREATE TRIGGER generate_audit_nbr_BIR
BEFORE INSERT
ON book_price_audit
FOR EACH ROW
BEGIN
    DECLARE maximum INT;

    SELECT IFNULL(MAX(audit_nbr), 0)
    INTO maximum
    FROM book_price_audit;

    SET new.audit_nbr = maximum + 1;
END$$
delimiter ;

--Update MC3021 to $10.00 (More than 10% increase)
UPDATE title
SET price = 10.00
WHERE title_id = 'MC3021';

--Update BU2075 to $6.00 (More than 10% increase)
UPDATE title
SET price = 6.00
WHERE title_id = 'BU2075';

--Update BU1032 to $24.50 (Less than 10% increase)
UPDATE title
SET price = 24.50
WHERE title_id = 'BU1032';

--Show price, ytd_sales, and total_income for BU1111 and MC2222
SELECT price, ytd_sales, total_income
FROM title
WHERE title_id IN('BU1111', 'MC2222');

--Create update_total_income_BUR
DROP TRIGGER update_total_income_BUR;
delimiter $$

CREATE TRIGGER update_total_income_BUR
BEFORE UPDATE
ON title
FOR EACH ROW
BEGIN
    --Whenever total_income changes OR if ytd changes, update total_income to be accurate
    IF new.total_income != old.total_income OR new.ytd_sales != old.ytd_sales THEN
        SET new.total_income = new.price * new.ytd_sales;
    END IF;
END$$

delimiter ;

--Change MC2222 sales from 2032 to 4000
UPDATE title
SET ytd_sales = 4000
WHERE title_id = 'MC2222';

--Change BU1111 price from $11.95 to $21.99
UPDATE title
SET price = 21.99
WHERE title_id = 'BU1111';

--View changes using a SELECT statement (wanted to add title_id into select cuz why not)
SELECT title_id, price, ytd_sales, total_income
FROM title
WHERE title_id IN ('MC2222', 'BU1111');