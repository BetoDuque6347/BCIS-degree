-- Assignment 3 Step 3
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: April 12, 2026

DROP TABLE IF EXISTS credential;
DROP TABLE IF EXISTS crew;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS charterRoute;
DROP TABLE IF EXISTS charter;
DROP TABLE IF EXISTS customer;
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
    yearsInService DATE,
    PRIMARY KEY (modelNum, aircraftNum),
    FOREIGN KEY (modelNum) REFERENCES model
) ENGINE=InnoDB;

CREATE TABLE customer (
    customerId INT AUTO_INCREMENT,
    charterId INT,
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
    FOREIGN KEY(modelNum) REFERENCES aircraft,
    FOREIGN KEY(aircraftNum) REFERENCES aircraft
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

-- Assignment 3 Step 4
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: April 12, 2026
UPDATE TABLE aircraft;
SET yearsInService = GETDATE() - yearsInService