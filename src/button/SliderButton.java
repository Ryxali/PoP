package button;


import file.OptionsFile;
import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import priceofprogress.Game;

/**
 * A button that can be subject to one-dimensional movement.
 * The button movement is restricted by an internal slider bar
 * and will store its position as a percentage value.
 * @author Niklas L
 * @see button.Button
 * @see button.ButtonStore
 * 
 */
public class SliderButton extends Button {

	private ImageStore sliderImg;
	private ImageStore sliderBar;
	private int imgX;
	private int imgY;
	private boolean facing;
	private String sliderVal;
	private double perc;
	public static final boolean ALIGN_HORIZONTAL = true;
	public static final boolean ALIGN_VERTICAL = false;
	private boolean renderBounds = false;

	/**
	 * Creates a new SliderButton
	 * @param x the absolute x position of the button
	 * @param y the absolute y position of the button
	 * @param sliderImg the image of the slider button
	 * @param sliderBar the image of the slider bar
	 * @param facing true if it slides horizontally, false if vertically
	 * @param sliderVal the value of the slider
	 */
	public SliderButton(int x, int y, ImageStore sliderImg,
			ImageStore sliderBar, boolean facing, String sliderVal) {
		super(x, y);
		perc = OptionsFile.get().fetchDoubleFromOptions(sliderVal);
		this.sliderImg = sliderImg;
		this.sliderBar = sliderBar;
		this.sliderVal = sliderVal;
		this.facing = facing;
		refreshImgPos();
	}

	/**
	 * refreshes the position of the slider image
	 * based on the percentage value
	 */
	private void refreshImgPos() {
		if (facing == ALIGN_HORIZONTAL) {
			imgX = x
					+ toInt(perc, sliderBar.getImage().getWidth()
							+ sliderImg.getImage().getWidth());
					//- sliderImg.getImage().getWidth();
			imgY = y + sliderBar.getImage().getHeight() / 2
					- sliderImg.getImage().getHeight() / 2;
		} else {
			imgX = x + sliderBar.getImage().getWidth() / 2
					- sliderImg.getImage().getWidth() / 2;
			imgY = y
					+ toInt(perc, sliderBar.getImage().getHeight()
							+ sliderImg.getImage().getHeight());
					//- sliderImg.getImage().getHeight();
		}
	}
	
	@Override
	public Button copy() {
		return new SliderButton(getX(), getY(), sliderImg, sliderBar, facing,
				sliderVal);
	}

	@Override
	public void draw(Graphics g) {
		if(renderBounds){
			sliderBar.draw(x, y);
		}
		sliderImg.draw(imgX, imgY);
	}
	
	public void setRenderBounds(boolean to){
		renderBounds = true;
	}

	private int toInt(double perc, int dist) {
		return (int) (dist * perc);
	}

	private double toPerc(int value, int dist) {
		
		if(1.0 <= (double)value/(double)dist){
			return 1.0;
		}else if((double)value/(double)dist <= 0.0){
			return 0.0;
		}
		return (double) value / (double) dist;
	}
	private int getImgX(){
		return (int) (imgX * Game.getWidthScale());
	}
	private int getImgY(){
		return (int) (imgY * Game.getHeightScale());
	}

	@Override
	public void buttonStateCheck(Input input) {
		int mX = input.getMouseX();
		int mY = input.getMouseY();

		if (pointContains(getX(), mX, getX() + sliderBar.getImage().getWidth())
				&& pointContains(getY(), mY, getY()
						+ sliderBar.getImage().getHeight())) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				setState(STATE_PRESSED);
				setClicked(true);
			} else {
				setState(STATE_HOVER);
			}
		} else if (!(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && getState() == STATE_PRESSED)) {
			setState(STATE_IDLE);
			setClicked(false);
		}
		if (getState() == STATE_PRESSED) {
			if (facing == ALIGN_HORIZONTAL) {
				perc = toPerc(mX - getX() - sliderImg.getImage().getWidth()/2
						, sliderBar.getImage().getWidth()
						- sliderImg.getImage().getWidth());
			} else {
				perc = toPerc(mY - getY() - sliderImg.getImage().getWidth()/2
						, sliderBar.getImage().getHeight()
						- sliderImg.getImage().getHeight());
			}
			refreshImgPos();
		}

	}

	@Override
	public ImageStore getStoredImage() {
		return sliderImg;
	}

	@Override
	public int getType() {
		return ButtonStore.MODE_SLIDER;
	}

	@Override
	public void unload() {
		sliderImg.unload();
		sliderBar.unload();
	}

	@Override
	public void reload() {
		sliderImg.reload();
		sliderBar.reload();
		
	}

}
