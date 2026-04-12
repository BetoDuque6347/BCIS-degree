-- Assignment 3 Step 3
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: April 12, 2026

--Drop tables to start on a clean slate
DROP TABLE IF EXISTS crew;
DROP TABLE IF EXISTS credential;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS charter;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS aircraft;
DROP TABLE IF EXISTS model;


--=================Create tables=================--
CREATE TABLE model (
    modelNum INT AUTO_INCREMENT,
    hrlyWaitingCharge INT,
    chargePerMile INT,
    PRIMARY KEY(modelNum)
) ENGINE=InnoDB;

CREATE TABLE aircraft (
    modelNum INT,
    aircraftNum INT AUTO_INCREMENT,
    autoPilotAvailability BOOLEAN,
    dateOfFirstLaunch DATE,
    yearsInService INT, --Derived attribute
    PRIMARY KEY (modelNum, aircraftNum),
    FOREIGN KEY (modelNum) REFERENCES model(modelNum)
) ENGINE=InnoDB;

CREATE TABLE customer (
    customerId INT AUTO_INCREMENT,
    name VARCHAR(50),
    creditLimit DECIMAL(10, 2),
    methodOfPay VARCHAR(10),
    addressNbr INT,
    street VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    PRIMARY KEY(customerId)
) ENGINE=InnoDB;

CREATE TABLE charter (
    charterId INT AUTO_INCREMENT,
    customerId INT,
    modelNum INT,
    aircraftNum INT,
    fuelUsage INT,
    costOfFuel DECIMAL(10, 2),
    waitingTime INT,
    distance INT,
    intermediateDestination VARCHAR(50),
    intermediateOrigin VARCHAR(50),
    PRIMARY KEY(charterId),
    FOREIGN KEY(modelNum, aircraftNum) REFERENCES aircraft(modelNum, aircraftNum),
    FOREIGN KEY(customerId) REFERENCES customer(customerId)
) ENGINE=InnoDB;

CREATE TABLE employee (
    empNum INT AUTO_INCREMENT,
    name VARCHAR(50),
    location VARCHAR(50),
    phoneNumber INT,
    PRIMARY KEY(empNum)
) ENGINE=InnoDB;

CREATE TABLE credential (
    credentialId INT AUTO_INCREMENT,
    credential_description VARCHAR(200),
    PRIMARY KEY(credentialId)
) ENGINE=InnoDB;

CREATE TABLE crew (
    charterId INT,
    empNum INT,
    credentialId INT,
    role VARCHAR(50),
    hrlyCharge DECIMAL(10, 2),
    startDate DATE,
    endDate DATE,
    PRIMARY KEY(charterId, empNum, credentialId),
    FOREIGN KEY(charterId) REFERENCES charter,
    FOREIGN KEY(empNum) REFERENCES employee,
    FOREIGN KEY(credentialId) REFERENCES credential(credentialId)
) ENGINE=InnoDB;


--=================Populate tables=================--
--Was careful to not insert values that are AUTO_INCREMENT

INSERT INTO model (hrlyWaitingCharge, chargePerMile) VALUES
    (100, 5),
    (150, 7),
    (200, 10);

INSERT INTO aircraft (modelNum, autoPilotAvailability, dateOfFirstLaunch, yearsInService) VALUES
    (1, TRUE,  '2015-01-10', NULL),
    (1, FALSE, '2016-03-15', NULL),
    (2, TRUE,  '2018-06-20', NULL);

INSERT INTO customer (name, creditLimit, methodOfPay, addressNbr, street, city, province) VALUES
    ('Lobsang Nyima', 5000.00, 'CASH', 12, 'Main St', 'Calgary', 'AB'),
    ('Jett Bachmann', 8000.00, 'CARD', 45, 'West St', 'Edmonton', 'AB'),
    ('Mark Lee', 10000.00, 'CARD', 88, 'River Rd', 'Toronto', 'ON');

INSERT INTO charter (customerId, modelNum, aircraftNum, fuelUsage, costOfFuel, waitingTime, distance, intermediateDestination, intermediateOrigin) VALUES
    (1, 1, 1, 50, 200.00, 2, 300, 'Vancouver', 'Calgary'),
    (2, 1, 2, 40, 180.00, 1, 200, 'Edmonton', 'Calgary'),
    (3, 2, 3, 60, 250.00, 3, 400, 'Montreal', 'Toronto');

INSERT INTO employee (name, location, phoneNumber) VALUES
    ('Beto Duque', 'Calgary', 4031111111),
    ('Micro Soft', 'Edmonton', 4032222222),
    ('Bill Gates', 'Calgary', 4033333333);

INSERT INTO credential (credential_description) VALUES
    ('Worlds best pilot.'),
    ('Great violin player.'),
    ('Owns an airfryer.');

INSERT INTO crew (charterId, empNum, credentialId, role, hrlyCharge, startDate, endDate) VALUES
    (1, 1, 1, 'Captain', 120.00, '2024-01-01', '2024-01-10'),
    (2, 2, 2, 'First Officer', 90.00, '2024-02-01', '2024-02-10'),
    (3, 3, 3, 'Captain', 130.00, '2024-03-01', '2024-03-10');



-- Assignment 3 Step 4
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: April 12, 2026

--=================Part 1. UPDATE statement=================--
UPDATE aircraft
SET yearsInService = TIMESTAMPDIFF(YEAR, dateOfFirstLaunch, CURDATE());


--=================Part 2. Procedures and Functions=================--
DROP FUNCTION IF EXISTS getAge;

