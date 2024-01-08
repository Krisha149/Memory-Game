//board class

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class board2 extends Level
{
    public static String[][] board=new String[4][4];
    public static String[][] cards; 
   
    public static Scanner sc=new Scanner(System.in);


    //print the starting board for the level
    public void printFirstBoard()
    {
        setXY(getLevel());
        for(int i=0;i<getXY();i++)
        {
            for(int j=0;j<getXY();j++)
            {
                board[i][j]= " _ ";
            }
        }
    }

    //print the current board

    public void printBoard() 
    {
        System.out.print("\u001B[35m"); // Set text color to purple
    
        printLevel();
        for (int i = 0; i < 2; i++) 
        {
            System.out.println(" ");
        }
        System.out.print("    ");
        for (int i = 0; i < getXY(); i++) 
        {
            int c = i + 1;
            System.out.print("\u001B[35mC" + c + "  \u001B[0m"); // Reset color after printing label
        }
        System.out.println("");
    
        for (int i = 0; i < getXY(); i++) 
        {
            int r = i + 1;
            System.out.print("\u001B[35mR" + r + "|\u001B[0m");
            for (int j = 0; j < getXY(); j++) 
            {
    
                System.out.print(board[i][j]);
                System.out.print("|");
    
            }
            System.out.println();
        }
        System.out.print("\u001B[0m");
    }
    
    //check if the user passes the level and starts the next game
    public void playagain(int guess) 
    {
        if (getLevel() == 1 && guess < 30) 
        {
            System.out.println("\u001B[1;35mYou passed level 1!\u001B[0m");
            System.out.println("\u001B[1;35myou need to get all the matches in less than 25 guesses to pass level 2\u001B[0m");
            upLevel();
        } 
        else if (getLevel() == 2 && guess < 25) 
        {
            System.out.println("\u001B[1;35myou passed level 2!\u001B[0m");
            System.out.println("\u001B[1;35myou need to get all the matches in less than 20 guesses to pass level 3\u001B[0m");
            upLevel();
        } 
        else if (getLevel() == 3 && guess < 20) 
        {
            System.out.println("\u001B[1;35myou passed level 3!\u001B[0m");
            System.out.println("\u001B[1;35myou need to get all the matches in less than 15 guesses to pass level 4\u001B[0m");
            upLevel();
        } 
        else if (getLevel() == 4 && guess < 15) 
        {
            System.out.println("\u001B[1;35myou passed level 4!\u001B[0m");
            System.out.println("\u001B[1;35myou need to get all the matches in less than 10 guesses to pass level 5\u001B[0m");
            upLevel();
        } 
        else if (getLevel() == 5 && guess < 10) 
        {
            System.out.println("\u001B[1;35myou passed level 5!\u001B[0m");
            //System.out.println("\u001B[1;35myou need to get all the matches in less than 5 guesses to pass level 6\u001B[0m");
            System.out.println("\u001B[1;35myou passed all levels!!\u001B[0m");

            //upLevel();
        } 
        else 
        {
            System.out.println("\u001B[1;35myou got all match! sadly, you didn't beat the level. try again!\u001B[0m");
        }
        shuffleCards();
    
        printBoard();
        checkInput();
    }
    
    //organizer the board
    public void shuffleCards()
    {
        Random random=new Random();
        ArrayList<String> letters=new ArrayList<>();

        int num= getXY()*getXY();

        for(int k=0;k<num/2;k++)
        {
            letters.add(String.valueOf(k));
        }

        for(int n= 0;n<num/2;n++)
        {
            letters.add(String.valueOf(n));
        }

        int index;
        cards = new String[getXY()][getXY()];
        for(int i=0;i<getXY();i++)
        {
            for(int j=0;j<getXY();j++)
            {
                index = random.nextInt(letters.size());
                cards[i][j]=letters.get(index);
                letters.remove(index);
            }
        }
    }


    public void checkInput() 
    {
        int guess = 0;
        while (true) 
        {
            if (!gameOver()) 
            {
                System.out.println("\u001B[1;35mrow: (1-4)\u001B[0m");
                int row1 = sc.nextInt();
    
                if (row1 > 4) 
                {
                    System.out.println("\u001B[1;35mit has to be 1-4\u001B[0m");
                    System.out.println();
                    printBoard();
                    //continue;
                    break;
                }
    
                System.out.println("\u001B[1;35mcolumn: (1-4)\u001B[0m");
                int column1 = sc.nextInt();
    
                if (column1 > 4) 
                {
                    System.out.println("\u001B[1;35mit has to be 1-4\u001B[0m");
                    System.out.println();
                    printBoard();
                    //continue;
                    break;
                }
    
                if (!board[row1 - 1][column1 - 1].equals(" _ ")) 
                {
                    System.out.println("\u001B[1;35malready entered\u001B[0m");
                    if (board[row1 - 1][column1 - 1].equals(" * "))
                        continue;
                    else 
                    {
                        board[row1 - 1][column1 - 1] = " _ ";
                    }
                    System.out.println();
                    continue;
                } 
                else 
                {
                    board[row1 - 1][column1 - 1] = " " + cards[row1 - 1][column1 - 1] + " ";
                    printBoard();
                }
    
                System.out.println("\u001B[1;35mrow: (1-4)\u001B[0m");
                int row2 = sc.nextInt();
    
                if (row2 > 4) 
                {
                    System.out.println("\u001B[1;35mit has to be 1-4\u001B[0m");
                    System.out.println();
                    printBoard();
                    //continue;
                    break;
                }
    
                System.out.println("\u001B[1;35mcolumn: (1-4)\u001B[0m");
                int column2 = sc.nextInt();
    
                if (column2 > 4) 
                {
                    System.out.println("\u001B[1;35mit has to be 1-4\u001B[0m");
                    System.out.println();
                    printBoard();
                    //continue;
                    break;
                }
    
                if (!board[row2 - 1][column2 - 1].equals(" _ ")) 
                {
                    System.out.println("\u001B[1;35malready entered\u001B[0m");
                    if (board[row1 - 1][column1 - 1].equals(" * "))
                        continue;
                    else 
                    {
                        board[row1 - 1][column1 - 1] = " _ ";
                    }
                    System.out.println();
                    printBoard();
                    continue;
                } 
                else 
                {
                    board[row2 - 1][column2 - 1] = " " + cards[row2 - 1][column2 - 1] + " ";
    
                    if (board[row1 - 1][column1 - 1].equals(board[row2 - 1][column2 - 1])) 
                    {
                        printBoard();
                        System.out.println("\u001B[1;35mcorrect!\u001B[0m");
                        board[row1 - 1][column1 - 1] = " * ";
                        board[row2 - 1][column2 - 1] = " * ";
                    } 
                    else 
                    {
                        printBoard();
                        System.out.println("\u001B[1;35mfalse!!\u001B[0m");
                        board[row1 - 1][column1 - 1] = " _ ";
                        board[row2 - 1][column2 - 1] = " _ ";
                        printBoard();
                    }
                }
                guess++;
            } 
            else 
            {
                guess++;
                printBoard();
                System.out.println("\u001B[1;35mit took you" + guess + " guess to win!\u001B[0m");
                printFirstBoard();
                playagain(guess);
            }
        }
    }
       


    public boolean gameOver()
    {
        for(int i = 0;i<getXY();i++)
        {
            for(int j=0;j<getXY();j++)
            {
                if(board[i][j].equals(" _ "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
