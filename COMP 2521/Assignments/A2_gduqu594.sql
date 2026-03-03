--COMP 2521 - Assignment 2 - March 02 2026

--Queries 1 - 6 (Eliezer)

-- 1) ELiezer
SELECT grade_type.DESCRIPTION, grade.GRADE_TYPE_CODE
FROM university.grade grade
JOIN university.grade_type grade_type USING(GRADE_TYPE_CODE)
GROUP BY grade_type.DESCRIPTION;

--2) Eliezer 
SELECT s.SECTION_ID, g.GRADE_TYPE_CODE
FROM university.section s
JOIN university.grade_type_weight g USING(SECTION_ID)
WHERE s.COURSE_NO = 130;

--3) Eliezer
SELECT c.DESCRIPTION, s.CAPACITY  AS TOTAL_CAPACITY
FROM university.course c
JOIN university.section s USING(COURSE_NO)
WHERE c.DESCRIPTION LIKE "%Ad%"
AND c.DESCRIPTION NOT LIKE "Advanced%"
GROUP BY c.DESCRIPTION, s.CAPACITY;

-- 4 a) Eliezer
SELECT c.COURSE_NO, c.DESCRIPTION, c.PREREQUISITE, c.COST
FROM university.course c
WHERE c.DESCRIPTION LIKE "Intro to%" AND (c.COST < 1100 OR c.COST IS NULL);

-- 4 b) Eliezer
SELECT COURSE_NO, DESCRIPTION, PREREQUISITE, COST
FROM university.course
WHERE COURSE_NO IN ( -- Same as multiple OR conditions, Filtering based on another tables results. 
    SELECT COURSE_NO
    FROM university.course
    WHERE DESCRIPTION LIKE 'Intro to%'
      AND (COST < 1100 OR COST IS NULL)
);

--5) Eliezer
SELECT c.COURSE_NO, c.DESCRIPTION, c.COST, c.PREREQUISITE
FROM university.course c
WHERE c.COST = 1195 
AND (c.PREREQUISITE = 20 OR c.PREREQUISITE = 25);

--6 Eliezer
SELECT e.STUDENT_ID 
FROM university.enrollment e
GROUP BY e.STUDENT_ID
HAVING COUNT(e.SECTION_ID) >= 4;

-- 6 (proof) Eliezer

-- This query displays the 4 courses from the DESCRIPTION header that each student is enrolled in. Showing what 4 >= classes they are enrolled in. 
SELECT e.STUDENT_ID, c.DESCRIPTION
FROM university.enrollment e
JOIN university.section s USING (SECTION_ID)
JOIN university.course c USING (COURSE_NO)
WHERE e.STUDENT_ID IN ( -- In used to check specifified column based on list 
    SELECT STUDENT_ID
    FROM university.enrollment
    GROUP BY STUDENT_ID
    HAVING COUNT(SECTION_ID) >= 4
);


--Queries 7 - 12 (Muji)

--7.
SELECT s.employer AS company,
    COUNT(*) AS student_count
FROM university.student AS s
WHERE s.employer IS NOT NULL
    AND s.employer <> ''
GROUP BY s.employer
HAVING COUNT(*) = (
        SELECT MAX(cnt)
FROM (
                SELECT COUNT(*) AS cnt
    FROM university.student
    WHERE employer IS NOT NULL
        AND employer <> ''
    GROUP BY employer
        ) AS x
);
 
--8.
SELECT COUNT(DISTINCT s.instructor_id) AS total_instructors_teaching
FROM university.section AS s
WHERE s.instructor_id IS NOT NULL;

--9. 
SELECT e.section_id,
    MAX(e.enroll_date) AS most_recent_enrollment_date
FROM university.enrollment AS e
GROUP BY e.section_id;

--10.
SELECT DISTINCT i.first_name,
    i.last_name,
    c.description AS course_description
FROM university.enrollment AS e
    JOIN university.section AS s
    ON s.section_id = e.section_id
    JOIN university.instructor AS i
    ON i.instructor_id = s.instructor_id
    JOIN university.course AS c
    ON c.course_no = s.course_no
WHERE e.final_grade IS NOT NULL;

--11.
SELECT s.section_id,
    c.course_no,
    c.description AS course_description
FROM university.section AS s
    JOIN university.course AS c
    ON c.course_no = s.course_no
WHERE c.prerequisite IS NULL;

--12.
SELECT DISTINCT c.description AS course_description,
    i.last_name AS instructor_last_name
FROM university.course AS c
    JOIN university.section AS s
    ON s.course_no = c.course_no
    JOIN university.instructor AS i
    ON i.instructor_id = s.instructor_id
WHERE c.prerequisite = 350;


--Queries 13 - 19 (Beto)
--Query 13:
SELECT s.first_name, s.last_name, g.section_id, gt.description AS grade_type, g.numeric_grade
FROM university.student s
JOIN university.grade g ON s.student_id = g.student_id
JOIN university.grade_type gt ON g.grade_type_code = gt.grade_type_code
WHERE s.first_name = 'Larry' AND s.last_name = 'Walter'
AND gt.description IN ('Homework', 'Quiz');


--Query 14:
SELECT s.first_name, s.last_name, g.numeric_grade
FROM university.student s
JOIN university.zipcode z ON s.zip = z.zip
JOIN university.enrollment e ON s.student_id = e.student_id
JOIN university.section sec ON e.section_id = sec.section_id
JOIN university.grade g ON g.student_id = s.student_id
    AND g.section_id = sec.section_id
WHERE z.state = 'NJ'
    AND sec.course_no = 350
    AND g.grade_type_code = "FI";


--Query 15:
SELECT c.description, sec.section_id, MIN(g.numeric_grade) AS lowest_final_exam
FROM university.grade g
JOIN university.section sec ON g.section_id = sec.section_id
JOIN university.course c ON sec.course_no = c.course_no
WHERE g.grade_type_code = 'FI'
GROUP BY c.description, sec.section_id
ORDER BY lowest_final_exam DESC;


--Query 16:
SELECT e.student_id, sec.course_no, COUNT(*) AS times_enrolled
FROM university.enrollment e
JOIN university.section sec ON e.section_id = sec.section_id
GROUP BY e.student_id, sec.course_no
HAVING COUNT(*) > 1;


--Query 17:
SELECT prerequisite AS prerequisite_course, COUNT(*) AS number_of_courses
FROM university.course
WHERE prerequisite IS NOT NULL
GROUP BY prerequisite;


--Query 18:
SELECT c.description, c.prerequisite, p.description AS prerequisite_description
FROM university.course c
JOIN university.course p ON c.prerequisite = p.course_no
ORDER BY c.description DESC;


--Query 19:
SELECT location, MIN(capacity), MAX(capacity), SUM(capacity)
FROM university.section
GROUP BY location;