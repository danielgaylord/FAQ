import java.awt.Point;

class Actor extends Point
{
	private String type;
	
	
	
	public Actor(int x, int y, String type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	
	
	public Actor(String type)
	{
		this(0, 0, type);
	}
	
	
	
	public String getType()
	{
		return type;
	}
}
