//Ryan Schermerhorn
//Lab 3

import java.util.*;

public class LetterInventory{
    public static void main(String[] args) {//Tests all required methods
        LetterInventory inventory1 = new LetterInventory("Ryan Schermerhorn");
        LetterInventory inventory2 = new LetterInventory("duck");
        LetterInventory sumAdd = inventory1.add(inventory2);
        LetterInventory sumMinus = inventory1.subtract(inventory2);
        LetterInventory onlySymbols = new LetterInventory("\\>-</");

        System.out.println(inventory1.toString());
        System.out.println(inventory1.size());
        System.out.println(inventory1.get('R'));
        inventory1.set('b', 5);

        System.out.println(inventory1.toString());
        System.out.println(inventory2.toString());
        System.out.println(sumAdd.toString());
        System.out.println(sumMinus.toString());
        System.out.println(onlySymbols.toString());

    }

    private String d; //input data
    private int[] letters; //Array for inventory

    public LetterInventory(String data) {
        d = data;
        letters = new int[26];
        int temp;
        
        for(int s = 0; s < d.length(); s++)
        {
            temp = chIn(d.charAt(s));
            for(int i = 0; i < 26; i++)
            {
                if(temp == i)
                {
                    letters[i]++;
                }
            }
        }
    }

    public int get(char letter) {
        return letters[chIn(letter)];
    }

    public void set(char letter, int value) {
        letters[chIn(letter)] += value;
    }

    public int chIn(char letter) { //Converts character to interger
        return (int) (Character.toLowerCase(letter) - 97);
    }

    public char inCh(int letter) { //Converts interger to character 
        return (char) (letter + 97);
    }

    public int size() {
        int sz = 0;

        for(int i = 0; i < 26; i++)
        {
            sz = sz + letters[i];
        }
        return sz;
    }

    public boolean isEmpty() {
        for(int i = 0; i < 26; i++)
        {
            if(letters[i] > 0)
            {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String inventory = "[ ";
        if(!isEmpty())
        {
            for(int i = 0; i < 26; i++)
            {
                for(int c = 0; c < letters[i]; c++)
                {
                    inventory += inCh(i);
                }
            }
        }
        else
        {
            inventory += "Empty";
        }
        inventory += " ]";
        return inventory;
    }

    public LetterInventory add (LetterInventory other) {
        for(int i = 0; i < 26; i++)
        {
            set(inCh(i), other.get(inCh(i)));
        }
        return new LetterInventory(toString());
    }

    public LetterInventory subtract (LetterInventory other) {

        for(int i = 0; i < 26; i++)
        {
            set(inCh(i), -other.get(inCh(i)));
        }
        return new LetterInventory(toString());

    }

}

