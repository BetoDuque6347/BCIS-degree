package main;

import java.util.Scanner;

class Main {
    public static void main(String[] args) 
    {
        Scanner scnr = new Scanner(System.in);

        String letter = scnr.next();
        String word = scnr.nextLine();

        for(int i = 0; i < word.length(); i++)
        {
            char currLetter = word.charAt(i);
            
            int hand = 0;

            if (letter.equals(currLetter)) 
            {
                System.out.print("DONE!");
            }




        }

        System.System.out.println("Noo dont delete my codespace!");
    }
}