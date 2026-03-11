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

ALTER TABLE project(
    FOREIGN KEY(parent_p_id) REFERENCES(project)
)