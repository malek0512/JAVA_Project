package composant;

public class Couple
{
    public int x;
    public int y;

    public Couple(int x, int y)
    {
         this.x = x;
         this.y = y;
    }
    public String toString()
    {
    	return "(x:"+x+",y:"+y+")";
    }
}
