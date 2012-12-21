package button;

import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;


public class SliderButton extends Button{
	
	private ImageStore sliderImg;
	private ImageStore sliderBar;
	private boolean facing;
	public static final boolean FACING_HORIZONTAL = true;
	public static final boolean FACING_VERTICAL = false;
	public SliderButton(int x, int y, ImageStore sliderImg, ImageStore sliderBar,
			boolean facing) {
		super(x, y);
		this.sliderImg = sliderImg;
		this.sliderBar = sliderBar;
		this.facing = facing;
	}

	@Override
	public Button copy() {
		return new SliderButton(x, y, sliderImg, sliderBar, facing);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonStateCheck(Input input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImageStore getStoredImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		return ButtonStore.MODE_SLIDER;
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}
	

}
