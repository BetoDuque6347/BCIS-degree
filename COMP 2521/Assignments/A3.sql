-- Assignment 3 Step 3
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: April 12, 2026

DROP TABLE IF EXISTS credential;
DROP TABLE IF EXISTS crew;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS charter;
DROP TABLE IF EXISTS aircraft;
DROP TABLE IF EXISTS model;

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
    yearsInService INT,
    PRIMARY KEY (modelNum, aircraftNum),
    FOREIGN KEY (modelNum) REFERENCES model
) ENGINE=InnoDB;

CREATE TABLE charter (
    charterId INT AUTO_INCREMENT,
    modelNum INT,
    aircraftNum INT,
    fuelUseage INT,
    costOfFuel DECIMAL,
    waitingTime INT,
    distance INT,
    intermediateDestination VARCHAR(50),
    intermediateOrigin VARCHAR(50),
    PRIMARY KEY(charterId),
    FOREIGN KEY(modelNum, aircraftNum) REFERENCES aircraft(modelNum, aircraftNum),
) ENGINE=InnoDB;

CREATE TABLE customer (
    customerId INT AUTO_INCREMENT,
    name VARCHAR(50),
    creditLimit DECIMAL,
    methodOfPay VARCHAR(10),
    addressNbr INT,
    street VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    PRIMARY KEY(customerId),
    FOREIGN KEY(charterId) REFERENCES charter
) ENGINE=InnoDB;

CREATE TABLE employee (
    empNum INT AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY(empNum)
) ENGINE=InnoDB;

CREATE TABLE crew (
    charterId INT,
    empNum INT,
    role VARCHAR(50),
    hrlyCharge DECIMAL,
    startDate DATE,
    endDate DATE,
    PRIMARY KEY(charterId, empNum),
    FOREIGN KEY(charterId) REFERENCES charter,
    FOREIGN KEY(empNum) REFERENCES employee
) ENGINE=InnoDB;

CREATE TABLE credential (
    empNum INT,
    certificationDate DATE,
    dateOfExpiry DATE,
    PRIMARY KEY(empNum),
    FOREIGN KEY(empNum) REFERENCES employee
) ENGINE=InnoDB;

--TODO: populate tables
INSERT INTO model (hrlyWaitingCharge, chargePerMile) VALUES
    (100, 5),
    (150, 7),
    (200, 10);

INSERT INTO aircraft (modelNum, autoPilotAvailability, dateOfFirstLaunch, yearsInService) VALUES
    (1, TRUE, '2015-06-01', '2015-06-01'),
    (2, FALSE, '2018-03-15', '2018-03-15'),
    (3, TRUE, '2020-09-20', '2020-09-20');

INSERT INTO charter (modelNum, aircraftNum, fuelUseage, costOfFuel, waitingTime, distance, intermediateDestination, intermediateOrigin) VALUES
    (1, 1, 500, 1200.50, 2, 300, 'Edmonton', 'Calgary'),
    (2, 1, 600, 1500.75, 3, 450, 'Vancouver', 'Calgary'),
    (3, 1, 700, 1800.00, 1, 600, 'Toronto', 'Montreal');

INSERT INTO customer (charterId, name, creditLimit, methodOfPay, addressNbr, street, city, province) VALUES
    (1, 'Lobsang Nyima', 5000.00, 'Card', 123, 'Main St', 'Calgary', 'AB'),
    (2, 'Jett Bachmann', 7500.00, 'Cash', 456, 'Oak Ave', 'Edmonton', 'AB'),
    (3, 'Mike Brown', 6000.00, 'Card', 789, 'Pine Rd', 'Vancouver', 'BC');

INSERT INTO employee (name) VALUES
    ('Beto Duque'),
    ('Bob Williams'),
    ('Charlie Davis');

INSERT INTO crew (charterId, empNum, role, hrlyCharge, startDate, endDate) VALUES
    (1, 1, 'Pilot', 120.00, '2024-01-01', '2024-01-02'),
    (2, 2, 'Co-Pilot', 90.00, '2024-02-01', '2024-02-02'),
    (3, 3, 'Attendant', 60.00, '2024-03-01', '2024-03-02');

INSERT INTO credential (empNum, certificationDate, dateOfExpiry) VALUES
    (1, '2020-01-01', '2025-01-01'),
    (2, '2021-02-01', '2026-02-01'),
    (3, '2022-03-01', '2027-03-01');

-- Assignment 3 Step 4
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: April 12, 2026
UPDATE TABLE aircraft;
SET yearsInService = GETDATE() - yearsInService