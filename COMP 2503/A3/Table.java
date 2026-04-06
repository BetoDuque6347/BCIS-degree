package A3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * <p>A collection of {@code Rows}.</p>
 * 
 * <p>The {@code Table} is stored as an <b>ArrayList</b>.</p>
 * 
 * <p>COMP 2503</p>
 * @author Beto Duque
 */
public class Table 
{
    private ArrayList<BST> indexes = new ArrayList<>();
    private ArrayList<Row> table; //The actual data within the table, as an ArrayList
    private int rowCount; //The amount of rows within a table.

    private static final String DELIMITER = ","; //Delimiter for CSVs. Is a String because subString() only accepts Strings.
    private static final int HEADER_INDEX = 0; //The index of the header row.

    //Getters and setters for instance variables.
    public ArrayList<Row> getArrayList() {return table;}
    public int getRows() {return rowCount;}
    private Row getHeaderRow() { return table.get(HEADER_INDEX);} //Made a getter for the header row since it is used quite often.

    public void setArrayList(ArrayList<Row> table) {this.table = table;}
    public void setRows(int rowCount) {this.rowCount = rowCount;}

    /**
     * <b>Default constructor.</b>
     * <p>Creates a new, empty {@code Table}.</p>
     */
    public Table()
    {
        this.table = new ArrayList<>();
        this.rowCount = 0;
    }

    /**
     * Creates a {@code Table} with a specified <b>.csv</b> file.
     * 
     * @param filePath
     * the path for the <b>.csv</b> file.
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

            //Finds the amount of columns within the current line.
            //This feels extremely inefficient, but it works!
            int columnCount = currentLine.split(DELIMITER).length;

            Row row = new Row(rowCount, columnCount, currentLine);
            this.addRow(row);
        }

        fileScanner.close();
    }

    /**
     * Add a {@code Row} to the end of the table via a String.
     * 
     * @param s
     * The data to be added, formatted as a String.
     */
    public void addRow(String s)
    {
        //Convert the String to a String[], then add it via the other addRow(String[] s) method.
        String[] data = s.split(DELIMITER);
        this.addRow(data);
    }

    /**
     * Add a {@code Row} to the end of the table via a String[].
     * 
     * @param s
     * The data to be added, formatted as a String[].
     */
    public void addRow(String[] s)
    {
        //Create a new row, then add it to the table.
        Row row = new Row(rowCount, s.length, s);
        table.add(row);
        rowCount++;
    }

    /**
     * Add a {@code Row} to the end of the table via an existing {@code Row}.
     * 
     * @param r
     * The {@code Row} to be added, via an existing {@code Row}.
     */
    public void addRow(Row r)
    {
        //Get the data from the row, then add it via the other addRow(String[] s) method.
        String[] data = r.getData();
        this.addRow(data);
    }

    /**
     * Retrieve a {@code Row} within the table, specified by <b>index</b>.
     * 
     * @param index
     * The index of the {@code Row} to be returned.
     * @return
     * The {@code Row} to be returned.
     */
    public Row getRowAt(int index)
    {
        return table.get(index);
    }

    /**
     * <p>Prints out every {@code Row} in the {@code Table} up to <b>r</b>.</p>
     * 
     * <p>If <b>r</b> is 0, print out the whole {@code Table}.</p>
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

        //Base case where r is bigger than the amount rows that exist in a table.
        else if (r > getRows())
            r = getRows();

        //Iterate through the table, adding each row to formattedTable starting from the beginning.
        String formattedTable = "";

        for(int i = 0; i < r; i++)
            formattedTable += (table.get(i).toString() + "\n");

        return formattedTable;
    }

    /**
     * <p>Sort a {@code Table} using the natural ordering of its rows.</p>
     * <p>Does not include the header row when sorting.</p>
     */
    public void sortNatural()
    {
        //Since the header index is always 0, we can guarantee the header
        //row will appear first due to natural ordering.
        Collections.sort(table);
    }

