//Ryan Schermerhorn
//Lab 6

import java.util.*;

public class Entry{

    //Initializations
    private HashMap<Integer, String> data;
    private int key;

    public Entry(int k, String fn, String ln, String ad, String c, String st, String z, String em, String ph) {//Constructor
        data = new HashMap<Integer, String>();
        this.key = k;
        data.put(0, fn); //0 = first name
        data.put(1, ln); //1 = last name
        data.put(2, ad); //2 = street address
        data.put(3, c); //3 = city
        data.put(4, st); //4 = state
        data.put(5, z); //5 = zip
        data.put(6, em); //6 = e-mail
        data.put(7, ph); //7 = phone number

        for(int i = 0; i <= 7; i++) //Replaces any null values
        {
            if(data.get(i) == null)
            {
                data.replace(i, "|Empty|");
            }
        }
    } 

    public int getKey() {
        return this.key;
    }

    public void changeKey(int k) {
        this.key = k;
    }

    public String getData(int type) {
        return data.get(type);
    }

    public String toString() {
        String s;
        s = data.get(0);
        for(int i = 1; i <= 7; i++)
        {
            s += ", " + data.get(i);
        }
        return s;
    }

    public void changeData(int type, String s) {
        if(s == null)
        {
            s = "|Empty|";
        }
        data.replace(type, s);
    }

}