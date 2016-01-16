/**
 * 
 * Author: Evan Manns
 * 
 * SnakeHead.java
 * 
 * Represents the head of a snake 
 */
public class SnakeHead extends Actor{
	//Constant
	private static final Location DEFAULT = new Location(0, 0);
	
	//Instance data
	private Snake snake;
	private GridPanel panel; //panel this is contained in
	
	//Constructors
	public SnakeHead(Snake s, GridPanel p) {
		super(DEFAULT);
		snake = s;
		panel = p;
	}

	public SnakeHead(Location loc, Snake s) {
		super(loc);
		snake = s;
	}
	
	//Handle what to do if the snake runs into an occupied location
	public void eat(Location loc) {
		Actor a = (Actor) getGrid().get(loc);
		//if food, remove and send to random location
		if (a instanceof Food) {
			a.removeSelfFromGrid();
			Location newLoc  = getGrid().getRandomLocation();
			if(getGrid().get(newLoc) != null)
				newLoc = getGrid().getRandomLocation();
			a.putSelfInGrid(getGrid(), newLoc);
			snake.lengthen();
		}
		//if body, remove self(ending game)
		if (a instanceof SnakeBody){
			panel.gameOver();
		}
	}

	//override move method
	public void move() {
		//don't move if not in grid
		if (getGrid() == null)
			return;
		
		//get next location in the direction head is going
		Location next = getLocation().getAdjacentLocation(getDirection());
		//if next is valid and empty move to it
		if (getGrid().isValid(next)) {
			//if valid and occupied, "eat" occupant
			if (getGrid().get(next) != null)
				eat(next);
			moveTo(next);
		}
		//if next isn't valid remove self
		else{
			panel.gameOver();
		}
	}

}
