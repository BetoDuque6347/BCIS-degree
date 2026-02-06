package main;

import java.util.Scanner;

class SushiGoScore
{
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        int myInt;
        
        do 
        {
            System.out.print("Enter a number: ");
            myInt = keyboard.nextInt();
        }
        while(myInt != -1);
    }
}