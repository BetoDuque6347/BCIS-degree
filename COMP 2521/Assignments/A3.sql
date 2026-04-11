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


--Create tables
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
    FOREIGN KEY (modelNum) REFERENCES model
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
    certificationDate DATE,
    dateOfExpiry DATE,
    PRIMARY KEY(credentialId),
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

--Populate tables
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

INSERT INTO credential (certificationDate, dateOfExpiry) VALUES
    ('2022-01-01', '2027-01-01'),
    ('2021-06-10', '2026-06-10'),
    ('2023-03-15', '2028-03-15');

INSERT INTO crew (charterId, empNum, credentialId, role, hrlyCharge, startDate, endDate) VALUES
    (1, 1, 1, 'Captain', 120.00, '2024-01-01', '2024-01-10'),
    (2, 2, 2, 'First Officer', 90.00, '2024-02-01', '2024-02-10'),
    (3, 3, 3, 'Captain', 130.00, '2024-03-01', '2024-03-10');



-- Assignment 3 Step 4
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: April 12, 2026
UPDATE TABLE aircraft;
SET yearsInService = GETDATE() - yearsInService

CREATE FUNCTION getAge()
