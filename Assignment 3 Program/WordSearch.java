//Ryan Schermerhorn
//Assignment 1

import java.util.*;

public class WordSearch{
    
    public static void main(String[] args) {

        do{
            System.out.print("\t\tWelcome to my Wordsearch: ");
        }while(!Menu());
        System.out.println("Goodbye");
    }

    //Initializations
    private char[][] grid;
    private char[][] gridRand; //Grid with random letters
    private String[] list; //words that have been added
    private String[] defaultWords;
    private int[][] solution; //List of word solutions
    private int gridSize = 12;
    private int wordcount = 0;
    private int MaxWords = 10;
    private boolean[] found; //words that have been found
    private static Scanner input = new Scanner(System.in);

    public WordSearch() {//Constructor
        grid = new char[gridSize][gridSize];
        gridRand = new char[gridSize][gridSize];
        solution = new int[MaxWords][3]; 
        list = new String[MaxWords];
        defaultWords = new String[MaxWords];
        found = new boolean[MaxWords];
        defaultWords[0] = "sandwich";
        defaultWords[1] = "turtle";
        defaultWords[2] = "fish";
        defaultWords[3] = "bird";
        defaultWords[4] = "potato";
        defaultWords[5] = "quack";
        defaultWords[6] = "goblin";
        defaultWords[7] = "penguin";
        defaultWords[8] = "helmet";
        defaultWords[9] = "blooper";
    
        for(int i = 0; i < MaxWords; i++)
        {
            found[i] = false;
        }        
    }

    public void popGrid() { //Populates Grid
        for(int r = 0; r < gridSize; r++)
        {
            for(int c = 0; c < gridSize; c++)
            {
                    grid[r][c] = '.';
                    gridRand[r][c] = inCh(random(25));
            }
        }
        inputWords();
    }

    public void showGrid(boolean showAnswer) {
        System.out.print("  y ");//Y axis label
        for(int i = 1; i < gridSize + 1; i++)//Grid top
        {
            System.out.print(i);
            if(i < 10)
            {
                System.out.print(" ");
            }
            if(i < gridSize)
            {
                System.out.print(" ");
            }
        }
        System.out.println("|  ");
        System.out.print("x |");//X axis label
        for(int i = 1; i < gridSize + 1; i++)
        {
            System.out.print("---");
            if(i == gridSize)
            {
                System.out.println("|--");
            }
        }
        for(int r = 0; r < gridSize; r++)
        {
            
            if(r < 9)
            {
                System.out.print(" ");
            }
            System.out.print(r + 1);
            System.out.print("|");
            for(int c = 0; c < gridSize; c++)
            {
                if(grid[r][c] != '.') //Displays words
                {
                    if(grid[r][c] < 'a' && grid[r][c] != '.')//Checks if a word has been found yet
                    {
                        System.out.print("[" + grid[r][c] + "]");
                    }
                    else
                    {
                        System.out.print(" " + grid[r][c] + " ");
                    }
                }
                else
                {
                    if(!showAnswer)
                    {
                        System.out.print(" " + gridRand[r][c] + " "); //Show random characters
                    }
                    else
                    {
                        System.out.print(" " + grid[r][c] + " "); //Show "." instead of random letters
                    }
                }
            }
            System.out.println("|" + (r + 1));
        }
        System.out.print("--|");
        for(int i = 1; i < gridSize + 1; i++)
        {
            System.out.print("---");
            if(i == gridSize)
            {
                System.out.println("|--");
            }
        }
        System.out.print("  | "); //Grid bottom
        for(int i = 1; i < gridSize + 1; i++)
        {
            System.out.print(i);
            if(i < 10)
            {
                System.out.print(" ");
            }
            if(i < gridSize)
            {
                System.out.print(" ");
            }
        }
        System.out.println("|  ");
        if(showAnswer)//Shows answers
        {
            for(int i = 0; i < wordcount; i++)
            {
                System.out.println(solutionString(i));
            }
        }
        for(int i = 0; i < wordcount; i++)//Grid top
        {
            System.out.print(list[i]);
            if(found[i])
            {
                System.out.print(": Found");
            }
            if(!found[i])
            {
                System.out.print(": Not Found");
            }

            System.out.println("");
        }
        System.out.println("");
    }

