import javax.swing.JFrame;
import javax.swing.SpringLayout;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class FAQ extends JFrame
	implements KeyListener 
{
	RoomHandler 		roomHandler;
	ScreenHandler 		screenHandler;
	
	
	
	public static void main(String[] args) 
	{   
		new FAQ(); 
	}
	
	
	
	public FAQ() 
    {
    	roomHandler = new RoomHandler();
		screenHandler = new ScreenHandler();
    		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fantasy Adventure Quest");
        this.setResizable(false);
        this.setFocusTraversalKeysEnabled(false);

		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.add(screenHandler);
		layout.putConstraint(SpringLayout.WEST, screenHandler, 3, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, screenHandler, 3, SpringLayout.NORTH, this);
		//layout.putConstraint(SpringLayout.EAST, this, 3, SpringLayout.EAST, screenHandler);
		//layout.putConstraint(SpringLayout.SOUTH, this, 3, SpringLayout.SOUTH, screenHandler);
        this.pack();
        this.setSize(534, 399);
        this.setLocationRelativeTo(null);
        
        this.addKeyListener(this);
        this.setVisible(true);
        
        screenHandler.roomHandler = this.roomHandler;
        roomHandler.screenHandler = this.screenHandler;
        
        screenHandler.changeScreenState("room");
        new Thread(roomHandler).start();
    }
    
    
    
    public void keyPressed(KeyEvent event) 
	{	
		switch (event.getKeyCode()) {
			case 27: 
				System.exit(0);
				break;
			case 37: case 65: case 100:
				roomHandler.movePlayer('W');
				break;
			case 38: case 87: case 104:
				roomHandler.movePlayer('N');
				break;
			case 39: case 68: case 102:
				roomHandler.movePlayer('E');
				break;
			case 40: case 83: case 98:
				roomHandler.movePlayer('S');
				break;
			case 67: case 72: case 96:
				screenHandler.changeScreenState("stats");
				break;
			case 69: case 73: case 110:
				screenHandler.changeScreenState("inventory");
				break;
			case 77: case 9: case 107:
				screenHandler.changeScreenState("map");
				break;
			default: 
				//System.out.println(event.getKeyCode());
				break;
		}
	}
	
	
		
	public void keyReleased(KeyEvent event) 
	{	
	}



	public void keyTyped(KeyEvent event) 
	{
	}


}