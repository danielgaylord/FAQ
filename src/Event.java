import java.awt.Point;

class Event 
{
	private Point from;
	private Point to;
	private String toWhere;
	private String type;
	
	
	
	public Event(Point from, Point to, String toWhere)
	{
		this.from = from;
		this.to = to;
		this.toWhere = toWhere;
		this.type = "teleport";
	}
	
	
	
	public String getType()
	{
		return type;
	}
	
	
	
	public Point getFrom()
	{
		return from;
	}
	
	
	
	public Point getTo()
	{
		return to;
	}
	
	
	
	public String getToWhere()
	{
		return toWhere;
	}
}
