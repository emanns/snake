/**
 * 
 * Author: Evan Manns
 * (Some code borrowed from GridWorld)
 *
 * Grid.java
 * 
 * Class that handles all the Locations and Actors.
 * Part or all of the add, remove, get, and isValid
 * classes are borrowed from GridWorld.
 */
public class Grid {
	//Constants
	public static final int ROWS = 50;
	public static final int COLS = 50;

	//Instance data
	private Object[][] grid;

	//Constructor
	public Grid() {
		grid = new Object[ROWS][COLS];
	}

	//set one of the grid locations to contain the
	//given object
	public void add(Location loc, Object obj) {
		int row = loc.getRow();
		int col = loc.getCol();

		grid[row][col] = obj;
	}

	//set the given grid location to be empty
	public Actor remove(Location loc) {
		int row = loc.getRow();
		int col = loc.getCol();
		Actor a = (Actor) get(new Location(row, col));
		grid[row][col] = null;
		return a;
	}

	//return the object occupying the location 
	//passed in
	public Object get(Location loc) {
		int row = loc.getRow();
		int col = loc.getCol();

		return grid[row][col];
	}

	//return whether the location passed in is
	//within the bounds of the grid
	public boolean isValid(Location loc) {
		return (loc.getRow() >= 0 && loc.getRow() < ROWS && loc.getCol() >= 0 && loc
				.getCol() < COLS);
	}
	
	//return a Location with  random row and column
	//values within the bounds of the grid
	public Location getRandomLocation(){
		int r = (int)(Math.random()*(ROWS));
		int c = (int)(Math.random()*(COLS));
		return new Location(r, c);
	}

}
