package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;

public class Screens {
	
	static Font title = new Font("SANS_SERIF", Font.BOLD, 25);
	static Font text = new Font("SANS_SERIF", Font.PLAIN, 17);
	static Font num = new Font("SERIF", Font.BOLD, 50);
	
	public static void drawSplash(Graphics2D win) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString("Pong by Chelsea Wen", 450, 100);
		win.drawString("PLAYER ONE: W for up, S for down", 100, 150);
		win.drawString("PLAYER TWO: UP for up, DOWN for down", 600, 150);
		win.setFont(text);
		win.drawString("Use the paddles to prevent the ball from escaping out your side, "				+ "or else your opponent gets a point. First person to 5 points wins!", 180, 250);
		win.setColor(Color.YELLOW);
		win.drawString("Press and hold difficulty number, then hit ENTER to play Multiplayer. Hit SPACE to play Singleplayer", 210, 450);
		win.setFont(text);
		win.drawString("Difficulty Level: No number = 1, 2 = medium, 3 = hard  "
				+ "Default is easy mode.", 350 , 500);
	}
	
	public static void drawGame(Graphics2D win, Rectangle myBall, Rectangle playL, Rectangle playR, Scoreboard myScore) {
		win.setColor(Color.red);
		win.fillOval((int)myBall.getX(), (int)myBall.getY(), (int)myBall.getWidth(), (int)myBall.getHeight());
		win.setColor(Color.BLUE);
		win.fillRect((int)playL.getX(), (int)playL.getY(), (int)playL.getWidth(), (int)playL.getHeight());
		win.fillRect((int)playR.getX(), (int)playR.getY(), (int)playR.getWidth(), (int)playR.getHeight());
		win.setColor(Color.WHITE);
		win.fillRect(600-10, 0, 10, 800);	//net 
		win.setFont(num);
		win.drawString("" + myScore.getScoreL(), 300, 450);
		win.drawString("" + myScore.getScoreR(), 900, 450);
	}
	
	public static void drawGameOver(Graphics2D win, String winner, Rectangle myBall, Rectangle playL, Rectangle playR, Scoreboard myScore) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString("Game Over!", 500, 150);
		win.drawString(winner, 510, 200);
		win.setColor(Color.YELLOW);
		win.drawString("Press ENTER (1P) or SPACE (2P) to Play Again!", 300, 450);
		win.setFont(text);
		win.drawString("Difficulty Level: 1 = easy, 2 = medium, 3 = hard  "
				+ "Choose via keyboard numbers.", 300 , 500);
	}
	
}


