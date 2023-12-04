//Ryan Schermerhorn
//Lab 3
import java.util.*;

public class CardsMain{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Deck D = new Deck(); //Creates new deck
        int players = 4;
        Game G = new Game(players, D, input);
        G.Play();
    }
}

