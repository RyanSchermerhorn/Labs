//Ryan Schermerhorn
//Lab 5 

import java.util.*;
import java.awt.*; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 

public class PascalsTriangle extends Frame {
    
    public static void main(String[] args) {
        PascalsTriangle gui = new PascalsTriangle();
        Graphics g = gui.getGraphics(); 
    }

    //Initializations
    private int windowSize;
    private int tSize; //How many layers the triangle has
    private int[][] tri; //Array for the triangle
    private static HashMap<Integer, String> triString; //What will be used to display the triangle
    private Font f;

    public PascalsTriangle() {//Constructor
        tSize = 5;
        windowSize = 500;
        triString = new HashMap<Integer, String>();
        tri =  new int[tSize + 1][tSize + 1];
        f = new Font("MONOSPACED", 0, ((windowSize / ((tSize) * 2)))); //Monospace, plain
        String s = "";
        
        tri[0][1] = 1; //First point on the triangle
        
        for(int i = 0; i < tSize; i++)
        {
            for (int x = 1; x < tSize + 1; x++) 
            {
                tri[i + 1][x] = tri[i][x] + tri[i][x - 1]; //Populates Triangle
            }
        }
        
        if(tSize > 5) //Warning for making the triangle too big
        {
            System.out.println("Formatting breaks on any triangle larger than 5");
        }

        for(int i = 0; i < tSize; i++) //Turning the array into a string and putting it into the hashmap
        {
                for (int x = 0; x < tSize - i; x++) {
                    s += " ";
                }
                for (int x = 0; x <= tSize; x++) {
                
                    if(tri[i][x] != 0)
                    {
                        s += tri[i][x] + " ";
                    }
                }
                triString.put(i, s);
                s = "";
                System.out.println(triString.get(i));
        }

        for(int i = tSize; i < (tSize * 2); i++) //Extra formatting for the background
        {
                for (int x = 0; x < tSize - (i - tSize) - 1; x++) {
                    s += " ";
                }
                s += "/";
                for (int x = 2; x <= tSize; x++) {
                    if(tri[i - tSize][x] != 0)
                    {
                        s += " |";
                    }
                }
                s += " \\";
                triString.put(i, s);
                s = "";
                System.out.println(triString.get(i));
        }

        //Sets canvas
        setBackground(Color.WHITE);
        setVisible(true); 
        setSize(windowSize, windowSize); 
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            } 
        }); 
        
    }

    public void paint(Graphics g) 
    { 
        int grid = windowSize / (tSize + 1);
        g.setColor(Color.BLACK);
        g.setFont(f);
        for(int i = 0; i < tSize; i++)//Shows the triangle
        {  
            g.drawString(triString.get(i), grid, grid * (1 + i));
        }

        for(int i = 0; i < tSize; i++)//Extra formatting
        {  
            g.drawString(triString.get(i + tSize), grid - 1, grid * (1 + i));
            g.drawString(triString.get(i + tSize), grid + 1, grid * (1 + i));
        }
        g.setColor(Color.RED);
        for(int i = 0; i < tSize; i++)
        {  
            g.drawString(triString.get(i + tSize), grid, grid * (1 + i));
        }
    } 


}