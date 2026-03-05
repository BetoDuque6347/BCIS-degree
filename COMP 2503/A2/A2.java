package A2;

import java.util.Scanner;

/**
 * <p>
 *      The main class for the Table and Row system. Contains a main method.
 * </p>
 * 
 * <p>
 *      Creates a <b>Table</b> object, loads the data from a specified <b>.csv</b> and the following operations take place:
 * 
 *      <ol>
 *          <li>Print the first 10 rows of the table, and the amount of rows within the table.</li>
 *          <li>Sort the table alphabetically by the second column of the table (colour), then print hte firs 10 rows of the table.</li>
 *          <li>Create a new table that consists only of rows that the colour is black. Print the number of rows in
            that table and the first 10 rows.</li>
            <li>Create another new table that consists only of columns species, count and notes. Print the number of
            rows in that table and the first 10 rows.</li>
 *      </ol>
 * </p>
 * <p>COMP 2503</p>
 * @author Beto Duque
 */
public class A2 
{
    Scanner input = new Scanner(System.in);
    int rowsToPrint;
    String columnColour;
    String colourToFind;
    String[] columnsToFind = {"species", "count", "notes"};

    public static void main(String[] args) throws Exception
    {
        A2 a2 = new A2();
        a2.run();
    }

    /**
     * Runs the program.
     */
    public void run() throws Exception
    {
        rowsToPrint = 0;

        columnColour = "colour";
        colourToFind = "black";

        //Create the table using the specified .csv
        Table table = new Table(input.nextLine());

        //Part 1 of the assignment
        System.out.println("Part 1:\n");
        System.out.println("Number of rows in the table: " + table.getRows());
        System.out.println(table.printTable(rowsToPrint));

        //Part 2 of the assignment
        System.out.println("\nPart 2:");
        table.sortColour();
        System.out.println(table.printTable(rowsToPrint));
        table.sortNatural(); //Undo the previous sort, since it breaks things and acts weird.

        //Part 3 of the assignmnet
        System.out.println("\nPart 3:\n");
        Table tableWithOnlyBlack = table.select(columnColour, colourToFind);
        System.out.println(tableWithOnlyBlack.printTable(rowsToPrint));

        //Part 4 of the assignment
        System.out.println("\nPart 4:\n");
        Table tableWithSpeciesCountNotes = table.project(columnsToFind);
        System.out.println(tableWithSpeciesCountNotes.printTable(rowsToPrint));
    }
}