    /**
     * <p>Returns a new {@code Table} that contains all of the columns of the exiting {@code Table}
     * but with only the {@code Rows} from the {@code Table} where column <b>field</b> contains
     * the {@code String} <b>value</b>. Uses a column index if it is available.</p>
     * 
     * @param field
     * The column to be searched within the {@code Table}.
     * 
     * @param value
     * The value to find within the <b>field</b> column.
     * 
     * @return
     * A new {@code Table} consisting of only the {@code Rows} with <b>value</b> found in column <b>field</b>.
     */
    public Table select(String field, String value)
    {
        Table selectedTable = new Table();

        Row headers = getHeaderRow();
        int indexOfField = 0;
        Row currentRow;
        ArrayList<Row> matchingRows;

        //Iterate through the header row to find the index of field.
        for(int i = 0; i < headers.getSize(); i++)
        {
            String headerColumn = headers.getData()[i];

            if(headerColumn.equals(field))
            {
                indexOfField = i;

                //Since there is only one field to search for, once it is found we should leave the loop for optimization.
                break;
            }
        }
        //Force the header row to exist within the new table.
        selectedTable.addRow(headers);

        //Checks the following (in order):
        //1. Check if indexes exists at all
        //2. Checks if the column index fits within indexes
        //3. Checks if the BST exists in the correct position
        if (indexes != null && indexOfField < indexes.size() && indexes.get(indexOfField) != null)
        {
            matchingRows = indexes.get(indexOfField).find(value);
            
            for(Row r : matchingRows)
            {
                selectedTable.addRow(r);
            }
        }
        else //Iterate through the entire table and check the ith column if it contains value.
        {
        for(int i = 0; i < rowCount; i++)
        {
            currentRow = table.get(i);

            if(currentRow.getColumnAt(indexOfField).equals(value))
                selectedTable.addRow(currentRow);
        }
        }

        return selectedTable;
    }

    /**
     * <p>Returns a new {@code Table} that consists of all the {@code Rows} of the existing
     * {@code Table} but with only the columns listed.</p>
     * 
     * @param cols
     * A {@code String[]} of values containing which columns should be returned.
     * 
     * @return
     * A new {@code Table} consisting of all the columns specified in <b>cols</b>.
     */
    public Table project(String[] cols)
    {
        //Base case where cols is empty for some reason.
        if (cols.length == 0)
            return null;

        int amountOfColumns = cols.length;
        String[] correctRowData = new String[amountOfColumns];
        int[] columnIndexes = new int[amountOfColumns];

        Table selectedColumns = new Table();
        Row headers = getHeaderRow();
        Row currentRow;
        Row rowToBeAdded;
        String currentColumn;
        String specifiedColumn;

        //Find the indexes of the specified columns. Add each index to columnIndexes.
        //Iterate through the header column.
        for(int i = 0; i < headers.getSize(); i++)
        {
            //Find each individual column index.
            for(int k = 0; k < amountOfColumns; k++)
            {
                currentColumn = headers.getColumnAt(i);
                specifiedColumn = cols[k];

                if(currentColumn.equals(specifiedColumn))
                    columnIndexes[k] = i;
            }
        }

        //Using columnIndexes, create a new row containing only the columns listed
        //then add it to selectedColumns.
        //Iterate through the entire table.
        for(int i = 0; i < getRows(); i++)
        {
            currentRow = table.get(i);

            //Retrieve the desired columns from the current table into correctRowData[].
            //This will be used to create a new row.
            for(int k = 0; k < correctRowData.length; k++)
            {
                correctRowData[k] = currentRow.getColumnAt(columnIndexes[k]);
            }

            //Create the row with the correct data and add it to selectedColumns.
            rowToBeAdded = new Row(i, amountOfColumns, correctRowData);
            selectedColumns.addRow(rowToBeAdded);
        }

        return selectedColumns;
    }

    /**
     * Creates a Binary Search Tree of all vlaues in the column <b>column</b>.
     * 
     * @param column
     * The column to be indexed.
     */
    public void addIndex(String column)
    {
        if(indexes == null)
            indexes = new ArrayList<>();

        Row headerRow = getHeaderRow();
        int colIndex = 0;

        //Find the index of column within the current table
        for(int i = 0; i < headerRow.getSize(); i++)
        {
            if(headerRow.getColumnAt(i).equals(column))
            {
                colIndex = i;
                break;
            }
        }

        BST tree = new BST();
        
        //Loop through table, start at 1 to skip header row
        for(int i = 1; i < getRows(); i++)
        {
            Row currentRow = getRowAt(i);
            String key = currentRow.getColumnAt(colIndex);
            tree.add(key, headerRow); //Most of the heavy lifting is done in the BST class code
        }

        indexes.add(tree);
    }

