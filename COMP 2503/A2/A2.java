package A2;

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
    private static final int ROWS_TO_PRINT = 10; //Specifies how many rows to print.
    private static final String COLUMN = "colour"; //The colour column is always named "colour" (with a u).
    private static final String COLOUR = "black"; //Specifies which colour to look for in the colour column.
    private static final String[] COLUMNS_TO_FIND = {"species", "count", "notes"}; //Specifies which columns to project.
    Table table; //Initialize the table to be used in the assignment.

    public static void main(String[] args) throws Exception
    {
        A2 a2 = new A2();
        a2.run(args[0]);
    }

    /**
     * Runs the program.
     * 
     * @param filePath
     * The file (.csv) to be used for the assignment.
     */
    public void run(String filePath) throws Exception
    {
        //Create the table using the specified .csv
        table = new Table(filePath);

        //Part 1 of the assignment
        System.out.println("Part 1 (Print table and number of rows):\n");
        System.out.println("Number of rows in the table: " + table.getRows());
        System.out.println(table.printTable(ROWS_TO_PRINT));

        //Part 2 of the assignment
        System.out.println("\nPart 2 (Sort by colour):\n");
        table.sortColour();
        System.out.println(table.printTable(ROWS_TO_PRINT));

        //Part 3 of the assignmnet
        System.out.println("\nPart 3 (Print out only rows containing black):\n");
        Table tableWithOnlyBlack = table.select(COLUMN, COLOUR);
        System.out.println(tableWithOnlyBlack.printTable(ROWS_TO_PRINT));

        //Part 4 of the assignment
        System.out.println("\nPart 4 (Print out species, count, and notes):\n");
        Table tableWithSpeciesCountNotes = table.project(COLUMNS_TO_FIND);
        System.out.println(tableWithSpeciesCountNotes.printTable(ROWS_TO_PRINT));
    }
}