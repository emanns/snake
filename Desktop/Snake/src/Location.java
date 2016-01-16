/**
 * 
 * Author: Evan Manns
 * (Some code borrowed from GridWorld)
 *
 * Location.java
 * 
 * Represents a location on the grid.
 * Part or all of the getAdjacentLocation, equals,
 * compareTo, and toString methods as well as 
 * directional constants are borrowed from GridWorld.
 */
public class Location implements Comparable{
	//Constants
	public static final int NORTH = 0;
	public static final int SOUTH = 180;
	public static final int EAST = 90;
	public static final int WEST = 270;

	//Instance data
	private int row, col;

	//Constructor
	public Location(int r, int c) {
		row = r;
		col = c;
	}

	//Getters
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	//return the nearest neighboring location in the
	//direction passed in
	public Location getAdjacentLocation(int direction) {
		int dr = 0;
		int dc = 0;
		if (direction == EAST)
			dc = 1;

		else if (direction == SOUTH)
			dr = 1;

		else if (direction == WEST)
			dc = -1;

		else if (direction == NORTH)
			dr = -1;

		return new Location(getRow() + dr, getCol() + dc);

	}
	
	//return whether or not this Location points to the same
	//location as the one passed in
	public boolean equals(Object other)
    {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }
	
	//returns -1 if this location is less than the one passed in,
	//1 if greater, and 0 if the two are equal.
	//Checks by row first, then column
	public int compareTo(Object other)
    {
        Location otherLoc = (Location) other;
        if (getRow() > otherLoc.getRow())
            return -1;
        if (getRow() < otherLoc.getRow())
            return 1;
        if (getCol() > otherLoc.getCol())
            return -1;
        if (getCol() < otherLoc.getCol())
            return 1;
        return 0;
    }
	
	//return the Location represented in a string in the
	//form of (row, col).
	public String toString(){
		return "(" + getRow() + ", " + getCol() + ")";
	}

}