    public boolean addWord(String w, int type, int px, int py) {
        char ch = 'a';
        char[][] temp = new char[gridSize][gridSize]; //Temporary grid so the real grid isnt changed until the word is validated
        boolean valid = true;
        px--;
        py--;

        if(wordcount > MaxWords)
        {
            return false;
        }

        for(int r = 0; r < gridSize; r++)//populates temp grid
        {
            for(int c = 0; c < gridSize; c++)
            {
                temp[r][c] = grid[r][c];
            }
        }
        for(int x = 0; x < w.length(); x++)
        {
            if(!valid)
            {
                return false;
            }
            ch = w.charAt(x);
            for(int r = 0; r < gridSize; r++)
            {
                for(int c = 0; c < gridSize; c++)
                {
                    //checks if a space is available, taken up by the same letter the word is trying to place, or taken up by a different letter

                    if(type == 1 && (r == py) && (c == px + x)) 
                    {
                        if(temp[r][c] == '.' || grid[r][c] == ch) //Across
                        {
                            temp[r][c] = ch;
                        }
                        else
                        {
                            valid = false;
                        }
                    }
                    if(type == 2 && (r == py + x) && (c == px + x)) 
                    {
                        if(temp[r][c] == '.' || grid[r][c] == ch)//Diagonal
                        {
                            temp[r][c] = ch;
                        }
                        else
                        {
                            valid = false;
                        }
                    }
                    if(type == 3 && (r == py + x) && (c == px)) 
                    {
                        if(temp[r][c] == '.' || grid[r][c] == ch)//Down
                        {
                            temp[r][c] = ch;
                        }
                        else
                        {
                            valid = false;
                        }
                    }
                }
            }
        }

        if(valid) //checks if the word was placed without any obstructions
        {
            
            for(int r = 0; r < gridSize; r++)
            {
                for(int c = 0; c < gridSize; c++)
                {
                    grid[r][c] = temp[r][c]; //adds new word to grid
                }
            }
            list[wordcount] = w; //adds word to list of active words
            solution[wordcount][0] = type; //Records location of the word
            solution[wordcount][1] = px + 1;
            solution[wordcount][2] = py + 1;
            wordcount++;
        }
        return valid;
    }

    public void addWordRand(String w) {
        int type = 0; //Direction the word points
        int px = 0; //First letter's location on the x axis
        int py = 0; //First letter's location on the y axis
        int tries = 0;
        do{
            type = random(3) + 1;
            if(type == 1)//across
            {
                px = random(gridSize - w.length()) + 1;
                py = random(gridSize) + 1;
            }
            else if(type == 2)//diagonal
            {
                px = random(gridSize - w.length()) + 1;
                py = random(gridSize - w.length()) + 1;
            }
            else//down
            {
                px = random(gridSize) + 1;
                py = random(gridSize - w.length()) + 1;
            }
            tries++;
            
        }while(!addWord(w, type, px, py) || tries > 99);//keeps trying until word is validated or it fails 100 times
    }
    
