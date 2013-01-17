package button;



import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import priceofprogress.Game;



public abstract class Button {
	protected int x;
	protected int y;
	
	private int state = 0;
	private boolean clicked = false;
	
	
	public static final int STATE_IDLE = 0;
	public static final int STATE_HOVER = 1;
	public static final int STATE_PRESSED = 2;
	
	public static final int PRESSED_TRUE = 1;
	public static final int PRESSED_FALSE = 0;
	public Button(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return (int) (x*Game.getWidthScale());
		
	}
	public int getY(){
		return (int) (y*Game.getHeightScale());
	}
	
	public int getState(){
		return state;
	}
	public void setState(int state){
		this.state = state;
	}
	
	public abstract Button copy();
	
	public abstract void draw(Graphics g);
	
	public abstract void buttonStateCheck(Input input);
	
	public abstract int getType();
	
	public abstract ImageStore getStoredImage();
	
	public boolean isClicked(){
		return clicked;
	}
	
	public void setClicked(boolean value){
		clicked = value;
	}
	public int hasBeenClicked(){
		if(clicked && getState() == STATE_HOVER){
			return 1;
		}
		return 0;
	}
	
	public DropdownList getDList(){
		return null;
	}
	
	public boolean pointContains(int lBound, int point, int rBound){
		if(lBound <= point && point <= rBound){
			return true;
		}
		return false;
	}
	
	public abstract void unload();
}
