import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;

class SubScreenHandler extends JPanel
{
	
	final int 		WINDOW_WIDTH = 520;
	final int 		WINDOW_HEIGHT = 156;
	
	RoomHandler 	roomHandler;
	
	private String	subScreenState;
	private Image 	screen;
	
	
	public SubScreenHandler() 
	{
		this.setPreferredSize(new Dimension(WINDOW_WIDTH , WINDOW_HEIGHT));
		this.subScreenState = "info";
	}
	
	
	
	public String getSubScreenState() 
	{
		return subScreenState;
	}
	
	
	
	public void changeSubScreenState(String subScreenState) 
	{
		if (subScreenState.equals("info")) {
			drawInfo();
		}
		
		this.subScreenState = subScreenState;
	}
	
	
	
	private void drawInfo() 
	{
		screen = this.createImage(WINDOW_WIDTH , WINDOW_HEIGHT);
		Graphics graphics = screen.getGraphics();
		
		graphics.setColor(Color.WHITE);
    	graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		repaint();
		graphics.dispose();
	}
	
	
	
	public void paintComponent(Graphics graphics) 
    {	  	
		graphics.drawImage(screen, 0, 0, this);
    }
}
