package A1;

import java.io.File;
import java.util.Scanner;

/**
 * <p>
 *      The main class for the Table and Row system. Contains a main method.
 * </p>
 * 
 * <p>
 *      Creates a <b>Table</b> object, loads the data from a specified <b>File</b> and the following operations take place:
 * 
 *      <ol>
 *          <li>Print the number of rows in the table.</li>
 *          <li>Sort the table alphabetically by the text in each row and then print the first 10 rows of the table.</li>
 *          <li>Create a new table that consists only of rows that contain the word 'the', then print the number of rows in that table and the first 10 rows.</li>
 *      </ol>
 * </p>
 * COMP 2503
 * @author Beto Duque
 */
public class A1 
{
    private Scanner fileScanner;
    private Table table;
    private Table tableWithWord;
    private File file;
    private int rowsToPrint;

    private static final String FILE_PATH = "COMP 2503/A1/data/data3.txt";
    private static final String WORD_TO_FIND = "the";  //The assignment specifies one word but I still want flexibility.

    public static void main(String args[]) throws Exception
    {
        A1 assignment = new A1();
        assignment.run();
    }

    /**
     * Runs the program.
     */
    public void run() throws Exception
    {
        file = new File(FILE_PATH);
        fileScanner = new Scanner(file);
        table = new Table();
        rowsToPrint = 0;

        //Populate the table with file contents.
        while (fileScanner.hasNextLine())
        {
            table.addRow(fileScanner.nextLine());
        }
        
        //The program will NEVER print MORE THAN 10 lines from the text file.
        if (table.getRows() > 10)
            rowsToPrint = 10;

        //Table must be populated before we can begin finding a word inside the table.
        tableWithWord = table.select(WORD_TO_FIND);

        //Part 1 of assignment
        System.out.println("Number of rows in the table: " + table.getRows());
        System.out.println("\n------------------");

        //Part 2 of assignment
        System.out.println("Table sorted in alphabetical order (First 10):");
        table.sortAlphabetical();
        table.printTable(rowsToPrint);
            
        System.out.print(table.printTable(rowsToPrint));
        System.out.println("\n------------------");

        //Part 3 of assignment
        System.out.println("First 10 rows containing \'" + WORD_TO_FIND + "\':");

        if(tableWithWord == null)
            System.out.println("The table does not contain this word.");
        else
        {
            System.out.println(tableWithWord.printTable(rowsToPrint));
            System.out.println("Number of rows containing \'" + WORD_TO_FIND + "\': " + tableWithWord.getRows());
        }
    }  
}