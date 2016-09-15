import java.awt.*;

public class PlayingField extends Canvas{
	
	int sizex;
	int sizey;
	int width;
	int height;
	Color color;
	
	public PlayingField(int x, int y, int w, int h, Color c){
		sizex = x;
		sizey = y;
		width = w;
		height = h;
		color = c;	
	}

	public int getX(){
		return sizex;
	}
	
	public int getY(){
		return sizey;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Color getColor(){
		return color;
	}
}
