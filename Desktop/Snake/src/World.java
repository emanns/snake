/**
 * Author: Evan Manns
 * 
 * World.java
 * 
 * Frame used to contain the game
 */
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class World extends JFrame{
	//Constructor
	public World(){
		super();
		setTitle("Snake");
		setBackground(Color.BLACK);
		setIconImage(new ImageIcon("s_Icon.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
