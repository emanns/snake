/**
 * Author: Evan Manns
 * 
 * Main.java
 * 
 * Main program used to organize all GUI components
 * and run the game
 */
import javax.swing.SwingUtilities;

public class Main {
	//Frame containing the game
	private static World f = new World();
	
	//main method to show GUI.
	//borrowed from Oracle website
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	//initialize start title screen
	private static void createAndShowGUI() {
		f.getContentPane().add(new Title());
		f.pack();
		f.setVisible(true);
	}
	
	//switch title screen to game screen
	//some code found on Stack Overflow
	public static void play(){
		GridPanel game = new GridPanel(f);
		f.getContentPane().removeAll();
		f.getContentPane().add(game);
		f.revalidate();
		game.requestFocusInWindow();
	}
}
