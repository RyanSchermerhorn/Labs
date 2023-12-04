//Ryan Schermerhorn
//Lab 6 

import java.util.*;

public class BinarySearchTree{
    
    public static void main(String[] args) {
        BinarySearchTree b = new BinarySearchTree();
    }

    //Initializations
    private TreeMap<Integer, Entry> map;
    private int eCount = 0;
    private static Scanner input = new Scanner(System.in);

    public BinarySearchTree() {//Constructor
        map = new TreeMap<>();
        //Example entries
        addEntry("Ryan", "Schermerhorn", "123ABC", "Bellingham", "WA", "98225", "ryan@ryan.ryan", "555-555-5555");
        addEntry("John", "Smith", "213", "New York", "NY", "44988", "jSmith@bloop.doop", "555-555-5555");
        addEntry("Fred", "Jones", "224", "Tennessee", "Nashvilel", "67673", "jSmith@ham.sandwich", "123-456-789");
        do{ //Runs menu
            System.out.print("\t\tChoose an option: ");
        }while(!Menu());
    } 

    public boolean Menu()//add, delete, modify, lookup, list number of records
    {
        String cont;
        String s;
        char contInput;
        int x;
        int y;
        do
        {
            //menu prompt
            System.out.println("\nA to add new entry");
            System.out.println("D to delete an entry");
            System.out.println("M to modify an entry");
            System.out.println("S to search entries");
            System.out.println("P to list all records");
            System.out.println("L to list a specific records");
            System.out.println("Q to quit");
            cont = input.next();
            contInput = cont.charAt(0);
            switch (contInput) //checks user input
            {
                case 'a': //add
                case 'A': 
                    System.out.println("Enter the Following:");
                    System.out.println("First Name: ");
                    String fn = input.next();
                    System.out.println("Last Name: ");
                    String ln = input.next();
                    System.out.println("Address: ");
                    String ad = input.next();
                    System.out.println("City: ");
                    String c = input.next();
                    System.out.println("State: ");
                    String st = input.next();
                    System.out.println("Email: ");
                    String z = input.next();
                    System.out.println("Zip: ");
                    String em = input.next();
                    System.out.println("Phone: ");
                    String ph = input.next();
                    addEntry(fn, ln, ad, c, st, z, em, ph);
                    contInput = 'o';
                    break;
                case 'd': //delete
                case 'D': 
                    System.out.println("Choose an entry to delete: ");
                    x = input.nextInt();
                    if(map.get(x) != null)
                    {
                        System.out.println("Entry " + x + ": " + map.get(x).toString());
                        System.out.print("Are you sure you wany to delete this entry? ");
                        if(askYN())
                        {
                            removeEntry(x);
                        }
                    }
                    else
                    {
                        System.out.println("Entry number " + x + "not found");
                    }
                    contInput = 'o';
                    break;
                case 'm': //modify
                case 'M': 
                    System.out.println("Choose an entry to modify: ");
                    x = input.nextInt();
                    if(map.get(x) != null)
                    {
                        System.out.println("Entry " + x + ": " + map.get(x).toString());
                        y = datatypes();
                        s = input.next();
                        System.out.print("Enter New data:");
                        map.get(x).changeData(y, s);
                    }
                    else
                    {
                        System.out.println("Entry number " + x + "not found");
                    }
                    contInput = 'o';
                    break;
                case 's': //Search
                case 'S': 
                    y = datatypes();
                    System.out.print("Enter data to search for (Case Sensitive): ");
                    s = input.next();
                    searchData(s.toLowerCase(), y);
                    searchData("Ryan", 0);
                    contInput = 'o';
                    break;
                case 'q': //Quit 
                case 'Q': 
                    return true; 
                case 'p': //Print
                case 'P': 
                    for(int i = 1; i <= map.size(); i++)
                    {
                        System.out.println(map.get(i).toString());
                    }
                    contInput = 'o';
                    break;
                case 'l': //Print
                case 'L': 
                    System.out.println("Choose an entry to display: ");
                    x = input.nextInt();
                    if(map.get(x) != null)
                    {
                        System.out.println(map.get(x).toString());
                    }
                    else
                    {
                        System.out.println("Entry number " + x + "not found");
                    }
                    contInput = 'o';
                    break;
                default: //check for other input
                    System.out.println("Invalid answer, try again\n");
                    contInput = 'o';
            };


        }while (contInput == 'o'); //repeats loop unless quit is chosen
        
        System.out.println("");

        return true;
        
    }

    public int searchData(String s, int type) {//s = String to search for, t = type of data 
        int find = -1;
        String temp = "";
        for(int i = 1; i <= eCount; i++)
        {
            if(map.get(i).getData(type) != null)
            {
                temp = map.get(i).getData(type);
                if(temp == s)
                {
                    if(find == -1)
                    {
                        find = i;
                        System.out.print(s + " Found at key(s): " + find);
                    }
                    else
                    {
                        System.out.print(", " + i);
                    }
                    
                }
            }
        }
        if(find == -1) //if no matches are found
        {
            System.out.print(s + " Not Found");
        }
        System.out.println("");

        return find; //returns first match
    } 

    public void addEntry(String fn, String ln, String ad, String c, String st, String z, String em, String ph) {
        eCount++;
        Entry e;
        e = new Entry(eCount, fn, ln, ad, c, st, z, em, ph);
        map.put(eCount, e);
    } 

    public void removeEntry(int key) { 
        Entry e;
        for(int i = key; i < eCount; i++)
        {
            e = map.get(i + 1);
            map.put(i, e);
        }
        map.remove(eCount);
        eCount--;
    } 

    public void sort(int type) { //sorts alphabetically by type
        TreeMap<String, Entry> temp = new TreeMap<>();
        int counter = 1;
        for(int i = 1; i <= eCount; i++)
        {
            temp.put(map.get(i).getData(type), map.get(i));
        }
        for (String i : temp.keySet())
        {
            map.replace(counter, temp.get(i));
            map.get(counter).changeKey(counter);
            counter++;
        }
    } 

    public static boolean askYN() //asks for y or n input
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

    public int datatypes(){ //returns number for corresponding data type
        int x = -1;
        System.out.println("1 = first name");
        System.out.println("2 = last name");
        System.out.println("3 = street address");
        System.out.println("4 = city");
        System.out.println("5 = state");
        System.out.println("6 = zip");
        System.out.println("7 = e-mail");
        System.out.println("8 = phone number");
        
        while(x < 1 || x > 8)
        {
            System.out.print("Choose a Data type (1-8)");
            x = input.nextInt();
        }
        x--;
        return x;
    }
}