import java.util.Random;

class ActorHandler 
{
	public ActorHandler() 
	{
	}
	
	
	
	public void randomPlayerLoc(Room room) 
    {
    	Random 	RNG = new Random();
    	char[][] tiles = room.getTiles();
    	Actor player = room.getPlayer();
    	int x = RNG.nextInt(room.X_TILES); 
    	int y = RNG.nextInt(room.Y_TILES);
    	
		while (tiles[x][y] == '#') {
			x = RNG.nextInt(room.X_TILES); 
			y = RNG.nextInt(room.Y_TILES);
		} 
		player.setLocation(x, y);
    }
    
    
    
	public void movePlayer(char dir, Room room) 
    {  	
    	char[][] tiles = room.getTiles();
    	Actor player = room.getPlayer();
    	
    	if ((dir == 'W') && (tiles[player.x - 1][player.y] != '#')) {
			player.translate(-1, 0);
		}
		if ((dir == 'N') && (tiles[player.x][player.y - 1] != '#')) {
			player.translate(0, -1);
		}
		if ((dir == 'E') && (tiles[player.x + 1][player.y] != '#')) {
			player.translate(1, 0);
		}
		if ((dir == 'S') && (tiles[player.x][player.y + 1] != '#')) {
			player.translate(0, 1);
		}
		
		checkEvent(room);
    }
    
    private void checkEvent(Room room)
    {
    	
    }
}