package A2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * <p>
 *      A collection of {@code Rows}.
 * </p>
 * 
 * <p>
 *      The {@code Table} is stored as an <b>ArrayList</b>.
 * </p>
 * 
 * <p>
 *      COMP 2503
 *      @author Beto Duque
 * </p>
 */
public class Table 
{
    private ArrayList<Row> table;
    private int rowCount;

    //Getters and setters for instance variables.
    public ArrayList<Row> getArrayList() {return table;}
    public int getRows() {return rowCount;}

    public void setArrayList(ArrayList<Row> table) {this.table = table;}
    public void setRows(int rowCount) {this.rowCount = rowCount;}

    /**
     * <b>Default constructor.</b>
     * <p>
     *      Creates a new, empty {@code Table}.
     * </p>
     */
    public Table()
    {
        this.table = new ArrayList<>();
        this.rowCount = 0;
    }

    /**
     * Allows for the creation of a {@code Table} via a filepath. The file must be a <b>.csv</b>.
     */
    public Table(String filePath) throws Exception
    {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
        this.rowCount = 0;
        this.table = new ArrayList<>();

        //Convert the .csv file into a Table using a loop.
        while (fileScanner.hasNextLine())
        {
            String currentLine = fileScanner.nextLine();
            Row row = new Row(rowCount, currentLine);
            this.addRow(row);
        }

        fileScanner.close();
    }

    /**
     * Add a row to the end of the table via a String.
     * @param s
     * The data to be added, formatted as a String.
     */
    public void addRow(String s)
    {
        //Create a new row, then add it to the table.
        Row row = new Row(rowCount, s);
        table.add(row);
        rowCount++;
    }

    /**
     * Add a row to the end of the table via a String[].
     * @param s
     * The row to be added, formatted as a String[]
     */
    public void addRow(String[] s)
    {
        //Create a new row, then add it to the table.
        Row row = new Row(rowCount, s);
        table.add(row);
        rowCount++;
    }

    /**
     * Add a row to the end of the table via an existing row.
     * @param r
     * The row to be added, via a row object.
     */
    public void addRow(Row r)
    {
        //Get the data from the row, then add it via the other addRow(String[] s) method.
        String data = r.getData();
        this.addRow(data);
    }

    /**
     * <p>
     *      Prints out every {@code Row} in the {@code Table} up to <b>r</b>.
     * </p>
     * 
     * <p>
     *      If <b>r</b> is 0, print out the whole {@code Table}.
     * </p>
     * 
     * @param r
     * The amount of {@code Rows} to be printed.
     * 
     * @return
     * A {@code String} containing the first <b>r</b> {@code Rows} in the table.
     */
    public String printTable(int r)
    {
        //Base case where the entire table should be printed.
        if (r == 0)
            r = table.size();

        //Iterate through the table, adding each row to formattedTable starting from the beginning.
        String formattedTable = "";

        for(int i = 0; i < r; i++)
            formattedTable += (table.get(i).toString() + "\n");

        return formattedTable;
    }

    /**
     * Sort a {@code Table} using the natural ordering of its rows.
     */
    public void sortNatural()
    {
        Collections.sort(table);
    }

    /**
     * Sort a {@code Table} by ascending alphabetical order.
     */
    public void sortAlphabetical()
    {
        Collections.sort(table, new AlphabeticalRowComparator());
    }

    /**
     * @param s
     * 
     * @return
     */
    public Table select(String s)
    {

    }

    /**
     * @param cols
     * 
     * @return
     */
    public Table project(String[] cols)
    {
        
    }
}