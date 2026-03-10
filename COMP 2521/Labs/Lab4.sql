-- Lab 4
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: Mar 9, 2026




--OUTER JOINS

--1. Retrieve the locations to show the number of course sections scheduled to run in each of those
--locations. Sort the result by location ID. Include also locations that do not have a course section
--scheduled in them yet.
SELECT l.loc_id, l.room, COUNT(c_sec.loc_id) AS section_amount
FROM location l
LEFT JOIN course_section c_sec ON l.loc_id = c_sec.loc_id
GROUP BY l.loc_id
ORDER BY l.loc_id;

--2. Write an INSERT statement (as shown in class; you may also get help from the script file that
--populated the tables) to add a course section that has not been scheduled in a location yet. i.e, with
--loc_id is null.
INSERT INTO course_section
VALUES(14, 1, 4, 6, NULL, 'MWF', '0000-00-00', '1800', NULL, 140);


--2a. Now, retrieve the faculty's last name, bldg code, room, and the course section ID of all course sections
--the faculty member is assigned to teach. Include all course sections regardless of whether or not they
--have been scheduled in a location yet.
--The result should basically include the row you added above.
SELECT f.f_last, l.bldg_code, l.room, cs.c_sec_id
FROM course_section cs
LEFT JOIN location l ON cs.loc_id = l.loc_id
LEFT JOIN faculty f ON cs.f_id = f.f_id;

--You can delete the row by the DELETE statement.
DELETE FROM course_section
WHERE c_sec_id = 14;

--3. Write another insert statement to add a course section that does not have a faculty member assigned
--to it yet, i.e., with f_id is null.
INSERT INTO course_section VALUES (15, 1, 4, 1, 1, 'MWF', '0000-00-00', '1800', NULL, 140);

--3a. Now, retrieve the faculty's last name, bldg code, room, and the course section ID of all course sections,
--regardless of whether or not they have been assigned a faculty member yet OR whether or not they
--have been scheduled in a location yet.
SELECT f.f_last, l.bldg_code, l.room, cs.c_sec_id
FROM course_section cs
LEFT JOIN faculty f ON cs.f_id = f.f_id
LEFT JOIN location l ON cs.loc_id = l.loc_id;

--4. Retrieve the course sections and display the number of students enrolled in each of them. Include also
--those course sections that do not have students enrolled in them.
SELECT cs.c_sec_id, COUNT(e.s_id) AS num_students
FROM course_section cs
LEFT JOIN enrollment e ON cs.c_sec_id = e.c_sec_id
GROUP BY cs.c_sec_id;

--5. Retrieve the term description along with the number of course sections scheduled for that term.
SELECT t.term_desc, COUNT(cs.c_sec_id) AS num_sections
FROM term t
JOIN course_section cs ON t.term_id = cs.term_id
GROUP BY t.term_desc;

--5a. Modify the query to include also those terms that do not have a course section scheduled in
--them.
SELECT t.term_desc, COUNT(cs.c_sec_id) AS num_sections
FROM term t
LEFT JOIN course_section cs ON t.term_id = cs.term_id
GROUP BY t.term_desc;

--6. Rewrite the above query in another way (Note: this is not necessarily a different way!).
SELECT t.term_desc, COUNT(cs.c_sec_id) AS num_sections
FROM course_section cs
RIGHT JOIN term t
ON cs.term_id = t.term_id
GROUP BY t.term_desc;

--7. Retrieve the term ID, term description, and the total maximum enrollment of all course sections
--scheduled in that term.
SELECT t.term_id, t.term_desc, SUM(cs.max_enrl) AS total_max
FROM term t
JOIN course_section cs ON t.term_id = cs.term_id
GROUP BY t.term_id, t.term_desc;

--8. Modify the above query to include also those terms that do not have any course sections scheduled in
--it. Show the total as null for those terms that do not have course sections scheduled in them.
SELECT t.term_id, t.term_desc, SUM(cs.max_enrl) AS total_max
FROM term t
LEFT JOIN course_section cs ON t.term_id = cs.term_id
GROUP BY t.term_id, t.term_desc;

--Gonna delete the extra row I made in query 3.
DELETE FROM course_section
WHERE c_sec_id = 15;

--SUB-QUERIES

--Write the following queries using a subquery. When the question says, write it a different
--way, you may write it as a join.

--1. Retrieve the last name of all faculty members who teach during the “Summer 2007” term.
SELECT f_last
FROM faculty
WHERE f_id IN
(
    SELECT f_id
    FROM course_section
    WHERE term_id =
    (
        SELECT term_id
        FROM term
        WHERE term_desc = 'Summer 2007'
    )
);

--2. Retrieve the course section’s day and location of the course ‘Database Management’ during ‘open’
--terms.
SELECT cs.day, cs.loc_id
FROM course_section cs
WHERE cs.course_id =
(
    SELECT course_id
    FROM course
    WHERE course_name = 'Database Management'
)
AND cs.term_id IN
(
    SELECT term_id
    FROM term
    WHERE status = 'OPEN'
);

--3. Retrieve those faculty IDs who have the same name (first, last) as a student.
SELECT f_id
FROM faculty f
WHERE (f.f_first, f.f_last) IN
(
    SELECT s_first, s_last
    FROM student
);

--4. Retrieve the course section ID and the value of the max_enrl column for all sections that
--correspond to the highest value for max_enrl.
SELECT c_sec_id, max_enrl
FROM course_section
WHERE max_enrl =
(
    SELECT MAX(max_enrl)
    FROM course_section
);

--5. Retrieve the course section ID and the value of the max_enrl column for all sections that do NOT
--correspond with the course section that has the highest value for max_enrl.
SELECT c_sec_id, max_enrl
FROM course_section
WHERE max_enrl <
(
    SELECT MAX(max_enrl)
    FROM course_section
);


--6. Modify the above query to show those course sections below the average value for max_enrl.
SELECT c_sec_id, max_enrl
FROM course_section
WHERE max_enrl <
(
    SELECT AVG(max_enrl)
    FROM course_section
);

--7. An interesting one ☺ Retrieve the grade and term ID values for the student Sarah Miller’s System
--Analysis course.
SELECT e.grade, cs.term_id
FROM enrollment e
JOIN course_section cs
ON e.c_sec_id = cs.c_sec_id
WHERE e.s_id =
(
    SELECT s_id
    FROM student
    WHERE s_first = 'Sarah'
    AND s_last = 'Miller'
)
AND cs.course_id =
(
    SELECT course_id
    FROM course
    WHERE course_name = 'Systems Analysis'
);

--8. Retrieve the course name and the course ID of all courses that are prerequisites for other courses.
SELECT course_name, course_id
FROM course
WHERE course_id IN
(
    SELECT prereq_id
    FROM prerequisite
);

--9. Retrieve the term description of those terms that do not have a course section scheduled in them.
SELECT term_desc
FROM term
WHERE term_id NOT IN
(
    SELECT term_id
    FROM course_section
);

--10. Another interesting one ☺ write the above query a different way!
SELECT t.term_desc
FROM term t
LEFT JOIN course_section cs
ON t.term_id = cs.term_id
WHERE cs.term_id IS NULL;



--UNION

--11. Retrieve the last and first names of all students and faculty. Add a comment to indicate which
--entity they belong to. If faculty, say “FACULTY”, if student, say “STUDENT”. Order the results by last
--name.
SELECT s_last, s_first, 'STUDENT' AS type
FROM student
UNION
SELECT f_last, f_first, 'FACULTY'
FROM faculty
ORDER BY s_last;