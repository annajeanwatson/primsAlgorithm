/*
* Anna Watson
* CSCI232 - Program 4
* This program inplements prim's algorithm for creating a minimum spanning tree.
* It reads in a matrix from a file and outputs the solution to a file.
* The height of the matrix, denoted as a public variable height is hardcoded into
* the function.
*/
package mymst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class MyMST {
    
    public static int height = 5;
    
    public void prim(int matrix[][]) throws FileNotFoundException
    {
        int pick[] = new int[height];
        int constructed[] = new int[height];
        Boolean notYetIncluded[] = new Boolean[height];
        
        for(int i = 0; i<height; i++)
        {
            pick[i] = Integer.MAX_VALUE;
            notYetIncluded[i] = false;
        }
        
        pick[0] = 0;
        constructed[0] = -1;
        
        for(int j = 0; j < height - 1; j++)
        {
            int minEdge = minEdge(pick, notYetIncluded);
            notYetIncluded[j] = true;
            
            for(int l = 0; l < height; l++)
            {
                if(matrix[j][l] != 0 && notYetIncluded[l] == false 
                        && matrix[j][l] < pick[l])
                {
                    constructed[l] = j;
                    pick[l] = matrix[j][l];
                }
            }
        }
        
        print(constructed, height, matrix);
    }
    
    public int minEdge(int pick[], Boolean notYetIncluded[])
    {
        int min = Integer.MAX_VALUE, index = -1;
        
        for(int k = 0; k < height; k++)
        {
            if(notYetIncluded[k] == false && pick[k] < min)
            {
                min = pick[k];
                index = k;
            }
        }
        return index;
    }
    
    public void print(int constructed[], int height, int matrix[][]) throws FileNotFoundException
    {
        
        PrintWriter wr;
        wr = new PrintWriter(new File("output.txt")); 
        wr.write("Edge     Weight");
        wr.write(System.getProperty("line.separator"));
        for(int i = 1; i < height; i++)
        {
            wr.write(constructed[i] + " - " + i + "     " + matrix[i][constructed[i]]);
            wr.write(System.getProperty("line.separator"));
        }
        wr.close();
    }


    public static void main(String[] args) throws FileNotFoundException 
    {
       
        MyMST mst = new MyMST();       
        
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        int rows = 5;
        int columns = 5;
        int [][] matrix = new int[rows][columns];
        while(scanner.hasNextLine()) {
             for (int i=0; i<matrix.length; i++) {
                String[] line = scanner.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        mst.prim(matrix);
    }
}
