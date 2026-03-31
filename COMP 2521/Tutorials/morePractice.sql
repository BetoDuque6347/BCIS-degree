-- Retrieve the locations to show the number of course sections scheduled to run in each of those 
-- locations. Sort the result by location ID. Include also locations that do not have a course section 
-- scheduled in them yet. 
SELECT l.loc_id, COUNT(c.loc_id) as 'AMOUNT'
FROM location l
LEFT JOIN course_section c ON l.loc_id
WHERE l.loc_id = c.loc_id
GROUP BY l.loc_id
ORDER BY loc_id;

--3a. Now, retrieve the faculty's last name, bldg code, room, and the course section ID of all course sections,
--regardless of whether or not they have been assigned a faculty member yet OR whether or not they
--have been scheduled in a location yet.
SELECT f.f_last, l.bldg_code, l.room, cs.c_sec_id
FROM course_section cs
LEFT JOIN faculty f ON cs.f_id = f.f_id
LEFT JOIN location l ON cs.loc_id = l.loc_id;

--4. Retrieve the course sections and display the number of students enrolled in each of them. Include also
--those course sections that do not have students enrolled in them.
SELECT cs.c_sec_id, COUNT(e.s_id) AS 'amount'
FROM course_section cs
LEFT JOIN enrollment e ON e.c_sec_id = cs.c_sec_id
GROUP BY cs.c_sec_id;

--5. Retrieve the term description along with the number of course sections scheduled for that term.
SELECT t.term_desc, COUNT(cs.term_id) AS 'amount'
FROM term t
LEFT JOIN course_section cs ON t.term_id = cs.term_id
GROUP BY t.term_id;