    /**
     * Adds a new {@code Row} to all existing Binary Search Trees in the {@code Table}'s indexes based on the given <b>key</b>.
     * 
     * @param key
     * The value from a column that will serve as the key for the BST nodes
     * @param row
     * The {@code Row} to be added to the BST node
     */
    public void add(String key, Row row)
    {
        //addIndex() should be called first before this method is used.
        if (indexes == null)
            return;

        for(BST tree : indexes)
        {
            tree.add(key, row);
        }
    }

    /**
     * Returns all {@code Rows} from the indexed column that match the given <b>key</b>.
     * 
     * @param key
     * The value to search for in the column index.
     * @return
     * An {@code ArrayList} of {@code Rows} that contain the key.
     */
    public ArrayList<Row> find(String key)
    {
        return indexes.get(HEADER_INDEX).find(key);
    }

    /**
     * <p>Returns a new {@code Table} that takes all the {@code Rows} in the current {@code Table} and appends 
     * the {@code Rows} in {@code Table} <b>t</b>.</p>
     * <p>If the number of columns is not equal, return null.</p>
     * 
     * @param t
     * The {@code Table} that will be appended to the current {@code Table}.
     * @return
     * A new {@code Table} with {@code Table} <b>t</b> appended to it.
    */
    public Table union(Table t)
    {
        //Base case where the tables do not have the same amount of columms.
        if(getHeaderRow().getSize() != t.getHeaderRow().getSize())
            return null;
    
        Table unionedTable = new Table();

        //Create a deep copy of this.table into unionedTable.
        for(int i = 0; i < getRows(); i++)
        {
            unionedTable.addRow(getRowAt(i));
        }

        //Also add the rows from t into unionedTable.
        for(int i = 1; i < t.getRows(); i++) //Start at 1 to skip header row
        {
            unionedTable.addRow(t.getRowAt(i));
        }

        return unionedTable;
    }

    /**
     * <p>Creates a new {@code Table} that is the cross product of the current {@code Table} and {@code Table} <b>t</b></p>
     * 
     * @param t
     * The second {@code Table} to cross the current {@code Table}
     * @return
     * The cross product of the current {@code Table} and {@code Table} <b>t</b>.
     */
    public Table cross(Table t)
    {
        Table crossedTable = new Table();
        
        //Create and add new header row from table A and B
        Row rowAHeaders = this.getRowAt(HEADER_INDEX);
        Row rowBHeaders = t.getRowAt(HEADER_INDEX);

        //HEADER_INDEX was used although any row could be used.
        int tableARowCount = this.getRowAt(HEADER_INDEX).getSize();
        int tableBRowCount = t.getRowAt(HEADER_INDEX).getSize();

        final int CROSSED_TABLE_COLUMN_COUNT = tableARowCount + tableBRowCount;

        String[] newHeaderRow = new String[CROSSED_TABLE_COLUMN_COUNT];

        //Copy header row from table A
        for(int i = 0; i < tableARowCount; i++)
        {
            newHeaderRow[i] = rowAHeaders.getColumnAt(i);
        }

        //Copy header row from table B
        for(int i = 0; i < tableBRowCount; i++)
        {
            newHeaderRow[i + tableARowCount] = rowBHeaders.getColumnAt(i);
        }

        crossedTable.addRow(newHeaderRow);


        //Create the cross product of table A and B
        for(int i = 1; i < this.getRows(); i++) //Start at 1 to skip header row
        {
            Row rowA = this.getRowAt(i);

            for(int j = 1; j < t.getRows(); j++) //Start at 1 to skip header row
            {
                Row rowB = t.getRowAt(j);
                String[] newRowData = new String[CROSSED_TABLE_COLUMN_COUNT];

                //Copy A row
                for(int k = 0; k < tableARowCount; k++)
                {
                    newRowData[k] = rowA.getColumnAt(k);
                }

                //Copy B row
                for(int k = 0; k < tableBRowCount; k++)
                {
                    newRowData[tableARowCount + k] = rowB.getColumnAt(k);
                }

                crossedTable.addRow(newRowData);
            }
        }

        return crossedTable;
    }
}