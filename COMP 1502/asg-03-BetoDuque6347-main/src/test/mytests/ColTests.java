package test.mytests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.space.Col;

public class ColTests {
    @Test
    public void col_with_value_1_is_equal_to_1() {
        
        //Given a col with a value of 1
        Col col = null; //Initializing a row like this feels wrong
        col = col.at(1);

        //When asked for its value with getVal()
        int colValue = col.getVal();

        //Then it will return 1
        assertEquals(1, colValue);
    }

    @Test
    public void col_with_value_2_is_equal_to_2() {
        
        //Given a col with a value of 1
        Col col = null; //Initializing a row like this feels wrong
        col = col.at(2);

        //When asked for its value with getVal()
        int colValue = col.getVal();

        //Then it will return 1
        assertEquals(2, colValue);
    }
}
