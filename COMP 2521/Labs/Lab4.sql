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
SELECT f.f_last, 


--You can delete the row by the DELETE statement.
DELETE FROM course_section
WHERE c_sec_id = 14;

--3. Write another insert statement to add a course section that does not have a faculty member assigned
--to it yet, i.e., with f_id is null.


--3a. Now, retrieve the faculty's last name, bldg code, room, and the course section ID of all course sections,
--regardless of whether or not they have been assigned a faculty member yet OR whether or not they
--have been scheduled in a location yet.


--4. Retrieve the course sections and display the number of students enrolled in each of them. Include also
--those course sections that do not have students enrolled in them.

--5. Retrieve the term description along with the number of course sections scheduled for that term.


--5a. Modify the query to include also those terms that do not have a course section scheduled in
--them.


--6. Rewrite the above query in another way (Note: this is not necessarily a different way!).


--7. Retrieve the term ID, term description, and the total maximum enrollment of all course sections
--scheduled in that term.


--8. Modify the above query to include also those terms that do not have any course sections scheduled in
--it. Show the total as null for those terms that do not have course sections scheduled in them.




--SUB-QUERIES

--Write the following queries using a subquery. When the question says, write it a different
--way, you may write it as a join.

--1. Retrieve the last name of all faculty members who teach during the “Summer 2007” term.


--2. Retrieve the course section’s day and location of the course ‘Database Management’ during ‘open’
--terms.


--3. Retrieve those faculty IDs who have the same name (first, last) as a student.


--4. Retrieve the course section ID and the value of the max_enrl column for all sections that
--correspond to the highest value for max_enrl.


--5. Retrieve the course section ID and the value of the max_enrl column for all sections that do NOT
--correspond with the course section that has the highest value for max_enrl.


--6. Modify the above query to show those course sections below the average value for max_enrl.


--7. An interesting one ☺ Retrieve the grade and term ID values for the student Sarah Miller’s System
--Analysis course.


--8. Retrieve the course name and the course ID of all courses that are prerequisites for other courses.


--9. Retrieve the term description of those terms that do not have a course section scheduled in them.


--10. Another interesting one ☺ write the above query a different way!




--UNION

--11. Retrieve the last and first names of all students and faculty. Add a comment to indicate which
--entity they belong to. If faculty, say “FACULTY”, if student, say “STUDENT”. Order the results by last
--name.