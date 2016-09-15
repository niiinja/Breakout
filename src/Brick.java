import java.awt.Color;

public class Brick {
	int x;
	int y;
	int width;
	int height;
	Color color;
	int hitpoints;
	 
	//Receives values for the balls from the BO2 class
	public Brick(int xpos, int ypos, int w, int h, Color c, int hp){
		x = xpos;
		y = ypos;
		width = w;
		height = h;
		color = c;
		hitpoints = hp;
	}
	
	
	//When a brick is hit by a ball it loses a hit point
	   public void decreasehitpoints(int decrease){
	    	hitpoints = hitpoints + decrease;
	    	//In earlier versions the game started with green blocks that had to be hit twice before they fell down,
	    	//you can still try it
	    	if (hitpoints == 1){ 
		   		color = Color.GREEN;
		   	}
	    	//Basic state of hit points of bricks
	    	if (hitpoints == 0){
		   		color = Color.BLUE;	
		   	}
	    	
	    	//When a brick has been hit is falls down
	    	if(hitpoints == -1){
	    		y = y+2;	   		
		   	 }
	    	
	    	//If the brick is hit a second time it disappears
	    	if (hitpoints == -2){
	    		width = 0;
	    	}
		    }
	
	//Function to let a brick disappear after it's been caught by the paddle   
	public void brickpaddle(){
		width = 0;
		
	}
	
	public Color getColor(){
		
		return color;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	
}
