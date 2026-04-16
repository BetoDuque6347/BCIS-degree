-- Practice
-- Author: Beto Duque
-- NOTE: hope I do good on the final
-- Date: 4/15/2026

-- ================ SUB QUERIES ================ --
-- Q1.
SELECT s_id
FROM student
WHERE s_id = 1001;

-- Q2.
SELECT c_sec_id
FROM course_section
WHERE loc_id IN (
    SELECT loc_id
    FROM location
    WHERE room = 101
);

-- Q3.
SELECT s_id
FROM enrollment
WHERE c_sec_id NOT IN (
    SELECT c_sec_id
    FROM enrollment
    WHERE c_sec_id IS NOT NULL
);

-- Q4.
SELECT c_sec_id
FROM course_section
WHERE loc_id IN (
    SELECT loc_id
    FROM location
    WHERE bldg_code = 'CR'
);

-- Q5.
SELECT c_sec_id
FROM course_section
WHERE max_enrl IN (
    SELECT MAX(max_enrl)
    FROM course_section
);

-- Q6.
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

-- Q7.
SELECT s_id
FROM student
WHERE s_id NOT IN(
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN(
        SELECT c_sec_id
        FROM course_section
        WHERE f_id IN (
            SELECT f_id
            FROM faculty
            WHERE f_rank = 'Professor'
        )
    )
);

-- Q8.
SELECT s_id
FROM student
WHERE s_id IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE loc_id IN (
            SELECT loc_id
            FROM location
            WHERE capacity > 50
        )
    )
);

-- Q9.
SELECT c_sec_id, max_enrl
FROM course_section
WHERE max_enrl >= ALL (
    SELECT max_enrl
    FROM course_section
)

-- Q10.
SELECT s_id
FROM student
WHERE s_id IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE term_id = 2
    )
);

-- Second set

-- Q1.
SELECT s_id
FROM student
WHERE s_id = MIN(s_id);
GROUP BY s_id

-- Q2.
SELECT c_sec_id
FROM course_section
WHERE loc_id IN (
    SELECT loc_id
    FROM location
    WHERE bldg_code = 'CR'
);

-- Q3.
SELECT s_id
FROM students
WHERE s_id NOT IN (
    SELECT s_id
    FROM enrollment
    WHERE term_id = 1
);

-- Q4.
SELECT c_sec_id
FROM course_section
WHERE max_enrl > (
    SELECT AVG(max_enrl)
    FROM course_section
)
GROUP BY max_enrl;

-- Q5.
SELECT s_id
FROM student
WHERE s_id IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE f_id IN (
            SELECT f_id
            FROM faculty
            WHERE f_rank <> 'Assistant'
        )
    )
);

-- Q6.
SELECT s_id
FROM student
WHERE s_id NOT IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE loc_id IN (
            SELECT loc_id
            FROM location
            WHERE capacity >= 100
        )
    )
);

-- Q7.
SELECT s_id
FROM student
WHERE s_id IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE f_id = (
            SELECT f_id
            FROM course_id
            WHERE c_sec_id = 5;
        )
    )
);

-- ================ UNIONS ================ --

-- Q1.
SELECT s_id AS person_id
FROM student

UNION

SELECT f_id
FROM faculty;

-- Q2.
SELECT s_city
FROM student

UNION

SELECT loc_id
FROM faculty;

-- Q3.
SELECT UNIQUE loc_id
FROM faculty

UNION

SELECT UNIQUE loc_id
FROM course_section;

-- Q4.
SELECT s_first AS first_name
FROM student

UNION

SELECT f_first
FROM faculty;

-- Q5. (ChatGPT hallucinated an extra column lol)

-- Q6.
SELECT f_id AS id
FROM faculty

UNION

SELECT s_id AS id
FROM student

UNION

SELECT term_id AS id
FROM term 

UNION

SELECT course_id AS id
FROM course

UNION

SELECT c_sec_id AS id
FROM course_section

UNION

SELECT loc_id AS id
FROM location;

-- Q7.
SELECT s_id, 'Student' AS type
FROM enrollment -- All students are currently enrolled in atleast one class

UNION

SELECT f_id, 'Faculty' AS type
FROM faculty
WHERE f_id IN (
    SELECT f_id
    FROM course_section
    WHERE loc_id IN (
        SELECT loc_id
        FROM location
        WHERE bldg_code = 'CR'
    )
);

-- Q8.
SELECT s_last AS last_name
FROM student

UNION

SELECT f_last AS last_name
FROM faculty;

-- Q9.
SELECT s_id, 'Student' as source
FROM student
WHERE s_id IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE term_id = 1
    )
)

UNION

SELECT f_id, 'Faculty' as source
FROM faculty
WHERE f_id NOT IN (
    SELECT f_id
    FROM course_section
);

-- Q10.

SELECT s_id AS 'Id', 'STUDENT' AS 'Type'
FROM student
WHERE s_id IN (
    SELECT s_id
    FROM enrollment
    WHERE c_sec_id IN (
        SELECT c_sec_id
        FROM course_section
        WHERE loc_id IN (
            SELECT loc_id
            FROM location
            WHERE capacity > 50
        )
    )
)

UNION

SELECT f_id AS 'Id', 'FACULTY' AS 'Type'
FROM faculty
WHERE f_id IN (
    SELECT f_id
    FROM course_section
    WHERE term_id = 2
);