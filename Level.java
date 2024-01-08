//level class

abstract class Level
{
    int XY=4;
    int l=1;

    //return the level the current user is on
    public int getLevel()
    {
        return l;
        
    }

    //sets the dimensions for the board
    public int setXY(int l)
    {
        XY=4;
        return XY;
    }

    //returns the dimensions for the board
    public int getXY()
    {
        return XY;
    
    }

    //increases the level of the game
    public int upLevel()
    {
        l++;
        return l;
    }

    //decreases the level of the game
    public int downLevel()
    {
        l--;
        return l;
    
    }

    //prints the level onto the board
    public void printLevel()
    {
        System.out.println("    level "+ l + "  ");
    }
}
