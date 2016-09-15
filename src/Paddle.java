import java.awt.Color;

import java.awt.Color;
public class Paddle { 
	int width;
	int height;
	Color color;
	int x;
	int y;

	public Paddle(int xpos, int ypos, int w, int h, Color c){
		x = xpos;
		y = ypos;
		width = w;
		height = h;
		color = c;	
	}	
	
	//Function to let the paddle move. Input is received by the keys
	public void move(int xchange){
		x += xchange;
		
	}
	//Function to let the paddle disappear when it catches a brick after the red brick has been hit.
	public void redBrick(){
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