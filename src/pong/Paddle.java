package pong;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Paddle extends Rectangle {

	static int slideSpeed = 8;	
	static int length = 150;
	static int locateY = 400-length/2;						//y location of paddle (so it can slide up/down)
	
	public Paddle(int locateX) {							//locateX = 0+5 if left paddle, 1200-30-5 if right paddle
		super(locateX, locateY, 20, length);
	}

	
	int yEdge = 800;
	
	public void move (boolean[]KeysPressed, boolean isLeft) {
		if (!isLeft) {													//right paddle
			if (KeysPressed[KeyEvent.VK_UP] && (this.getY() > 0)) {
				translate(0,-slideSpeed);
			}
			if (KeysPressed[KeyEvent.VK_DOWN] && (this.getMaxY() < yEdge)) {
				translate(0,slideSpeed);
			}
		}	
		
		if (isLeft) {
			if (KeysPressed[KeyEvent.VK_W] && (this.getY() > 0)) {
				translate(0,-slideSpeed);
			}
			if (KeysPressed[KeyEvent.VK_S] && (this.getMaxY() < yEdge)) {
				translate(0,slideSpeed);
			}
		}
	}
	
	public void ai(Rectangle ball) {
		if (this.getMinY() >= 0 && this.getMaxY() <= 800) {
			setLocation((int)this.getX(), (int)ball.getCenterY() - (length/2));
		}
		else if (this.getMaxY() >= 800){
			setLocation((int)this.getX(), 800 - length);
		}
		else {
			setLocation ((int)this.getX(), 1);
		}
	}
	
	public void groove(int yes, boolean[] KeyPress, Rectangle ball) {
		if (yes == 2) {
			//System.out.println("AI take-over");
			this.ai(ball);
		}
		else {
			this.move(KeyPress, false);
		}
	}
	
	public int getSpeed() {
		return slideSpeed;
	}
}

