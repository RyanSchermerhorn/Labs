import java.util.*;

public class Deck {

    private int[][] deck;
    private Stack<Integer> suit;
    private Stack<Integer> face;
    private int count;

    public Deck() { //Constructor
        shuffle();
    }

    public Card Draw() {

        if(count < 1) //Returns blank card if deck is empty to avoid errors
        {
            return new Card(-1, -1);
        }
        count--;
        return new Card(this.suit.pop(), this.face.pop());
    }

    public int CardCount() {
        return count;
    }

    public void shuffle() { //Populates/Repopulates deck
        
        int num1;
        int num2;

        deck = new int[4][13];
        suit = new Stack<Integer>();
        face = new Stack<Integer>();
        count = 52;

        Random rand = new Random();

        for(int i = 0; i < 52; i++)
        {
            do{
                num1 = rand.nextInt(4);
                num2 = rand.nextInt(13);
            }while(deck[num1][num2] != 0);

            deck[num1][num2] = 1;
            suit.push(num1);
            face.push(num2);
            
        }   
    }      

}