    public boolean check(String w, int type, int px, int py) {
        char ch = 'a';
        char[][] temp = new char[gridSize][gridSize];
        boolean valid = false;
        px--;
        py--;

        for(int r = 0; r < gridSize; r++)
        {
            for(int c = 0; c < gridSize; c++)
            {
                temp[r][c] = grid[r][c];
            }
        }

        for(int x = 0; x < w.length(); x++)
        {
            ch = w.charAt(x);
            for(int r = 0; r < gridSize; r++)
            {
                for(int c = 0; c < gridSize; c++)
                {
                    //Checks if the letter on the grid matches the corresponding letter of the input word in that location
                    if(type == 1 && (r == py) && (c == px + x)) 
                    {
                        if(grid[r][c] == ch) //Across
                        {
                            temp[r][c] = (char)(ch - 32); //sets to caps so the showgrid method can tell which letters are part of found words
                            valid = true;
                        }
                        else
                        {
                            return false;
                        }

                    }
                    if(type == 2 && (r == py + x) && (c == px + x)) 
                    {
                        if(grid[r][c] == ch)//Diagonal
                        {
                            temp[r][c] = (char)(ch - 32); //sets to caps so the showgrid method can tell which letters are part of found words
                            valid = true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    if(type == 3 && (r == py + x) && (c == px)) 
                    {
                        if(grid[r][c] == ch)//Down
                        {
                            temp[r][c] = (char)(ch - 32); //sets to caps so the showgrid method can tell which letters are part of found words
                            valid = true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
            }
        }

        if(valid)
        {
            
            for(int r = 0; r < gridSize; r++)
            {
                for(int c = 0; c < gridSize; c++)
                {
                    grid[r][c] = temp[r][c];
                }
            }

            for(int i = 0; i < wordcount; i++)
            {
                if(list[i] == w)
                {
                    found[i] = true;//Adds found word to list of found words
                    System.out.println("You found: " + w);
                }
            }
        }
        return valid;

    }

    public static boolean endGame(WordSearch w) { //Checks if all words have been found
        for(int i = 0; i < w.wordcount; i++)
        {
            if(!w.found[i])
            {
                return false;
            }
        }
        
        return true;
    }

    public String solutionString(int num) { //Returns location of a word as a string
        String answer = list[num] + " Direction: ";
            if(solution[num][0] == 1)
            {
                answer += "Across ";
            }
            else if(solution[num][0] == 2)
            {
                answer += "Diagonal ";
            }
            else if(solution[num][0] == 3)
            {
                answer += "Down ";
            }
            answer += "Starting at " + (solution[num][1]) + " " + (solution[num][2]);

            return answer;
    }

    public int chIn(char letter) { //Converts character to interger
        return (int) (Character.toLowerCase(letter) - 97);
    }

    public char inCh(int letter) { //Converts interger to character 
        return (char) (letter + 97);
    }

    public static int random(int range){
        Random rand = new Random();//to generate random numbers
        final int randomInt = rand.nextInt(range);
        return randomInt;
     }

    public void inputWords()
    {
        int Words = 0;
        int r = 0;
        boolean check = false;
        String temp = "";
        System.out.println("You can manually add words to find and/or use default words");
        do{
            System.out.print("How many words would you like to enter manually? (0-10): ");
            Words = input.nextInt();
            if(Words < 0 || Words > 10)
            {
                System.out.print("Must be a number between 0 and 10");
            }
        }while(Words < 0 || Words > 10);
        for(int i = 0; i < Words; i++)
        {
            //input words
            do{
                check = false;
                System.out.print("Enter Word "+ (i + 1) + ": ");
                temp = input.next();
                temp = temp.toLowerCase();
                if(temp.length() >= gridSize)
                {
                    System.out.println("Word is too long, pick a shorter one.");
                }
                for(int l = 0; l < temp.length(); l++)
                {
                    if(temp.charAt(l) > 'z' || temp.charAt(l) < 'a')
                    {
                        System.out.println("Illegal character: " + temp.charAt(l) + " Try again");
                        check = true;
                    }
                }
            }while(temp.length() >= gridSize || check); //checks if input is valid
            addWordRand(temp);
        }
        r = MaxWords - Words;

        //Randomly chooses from pre set words
        do{
            System.out.print("How many words would you to randomly generate? (0-" + r + "): ");
            Words = input.nextInt();
            if(Words < 0 || Words > 10)
            {
                System.out.println("Must be a number between 0 and " + r);
            }
        }while(Words < 0 || Words > 10);
        for(int i = (MaxWords - Words); i < MaxWords; i++)
        {
            do{
                check = false;
                r = random(10);
                for(int x = 0; x < MaxWords; x++)
                {
                    if(list[x] == defaultWords[r]) //checks if a word has already been added
                    {
                        check = true;
                    }
                }
            }while(check);

            addWordRand(defaultWords[r]);
        }
            
    }

    public void inputCheck()
    {
        String Word = "";
        int type = 0;
        int px = 0;
        int py = 0;

        System.out.print("Enter Word: ");
        Word = input.next();//User input's word to check

        do{
            System.out.println("Enter the word's direction: ");
            System.out.print("(1 for Across, 2 for Diagonal, 3 for Down): ");
            
            type = input.nextInt();
            if(type < 1 || type > 3)
            {
                System.out.print("Must be a number between 1 and 3");
            }
        }while(type < 1 || type > 3);

        do{
            System.out.print("Enter the first Letter's position on the X axis: ");
            px = input.nextInt();
            if(px < 1 || px > gridSize)
            {
                System.out.print("Must be a number between 1 and " + gridSize);
            }
        }while(px < 1 || px > gridSize);
        do{
            System.out.print("Enter the first Letter's position on the Y axis: ");
            py = input.nextInt();
            if(py < 1 || py > gridSize)
            {
                System.out.print("Must be a number between 1 and " + gridSize);
            }
        }while(px < 1 || px > gridSize);

        check(Word, type, px, py);
        
    }

    public static boolean Menu()
    {
        String cont;
        char contInput;
        WordSearch w = null;
        
        do
        {
            //menu prompt
            System.out.println("\nG for new game");
            System.out.println("A to find a word");
            System.out.println("P to print current wordsearch");
            System.out.println("S to get the solution to the current wordsearch");
            System.out.println("Q to quit");
            cont = input.next();
            contInput = cont.charAt(0);
            switch (contInput) //checks user input
            {
                case 'g': //New wordSearch
                case 'G': 
                    if(w == null || endGame(w)) 
                    {
                        w = new WordSearch();
                        w.popGrid();
                        w.showGrid(false);
                    }
                    else //If a game is in progress
                    {
                        w.showGrid(true);
                        System.out.println("Starting new word search");
                        w = new WordSearch();
                        w.popGrid();
                        w.showGrid(false);
                    }
                    contInput = 'o';
                    break;
                case 'a': //Find word
                case 'A': 
                    if(w != null)
                    {
                        w.inputCheck();
                        w.showGrid(false);
                    }
                    else
                    {
                        System.out.println("Must Start new wordsearch first!");
                    }
                    contInput = 'o';
                    break;
                case 'q': //Quit 
                case 'Q': 
                    return true; 
                case 'p': //Print Wordsearch
                case 'P': 
                    if(w != null)
                    {
                        w.showGrid(false);
                    }
                    else
                    {
                        System.out.println("Must Start new wordsearch first!");
                    }
                    contInput = 'o';
                    break;
                case 's': //Show solution
                case 'S': 
                    if(w != null)
                    {
                        w.showGrid(true);
                    }
                    else
                    {
                        System.out.println("Must Start new wordsearch first!");
                    }
                    contInput = 'o';
                    break;
                default: //check for other input
                    System.out.println("Invalid answer, try again\n");
                    contInput = 'o';
            };

            if(w != null)
            {
                if(endGame(w))//checks if game is over
                {
                    System.out.println("You found Every Word!");
                }
            }

        }while (contInput == 'o'); //repeats loop unless quit is chosen
        
        System.out.println("");

        return true;
        
    }

}
