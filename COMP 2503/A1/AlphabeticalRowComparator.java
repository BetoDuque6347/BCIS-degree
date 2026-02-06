package A1;

import java.util.Comparator;

/**
 * A comparator that orders {@code Rows} in ascending alphabetical order.
 * 
 * <p>
 *      COMP 2503
 *      @author Beto Duque
 * </p>
 */
public class AlphabeticalRowComparator implements Comparator<Row>
{
    /**
     * Sorts two {@code Rows} and compares them alphabetically.
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
        //Take the data within each row then convert them to Strings.
        String firstRow = r1.getData();
        String secondRow = r2.getData();

        //Kind of feels like cheating using the built in String comparator method but if it works then it works!
        return firstRow.compareToIgnoreCase(secondRow);
    }
}
