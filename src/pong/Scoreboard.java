package pong;

import java.awt.event.KeyEvent;

public class Scoreboard {

	static int scoreL = 0;
	static int scoreR = 0;
	static int round = 5;
	
	boolean roundEnd = false;
	
	boolean playerScored = true;
	
	static int mode = 0;
	public boolean gameMode(boolean[] KeyType) {
		if (KeyType[KeyEvent.VK_SPACE]){
			mode = 2;
			return false;
		}
		if (KeyType[KeyEvent.VK_ENTER]) {
			mode = 1; 
			return false;
		}
	return true;	
	}	 
	
	public int getMode() {
		return mode;
	}
	
	public boolean endGame() {
		if (scoreL >= round || scoreR >= round) {
			return true;
		}
		return false;
	}
	
	public void scoreL() {
		scoreL++;
	}
	public void scoreR() {
		scoreR++;
	}
	public int getScoreL() {
		return scoreL;
	}
	public int getScoreR() {
		return scoreR;
	}
	
	public void reset(boolean yes) {
		scoreL = 0;
		scoreR = 0;
	}
	
}



