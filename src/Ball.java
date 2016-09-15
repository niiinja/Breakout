
import java.applet.AudioClip;
import java.awt.*;
import javax.swing.ImageIcon;


public class Ball extends BO2 implements Runnable  {
	// integer for the balls' position
	int x;
	int y;

    // integer for movement directions
	int xdir; 
    int ydir;
    
    //integers for dimensions of ball
    int i_height;
    int i_width;
    
    Color color;

    //Receives values for the balls from the BO2 class
    public Ball(int xpos, int ypos, int dx,int dy,int width, int height, Color c) {
    	x = xpos;
    	y = ypos;
    	xdir = dx;
    	ydir = dy;
    	i_width = width;
    	i_height = height;
    	color = c;
    }

    
    //Function for letting the balls move
    public void move(int paddlex, int paddley, int paddlewidth, int paddleheight, int brickx, int bricky, int brickw, int brickh) {
        
    	//To let a ball bounce of a brick
    	if (y == bricky+brickw/2){
    		ydir = ydir*-1;
    	}
    	
        //When the ball reaches the outer walls, it bounces back
        if (x == 0 || x == 600) {
            xdir = xdir*-1;
        }

        //When the ball reaches the ceiling, it bounces back
        if (y == 1) {
            ydir = ydir * -1;
        }
        
        //To bounce back on paddle
        if (y == paddley-paddleheight/2 && x <= paddlex+paddlewidth && x >= paddlex){
        	ydir = ydir *-1;
        	y = y + ydir;
        	}

        else{
        	 x = x + xdir;
             y = y + ydir;
 
        }
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
		return i_width;
	}
	
	public int getHeight(){
		return i_height;
	}

}