/**
 * Author: Evan Manns
 * 
 * GridPanel.java
 * 
 * Panel that contains a graphical representation
 * of the grid object and its contents
 */
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GridPanel extends JPanel implements KeyListener {
	// Constants
	public final static int GRID_HEIGHT = 500;
	public final static int GRID_WIDTH = 500;
	public final static int CELL_HEIGHT = 10;
	public final static int CELL_WIDTH = 10;

	// Instance data
	private Grid gr;
	private Snake snake;
	private Food f;
	private Timer t;
	private World world;

	// Constructor
	public GridPanel(World frame) {
		gr = new Grid();
		t = new Timer();
		snake = new Snake(3, this);
		world = frame;

		f = new Food();
		f.putSelfInGrid(gr, gr.getRandomLocation());

		// set up the original snake
		Location loc = new Location(10, 10);
		// create a certain number of body pieces
		for (int i = 0; i < snake.getLength(); i++) {
			SnakeBody b = new SnakeBody(snake.getHead().getDirection());
			snake.getBody().add(snake.getBody().size(), b);
		}
		snake.getHead().putSelfInGrid(gr, loc);
		int row = loc.getRow() + 1, col = loc.getCol();
		for (SnakeBody s : snake.getBody()) {
			s.putSelfInGrid(gr, new Location(row, col));
			row++;
		}
		
		// makes the snake move automatically
		TimerTask move = new TimerTask() {
			public void run() {
				snake.getHead().move();
				for (SnakeBody b : snake.getBody())
					b.move();
				repaint();
			}
		};
		
		// set the snake to move every 50 milliseconds starting after a half
		// second
		t.scheduleAtFixedRate(move, 500, 50);

		addKeyListener(this);
		setFocusable(true);
		setPreferredSize(new Dimension(GRID_HEIGHT, GRID_WIDTH));
	}

	public void paintComponent(Graphics g) {
		int x = 0, y = 0;
		for (int r = 0; r < Grid.ROWS; r++) {
			for (int c = 0; c < Grid.COLS; c++) {
				Location loc = new Location(r, c);
				// if location is occupied make it green
				if (gr.get(loc) != null) {
					g.setColor(Color.GREEN);
					g.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);
				}
				// if location is empty make it black
				else {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, CELL_HEIGHT, CELL_WIDTH);
				}
				x += CELL_WIDTH;
			}
			y += CELL_HEIGHT;
			x = 0;
		}

		// display score
		g.setColor(Color.GREEN);
		g.drawString("Score: " + snake.getScore(), 445, 480);
	}

	//Handle what to do when player loses
	public void gameOver() {
		//create dialog box to tell game has ended and report score
		JOptionPane.showMessageDialog(world,
				"Game Over! \n Score: " + snake.getScore(), "Game Over!",
				JOptionPane.PLAIN_MESSAGE);
		//stop the timer to keep snake from moving
		//after exiting dialog box
		t.cancel();
	}

	public void keyPressed(KeyEvent e) {
		SnakeHead head = snake.getHead();
		ArrayList<SnakeBody> body = snake.getBody();

		// Handle arrow key input
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (head.getDirection() == Location.SOUTH)
				return;
			else {
				head.setDirection(Location.NORTH);
				for (SnakeBody b : body)
					b.setTurn(head.getLocation(), Location.NORTH);
			}
			break;
		case KeyEvent.VK_DOWN:
			if (head.getDirection() == Location.NORTH)
				return;
			else {
				head.setDirection(Location.SOUTH);
				for (SnakeBody b : body)
					b.setTurn(head.getLocation(), Location.SOUTH);
			}
			break;
		case KeyEvent.VK_LEFT:
			if (head.getDirection() == Location.EAST)
				return;
			else {
				head.setDirection(Location.WEST);
				for (SnakeBody b : body)
					b.setTurn(head.getLocation(), Location.WEST);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (head.getDirection() == Location.WEST)
				return;
			else {
				head.setDirection(Location.EAST);
				for (SnakeBody b : body)
					b.setTurn(head.getLocation(), Location.EAST);
			}
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
