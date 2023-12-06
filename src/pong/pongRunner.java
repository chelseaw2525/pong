package pong;

import java.awt.Graphics2D;
import java.util.Scanner;
import utilities.GDV5;
//import utilities.Sound;

@SuppressWarnings("serial")
public class pongRunner extends GDV5 {

	Scanner input = new Scanner(System.in);
	Ball myBall = new Ball(24);
	Paddle playL = new Paddle(0 + 5);
	Paddle playR = new Paddle(1200 - 20 - 5);
	Scoreboard myScore = new Scoreboard();
	String winner = "";
	
	boolean start = false;			//ball moving?
	boolean gameOver = false;			//show gameOver screen?
	boolean splash = true; 			//show splash screen?
	
	public static void main(String[] args) {
		pongRunner myRunner = new pongRunner();
		//Sound.loop("rick.wav");
		myRunner.start();
	}
	
	// 0 = right, 1 = up, 2 = left, 3 = down    controls behavior based on where paddle is hit
	Boolean[] dirHitPadL = {true, true, false, true};
	Boolean[] dirHitPadR = {false, true, true, true};
	Boolean[] sideHit = {false, true, false, true};
	
	public boolean hitPadL() {
		int i = collisionDirection(playL, myBall, myBall.getSpeedX(), myBall.getSpeedY(), playL.getSpeed());
		return dirHitPadL[i];
	}
	
	public boolean hitPadR() {
		int i = collisionDirection(playR, myBall, myBall.getSpeedX(), myBall.getSpeedY(), playR.getSpeed());
		return dirHitPadR[i];
	}
	
	public boolean sideHitL() {
		int i = collisionDirection(playL, myBall, myBall.getSpeedX(), myBall.getSpeedY(), playL.getSpeed());
		return sideHit[i];
	}
	
	public boolean sideHitR() {
		int i = collisionDirection(playR, myBall, myBall.getSpeedX(), myBall.getSpeedY(), playR.getSpeed());
		return sideHit[i];
	}
	
	@Override
	public void update() { 	// 60 fps
		myBall.move(start, playL, playR, hitPadL(), hitPadR(), sideHitL(), sideHitR(), myScore);
		playL.move(KeysPressed, true);
		playR.groove(myScore.getMode(), KeysPressed, myBall);
		if (myBall.getMaxX() >= 1200) {
			winner = "Left wins!";
			start = false;
		}
		if (myBall.getMinX() <= 0) {
			winner = "Right wins!";
			start = false;
		}
	}

	@Override
	public void draw(Graphics2D draw) {											//processor speed, apprx. 3000 fps
		if (splash) {									//shows splash screen
			Screens.drawSplash(draw);
			draw.drawString("You Chose: " + myBall.level(!start), 500, 600);
			splash = myScore.gameMode(KeysPressed);
		}
		else {										//gameplay animation
			if (!myScore.endGame() && !gameOver) {
				Screens.drawGame(draw, myBall, playL, playR, myScore);
				start = true;						// ball starts moving!
			} 	
			else {									//game over screen
				gameOver = true;
				Screens.drawGameOver(draw, winner, myBall, playL, playR, myScore);
				draw.drawString("You Chose: " + myBall.level(!start), 500, 600);
				gameOver = myScore.gameMode(KeysPressed);							//waits for space/enter key
				start = !gameOver;
				myScore.reset(gameOver);											//reset ball location
			}
		}		
	}
}


