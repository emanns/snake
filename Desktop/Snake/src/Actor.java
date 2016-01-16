/**
 * 
 * Author: Evan Manns
 * (Some code borrowed from GridWorld)
 * 
 * Actor.java
 * 
 * Base class for all objects that will be found on the grid.
 * Parts or all of putSelfInGrid, removeSelfFromGrid, move, moveTo, 
 * and canMove methods are borrowed from GridWorld.
 */
public class Actor {
	//Instance data
	private Location loc;
	private int direction;
	private Grid grid;

	//Constructors
	public Actor() {
		loc = new Location(0, 0);
		direction = 0;
		grid = null;
	}

	public Actor(Location l) {
		loc = l;
		direction = 0;
		grid = null;
	}

	//Getters
	public int getDirection() {
		return direction;
	}

	public Location getLocation() {
		return loc;
	}

	public Grid getGrid() {
		return grid;
	}

	//Setters
	public void setDirection(int newDir) {
		direction = newDir;
	}

	public void setLocation(Location newLoc) {
		loc = newLoc;
	}

	//Puts the actor in the grid and instantiates the 
	//grid instance variable
	public void putSelfInGrid(Grid gr, Location loc) {
		gr.add(loc, this);

		grid = gr;
		setLocation(loc);
	}
	
	//removes the actor from the grid
	public void removeSelfFromGrid(){
		grid.remove(getLocation());
		grid = null;
		loc = null;
	}

	//sets the actor's location to the location
	//directly in front of it in the direction it is facing
	public void move() {
		if (grid == null)
			return;
		Location next = getLocation().getAdjacentLocation(getDirection());
		if (grid.isValid(next)) {
			moveTo(next);
			next = getLocation().getAdjacentLocation(getDirection());
		}
	}

	//changes the actor from its original location
	//to the location passed in
	public void moveTo(Location newLocation) {
		getGrid().remove(loc);

		loc = newLocation;
		getGrid().add(loc, this);
	}
	
	//tell whether the next location is valid
	public boolean canMove() {
		if (grid == null)
			return false;
		Location next = getLocation().getAdjacentLocation(getDirection());
		return (grid.isValid(next));

	}
}
