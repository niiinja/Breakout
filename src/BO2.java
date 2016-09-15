/* BREAKOUT CHAOS by Nina Boelsums
 * Program Your Breakout 2016, Erik van der Spek 
*/
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

public class BO2 extends Applet implements KeyListener, Runnable{
	
	//Creating new objects of classes
	Brick[][] bricks; 
	Paddle paddle;
	PlayingField pf;
	Ball[] balls;
	
	//Used for keeping the thread turned off until it is switched on 
	Thread running = null;
	
	//Used for doublebuffering
	Graphics buffergraphics;
	Image offscreen;
	

	//All variables for sounds
	AudioClip shot;
	AudioClip allaboutthatbase;
	AudioClip weee;
	AudioClip horn;
	AudioClip bigexplosion;
	
	
	int redBrick = 0;//Functions as a boolean to see whether the red brick has been hit or not
	int ballnb = 0;//Keeps track in which position in the Balls array the last ball was activated
	
	
	public void init(){
		this.setSize(600, 900);//Size of the window
		this.addKeyListener(this);//Used to get keyboard input
		
		//Creates new objects
		pf = new PlayingField(0,0,600,900,Color.BLACK);
		bricks = new Brick[20][15];
		paddle = new Paddle(200,pf.getHeight()-200,150,20,Color.RED);
		balls = new Ball[101];
		
		//Fills the balls array with up to 100 "invisible" balls
		for(int n=0; n<100; n++){
			balls[n] = new Ball(1, 1, 0, 0, 0, 0, Color.WHITE);
			balls[0] = new Ball(100, 510, 5, -5, 20, 20, Color.YELLOW);//Activates the first ball
			}
		
		//Assigns audio files to the sound variables
		allaboutthatbase = getAudioClip(getCodeBase(), "bassline.wav");
		shot = getAudioClip(getCodeBase(), "shot.wav");
		weee = getAudioClip(getCodeBase(), "weee.wav");
		horn =getAudioClip(getCodeBase(), "horn.wav");
		bigexplosion = getAudioClip(getCodeBase(), "bigexplosion.wav");
		
		//Play a funky bassline throughout the game.
		allaboutthatbase.loop();
		
		//Creates a block of bricks, i wide and j long
		for(int i=0; i<bricks[i].length; i++){ 
			for(int j=0; j<bricks[j].length; j++){
			//Regular Bricks	
			bricks[i][j] = new Brick(10+(45*i),10+(25*j),40,20, Color.BLUE, 0);
			
			//One red Brick of DOOM
			bricks[10][8] = new Brick(10+45*10, 10+25*8, 40, 20, Color.RED, 0);
			}
		}
	}
	
