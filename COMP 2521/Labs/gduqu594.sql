--Lab 5
--Author: Beto Duque
--Instructor: Shoba Ittyipe
--Due date: March 16, 2026

DROP TABLE IF EXISTS project_consultant;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS consultant;

CREATE TABLE consultant ( 
    c_id INT AUTO_INCREMENT,
    c_last VARCHAR(20) NOT NULL,
    c_first VARCHAR(20) NOT NULL,
    c_dob date NOT NULL,
    c_email VARCHAR(30) UNIQUE
) ENGINE = InnoDB;

CREATE TABLE project (
    p_id INT,
    p_desc VARCHAR(30) UNIQUE NOT NULL,
    parent_p_id INT,
    mgr_id INT,
    PRIMARY KEY(p_id)
    FOREIGN KEY(mgr_id) REFERENCES(consultant)
) ENGINE = InnoDB;

CREATE TABLE project_consultant (
    p_id INT,
    c_id INT,
    roll_on_date DATE,
    roll_off_date DATE,
    PRIMARY KEY(p_id, c_id),
    FOREIGN KEY(p_id) REFERENCES(project),
    FOREIGN KEY(c_id) REFERENCES(consultant)
) ENGINE = InnoDB;

ALTER TABLE project (
    FOREIGN KEY(parent_p_id) REFERENCES(project)
)

INSERT INTO consultant (c_last, c_first, c_dob, c_email) VALUES (
    ("Myers", "Mark", '1968-5-5', "mmyers@swexpert.com"),
    ("Hernandez", "Sheila", '1971-10-8', "shernandez@earthware.com"),
    ("Zhang", "Brian", '1968-8-8', "zhang@swexpert.com"),
    ("Carlson", "Sarah", '1981-12-14', "carlsons@swexpert.com"),
    ("Courtlandt", "Paul", '1978-1-21', "courtlpr@yamail.com"),
    ("Park", "Janet", '1986-3-23', "jpark@swexpert.com")
);

INSERT INTO project (p_desc, parent_p_id, mgr_id) VALUES (
    "Hardware Support Intranet", NULL, 1 --Using constants here because each c_id is defined via AUTO_INCREMENT
    "Hardware Support Interface", 1, 1
    "Hardware Support Database", 1, 1
    "Teller Support System", NULL, 6
    "Internet Advertising", 1, 1
    "Network Design", 1, 1
    "Exploration Database", NULL, NULL
);

INSERT INTO project_consultant (p_id, c_id, roll_on_date, roll_off_date) VALUES (
  (2, 1, '2026-1-1', '2026-12-31'),
  (3, 1, '2026-1-1', '2026-12-31'),
  (4, 2, '2026-1-1', '2026-12-31')
);