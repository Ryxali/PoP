package priceofprogress;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public enum ButtonStore {
	NEW_GAME(ImageStore.BUTTON_PLAY_IDLE, 
			ImageStore.BUTTON_PLAY_HOVER, 
			ImageStore.BUTTON_PLAY_PRESSED,
			247, 333, 0, null),
	LOAD_GAME(ImageStore.BUTTON_LOADGAME_IDLE,
			ImageStore.BUTTON_LOADGAME_HOVER,
			ImageStore.BUTTON_LOADGAME_PRESSED,
			496, 604, 0, null),
	OPTIONS(ImageStore.BUTTON_OPTIONS_IDLE,
			ImageStore.BUTTON_OPTIONS_HOVER,
			ImageStore.BUTTON_OPTIONS_PRESSED,
			943, 385, 0, null),
	EXIT(ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED,
			1597, 453, 0, null),
	RESOLUTION(ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED,
			250, 300, 1, DropdownList.RESOLUTION),
	KEY_CONFIG(ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED,
			600, 300, 0, null),
	VOLUME_SOUND(ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED,
			600, 500, 2, null),
	VOLUME_MUSIC(ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED,
			600, 700, 2, null),
	BACK(ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED,
			900, 300, 0, null);
	
	private final ImageStore imgIdle;
	private final ImageStore imgHover;
	private final ImageStore imgPressed;
	private final int mode;
	
	public static final int STATE_IDLE = 0;
	public static final int STATE_HOVER = 1;
	public static final int STATE_PRESSED = 2;
	
	private static final int MODE_REGULAR = 0;
	private static final int MODE_DROPDOWN = 1;
	private static final int MODE_SLIDER = 2;
	
	private static final ImageStore SLIDER_BAR = ImageStore.BUTTON_SLIDER;
	
	private int state = 0;
	private boolean clicked = false;
	private int xPos;
	private int yPos;
	
	private DropdownList dList;
	
	private ButtonStore(ImageStore imgIdle, ImageStore imgHover, ImageStore imgPressed,
			int xPos, int yPos, int mode, DropdownList dList){
		this.imgIdle = imgIdle;
		this.imgHover = imgHover;
		this.imgPressed = imgPressed;
		this.xPos = xPos;
		this.yPos = yPos;
		this.mode = mode;
		this.dList = dList;
	}
	
	public void draw(Graphics g){
		if(mode == MODE_SLIDER){
			SLIDER_BAR.getImage().draw();
		}else if(mode == MODE_DROPDOWN){
			if(dList != null){
				dList.draw(g, this);
				
			}
		}
		getImage().draw((int)(xPos * Game.getWidthScale()) , (int)(yPos * Game.getHeightScale()));
	}
	
	public int getX(){
		return (int)(xPos * Game.getWidthScale());
	}
	
	public int getY(){
		return (int)(yPos * Game.getHeightScale());
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
