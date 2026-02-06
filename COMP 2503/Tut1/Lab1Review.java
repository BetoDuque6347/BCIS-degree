package Tut1;
import java.util.Scanner;

/**
 * Fuckass string replacement i ain gon lie
 */
public class Lab1Review
{
    public void run()
    {
        Scanner input = new Scanner(System.in);
        String output = "";

        System.out.print("Enter a sentence: ");
        String sentence = input.nextLine();

        //Surrunds output with double quotation marks, then replaces each space with '" "'
        output += "\"";
        output += sentence.replace(" ", "\" \"");
        output += "\"";

        System.out.println(output);
    }

    public static void main(String[] args) 
    {
        Lab1Review lab = new Lab1Review();
        lab.run();
    }
}