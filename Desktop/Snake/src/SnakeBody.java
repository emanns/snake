/**
 * Author: Evan Manns
 * 
 * SnakeBody.java
 * 
 * Represents one body piece of a snake.
 * Will usually only be found as part of an arraylist
 */
import java.util.ArrayList;

public class SnakeBody extends Actor {
	//Instance data
	private ArrayList<Location> turnLocs; //locations that the head turned at
	private ArrayList<Integer> turnDirs; //directions that the head turned

	//Contructor
	public SnakeBody(int direction) {
		//instantiate arraylists
		turnLocs = new ArrayList<Location>();
		turnDirs = new ArrayList<Integer>();
		//add initial direction
		setDirection(direction);
		turnDirs.add(direction);
	}
	//Getters
	public ArrayList<Location> getTurnLocation() {
		return turnLocs;
	}

	public ArrayList<Integer> getTurnDirs() {
		return turnDirs;
	}
	
	//set up future turns
	public void setTurn(Location turn, int dir) {
		if (turnDirs.size() <= 0){
			return;
		}
		//if continuing in same direction, do nothing
		else if (turnDirs.size() > 1
				&& dir == turnDirs.get(turnDirs.size()-1))
			return;
		//if direction changes, add the location it changed at
		//and the direction
		else {
			turnLocs.add(turn);
			turnDirs.add(dir);
		}
	}

	//override the move method to account for turns
	public void move() {
		//if no turns needed, move forward
		if (turnLocs.size() <= 0)
			super.move();
		
		//otherwise turn in specified direction at the corresponding location
		else {
			if (this.getLocation().equals(turnLocs.get(0))) {
				setDirection(turnDirs.get(1));
				super.move();
				turnDirs.remove(0);
				turnLocs.remove(0);
				
			} 
			//if not at location yet, continue moving in current direction
			else
				super.move();
		}
	}
}
