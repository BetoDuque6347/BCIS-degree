package A2;

/**
 * <p>
 *      A {@code String} of text found within a {@code Table} class.
 * </p>
 * 
 * <p>
 *      Each {@code Table} contains both an {@code ID}, and data stored as a {@code Table}.
 * </p>
 * 
 * <p>
 *      COMP 2503
 *      @author Beto Duque
 * </p>
 */
public class Row implements Comparable<Row>
{
    private int ID;
    private String[] data;

    private static final int ROW_SIZE = 5;
    private static final int LAST_TWO_LETTERS = 2;

    //Getters and setters for instance variables
    public int getID() {return ID;}
    public String getData()
    {
        //Returns the actual data within each row, not the memory address of data.
        String actualData = "";

        for(int i = 0; i < data.length; i++)
        {
            actualData += this.data[i] + ", ";
        }
        
        //Kind of a weird solution to the string ending with ", ". It works though!
        actualData = actualData.substring(0, actualData.length() - LAST_TWO_LETTERS);

        return actualData;
    }

    /**
     * Constructor for comma separated Strings
     */
    public Row(int ID, String s)
    {
        this.ID = ID;
        data = new String[ROW_SIZE];
        String[] splitString = s.split(",");

        //Create a deep copy of s to feed into data.
        //Didn't use ROW_SIZE for this loop because some rows only have four columns.
        for(int i = 0; i < splitString.length; i++)
        {
            this.data[i] = splitString[i];
        }
    }

    /**
     * Constructor for array of Strings
     */
    public Row(int ID, String[] s)
    {
        this.ID = ID;
        data = new String[ROW_SIZE];
        
        //Create a deep copy of s (not sure if we need a deep copy to begin with)
        for(int i = 0; i < s.length; i++)
        {
            data[i] = s[i];
        }
    }
    
    /**
     * Compares two rows together via {@code ID}. Returns the <b>difference</b> between the two {@code ID}s.
     * 
     * @param o
     * The other row to compare to the current row.
     * 
     * @return
     * The difference between the two {@code ID}s.
     */
    @Override
    public int compareTo(Row o) 
    {
        int comparison = this.getID() - o.getID();

        return comparison;
    }

    /**
     * Prints out the {@code Row} object with the {@code ID} and {@code data} of that row.
     * 
     * @return
     * The {@code Row}, formatted to show both its associated {@code ID} and {@code data}.
     */
    @Override
    public String toString()
    {
        return("Row " + getID() + ": " + getData());
    }

    /**
     * Get the value of an item within a row at a specified index.
     * 
     * @param index
     * The index of the column to be returned.
     * 
     * @return
     * The data at the specified index, returned as a {@code String}.
     */
    public String getColumnAt(int index)
    {
        return data[index];
    }

    /**
     * Set the value of an item within a row at a specified index.
     * 
     * @param index
     * The index of the column to be set.
     * 
     * @param data
     * The data to be set at the specified index.
     */
    public void setColumnAt(int index, String data)
    {
        //Not gonna lie, I could not find a cooler adjective for data.
        this.data[index] = data;
    }
}