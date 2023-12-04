// Ryan Schermerhorn
// CS 145
// Lab #1
// This program will generate a random number for the player to guess, and keep track of the stats of the game
// For extra credit I Made the main method as short as possible, added error protection, and added a switch statement

import java.util.Scanner;
import java.util.*;

public class RSGuess
{
    //Executes program
    public static void main(String[] args)
    {
        GuessingGame();
    }

    public static void GuessingGame()
    {
        Scanner input = new Scanner(System.in);
        
        int totalGuesses = 0;
        int guessCount = 0;
        int bestGame = 0;
        int gameCount = 0;
        final int range = 100; //maximum possible answer
        
        introduction(range); 

        while (loop(input))
        {
            gameCount++; 
            System.out.println("\tGame #" + gameCount + ": Start!"); 
            
            guessCount = game(input, range); //runs the game and returns the number of guesses made
            totalGuesses += guessCount;
            
            if(guessCount < bestGame || bestGame == 0)
            {
                bestGame = guessCount;
            }
            
        }
        
        if(gameCount > 0)
        {
            stats(totalGuesses,gameCount, bestGame);
        }
        else
        {
            System.out.println("");
        }
        
    }

    //Checks whether the player wants to play again or not
    public static boolean loop(Scanner input)
    {
        boolean end = false; 
        String cont;
        char contInput;
        
        do
        {
            System.out.print("\t\tStart New Game? (y/n): ");
            cont = input.next();
            contInput = cont.charAt(0);
            switch (contInput) //checks user input
            {
                case 'y': //play new game 
                case 'Y': 
                    end = true;
                    break;  
                case 'n': //end game 
                case 'N': 
                    end = false;
                    break;
                default: //Error bucket if the first character is not y or n
                    System.out.println("Invalid answer, try again\n");
                    contInput = 'o';
            };

        }while (contInput == 'o'); //Asks again if the player inputs something that doesn't start with y or n
        
        System.out.println("");
        
        return end;
    }
    
    //Checks whether or the player's answer is correct or if it's too high or low
    public static int game(Scanner input, int range)
    {
        Random rand = new Random();
        int num = rand.nextInt(range) + 1; //Sets random number for the game
        int guess = 0;
        int guessCount = 0;
         
        do
        {
            do //get player guess
            {
                System.out.print("\nGuess a Number between 1 and " + range + ": "); 
                
                guess = input.nextInt();
            }while(guess < 1 || guess > range);
            
            guessCount++;

            if(num > guess) //guess is too low
            {
                System.out.println("\n\t" + guess +" is too low!");
            }
            else if(num < guess)  //guess is too high
            {
                System.out.println("\n\t" + guess +" is too high!");
            }
            else if(num < 1 || num > range) //guess is out of range
            {
                System.out.println("Guess must be between 1 and " + range + ".");
            }

        }while(num != guess);
        
        System.out.print("\n\tYou got it right in " + guessCount + " guess");
        
        if (guessCount > 1) //Checks if the player got the answer in a single guess
        {
            System.out.print("es");
        }
        System.out.println("!\n");
                
        return guessCount;
    }
    
    //Introduces the game to the player
    public static void introduction(int range)
    {
        System.out.println("This program generates a random number between 1 and " + range + ".");
        System.out.println("If you can correctly guess that number, you win!");
        System.out.println("If you guess wrong, you will be told whether");
        System.out.println("your answer is too high or too low.\n");
    }
    
    //Introduces the game to the player
    public static void stats(int totalGuesses, int gameCount, int bestGame)//Outputs game stats
    {
        double guessAvg = totalGuesses; 
        guessAvg /= gameCount; //Calculates guesses per game
        
        System.out.println("\nStats: \n"); 
        System.out.println(" - Total Games = " + gameCount);
        System.out.println(" - Total Guesses = " + totalGuesses);
        System.out.println(" - Guesses / Game = " + guessAvg);
        System.out.println(" - Best Game = " + bestGame);
    }

    
} //end class