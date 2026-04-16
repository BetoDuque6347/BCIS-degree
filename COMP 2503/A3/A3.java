package A3;

import java.util.ArrayList;

/**
 * <p>
 *      The main class for the Table and Row system. Contains a main method.
 * </p>
 * 
 * <p>
 *      Creates a list of <b>Table</b>s, loads the data from a list of <b>.csv</b> files and the following operations take place:
 * 
 *      <ol>
 *          <li>Print the name and the number of rows for each table.</li>
 * 
 *          <li>Create an index on the column colour on a3 d1.csv. Perform a select on colour = black, and display
            the resulting table.</li>

 *          <li>Create a new table that is the cross product of a3 d2.csv and a3 d3.csv. Print the resulting table.</li>

            <li>Create a new table that is the union of a3 d2.csv and a3 d4.csv. Print the resulting table.</li>
 *      </ol>
 * </p>
 * <p>COMP 2503</p>
 * @author Beto Duque
 */
public class A3 
{
    private static final ArrayList<Table> TABLES = new ArrayList<>(); //The ArrayList<> that holds the Tables.
    private static final String COLUMN_NAME = "colour"; //The column to be indexed in part 2 of the assignment.
    private static final String COLOUR_TO_FIND = "Black"; //The colour to find within the colour column as specified in part 2 of the assignment.

    private static final int FIRST_TABLE_INDEX = 0;
    private static final int SECOND_TABLE_INDEX = 1; //The index of the second Table in the TABLES
    private static final int THIRD_TABLE_INDEX = 2; //The index of the third Table in TABLES
    private static final int FOURTH_TABLE_INDEX = 3; //The index of the fourth Table in TABLES

    private static final int ROWS_TO_PRINT = 0; //The assignment will always print out every Table in full.

    public static void main(String[] args) throws Exception
    {
        A3 a3 = new A3();
        a3.run(args);
    }

    /**
     * Runs the program.
     * 
     * @param files
     * A String[] of files to be used within the assignment.
     */
    public void run(String[] files) throws Exception
    {
        Table table;
        
        //Convert file paths into Tables and add them into TABLES.
        for(String file : files)
        {
            table = new Table(file);
            TABLES.add(table);
        }

        //Part 1 of the assignment:
        System.out.println("\n--------PART 1------");
        for(int i = 0; i < TABLES.size(); i++)
        {
            System.out.println("\nTable a3_d" + (i + 1) + ".csv:"); //Offset by 1 because we love zero-based indexing.
            System.out.println(TABLES.get(i).printTable(0));
            System.out.println("Number of rows in a3_d" + (i + 1) + ".csv: " + TABLES.get(i).getRows());
        }

        //Part 2 of the assignment:
        System.out.println("\n--------PART 2------");
        Table firstTable = TABLES.get(FIRST_TABLE_INDEX);
        Table colours;

        firstTable.addIndex(COLUMN_NAME);
        colours = firstTable.select(COLUMN_NAME, COLOUR_TO_FIND); //NOTE: There are no Black cars in this Table.

        System.out.println(colours.printTable(ROWS_TO_PRINT));

        //Part 3 of the assignment (cross product):
        System.out.println("\n--------PART 3------");
        Table secondTable = TABLES.get(SECOND_TABLE_INDEX);
        Table thirdTable = TABLES.get(THIRD_TABLE_INDEX);
        Table crossedTable = secondTable.cross(thirdTable);

        System.out.println(crossedTable.printTable(ROWS_TO_PRINT));

        //Part 4 of the assignment:
        System.out.println("\n--------PART 4------");
        Table fourthTable = TABLES.get(FOURTH_TABLE_INDEX);
        Table unionedTable = secondTable.union(fourthTable);

        System.out.println(unionedTable.printTable(ROWS_TO_PRINT));
    }
}
