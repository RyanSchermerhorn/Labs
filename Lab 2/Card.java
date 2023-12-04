public class Card {

    public static enum Suit {
        Hearts, Clubs, Diamonds, Spades, None
    };

    private int s;
    private int f;
    
    private Suit self;
    private String name;
    
    public Card(int suit, int face) { //Constructor
        s = suit;
        f = face;
    }

    public Suit getSuit() {
        switch(s)
        {
            case 0: 
                self = Suit.Hearts;
                break;
            case 1: 
                self = Suit.Clubs;
                break;
            case 2: 
                self = Suit.Diamonds;
                break;
            case 3: 
                self = Suit.Spades;
                break;
            default:
                self = Suit.None; //For when a card is drawn from an empty deck
        }
        return self;
    }

    public int getFace() {
        return f + 1;
    }

    public String toString() {
        name = "";
        switch(f)
        {
            case 0: 
                name += "Ace";
                break;
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            name += (f + 1);
                break;
            case 10: 
            name += "Jack";
                break;
            case 11: 
            name += "Queen";
                break;
            case 12: 
            name += "King";
                break;
        }
        switch(s)
        {
            case 0: 
            name += " of Hearts";
                break;
            case 1: 
            name += " of Clubs";
                break;
            case 2: 
            name += " of Diamonds";
                break;
            case 3: 
            name += " of Spades";
        }
        if(f == -1)//For when a card is drawn from an empty deck
        {
            name = "Nothing";
        }
        return (name);
    }


}