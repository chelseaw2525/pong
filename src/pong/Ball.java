package pong;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
//import utilities.Sound;

@SuppressWarnings("serial")
public class Ball extends Rectangle{
	
	int speeds[] = {3, 4, 5}; 			//possible ball speeds for randomization
	static int centerX = 600 - 12;			//center of screen - offset for ball
	static int centerY = 400;
	static int placeX = centerX;
	static int placeY = centerY;
	int lvmod = 0;
	int padding = 20;
	boolean pause = false;
	
	public Ball(int size) {
		super(placeX, placeY, size, size);
	}
	
	static int lvmed = 1;
	static int lvhd = 3;
	
	public String level(boolean used) {		//how fast is ball?
		if (pongRunner.KeysPressed[KeyEvent.VK_1]) {
			lvmod = 0;
			return "easy";
		}
		if (pongRunner.KeysPressed[KeyEvent.VK_2]) {
			lvmod = lvmed;
			return "medium";
		}
		else if (pongRunner.KeysPressed[KeyEvent.VK_3]) {
			lvmod = lvhd;
			return "hard";
		}
		lvmod = 0;
		return "default (easy)";
	}
	
	public int randomDirect() {					//returns random direction, below gives magnitude --> vector!
		int random = (int)(Math.random()*2);
		if (random == 0){
			random -= 1;
		}
		return random;
	}
	
	public int getSpeedX() {
		return randomDirect() * (speeds[(int)(Math.random()*3)] + lvmod);
	}
	
	public int getSpeedY() {
		return randomDirect() * (speeds[(int)(Math.random()*3)] + lvmod);
	}
	
	int speedX = getSpeedX();			// change in x
	int speedY = getSpeedY();			// change in y
	
	public void recenter() {
		speedX = 0;
		speedY = 0;
		placeX = centerX;				//back to center
		placeY = centerY;
	}
	
	int counter = 90;	
	
	public void move(boolean start, Rectangle L, Rectangle R, boolean hitPadL, boolean hitPadR, boolean sideHitL, boolean sideHitR, Scoreboard sb) {    //  		see below
		if (start && counter >= 90)	{
			if (this.getMinX() <= 0) {									
				//Sound.playSound("beep.wav");
				recenter();
				sb.scoreR();
				counter = 0;
			}
			if (this.getMaxX() >= 1200) {								// -25 is based on top left corner of ball				
				//Sound.playSound("beep.wav");
				recenter();
				sb.scoreL();
				counter = 0;
			} 
			if (this.getMinY() < 0) {
				//Sound.playSound("boing.wav");
				speedY *= -1;											// reverses direction of velocity!
			} 
			if (this.getMaxY() > 800) {
				//Sound.playSound("boing.wav");
				speedY *= -1;
			}		
			if (this.intersects(R) && hitPadR) {
				//Sound.playSound("boing.wav");
				speedX *= -1;
				if (sideHitR) {
					speedY *= -1;
				}
			}
			if (this.intersects(L) && hitPadL) {
				//Sound.playSound("boing.wav");
				speedX *= -1;
				if (sideHitL) {
					speedY *= -1;
				}
			}
			this.setLocation(placeX+=speedX, placeY+=speedY);
		}
		else {							//returns ball to center
			recenter();
			counter++;
			speedX = getSpeedX();
			speedY = getSpeedY();
		}
	}	
}
