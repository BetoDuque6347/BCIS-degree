package A3;

/**
 * <p>A {@code String[]} found within a {@code Table} class.</p>
 * 
 * <p>Each {@code Row} contains both an {@code ID}, {@code size}, and data formatted as a {@code String[]}.</p>
 * 
 * <p>COMP 2503</p>
 * @author Beto Duque
 */
public class Row implements Comparable<Row>
{
    private int ID; //The ID of the row, used for natural ordering.
    private int size; //The amount of columns within the row.
    private String[] data; //Data stored in the row.

    private static final String DELIMITER = ","; //Delimiter for CSVs. Is a String because subString() only accepts Strings.
    private static final int START_OF_STRING = 0; //Index of the first char in a String. Used in getStringData().
    private static final int LAST_TWO_CHARS = 2; //The amount of chars to remove in subString(). Used in getStringData().

    //Getters and setters for instance variables
    public int getID() {return ID;}
    public int getSize() {return size;}
    public String[] getData() {return data;}

    public void setID(int ID) {this.ID = ID;}
    public void setSize(int size) {this.size = size;}

    /**
     * <p>Sets the data within a row (should not be used, just make a new Row).</p>
     * <p>This method exists to fulfill the "getters and setters for all instance variables" requirement.</p>
     * 
     * @param data
     * The data to replace the existing data.
     */
    public void setData(String[] data)
    {
        //Create a deepcopy of data.
        for(int i = 0; i < data.length; i++)
        {
            this.data[i] = data[i];
        }
    }

    /**
     * Returns the data within each row formatted as a String, not the memory address of data.
     * 
     * @return
     * The data within each row.
     */
    public String getStringData()
    {
        String actualData = "";

        //Compile each index of data and add it to a String
        for(int i = 0; i < data.length; i++)
        {
            actualData += this.data[i] + ", ";
        }
        
        //Kind of a weird solution to the String ending with ", ". It works though!
        //Remove the very last two characters of actualData. These will always be ", " because of the algorithm above this line.
        actualData = actualData.substring(START_OF_STRING, actualData.length() - LAST_TWO_CHARS);

        return actualData;
    }

    /**
     * Creates a row with a specified {@code ID}, {@code size}, and {@code String}
     * 
     * @param ID
     * The ID of the row.
     * 
     * @param size
     * The length (amount of items) in the row.
     * 
     * @param s
     * The data within the row.
     */
    public Row(int ID, int size, String s)
    {
        this.ID = ID;
        this.size = size;
        data = new String[size];
        String[] splitString = s.split(DELIMITER);

        //Create a deep copy of s to feed into data.
        for(int i = 0; i < splitString.length; i++)
        {
            this.data[i] = splitString[i];
        }
    }

    /**
     * <p>Creates a row with a specified {@code ID}, {@code size}, and {@code String[]}.</p>
     * <p>Creates a deep copy of <b>s</b>.</p>
     * 
     * @param ID
     * The ID of the row.
     * 
     * @param size
     * The length (amount of items) in the row.
     * 
     * @param s
     * The data within the row.
     */
    public Row(int ID, int size, String[] s)
    {
        this.ID = ID;
        this.size = size;
        this.data = new String[size];

        //Create a deep copy of s to feed into data.
        for(int i = 0; i < s.length; i++)
        {
            this.data[i] = s[i];
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
        return("Row " + getID() + ": " + getStringData());
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