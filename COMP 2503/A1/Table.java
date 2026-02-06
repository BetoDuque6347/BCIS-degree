package A1;

import java.util.ArrayList;
import java.util.Collections;

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
public class Table {

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
     * Adds a new {@code Row} to the {@code Table} while tracking the {@code Row}'s {@code ID}.
     * 
     * @param s The data of the {@code Row} that will be added to the {@code Table}.
     */
    public void addRow(String s)
    {
        //Create a new row, then add it to the table.
        Row row = new Row(rowCount, s);
        table.add(row);
        rowCount++;
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
     * Returns a new {@code Table} containing the {@code String} <b>s</b> from another {@code Table}.
     * 
     * @param s
     * The target {@code String} to find within a {@code Table}.
     * 
     * @return
     * A new {@code Table} with {@code Rows} that contain the target <b>s</b>.
     */
    public Table select(String s)
    {
        //Base case where the table is empty for some reason.
        if (this.getRows() == 0)
            return null;

        Table tableWithWord = new Table();

        //Iterate through the table and find any lines containing the target word.
        for(Row row : this.table)
        {
            if (row.toString().contains(s))
                tableWithWord.addRow(row.getData());
        }

        //Word does not appear in table.
        if (tableWithWord.getRows() == 0)
            return null;
        
        return tableWithWord;
    }
}