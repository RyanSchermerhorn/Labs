import java.util.*;

public class Game{

    private static int players;
    private static Deck D;
    private static Scanner input;

    public Game(int pl, Deck dk, Scanner i){
        players = pl;
        D = dk;
        D.shuffle();
        input = i;
    }

    public void Play()
    {
        do{
            System.out.println("Now playing: 21!\n");
            System.out.println("-------------\nWinner:  Player " + TwentyOne() + "\n-------------");
            System.out.print("Play again?");
        }while(askYN(input));
    }

    private static int TwentyOne()
    {
        Card[][] hands = new Card[4][56]; 
        int total[] = new int[players]; //hand total
        boolean ingame[] = new boolean[players]; //Who hasn't lost yet
        int winner = -1;
        int outcount = 0; //how many players are out
        int drawcount = 0;
        int firstdraw = 1; //maxes an extra card be drawn on the first turn

        for(int p = 0; p < players; p++)
        {
            ingame[p] = true;
        }

        do{
            for(int p = 0; p < players; p++)
            {
                if(ingame[p] && (outcount != players - 1))
                {
                    System.out.println("player " + (p + 1) + " Draws: ");
                    for(int i = 0; i < 1 + firstdraw; i++)
                    {
                        if(p == 0 && firstdraw == 0) //drawing for player after first turn
                        {
                            System.out.println("Player " + (p + 1)+ " total: " + total[p]);
                            System.out.print("Draw another card?");
                            if(askYN(input))
                            {
                                hands[p][drawcount] = D.Draw();
                                System.out.println(" - " + hands[p][drawcount].toString());
                            
                            total[p] += hands[p][drawcount].getFace(); 
                            
                            drawcount++;
                            }
                        }
                        else //drawing for everyone else
                        {
                            hands[p][drawcount] = D.Draw();
                            System.out.println(" - " + hands[p][drawcount].toString());
                            
                            total[p] += hands[p][drawcount].getFace(); 
                            
                            drawcount++;
                        }
                    }
                    if(total[p] > 21)//Bust
                    {
                        ingame[p] = false;
                        outcount++;
                        System.out.println("player " + (p + 1)+ " total: " + total[p] + " Bust");
                    }
                    else//shows total in hand
                    {
                        System.out.println("player " + (p + 1)+ " total: " + total[p]);
                    }

                }
                
            }

            firstdraw = 0;

            for(int p = 0; p < players; p++)
            {
                if(ingame[p] && (total[p] == 21 || outcount == players - 1))//chooses the winner
                {
                        winner = p;
                }
            }
                        

        }while(winner < 0);

        System.out.println("-------------\nScore:");//scores
        for(int p = 0; p < players; p++)
            {
                if(ingame[p])
                {
                    System.out.println("-Player " + (p + 1) + " total: " + total[p]);
                }
                else{
                    System.out.println("-Player " + (p + 1) + " Bust");
                }
            }

        return winner;
    }

    public static boolean askYN(Scanner input) //asks for y or n input
    {
        boolean answer = false; 
        String in;
        char inChar;
        do
        {
            System.out.print(" (y/n): ");
            in = input.next();
            inChar = in.charAt(0);
            switch (inChar) //checks user input
            {
                case 'y': //Yes 
                case 'Y': 
                    answer = true;
                    break;  
                case 'n': //No 
                case 'N': 
                    answer = false;
                    break;
                default: //check for other input
                    System.out.println("Invalid answer, try again\n");
                    inChar = 'o';
            };

        }while (inChar == 'o'); //Asks again if the player inputs something that doesn't start with y or n
        
        System.out.println("");
        
        return answer;
    }
}

