/**
 * 
 * Author: Evan Manns
 *
 * Food.java
 * 
 * Child of Actor class that is designed to do nothing but 
 * occupy a location in the grid.
 */
public class Food extends Actor{
	public Food(){
		super(new Location(0,0));
	}
	
	public Food(Location loc){
		super(loc);
	}
}
