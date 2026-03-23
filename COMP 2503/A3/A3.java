package A3;

import java.util.ArrayList;

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
public class A3 
{
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
        ArrayList<Table> tables = new ArrayList<>();
        Table table;
        
        //Convert file paths into Tables and add them into tables.
        for(int i = 0; i < files.length; i++)
        {
            table = new Table(files[i]);
            tables.add(table);
        }
    }
}
