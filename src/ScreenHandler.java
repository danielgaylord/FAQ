import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;

class ScreenHandler extends JPanel 
{
	final int 		WINDOW_WIDTH = 521;
	final int 		WINDOW_HEIGHT = 365;
	
	RoomHandler 	roomHandler;
	
	private String	screenState;
	private Image 	screen;
	
	
	public ScreenHandler() 
	{
		this.setPreferredSize(new Dimension(WINDOW_WIDTH , WINDOW_HEIGHT));
		this.screenState = "room";
	}
	
	
	
	public String getScreenState() 
	{
		return screenState;
	}
	
	
	
	public void changeScreenState(String screenState) 
	{	
		if (screenState.equals(this.screenState) && !(this.screenState.equals("room"))) {
			changeScreenState("room");
		} else {
			if (screenState.equals("room")) {
				drawRoom();
				drawPlayer();
				drawEnemies();
			}
			if (screenState.equals("stats")) {
				drawStats();
			}
			if (screenState.equals("inventory")) {
				drawInventory();
			}
			if (screenState.equals("map")) {
				drawMap();
			}
			
			this.screenState = screenState;	
		}
	}
	
	
	
	private void drawRoom() 
	{
		screen = this.createImage(WINDOW_WIDTH , WINDOW_HEIGHT);
		Graphics graphics = screen.getGraphics();
		
		char[][] tiles = roomHandler.getRoom().getTiles();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				drawTile(i, j, tiles[i][j]);
			}
		}
		
		repaint();
		graphics.dispose();
	}
	
	
	private void drawTile(int x, int y, char tile)
	{
		Graphics graphics = screen.getGraphics();
		
		char wall = roomHandler.getRoom().WALL_ID;
		char floor = roomHandler.getRoom().FLOOR_ID;
		int size = roomHandler.getRoom().TILE_SIZE;
		Color mainColor = Color.black;
		Color subColor = Color.black;
		
		if (tile == floor) { // Floor
			mainColor = new Color(185, 185, 185);
			subColor = new Color(175, 175, 175);
		}  
		if (tile == wall) { // Impassable blocks
			mainColor = Color.gray;
			subColor = Color.black;
		}
		
		graphics.setColor(mainColor);
        graphics.fillRect((x * size) + x + 1, (y * size) + y + 1, size, size);
        graphics.setColor(subColor);
        graphics.drawRect((x * size) + x + 1, (y * size) + y + 1, size, size);		
		
		repaint();
		graphics.dispose();
	}
	
	private void drawPlayer()
	{
		Graphics graphics = screen.getGraphics();
		
		int size = roomHandler.getRoom().TILE_SIZE;
		int x = roomHandler.getRoom().getPlayer().x;
		int y = roomHandler.getRoom().getPlayer().y;
		
		graphics.setColor(Color.green);
		graphics.fillOval((x * size) + x + 1, (y * size) + y + 1, size, size);
		graphics.setColor(Color.black);
		graphics.drawOval((x * size) + x + 1, (y * size) + y + 1, size, size);
		
		repaint();
		graphics.dispose();
	}
	
	
	
	private void drawEnemies()
	{
		Graphics graphics = screen.getGraphics();
		
		int size = roomHandler.getRoom().TILE_SIZE;
		
		for (int i = 0; i < roomHandler.getRoom().getEnemies().size(); i ++) {
			int x = roomHandler.getRoom().getEnemies().get(i).x;
			int y = roomHandler.getRoom().getEnemies().get(i).y;
			
			graphics.setColor(Color.red);
			graphics.fillOval((x * size) + x + 1, (y * size) + y + 1, size, size);
			graphics.setColor(Color.black);
			graphics.drawOval((x * size) + x + 1, (y * size) + y + 1, size, size);
		}
		
		repaint();
		graphics.dispose();
	}
	
	
	
	private void drawStats() 
	{
		Graphics graphics = screen.getGraphics();
		
		graphics.setColor(Color.RED);
    	graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		repaint();
		graphics.dispose();
	}
	
	
	
	private void drawInventory() 
	{
		Graphics graphics = screen.getGraphics();
		
		graphics.setColor(Color.BLUE);
    	graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		repaint();
		graphics.dispose();
	}
	
	
	
	private void drawMap() 
	{
		Graphics graphics = screen.getGraphics();
		
		graphics.setColor(Color.GREEN);
    	graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		repaint();
		graphics.dispose();
	}
	
	
	
	public void paintComponent(Graphics graphics) 
    {	  	
		graphics.drawImage(screen, 0, 0, this);
    }
}