package Tut2;

import java.util.Comparator;

public class StudentNameCmp implements Comparator<Student>
{
    public int compare(Student s1, Student s2)
    {
        return s1.getFirstName().compareTo(s2.getFirstName());
    }
}