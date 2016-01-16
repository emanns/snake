/**
 * Author: Evan Manns
 * 
 * Title.java
 * 
 * Panel that shows the name of the game and instructions.
 * Implements MouseListener to begin the game when the user
 * clicks anywhere on the panel.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Title extends JPanel implements MouseListener{
	//Instance data
	private BufferedImage title;
	
	//Constructor
	public Title(){
		//attempt to fetch the title image
		try{
			title = ImageIO.read(new File("titleImage.png"));
		}
		catch(IOException e){
			System.out.println("File not found");
		}
		
		addMouseListener(this);
		setPreferredSize(new Dimension(GridPanel.GRID_HEIGHT, GridPanel.GRID_WIDTH));
		
	}
	
	//draw the title screen including instructions
	public void paintComponent(Graphics g){
		g.drawImage(title, 145, 60, null);
		g.setColor(Color.GREEN);
		g.drawString("Welcome to the Snake Game!", 100, 200);
		g.drawString("Use the Arrow keys to move the snake and collect food", 100, 250);
		g.drawString("Click anywhere to start", 300, 300);
	}

	//start the game when user clicks panel
	public void mouseClicked(MouseEvent e) {
		Main.play();
	}

	//unused method stubs
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
