//Trevor Figgins, Dean Serrano, Ryan Schermerhorn
//CS 145
//09/26/2023
//Lab 1: Guessing Game
//Assignment/Purpose: To prompt the user is they'd like to play a guessing game
// ask them to guess what number we have and tell them higher or lower depending on the input
// as well as keep track of the number of games played, how many guesses, and what was the best number of guesses

import java.util.Scanner;
import java.util.Random;

public class TF_DS_RSGuessingGame {

   public static void main(String[] args){
      
      Scanner input = new Scanner(System.in);// for user input

      //stats
      int best = 100;
      int guesses;
      int rounds = 0;
      int games = 0;
      
      userPrompt(); //intro

      while(loop(input)){ //checks if you want to play
         guesses = theGame(random());
         games += guesses;
         rounds++;

         if(guesses < best) //sets best score
         {
             best = guesses;
         }

      }

      stats(games, rounds, best);
   }   
   
   public static int random(){
      Random rand = new Random();//to generate random numbers
      final int randomInt = rand.nextInt(100) + (1);
      return randomInt;
   }
   
   public static int theGame(int randomInt){          
      int rounds = 0;
      int i = 1;
      while(i == 1){
         int userGuess = userGuess();
         rounds++;
         if (userGuess > randomInt){
            System.out.println("lower");

         } else if (userGuess < randomInt){
            System.out.println("higher");

         }else if(userGuess == randomInt){
            System.out.printf("you got it right in %d guess",rounds);

            if(rounds != 1)//checks if it took more than one guess
            {
               System.out.println("es");
            }
            i++;       
         }
      }
      return rounds;
   }

    //Ccheck if user wants to play again
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
                default: //check for other input
                    System.out.println("Invalid answer, try again\n");
                    contInput = 'o';
            };

        }while (contInput == 'o'); //Asks again if the player inputs something that doesn't start with y or n
        
        System.out.println("");
        
        return end;
    }

   public static void stats(int totalGuesses, int rounds, int bestGame)//Outputs game stats
    {
        double guessAvg = totalGuesses; 
        guessAvg /= rounds; //Calculates guesses per game
        
        System.out.println("\nStats:"); 
        System.out.println(" - Total Games = " + rounds);
        System.out.println(" - Total Guesses = " + totalGuesses);
        System.out.println(" - Guesses / Game = " + guessAvg);
        System.out.println(" - Best Game = " + bestGame);
    }
   
   public static int userGuess(){     
      Scanner input = new Scanner(System.in);// for user input
      System.out.println("I'm thinking of a number between 1 and 100...\n");
      System.out.print("Your guess? "); 
      return input.nextInt();
   } 
   
   public static void userPrompt() { 
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and");
      System.out.println("100 and will allow you to guess until");
      System.out.println("you get it. For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.println("than your guess.");
   }



}