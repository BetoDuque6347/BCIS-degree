package A2;

public class A2 
{
    public static void main(String[] args) throws Exception
    {
        A2 a2 = new A2();
        a2.run();
    }

    public void run() throws Exception
    {
        int rowsToPrint = 0;
        String columnColour = "colour";
        String colourToFind = "black";
        String[] columnsToFind = {"species", "count", "notes"};


        //Create the table using the specified .csv
        Table table = new Table("COMP 2503\\A2\\a2_data.csv");

        //Part 1 of the assignment
        System.out.println("Part 1:\n");
        System.out.println("Number of rows in the table: " + table.getRows());
        System.out.println(table.printTable(rowsToPrint));

        //Part 2 of the assignment
        System.out.println("\nPart 2:");
        table.sortColour();
        System.out.println(table.printTable(rowsToPrint));
        table.sortNatural(); //Undo the previous sort, since it breaks things and acts weird.

        //Part 3 of the assignmnet
        System.out.println("\nPart 3:\n");
        Table tableWithOnlyBlack = table.select(columnColour, colourToFind);
        System.out.println(tableWithOnlyBlack.printTable(rowsToPrint));

        //Part 4 of the assignment
        System.out.println("\nPart 4:\n");
        Table tableWithSpeciesCountNotes = table.project(columnsToFind);
        System.out.println(tableWithSpeciesCountNotes.printTable(rowsToPrint));
    }
}