DROP PROCEDURE IF EXISTS ADD_AIRCRAFT;
DROP PROCEDURE IF EXISTS MOD_ACYEARSERV;
DROP PROCEDURE IF EXISTS ADD_CREW;



delimiter $$
CREATE FUNCTION getAge(launchDt DATE)
    RETURNS INT
BEGIN
    RETURN TIMESTAMPDIFF(YEAR, launchDt, CURDATE());
END$$
delimiter ;



delimiter $$
CREATE PROCEDURE ADD_AIRCRAFT(
    IN p_modelNbr INT,
    IN p_aircraftNbr INT,
    IN p_launchDt DATE
)
BEGIN
    DECLARE model_exists INT;

    --Check if model already exists
    SELECT COUNT(*) INTO model_exists
    FROM model
    WHERE modelNum = p_modelNbr;

    IF model_exists > 0 THEN
        SELECT "ERROR: Model already exists." AS error_message;
    ELSE
        INSERT INTO model(modelNum) VALUES
            (p_modelNbr); --NOTE: hrlyWaitingCharge and chargePerMile will be NULL.

        INSERT INTO aircraft(modelNum, aircraftNum, dateOfFirstLaunch) VALUES
            (p_modelNbr, p_aircraftNbr, p_launchDt); --NOTE: autoPilotAvailability will be NULL.
    END IF;
END$$
delimiter ;



delimiter $$
CREATE PROCEDURE MOD_ACYEARSERV(
    IN p_modelNbr INT,
    IN p_aircraftNbr INT,
    IN p_newLaunchDt DATE
)
BEGIN
    IF p_newLaunchDt IS NULL THEN
        SELECT "ERROR: newLaunchDt cannot be NULL." as error_message;
    ELSE
        UPDATE aircraft
        SET dateOfFirstLaunch = p_newLaunchDt
        WHERE modelNum = p_modelNbr AND aircraftNum = p_aircraftNbr;
    END IF;
END$$
delimiter ;



delimiter $$
CREATE PROCEDURE ADD_CREW(
    IN p_empNbr INT,
    IN p_charterNbr INT,
    IN p_cred_desc VARCHAR(200),
    IN p_role VARCHAR(50),
    IN p_hrlyRate DECIMAL(10, 2)
)
BEGIN
    DECLARE emp_exists INT;
    DECLARE charter_exists INT;
    DECLARE cred_exists INT;

    DECLARE v_credentialId INT;

    --Verify that employee exists
    SELECT COUNT(*) INTO emp_exists
    FROM employee
    WHERE empNum = p_empNbr;

    --Verify that charter exists
    SELECT COUNT(*) INTO charter_exists
    FROM charter
    WHERE charterId = p_charterNbr;

    --Verify that credential exists
    SELECT COUNT(*) INTO cred_exists
    FROM credential
    WHERE credential_description = p_cred_desc;


    IF emp_exists = 0 THEN
        SELECT "ERROR: Employee does not exist." as error_message;
    ELSEIF charter_exists = 0 THEN
        SELECT "ERROR: Charter does not exist." as error_message;
    ELSEIF cred_exists = 0 THEN
        SELECT "ERROR: Credential description does not exist." as error_message;
    ELSE
        SELECT credentialId INTO v_credentialId
        FROM credential
        WHERE credential_description = p_cred_desc;

        INSERT INTO crew (charterId, empNum, credentialId, role, hrlyCharge) VALUES
        (p_charterNbr, p_empNbr, v_credentialId, p_role, p_hrlyRate); --NOTE: startDate and endDate will be NULL.
    END IF;
END$$
delimiter ;


--=================Part 3a. yearsInService Triggers=================--
DROP TRIGGER IF EXISTS aircraft_yearsInService_BIR;
DROP TRIGGER IF EXISTS aircraft_yearsInService_BUR;

delimiter $$
CREATE TRIGGER aircraft_yearsInService_BIR
BEFORE INSERT
ON aircraft
FOR EACH ROW

BEGIN
    SET NEW.yearsInService = getAge(NEW.dateOfFirstLaunch);
END$$
delimiter ;



delimiter $$
CREATE TRIGGER aircraft_yearsInService_BUR
BEFORE UPDATE
ON aircraft
FOR EACH ROW

BEGIN
    SET NEW.yearsInService = getAge(NEW.dateOfFirstLaunch);
END$$
delimiter ;

--=================Part 3b. crew_change_audit=================--
CREATE TABLE crew_change_audit (
    charterId INT,
    empNum INT,
    old_rate DECIMAL(10, 2),
    new_rate DECIMAL(10, 2)
) ENGINE=InnoDb;

delimiter $$
CREATE TRIGGER audit_hrlyCharge_AUR
AFTER UPDATE
ON crew
FOR EACH ROW

BEGIN
    IF OLD.hrlyCharge != NEW.hrlyCharge THEN
        INSERT INTO crew_change_audit VALUES
            (OLD.charterId, OLD.empNum, OLD.hrlyCharge, NEW.hrlyCharge);
    END IF;
END$$
delimiter ;



--=================Part 4.=================--
-- Test update
UPDATE aircraft
SET dateOfFirstLaunch = '2018-01-01'
WHERE aircraftNum = 1;

-- Test add aircraft
CALL ADD_AIRCRAFT(999, 1, '2020-01-01');

-- Test modify aircraft
CALL MOD_AC_YEARSERV(999, 1, '2022-01-01');

-- Test add crew
CALL ADD_CREW(1, 1, 'Worlds best pilot.', 'Captain', 150.00);

-- Test audit trigger
UPDATE crew
SET hrlyCharge = 200
WHERE empNum = 1 AND charterId = 1;

SELECT * FROM crew_change_audit;