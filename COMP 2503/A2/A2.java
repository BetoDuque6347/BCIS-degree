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
        Table table = new Table("COMP 2503\\A2\\a2_data.csv");

        System.out.println(table.printTable(0));
    }
}
