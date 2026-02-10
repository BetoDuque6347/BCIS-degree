-- Lab 3
-- Author: Beto Duque
-- Instructor: Shoba Ittyipe
-- Due date: Feb 13, 2026

-- 1. Retrieve the student's last names and the last name of their advisor. Make appropriate use of aliases.

-- 2. Retrieve the advisor name along with the number of students they are advising.

-- 3. Retrieve the last name of facutly members who are scheduled to teach course sections.
--    Show the total maximum enrollment per faculty member.

-- 4. Retrieve the course ID and course name of all sections that are scheduled in the course section table
--    and their corresponding maximum enrollment.
--    a) Write the above query using the JOIN keyword with an ON clause.
--    b) Write the above query using the JOIN keyword with the USING clause.

-- 5. Now, modify the above query to retrieve the total maximum enrollment per couse. For example, "Web-Based Systems"
--    course has 2 sections of 35 students each which makes its total maximum enrollment to 70.
--    Note that the "not a GROUP BY expression" error occurs when you try to display a column that has not been grouped.

-- 6. Now, modify the above query to retrieve only those courses that have their total maximum enrollment greater than 200.

-- 7. Retrieve the location Id, building code and room of locations that have been scheduled for a course section.
--    Show the nmber of course sections taking palce in each of those locations. Sort the result with location ID.
--    Learn about the ORDER BY clause.

-- 8. Retrieve the course name, term description, faculy last name and room of course sections that have been scheduled.
--    Write an insert statement to add a acourse section that has not been assigned to a location yet. 
--    In other words, the loc_id fofr that record is NULL. Look up on mySQL documentation for the INSERT statement.

-- 9. Run the previous query. Is the new course section included in your result? Explain your answer;
--    show as a comment in your SQL file.

-- 10. Retrieve the coursee name, and the name of the prerequisite course for the those courses that have prerequisites.