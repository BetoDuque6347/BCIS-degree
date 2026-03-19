--Retrieve the loc IDs and display a message in how they are used.
--if they are used as a faculty office, display a message 'Faculty Office'
--if they are used as a classroom, display a message 'Classroom'
--if they are used as neither, display a message 'Unused'

SELECT loc_id, bldg_code, room, 'Classroom'
FROM location
WHERE loco_id IN (
    SELECT loc_id
    FROM course_section
)
AND loc_id IN (
    SELECT loc_id
    FROM faculty
)

UNION

SELECT loc_id, bldg_code, room 'Faculty Office'
FROM location
WHERE loc_id NOT IN (
    SELECT loc_id
    FROM course_section
    )
OR loc_id IN (
    SELECT loc_id
    FROM faculty
)

UNION

SELECT location.loc_id, bldg_code, room, 'Unused'
FROM location LEFT OUTER JOIN course_section ON location.loc_id = course_section.loc_id
              LEFT OUTER JOIN faculty ON location.loc_id = faculty.loc_id
WHERE course_section..loc_id IS NULL
AND faculty.loc_id IS NULL
ORDER BY bldg_code;