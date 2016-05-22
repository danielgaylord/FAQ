import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.awt.Point;
 
class XMLHandler 
{
	DocumentBuilderFactory dbf;
    DocumentBuilder db;
    int gameStart = 0;
    	
    
    
    public XMLHandler()
    {
    }
    
    
    
    public void makeRoom(Room room, String fileName) 
    {
        try {
			String workingDirectory = System.getProperty("user.dir");
        	File file = new File(workingDirectory + "\\maps\\" + fileName + ".xml");
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	DocumentBuilder db = dbf.newDocumentBuilder();
        	char[][] tiles = room.getTiles();
	        System.out.print(workingDirectory);
	        if (file.exists()) {
	            Document doc = db.parse(file);
	            Element docEle = doc.getDocumentElement();
	            
	            String rawTiles = docEle.getTextContent();
	            rawTiles = rawTiles.replaceAll("\\s", "");
	            
	            for (int i = 0; i < rawTiles.length(); i++) {
	            	tiles[i%room.X_TILES][i/room.X_TILES] = rawTiles.charAt(i);
	            }
	           
	            NodeList nodes = docEle.getElementsByTagName("playerStart");
	            if (nodes.getLength() != 0 && gameStart == 0)
	            {
	            	NamedNodeMap attributes = nodes.item(0).getAttributes();
	            	
	            	int x = Integer.valueOf(attributes.getNamedItem("x").getNodeValue());
	            	int y = Integer.valueOf(attributes.getNamedItem("y").getNodeValue());
	            	
	            	room.getPlayer().setLocation(x, y);
	            	gameStart = 1;
	            }
	            
	            nodes = docEle.getElementsByTagName("exits").item(0).getChildNodes();
	            room.getEvents().clear();
	            for (int i = 0; i < (nodes.getLength() / 2); i++) {
	            	NamedNodeMap attributes = nodes.item((2 * i) + 1).getAttributes();
	            	
	            	int x = Integer.valueOf(attributes.getNamedItem("x").getNodeValue());
	            	int y = Integer.valueOf(attributes.getNamedItem("y").getNodeValue());
	            	String to = attributes.getNamedItem("to").getNodeValue();
	            	int x2 = Integer.valueOf(attributes.getNamedItem("x2").getNodeValue());
	            	int y2 = Integer.valueOf(attributes.getNamedItem("y2").getNodeValue());
	            	
	            	Event exit = new Event(new Point(x, y), new Point(x2, y2), to);
	            	room.getEvents().add(exit);
	            }
	            
	            nodes = docEle.getElementsByTagName("enemies").item(0).getChildNodes();
	            room.getEnemies().clear();
	            for (int i = 0; i < (nodes.getLength() / 2); i++) {
	            	NamedNodeMap attributes = nodes.item((2 * i) + 1).getAttributes();
	            	
	            	int x = Integer.valueOf(attributes.getNamedItem("x").getNodeValue());
	            	int y = Integer.valueOf(attributes.getNamedItem("y").getNodeValue());
	            	
	            	Actor enemy = new Actor(x, y, "monster");
	            	room.getEnemies().add(enemy);
	            }
	        }  
	  	} catch (Exception e) {
	        System.out.println(e);
	    }
	}
}