	//Thread for letting multiple processes run autonomously
	 public void run(){
			while (running != null){
				
				
				
				
				//Lets the balls move autonomously
				for(int n=0; n<100; n++){
					balls[n].move(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), 0, 0,0,0);
				}
				
				//Lets bricks fall autonomously
				for(int i=0; i <bricks[i].length; i++){
					for(int j=0; j<bricks[j].length; j++){
						bricks[i][j].getY();
						bricks[i][j].decreasehitpoints(0);
							}
						}
				
				brickcollision();//Checks whether a ball and a brick collide
				brickhitpaddle();//Checks whether  a brick and the paddle collide
				
				this.repaint();
		    	
				//Determines speed of the game
				try {
		    		Thread.sleep(30);
		    	}
		    	catch (Exception e){
		    		
		    	}
		    }
		}
	
	 //Overrides the standard update function
	 public void update(Graphics g){
		 this.paint(g);
	 }
	 
	public void paint(Graphics g){
		//Double buffering
		offscreen = createImage(pf.getWidth(), pf.getHeight());//Size of pre-made image
		buffergraphics = offscreen.getGraphics();

		buffergraphics.setColor(Color.BLACK);
		buffergraphics.clearRect(0, 0, getWidth(), getHeight());
		
		
		//Draw bricks
		for(int i=0; i <bricks[i].length; i++){
			for(int j=0; j<bricks[j].length; j++){
				//Normal Bricks
				buffergraphics.setColor(bricks[i][j].getColor());
				buffergraphics.fillRect(bricks[i][j].getX(), bricks[i][j].getY(), bricks[i][j].getWidth(), bricks[i][j].getHeight());
				
				//RED BRICK OF DOOM
				buffergraphics.setColor(bricks[10][8].getColor());
				buffergraphics.fillRect(bricks[10][8].getX(), bricks[10][8].getY(), bricks[10][8].getWidth(), bricks[10][8].getHeight());
				bricks[10][8] = new Brick(10+45*10, 10+25*8, 40, 20, Color.RED, 0);
			}
		}
		
		//Draw Paddle
		buffergraphics.setColor(paddle.getColor());
		buffergraphics.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
		
	
		//Starts the thread for moving the balls autonomously
		if (running == null){
			running = new Thread(this);
			running.start();
			}
		
		//Draw balls
		for(int n=0; n<100; n++){
		buffergraphics.setColor(balls[n].getColor());
		buffergraphics.fillOval(balls[n].getX(), balls[n].getY(), balls[n].getWidth(), balls[n].getHeight());}
		
		g.drawImage(offscreen, 0, 0, this);
       }
	
	
	//Used to control the Paddle with keys
	public void keyPressed(KeyEvent ke){
		if(ke.getKeyCode() == ke.VK_LEFT){
			paddle.move(-5);
			this.repaint();
		}
		if (ke.getKeyCode() == ke.VK_RIGHT){
			paddle.move(5);
			this.repaint();
		}
	}

	//Not sure why, but without this bit, the keyPressed function did not work.
	@Override
	public void keyReleased(KeyEvent ke) {
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
	}
	
	
	//This function deals with the balls hitting the bricks
	public void brickcollision(){
		
		for(int i=0; i <bricks[i].length; i++){
			for(int j=0; j<bricks[j].length; j++){
				for(int n=0; n<100; n++){
					
					//"When a ball hits a brick, then..."
					if(balls[n].getX() <= bricks[i][j].getX()+bricks[i][j].getWidth() && balls[n].getX() >= bricks[i][j].getX()-bricks[i][j].getWidth()){
						if (balls[n].getY() == bricks[i][j].getY()+bricks[i][j].getHeight()){
							shot.play(); //Sound effect for the hit bricks
							//The ball.move function uses the bricks coordinates so it needs to be called here. 
							//It is inefficient because the ball.move function was the first function, written with the least experience
							balls[n].move(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), bricks[i][j].getX(), bricks[i][j].getY(), bricks[i][j].getWidth(), bricks[i][j].getHeight());
				
							bricks[i][j].decreasehitpoints(-1);//Decreases hitpoints of the brick
				
							if(bricks[i][j] == bricks[10][8]){ //When the red brick was hit, then...
								redBrick = 1; // "Boolean" of redBrick turns to 1, indicating it has been hit
								horn.play(); // The alarm goes off
								bricks[10][8] = new Brick(10+45*10, 10+25*8, 0, 0, Color.RED, 0); // The red brick disappears
					
							}
						}
					}
				}
			}
		}
	}
	
	//Function used for the effects when a brick hits the paddle
	public void brickhitpaddle(){
		
		//In case the red brick has been hit...
		if (redBrick == 1){
			for(int i=0; i <bricks[i].length; i++){
				for(int j=0; j<bricks[j].length; j++){
					
					if (bricks[i][j].getY() == paddle.getY()){						
						if(paddle.getX()  <= bricks[i][j].getX()+bricks[i][j].getWidth()/2){
							if (paddle.getX()+ paddle.getWidth()  >= bricks[i][j].getX()-bricks[i][j].getWidth()/2){
								
								paddle.redBrick(); //The paddle disappears
								bigexplosion.play(); //The explosion indicates it is GAME OVER
							}
						}
					}
				}
			}
		}
		
		//When the red brick has not been hit yet...
		else{
			for(int i=0; i <bricks[i].length; i++){
				for(int j=0; j<bricks[j].length; j++){
					
					if (bricks[i][j].getY() == paddle.getY()){
						if(paddle.getX()  <= bricks[i][j].getX()){
							
							if (paddle.getX()+ paddle.getWidth()  >= bricks[i][j].getX()-bricks[i][j].getWidth()){
							ballnb ++; //The next position in the balls array
							
							//A new ball appears!  (Or: an invisible ball turns alive!)
							balls[ballnb] = new Ball(paddle.getX()+paddle.getWidth()/2,paddle.getY()-20,5,-5,20,20, Color.GREEN);
							
							weee.play();//sound indicating a new ball
							bricks[i][j].brickpaddle(); // Makes the caught brick disappear.
								}
							}
						}					
					}
				}
			}
		}
	}
			
	
			

