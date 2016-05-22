
class RoomHandler
	implements Runnable
{
	final String startingRoom = "StartRoom";
	
	ScreenHandler 	screenHandler;
	XMLHandler xmlHandler;
	
	private Room room = new Room();
	
	
	
	public RoomHandler() 
	{
		xmlHandler = new XMLHandler();
		 	
		xmlHandler.makeRoom(room, startingRoom);
	}
	
	
	
	public Room getRoom() 
	{
		return this.room;
	}
	
	
	
	public void changeRoom(String roomFile)
	{
		xmlHandler.makeRoom(room, roomFile);
	}
    
    
    
	public void movePlayer(char dir)
	{
		moveActor(dir, -1);	
	}
	
	
	
	public void moveActor(char dir, int actorNum) 
    {  	
    	char[][] tiles = room.getTiles();
    	Actor actor;
    	
    	if (actorNum == -1) {
	    	actor = room.getPlayer();
    	} else {
    		actor = room.getEnemies().get(actorNum);
    	}
    	
    	if ((dir == 'W') && (tiles[actor.x - 1][actor.y] != '#')) {
			actor.translate(-1, 0);
		}
		if ((dir == 'N') && (tiles[actor.x][actor.y - 1] != '#')) {
			actor.translate(0, -1);
		}
		if ((dir == 'E') && (tiles[actor.x + 1][actor.y] != '#')) {
			actor.translate(1, 0);
		}
		if ((dir == 'S') && (tiles[actor.x][actor.y + 1] != '#')) {
			actor.translate(0, 1);
		}
		
		checkEvent();
		screenHandler.changeScreenState("room");
    }
    
    
    
    private void checkEvent()
    {
    	for (int i = 0; i < room.getEvents().size(); i++) {
    		Event event = room.getEvents().get(i);
    		Actor player = room.getPlayer();
    		
    		if (event.getType().equals("teleport")) {
    			if (player.x == event.getFrom().x && player.y == event.getFrom().y)
    			{
    				player.x = event.getTo().x;
    				player.y = event.getTo().y;
    				changeRoom(event.getToWhere());
    				screenHandler.changeScreenState("room");
    			}
    		}
    	}
    }
    
    
    
    public void run() 
    {
    	try {
    		while (true) {
    			for (int i = 0; i < room.getEnemies().size(); i++) {
					switch((int)(Math.random() * 4)) {
						case 0:
							moveActor('W', i);
							break;
						case 1:
							moveActor('N', i);
							break;
						case 2:
							moveActor('E', i);
							break;
						case 3:
							moveActor('S', i);
							break;
					}
				}
				Thread.sleep((int)(Math.random() * 200) + 400);
    		}
    	} catch (InterruptedException iex) {}
    }
}