package test.mytests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.space.Row;

public class RowTests {
    @Test
    public void row_with_value_1_is_equal_to_1() {
        
        //Given a row with a value of 1
        Row row = null; //Initializing a row like this feels wrong
        row = row.at(1);

        //When asked for its value with getVal()
        int rowValue = row.getVal();

        //Then it will return 1
        assertEquals(1, rowValue);
    }

    @Test
    public void row_with_value_3_is_equal_to_3() {
        
        //Given a row with a value of 3
        Row row = null; //Initializing a row like this feels wrong
        row = row.at(3);

        //When asked for its value with getVal()
        int rowValue = row.getVal();

        //Then it will return 1
        assertEquals(3, rowValue);
    }
}
