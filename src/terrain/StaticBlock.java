package terrain;

import image.ImageStore;

import org.newdawn.slick.Graphics;

public class StaticBlock extends Block{

	public StaticBlock(int xPos, int yPos, ImageStore ref) {
		super(xPos, yPos, ref);
	}
	
	public void addPoses(int xPos, int yPos){
		setXPos(xPos);
		setYPos(yPos);
	}

	@Override
	public void draw(Graphics g) {
		
	}

	/**
	 * Creates and returns a static block with the desired values.
	 * 
	 */
	@Override
	public Block clone(int x, int y) {
		return new StaticBlock(x, y, getRef());
	}
	
}
