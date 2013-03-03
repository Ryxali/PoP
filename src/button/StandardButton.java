package button;



import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;



public class StandardButton extends Button {
	private ImageStore idleImg;
	private ImageStore hoverImg;
	private ImageStore pressedImg;

	public StandardButton(int x, int y, ImageStore idleImg, ImageStore hoverImg,
			ImageStore pressedImg) {
		super(x, y);
		this.idleImg = idleImg;
		this.hoverImg = hoverImg;
		this.pressedImg = pressedImg;
	}
	public StandardButton(ImageStore idleImg, ImageStore hoverImg, ImageStore pressedImg){
		super(0, 0);
		this.idleImg = idleImg;
		this.hoverImg = hoverImg;
		this.pressedImg = pressedImg;
	}

	@Override
	public void draw(Graphics g) {
		getStoredImage().draw(x, y);

	}
	
	public void draw(){
		getStoredImage().draw(x, y);
	}
	public void draw(int x, int y, Graphics g){
		getStoredImage().draw(x, y);
	}
	
	public ImageStore getStoredImage() {
		if (getState() == STATE_IDLE) {
			return idleImg;
		} else if (getState() == STATE_HOVER) {
			return hoverImg;
		} else if (getState() == STATE_PRESSED) {
			return pressedImg;
		}
		return null;
	}
	@Override
	public StandardButton copy(){
		return new StandardButton(x, y, idleImg, hoverImg, pressedImg);
	}

	@Override
	public void buttonStateCheck(Input input) {
		int mX = input.getMouseX();
		int mY = input.getMouseY();
		int farX = getX() + getStoredImage().getImage().getWidth();
		int farY = getY() + getStoredImage().getImage().getHeight();

		if (pointContains(getX(), mX, farX) && pointContains(getY(), mY, farY)) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				setState(STATE_PRESSED);
				setClicked(true);
			} else {
				setState(STATE_HOVER);
			}
		} else if (!(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) 
				&& getState() == STATE_PRESSED)) {
			setState(STATE_IDLE);
			setClicked(false);
		}
	}
	@Override
	public int getType() {
		return ButtonStore.MODE_REGULAR;
	}
	
	@Override
	public void unload(){
		idleImg.unload();
		hoverImg.unload();
		pressedImg.unload();
	}
	@Override
	public void reload() {
		System.out.println("Reloaded");
		idleImg.reload();
		hoverImg.reload();
		pressedImg.reload();
		
	}

}
