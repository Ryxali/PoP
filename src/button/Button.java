package button;



import image.DrawableG;
import image.ImageStore;
import image.Loadable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import priceofprogress.Game;


/**
 * The base button object that all other buttons are based on.
 * @author Niklas L
 * @see button.StandardButton
 * @see button.ListButton
 * @see button.SliderButton
 * @see image.ImageStore
 * @see image.Loadable
 * @see image.DrawableG
 */
public abstract class Button implements Loadable, DrawableG{
	/**
	 * The absolute x position of this object
	 */
	protected int x;
	/**
	 * The absolute y position of this object
	 */
	protected int y;
	/**
	 * The state of this button (eg. pressed, idle, hovered)
	 */
	private int state = 0;
	/**
	 * True if this button has been both hovered and clicked.
	 */
	private boolean clicked = false;
	
	/**
	 * This value corresponds with value for the button while being idle.
	 */
	public static final int STATE_IDLE = 0;
	/**
	 * This value corresponds with value for the button while being
	 * hovered.
	 */
	public static final int STATE_HOVER = 1;
	/**
	 * This value corresponds with value for the button while being
	 * pressed.
	 */
	public static final int STATE_PRESSED = 2;
	
	/**
	 * When calling the hasBeenClicked() method, it will return
	 * this value if the button indeed has been pressed.
	 */
	public static final int PRESSED_TRUE = 1;
	/**
	 * When calling the hasBeenClicked() method, it will return
	 * this value if the button indeed hasn't been pressed.
	 */
	public static final int PRESSED_FALSE = 0;
	/**
	 * Constructs an button containing nothing else than the x and y
	 * location of this component.
	 * @param x
	 * @param y
	 */
	public Button(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Get the relative x location of this component.
	 * @return the x value relative to screen size.
	 */
	public int getX(){
		return (int) (x*Game.getWidthScale());
		
	}
	/**
	 * Get the relative yy location of this component.
	 * @return the y value relative to screen size.
	 */
	public int getY(){
		return (int) (y*Game.getHeightScale());
	}
	/**
	 * Get the current state of this button.
	 * @return STATE_IDLE, STATE_HOVER or STATE_PRESSED
	 */
	public int getState(){
		return state;
	}
	/**
	 * Set the current state of this button.
	 * @param STATE_IDLE, STATE_HOVER or STATE_PRESSED
	 */
	public void setState(int state){
		this.state = state;
	}
	/**
	 * Creates and returns an identical copy of this object.
	 * @return the copy of this button.
	 */
	public abstract Button copy();
	/**
	 * Draws this button onto the screen.
	 * @param g the graphics context
	 */
	public abstract void draw(Graphics g);
	/**
	 * checks the state of this button.
	 * @param input the current input
	 */
	public abstract void buttonStateCheck(Input input);
	/**
	 * @return MODE_REGULAR, MODE_DROPDOWN, MODE_SLIDER as found in ButtonStore
	 * @see button.ButtonStore
	 */
	public abstract int getType();
	/**
	 * 
	 * @return the appropriate image to be used for rendering
	 */
	public abstract ImageStore getStoredImage();
	/**
	 * 
	 * @return true of this button has been pressed
	 */
	public boolean isClicked(){
		return clicked;
	}
	/**
	 * Set the clicked value to true or false.
	 * @param value true if this button has been both hovered and clicked, false if not.
	 */
	public void setClicked(boolean value){
		clicked = value;
	}
	/**
	 * check whether this button has been clicked.
	 * @return PRESSED_TRUE or PRESSED_FALSE
	 */
	public int hasBeenClicked(){
		if(clicked && getState() == STATE_HOVER){
			clicked = false;
			return PRESSED_TRUE;
		}
		return PRESSED_FALSE;
	}
	/**
	 * Retrieve the dropdown list of this button (if any).
	 * @return the DropDown list object associated with this button.
	 */
	public DropdownList getDList(){
		return null;
	}
	/**
	 * Checks if a certain point is within another point
	 * @param lBound the left bound
	 * @param point the point to check
	 * @param rBound the right bound
	 * @return true if point is between lBound and rBound
	 */
	public static boolean pointContains(int lBound, int point, int rBound){
		if(lBound <= point && point <= rBound){
			return true;
		}
		return false;
	}
	/**
	 * unloads all the Image-based resources used by this object.
	 */
	public abstract void unload();
	/**
	 * reloads all the Image-based resources used by this object.
	 */
	public abstract void reload();
}
