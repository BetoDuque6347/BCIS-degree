--Query 13:
SELECT
    g.student_id
    g.grade_type_code
    g.numeric_grade
FROM grade grade
WHERE g.student_id IN (
    SELECT
        s.student_id
        FROM studen s
        WHERE s.first_name = 'Larry'
        AND s.last_name = 'Walter'
)
AND g.grade_type_code IN (
    SELECT gt.grade_type_code
    FROM grade_type gt
    WHERE gt.description IN('Homework', 'Quiz')
);