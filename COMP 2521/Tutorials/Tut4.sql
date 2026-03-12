--Retrieve the student's last and first name of all students
--who are advised by Kim Cox.
--Write this query in two different ways.
SELECT s_last, s_first
FROM student
JOIN faculty USING (f_id)
WHERE f_last LIKE 'Cox'
AND f_first LIKE 'Kim';

--Retrieve the c_sec_id of the record which corresponds with
--the section of the MOST (MAX) value for the max_enrl column
SELECT c_sec_id
FROM course_section
WHERE max_enrl = (
    SELECT MAX(max_enrl)
    FROM course_section
);

--Retrieve all course sections (display the term id and max enrl column values)
--whose maximum enrollment is more than the average value in the max_enrl column
--of its term.
SELECT term_id, max_enrl
FROM course_section
WHERE max_enrl > (
    SELECT AVG(max_enrl)
    FROM course_section
);

--Retrieve the student last name of all students who are
--enrolled in the course 'Database Management'
SELECT s_last
FROM student
WHERE s_id IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE course_id = 3
    )
);

--multiple subqueries

--Retrieve course section time and duration and loc_id of all course sections
--of the course 'Database Management' during 'OPEN' terms.
SELECT c_sec_day, c_sec_time, loc_id
FROM course_section
WHERE term_id IN (
    SELECT term_id
    FROM term
    WHERE status LIKE 'OPEN'
)
AND course_id IN (
    SELECT c_sec_id
    FROM course_section
    WHERE course_name LIKE 'Database Management'
);

--Retrieve the grade adn the term id values for the student Sarah Miller's
--System Analysis course. Write this using at least one subquery.
SELECT e.enrollment, t.term_id
