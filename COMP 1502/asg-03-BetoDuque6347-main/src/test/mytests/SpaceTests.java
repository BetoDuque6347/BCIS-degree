package test.mytests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.space.Col;
import main.space.Row;
import main.space.Space;

public class SpaceTests {

    @Nested
    class ColValTests
    {
        @Test
        public void colVal_with_value_1_returns_1() {

        //Given a space with row 1 and col 1
        Row row = null;
        row = row.at(1);

        Col col = null;
        col = col.at(1);

        Space space = null;
        space = space.from(row, col);

        //When the colVal() method is called
        int columnValue = space.colVal();

        //Then it will return 1
        assertEquals(1, columnValue);
        }

        @Test
        public void colVal_with_value_2_returns_2() {

        //Given a space with row 1 and col 2
        Row row = null;
        row = row.at(1);

        Col col = null;
        col = col.at(2);

        Space space = null;
        space = space.from(row, col);

        //When the colVal() method is called
        int columnValue = space.colVal();

        //Then it will return 2
        assertEquals(2, columnValue);
        }
    }

    @Nested
    class RowValTests
    {
        @Test
        public void rowVal_with_value_1_returns_1() {

        //Given a space with row 1 and col 1
        Row row = null;
        row = row.at(1);

        Col col = null;
        col = col.at(1);

        Space space = null;
        space = space.from(row, col);

        //When the rowVal() method is called
        int rowValue = space.rowVal();

        //Then it will return 1
        assertEquals(1, rowValue);
        }

        @Test
        public void rowVal_with_value_3_returns_3() {

        //Given a space with row 3 and col 1
        Row row = null;
        row = row.at(3);

        Col col = null;
        col = col.at(1);

        Space space = null;
        space = space.from(row, col);

        //When the rowVal() method is called
        int rowValue = space.rowVal();

        //Then it will return 3
        assertEquals(3, rowValue);
        }
    }
}
