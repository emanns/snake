/**
 * Author: Evan Manns
 * 
 * Snake.java
 * 
 * Child of the Actor class that contains one head
 * and an arraylist of body objects. 
 */
import java.util.ArrayList;

public class Snake{
	//Instance data
	private SnakeHead head;
	private ArrayList<SnakeBody> body;
	private int length;
	private int score;
	private GridPanel panel; //panel this snake is contained in
	
	//Constructor
	public Snake(int bodyLength, GridPanel container){
		panel = container;
		length = bodyLength;
		head = new SnakeHead(this, panel);
		body = new ArrayList<SnakeBody>(length);
		score = 0;
	}
	
	//Getters
	public SnakeHead getHead(){
		return head;
	}
	
	public ArrayList<SnakeBody> getBody(){
		return body;
	}
	
	public int getLength(){
		return length;
	}
	
	public int getScore(){
		return score;
	}
	
	//Add a body piece to the end of the snake
	public void lengthen(){
		SnakeBody last = body.get(body.size()-1); //end of the snake
		Grid gr = last.getGrid();
		int row = 0, col = 0;
		
		//set location of new body piece based on the direction
		//the end of the snake is facing
		switch(last.getDirection()){
		case Location.NORTH:
			row = last.getLocation().getRow()+1;
			col = last.getLocation().getCol();
			break;
		case Location.SOUTH:
			row = last.getLocation().getRow()-1;
			col = last.getLocation().getCol();
			break;
		case Location.EAST:
			row = last.getLocation().getRow();
			col = last.getLocation().getCol()-1;
			break;
		case Location.WEST:
			row = last.getLocation().getRow();
			col = last.getLocation().getCol()+1;
			break;
		}
		Location loc = new Location(row, col);
		SnakeBody b = new SnakeBody(last.getDirection());
		
		//add all the turn locations and directions of the last 
		//body piece of the snake to the new piece
		for(int i = 0; i < last.getTurnLocation().size(); i++)
			b.setTurn(last.getTurnLocation().get(i), last.getTurnDirs().get(i+1));
		
		//add the new piece to the body array and the grid
		body.add(b);
		b.putSelfInGrid(gr, loc);
		
		//increment score
		score++;
	}
}
