-- Lab 2
-- Author: Muji Shah
-- Instructor: Shoba Ittyipe
-- Due date: Feb 6, 2026

-- 1. Retrieve the first and last name values for all faculty who are either full
-- professors or instructors
SELECT f_first, f_last, f_rank 
FROM faculty 
WHERE f_rank = 'FULL' OR f_rank = 'INST';

-- 2. Write the above query in another way.
SELECT f_first, f_last, f_rank 
FROM faculty 
WHERE f_rank LIKE 'FULL' OR f_rank LIKE 'INST';

-- 3. Retrieve the different classes of students. No DUPLICATEs
SELECT DISTINCT course_name
FROM course;

-- 4. Retrieve the first and last names and DOB values for all students who were
-- born in 1985
SELECT s_first, s_last, s_dob
FROM student
WHERE s_dob LIKE '1985%';


-- 5. Write the above query in another way
SELECT s_first, s_last, s_dob
FROM student
WHERE s_dob BETWEEN '1985-01-01' AND '1985-12-31';

-- 6. Retrieve all students who have a middle initial
SELECT s_first, s_last, s_mi
FROM student
WHERE s_mi IS NOT NULL;

-- 7. Retreive all enrollment records of students who do NOT have a grade yet
SELECT s_id, c_sec_id, grade
FROM enrollment
WHERE grade IS NULL;

-- 8. Retrieve the faculty IDs who are advisors. no duplciates
SELECT DISTINCT f_id
FROM student
WHERE f_id IS NOT NULL;

-- 9. Above query in another way
SELECT DISTINCT f_id
FROM student
WHERE f_id > 0;

-- 10. loc_id
SELECT DISTINCT loc_id
FROM faculty
WHERE loc_id IS NOT NULL;

-- 11. 
SELECT DISTINCT loc_id
FROM course_section
WHERE loc_id IS NOT NULL;

-- 12. 
SELECT c_sec_id
FROM course_section
WHERE c_sec_day NOT LIKE '%W%';

-- 13.
SELECT c_sec_id
FROM course_section
WHERE c_sec_day NOT LIKE '%W%'
AND c_sec_day NOT LIKE '%F%';

-- 14.
SELECT AVG(max_enrl)
FROM course_section
WHERE course_id = 1;

--15. 
SELECT COUNT(grade)
FROM enrollment
WHERE c_sec_id = 6 and grade = 'B';

-- 16.
SELECT f_id, COUNT(*)
FROM student
WHERE f_id IS NOT NULL
GROUP BY f_id;

-- 17.
SELECT *
FROM enrollment
WHERE grade = 'C' OR grade IS NULL;

-- 18.
SELECT loc_id, COUNT(*)
FROM course_section
GROUP BY loc_id
ORDER BY COUNT(*) DESC;

-- 19.
SELECT loc_id, COUNT(*)
FROM course_section
GROUP BY loc_id
HAVING COUNT(*) > 3
ORDER BY COUNT(*) DESC;