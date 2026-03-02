--Query 13:
SELECT
    g.student_id
    g.grade_type_code
    g.numeric_grade
FROM student s
JOIN grade g ON s.student_id = g.student_id
JOIN grade_type gt ON s.student_id = gt.grade_type_code
WHERE s.first_name = 'Larry', AND s.last_name = 'Walter'
AND gt.description IN ("Homework", "Quiz");

--Query 14:
SELECT s.first_name, s.last_name, g.numeric_grade
FROM student s
JOIN zipcode z ON s.zip = z.zip
JOIN enrollment e ON s.student_id = e.student_id
JOIN section sec ON e.section_id = sec.section_id
JOIN grade g ON g.student_id = s.student_id
    AND g.section_id = sec.section_id
WHERE z.state = 'NJ'
    AND sec.course_no = 350
    AND g.grade_type_code = "FI";

--Query 19
SELECT location, MIN(capacity), MAX(capacity), SUM(capacity)
FROM section
GROUP BY location;