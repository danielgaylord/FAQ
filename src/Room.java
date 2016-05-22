import java.util.ArrayList;

class Room 
{
	final int 		X_TILES = 20;
	final int 		Y_TILES = 14;
	final char 		WALL_ID = '#';
	final char 		FLOOR_ID = '.';
	final int 		TILE_SIZE = 25;
	
	private char[][] 			tiles = new char[X_TILES][Y_TILES];
	private Actor 				player = new Actor("player");
	private ArrayList<Event> 	events = new ArrayList<Event>();
	private ArrayList<Actor>	enemies = new ArrayList<Actor>();
	
	
	public Room()
	{	
	}
	
	
	
	public char[][] getTiles()
	{
		return tiles;
	}
	
	
	
	public Actor getPlayer()
	{
		return player;
	}
	
	
	
	public ArrayList<Event> getEvents()
	{
		return events;
	}
	
	
	
	public ArrayList<Actor> getEnemies()
	{
		return enemies;
	}
	
	
	
	public void setPlayer(Actor player)
	{
		this.player = player;
	}
}
