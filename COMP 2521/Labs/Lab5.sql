--Lab 5
--Author: Beto Duque
--Instructor: Shoba Ittyipe
--Due date: March 16, 2026


--DDL
DROP TABLE IF EXISTS project_consultant;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS consultant;

CREATE TABLE consultant (
    c_id INT AUTO_INCREMENT,
    c_last VARCHAR(20) NOT NULL,
    c_first VARCHAR(20) NOT NULL,
    c_dob DATE NOT NULL,
    c_email VARCHAR(30) UNIQUE,
    PRIMARY KEY (c_id)
) ENGINE=InnoDB;

CREATE TABLE project (
    p_id INT AUTO_INCREMENT,
    p_desc VARCHAR(50) NOT NULL UNIQUE,
    parent_p_id INT,
    mgr_id INT,
    PRIMARY KEY (p_id),
    FOREIGN KEY (mgr_id) REFERENCES consultant(c_id)
) ENGINE=InnoDB;

CREATE TABLE project_consultant (
    p_id INT,
    c_id INT,
    roll_on_date DATE,
    roll_off_date DATE,
    PRIMARY KEY (p_id, c_id),
    FOREIGN KEY (p_id) REFERENCES project(p_id),
    FOREIGN KEY (c_id) REFERENCES consultant(c_id)
) ENGINE=InnoDB;


ALTER TABLE project
ADD FOREIGN KEY (parent_p_id)
REFERENCES project(p_id);


--DML
INSERT INTO consultant (c_last, c_first, c_dob, c_email) VALUES
('Myers','Mark','1968-05-05','mmyers@swexpert.com'),
('Hernandez','Sheila','1971-10-08','shernandez@earthware.com'),
('Zhang','Brian','1968-08-08','zhang@swexpert.com'),
('Carlson','Sarah','1981-12-14','carlsons@swexpert.com'),
('Courtlandt','Paul','1978-01-21','courtlpr@yamail.com'),
('Park','Janet','1986-03-23','jpark@swexpert.com');


INSERT INTO project (p_desc, parent_p_id, mgr_id) VALUES
('Hardware Support Intranet', NULL, 1),
('Hardware Support Interface', 1, 1),
('Hardware Support Database', 2, 1),
('Teller Support System', NULL, 6),
('Internet Advertising', 1, 1),
('Network Design', 1, 1),
('Exploration Database', NULL, NULL);


INSERT INTO project_consultant (p_id, c_id, roll_on_date, roll_off_date) VALUES
(1,2,'2026-01-01','2026-06-30'),
(2,3,'2026-02-01','2026-08-31'),
(4,5,'2026-03-01','2026-12-31');


-- Sarah Carlson new email
UPDATE consultant
SET c_email = 'scarlson@gmail.com'
WHERE c_first = 'Sarah'
AND c_last = 'Carlson';


-- Brian Zhang becomes manager of Exploration Database
UPDATE project
SET mgr_id =
(
    SELECT c_id
    FROM consultant
    WHERE c_first='Brian'
    AND c_last='Zhang'
)
WHERE p_desc='Exploration Database';


-- Attempt to delete Hardware Support Interface
-- (Doesnt work because Hardware Support Database references it)
DELETE FROM project
WHERE p_desc='Hardware Support Interface';


-- Delete Network Design project
DELETE FROM project
WHERE p_desc='Network Design';


-- Teller Support System no longer managed by Sarah Carlson
UPDATE project
SET mgr_id = NULL
WHERE p_desc='Teller Support System'
AND mgr_id =
(
    SELECT c_id
    FROM consultant
    WHERE c_first='Sarah'
    AND c_last='Carlson'
);


-- SELECT statement showing project description and consultant last name
-- Includes all projects even if no consultant assigned
SELECT p.p_desc, c.c_last
FROM project p
LEFT JOIN project_consultant pc
ON p.p_id = pc.p_id
LEFT JOIN consultant c
ON pc.c_id = c.c_id;


-- Create view from the SELECT
CREATE VIEW sampleView AS
SELECT p.p_desc, c.c_last
FROM project p
LEFT JOIN project_consultant pc
ON p.p_id = pc.p_id
LEFT JOIN consultant c
ON pc.c_id = c.c_id;

SELECT * FROM sampleView;