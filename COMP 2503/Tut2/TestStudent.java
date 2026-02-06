package Tut2;

/** Student test class starting code. 
 *  COMP 2503 
 *  @author Maryam Elahi
 */
import java.util.ArrayList;
import java.util.Collections;

public class TestStudent 
{
	public static void main (String[] args) 
	{
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(filloutStudentObject("George", "Weasley", "Joke Shop", 3));
		studentList.add(filloutStudentObject("Fred", "Weasley", "Joke Shop", 3));
		studentList.add(filloutStudentObject("Ron", "Weasley", "Dumbledore Army", 7));
		studentList.add(filloutStudentObject("Neville", "L.", "Herbology", 4));
		studentList.add(filloutStudentObject("Barty", "Crouch", "DarkStuff", 12));
		
		// Sort with default order (based on Last Name, First Name), and print
		Collections.sort(studentList);
		printStudentList(studentList);
		
		System.out.println();
		
		// Sort based on customized comparator method, and print
		Collections.sort(studentList, new StudentNameCmp());
		printStudentList(studentList);
	}
	
	private static Student filloutStudentObject(String firstname, String lastname, String program, int owl) {
		Student student = new Student();
		student.setFirstName(firstname);
		student.setLastName(lastname);
		student.setProgram(program);
		student.setOWL(owl);
		return student;
	}
	
	private static void printStudentList (ArrayList<Student> studentlist) {
		System.out.println("ID\t\tLastName\tFirstName\tProgram\t\t\tOWL");
		for (int i = 0; i < studentlist.size(); i++) {
			System.out.println(
					studentlist.get(i).getID() +  "\t\t" + 
					studentlist.get(i).getLastName() + "\t\t" +
					studentlist.get(i).getFirstName()+ "\t\t" +
					studentlist.get(i).getProgram()+ "\t\t" +
					studentlist.get(i).getOWL());
		}
	}
}