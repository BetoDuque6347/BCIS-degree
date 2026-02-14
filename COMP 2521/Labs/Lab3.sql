-- Lab 3
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: Feb 13, 2026

-- 1. Retrieve the student's last names and the last name of their advisor. Make appropriate use of aliases.
SELECT s.s_last, f.f_last
FROM student AS s, faculty AS f
WHERE s.f_id = f.f_id;

-- 2. Retrieve the advisor name along with the number of students they are advising.
SELECT f.f_last, f.f_first, COUNT(s.s_id) AS student_count
FROM faculty AS f, student AS s
WHERE s.f_id = f.f_id
GROUP BY f.f_last, f.f_first;


-- 3. Retrieve the last name of facutly members who are scheduled to teach course sections.
--    Show the total maximum enrollment per faculty member.
SELECT f.f_last, SUM(c_sec.max_enrl) AS max_enrollment
FROM faculty AS f, course_section AS c_sec
WHERE f.f_id = c_sec.f_id
GROUP BY f.f_last;


-- 4. Retrieve the course ID and course name of all sections that are scheduled in the course section table
--    and their corresponding maximum enrollment.
--    a) Write the above query using the JOIN keyword with an ON clause.
SELECT c.course_id, c.course_name, c_sec.max_enrl
FROM course AS c
JOIN course_section AS c_sec
ON c.course_id = c_sec.course_id;


--    b) Write the above query using the JOIN keyword with the USING clause.
SELECT c.course_id, c.course_name, c_sec.max_enrl
FROM course AS c
JOIN course_section AS c_sec
USING (course_id);


-- 5. Now, modify the above query to retrieve the total maximum enrollment per couse. For example, "Web-Based Systems"
--    course has 2 sections of 35 students each which makes its total maximum enrollment to 70.
--    Note that the "not a GROUP BY expression" error occurs when you try to display a column that has not been grouped.
SELECT c.course_id, c.course_name, SUM(c_sec.max_enrl) AS total_max_enrollment
FROM course AS c
JOIN course_section AS c_sec
USING (course_id)
GROUP BY c.course_id, c.course_name;


-- 6. Now, modify the above query to retrieve only those courses that have their total maximum enrollment greater than 200.
SELECT c.course_id, c.course_name, SUM(c_sec.max_enrl) AS total_max_enrollment
FROM course AS c
JOIN course_section AS c_sec
USING (course_id)
GROUP BY c.course_id, c.course_name
HAVING SUM(c_sec.max_enrl) > 200;


-- 7. Retrieve the location Id, building code and room of locations that have been scheduled for a course section.
--    Show the nmber of course sections taking palce in each of those locations. Sort the result with location ID.
--    Learn about the ORDER BY clause.
SELECT l.loc_id, l.bldg_code, l.room, COUNT(c_sec.c_sec_id) AS number_of_sections
FROM location AS l, course_section AS c_sec
WHERE l.loc_id = c_sec.loc_id
GROUP BY l.loc_id, l.bldg_code, l.room
ORDER BY l.loc_id;


-- 8. Retrieve the course name, term description, faculy last name and room of course sections that have been scheduled.
--    Write an insert statement to add a acourse section that has not been assigned to a location yet. 
--    In other words, the loc_id for that record is NULL. Look up on mySQL documentation for the INSERT statement.
SELECT c.course_name, t.term_desc, f.f_last, l.room
FROM course_section AS c_sec
JOIN course AS c ON c_sec.course_id = c.course_id
JOIN term AS t ON c_sec.term_id = t.term_id
JOIN faculty AS f ON c_sec.f_id = f.f_id
JOIN location AS l ON c_sec.loc_id = l.loc_id;


-- 9. Run the previous query. Is the new course section included in your result? Explain your answer;
--    show as a comment in your SQL file.

    -- The new section is NOT included because INNER JOIN is used.
    -- Since loc_id is NULL, it does not match a row in location.


-- 10. Retrieve the course name, and the name of the prerequisite course for the those courses that have prerequisites.
SELECT c.course_name, p_course.course_name AS prerequisite
FROM course AS p_course
JOIN course AS c ON p.course_id = c.course_id
JOIN course AS p_course ON p.prereq_id = p_course.course_id;


-- 11. Retrieve the first and last name of all faculty who have the same rank as Kim Cox. Do not show Kim Cox in 
--     the result.  
SELECT f.f_first, f.f_last
FROM faculty AS f
WHERE f.f_rank = 
    (
        SELECT f_rank
        FROM faculty
        WHERE f_first = 'Kim'
        AND f_last = 'Cox'
    )
AND NOT (f.f_first = 'Kim' AND f.f_last = 'Cox');

-- 12. Retrieve all student last names of those that are advised by Sarah Miller’s advisor. Do not show Sarah 
--     Miller in the result. 
SELECT s.s_last
FROM student AS s
WHERE s.f_id = (
        SELECT f_id
        FROM student
        WHERE s_first = 'Sarah'
        AND s_last = 'Miller'
      )
AND NOT (s.s_first = 'Sarah' AND s.s_last = 'Miller');