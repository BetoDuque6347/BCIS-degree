package A1;

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
    private String data;

    //Getters and setters for instance variables
    public int getID() {return ID;}
    public String getData() {return data;}

    public void setID(int ID) {this.ID = ID;}
    public void setData(String Data) {this.data = Data;}

    /**
     * <p>
     *      Default Constructor.
     * </p>
     * 
     * <p>
     *      Assigns the default values to {@code ID} and {@code data}.
     * </p>
     */
    public Row()
    {
        this.ID = 0;
        this.data = null;
    }

    /**
     * <p>
     *      Overloaded Constructor.
     * </p>
     * 
     * <p>
     *      Allows for the creation of a {@code Row} with a specified {@code ID} and {@code data}.
     * </p>
     */
    public Row(int ID, String data)
    {
        this.ID = ID;
        this.data = data;
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
    public int compareTo(Row o) {
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
}