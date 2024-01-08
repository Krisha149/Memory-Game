//Memory game in java (using xampp and create table is mandatory)


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 class Main5 
 {
             public static Scanner sc=new Scanner(System.in);
             static  Connection con;
             static String Name;
             static PreparedStatement pst;
             
            static int level=1;
            static int id;
             static int i = 3;

   
public static void main(String[] args) throws Exception 
{

        
String dburl = "jdbc:mysql://localhost:3306/game";
String dbuser="root";
String dbpass="";
String driverName="com.mysql.cj.jdbc.Driver";
//Step:1: Load Driver
Class.forName(driverName);
//Step:2:Connect with db
 con=DriverManager.getConnection(dburl,dbuser,dbpass);

if(con!=null)
{
System.out.println();
System.out.println("\u001B[1;35mConnection Successfull....\u001B[0m");
System.out.println();
}
else
{
    System.out.println("\u001B[1;31mConnection failed\u001B[0m");
}
System.out.println("\u001B[1;35m          ENTER 1 FOR LOGIN\u001B[0m");// purple and bold
System.out.println("\u001B[1;35m          ENTER 2 FOR SIGNUP\u001B[0m");// purple and bold
System.out.println("\u001B[1;35m          ENTER 3 FOR EXIT\u001B[0m");// purple and bold




int n;
try
{
     n=sc.nextInt();
       boolean b=true;
    while(true)
    {
    switch(n)
    {
    case 1:
    {
        LOGIN();
        break;

    }
    case 2:
    {
        id = (int)(Math.random()*((10-1)+1)+1);
        SIGNUP();
        break;
    }
    case 3:
    {
        System.exit(0);


    }

    }

    }

}
catch(Exception e)
{
System.out.println("ENTER PROPERVALUE");
Main5.main(null);

}
}


///////////////////////////////////////////
   static public void run_game()
    {
        //crete a board
        board2 myBoard=new board2();
        myBoard.getLevel();
        System.out.println("          \u001B[33mHELLO WELCOME " + Name + "\u001B[0m");//yellow
        
        while(true)
        {
            System.out.println("\u001B[1;35mpress n for new game and  q to quit");
            String nq=sc.next();
            if(nq.equalsIgnoreCase("q"))
            {
                System.out.println("\u001B[1;35mExiting....\u001B[0m");

                break;
            }
            else if(nq.equalsIgnoreCase("n"))
            {
                System.out.println("\u001B[1;35myou need to get all the matches within 30 guesses to beat level 1. good Luck\u001B[0m");// bp
                myBoard.shuffleCards();
                myBoard.printFirstBoard();

                myBoard.printBoard();
                myBoard.checkInput();   
            }
            else
            {
                System.out.println("\u001B[1;35mInvalid characters\u001B[0m");
                continue;
            }
            System.out.println("\u001B[0m");

        }
    }
    static void LOGIN() throws Exception
    {


    while(i>0)
    {
    System.out.println("\u001B[1;35mENTER EMAIL:\u001B[0m");
    String s = sc.next();
    System.out.println("\u001B[1;35mENTER PASSWORD:\u001B[0m");
    String s1 = sc.next();
    
    String query = "SELECT Password,Player_name FROM memory_game WHERE Player_email=?";

    try (PreparedStatement preparedStatement = con.prepareStatement(query)) 
    {
        preparedStatement.setString(1, s);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) 
        {
            String storedPassword = resultSet.getString("Password");
              Name = resultSet.getString("Player_name");


            // Compare the entered password with the stored password
            if (s1.equals(storedPassword)) 
            {
                System.out.println("\u001B[1;35mPassword Matched! Access Granted\u001B[0m");
                try
                {
                    run_game();
                }
                catch(Exception e)
                {
                   
                    System.out.println("enter integer value not string value");
                    Main5.main(null); 
                
                }
                break;
                
                
            } 
            else 
            {
               
                if(i==0)
                {
                    System.out.println("\u001B[1;31mYOUR ALL ATTEMPTS ARE OVER\u001B[0m");
                    System.out.println("\u001B[1;31mYOU ARE EXITING......\u001B[0m");
                    
                      System.exit(0);
                }
                else
                {
                    System.out.println("\u001B[1;31mPassword Mismatch! Access Denied\u001B[0m");
                    i--;
                    System.out.println("\u001B[1;31mYOU HAVE " + i + " ATTEMPTS LEFT.....\u001B[0m");

                }
                

            }
        }
         else 
        {
            System.out.println("\u001B[1;31mUser not found\u001B[0m");
            System.out.println("\u001B[1;31mYOU ARE EXITING......\u001B[0m");
            System.exit(0);
            
        }
    }
    catch(Exception e)
    {
        System.out.println("\u001B[31m"+"NO such user exist! signup!"+"\u001B[0m");
        SIGNUP();

    }
 }
}


static void SIGNUP() throws Exception
{
    i++;
    
    System.out.println("ENTR NAME:");
    sc.nextLine();
    String name=sc.nextLine();
    System.out.println("ENTER EMAIL:");
    String Email=sc.nextLine();
    System.out.println("ENTER PASSWORD:");
    String pass=sc.nextLine();

    String sql2="Insert into memory_game (Player_id,Player_name,Player_email,Password,Status) values (?,?,?,?,?)";
       pst=con.prepareStatement(sql2);
       pst.setInt(1,id);
       pst.setString(2, name);
       pst.setString(3, Email);
       pst.setString(4, pass);
       pst.setInt(5, level);
       int r=pst.executeUpdate();
       if(r>0)
       {
        System.out.println("\u001B[1;35mINSERTED SUCCESSFULLY...\u001B[0m");
        System.out.println("\u001B[1;35mWELCOME TO LOGIN PAGE\u001B[0m");

        LOGIN();
       }
       else
       {
        System.out.println("\u001B[1;31mNOT INSERTED\u001B[0m");
        
       }
}


public static boolean isStrongPassword(String password) 
{
        // Check length (at least 8 characters)
        if (password.length() < 8) 
        {
            return false;
        }

        // Check for at least one uppercase letter
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        if (!uppercaseMatcher.find()) 
        {
            return false;
        }

        // Check for at least one lowercase letter
        Pattern lowercasePattern = Pattern.compile("[a-z]");
        Matcher lowercaseMatcher = lowercasePattern.matcher(password);
        if (!lowercaseMatcher.find()) 
        {
            return false;
        }

        // Check for at least one digit
        Pattern digitPattern = Pattern.compile("\\d");
        Matcher digitMatcher = digitPattern.matcher(password);
        if (!digitMatcher.find()) 
        {
            return false;
        }

        // Check for at least one special character
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        Matcher specialCharMatcher = specialCharPattern.matcher(password);
        if (!specialCharMatcher.find()) 
        {
            return false;
        }

        return true;
    }

}
