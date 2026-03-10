package A2;

import java.util.Comparator;

/**
 * A comparator that orders {@code Rows} by their colour, in alphabetical order.
 * 
 * <p>COMP 2503</p>
 * @author Beto Duque
 */
public class ColourColumnComparator implements Comparator<Row>
{
    /**
     * Sorts two {@code Rows} and sorts them based on their colour, alphabetically.
     * 
     * @param r1
     * The initial {@code Row} to be compared.
     * @param r2
     * The other {@code Row} to be compared.
     * 
     * @return
     * A <b>negative integer, zero, or a positive integer</b> if the data within <b>r1</b> is greater than, equal to, or less than <b>r2</b>.
     */
    @Override
    public int compare(Row r1, Row r2)
    {
        final int HEADER_INDEX = 0;
        final String COLOUR_COLUMN = "colour";
        int colourColumnIndex = 0;

        //Find the index of the colour column
        if(r1.getID() == HEADER_INDEX)
        {
            for(int i = 0; i < r1.getSize(); i++)
            {
                if(r1.getColumnAt(i).equals(COLOUR_COLUMN));
                {
                    colourColumnIndex = i;
                    break;
                }
            }
        }

        String row1Colour = r1.getData()[colourColumnIndex];
        String row2Colour = r2.getData()[colourColumnIndex];

        return row1Colour.compareToIgnoreCase(row2Colour);
    }
}