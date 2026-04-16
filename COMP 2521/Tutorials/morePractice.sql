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