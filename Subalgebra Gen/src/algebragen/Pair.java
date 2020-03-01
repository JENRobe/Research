package algebragen;

public class Pair 
{
	int x;
	int y;
	
	public Pair(int index, int algebraSize)
	{
		x = index / algebraSize;
		y = index % algebraSize;
	}
	
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
