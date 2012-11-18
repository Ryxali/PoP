package priceofprogress;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public enum ButtonStore {
	NEW_GAME(ImageStore.BUTTON_PLAY_IDLE, 
			ImageStore.BUTTON_PLAY_HOVER, 
			ImageStore.BUTTON_PLAY_PRESSED,
			250, 335),
	LOAD_GAME(ImageStore.BUTTON_LOADGAME_IDLE,
			ImageStore.BUTTON_LOADGAME_HOVER,
			ImageStore.BUTTON_LOADGAME_PRESSED,
			496, 604),
	OPTIONS(ImageStore.BUTTON_OPTIONS_IDLE,
			ImageStore.BUTTON_OPTIONS_HOVER,
			ImageStore.BUTTON_OPTIONS_PRESSED,
			950, 385),
	EXIT(ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED,
			1570, 445);
	
	private final ImageStore imgIdle;
	private final ImageStore imgHover;
	private final ImageStore imgPressed;
	
	public static final int STATE_IDLE = 0;
	public static final int STATE_HOVER = 1;
	public static final int STATE_PRESSED = 2;
	
	private int state = 0;
	private boolean clicked = false;
	private int xPos;
	private int yPos;
	
	private ButtonStore(ImageStore imgIdle, ImageStore imgHover, ImageStore imgPressed, int xPos, int yPos){
		this.imgIdle = imgIdle;
		this.imgHover = imgHover;
		this.imgPressed = imgPressed;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void draw(){
		float [] f = Game.getScales();
		getImage().draw((int)(xPos * f[0]) , (int)(yPos * f[1]));
	}
	
	public int getX(){
		float [] f = Game.getScales();
		return (int)(xPos * f[0]);
	}
	
	public int getY(){
		float [] f = Game.getScales();
		return (int)(yPos * f[1]);
	}
	
	public Image getImage(){
		if(state == STATE_IDLE){
			return imgIdle.getImage();
		}else if(state == STATE_HOVER){
			return imgHover.getImage();
		}else if(state == STATE_PRESSED){
			return imgPressed.getImage();
		}else{
			return ImageStore.DEFAULT.getImage();
		}
	}
	public ImageStore getButton(){
		if(state == STATE_IDLE){
			return imgIdle;
		}else if(state == STATE_HOVER){
			return imgHover;
		}else if(state == STATE_PRESSED){
			return imgPressed;
		}else{
			return ImageStore.DEFAULT;
		}
	}
	
	public void buttonStateCheck(Input input){
		int mX = input.getMouseX();
		int mY = input.getMouseY();
		if(getX() <= mX &&
				mX <= getX() + getImage().getWidth() &&
				getY() <= mY &&
				mY <= getY() + getImage().getHeight()){
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				state = 2;
				clicked = true;
			}else{
				state = 1;
			}
		}else{
			state = 0;
			if(!input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				clicked = false;
			}
		}
	}
	
	public void setState(int newState){
		state = newState;
	}
	
	public int getState(){
		return state;
	}
	public boolean isClicked(){
		return clicked;
	}
	
	
}
