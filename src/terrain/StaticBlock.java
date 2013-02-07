package terrain;

import image.ImageStore;

import org.newdawn.slick.Graphics;

public class StaticBlock extends Block{

	public StaticBlock( int iD, int xPos, int yPos, ImageStore ref) {
		super(iD, xPos, yPos, ref);
	}
	
	public void addPoses(int xPos, int yPos){
		setXPos(xPos);
		setYPos(yPos);
	}

	@Override
	public void draw(Graphics g) {
		
	}

	/**
	 * Creates and returns a new static block with the desired positions
	 * and the same block-subclass as this one (StaticBlock).
	 * 
	 * @param x the horizontal position of the new block.
	 * @param y the vertical position of the new block.
	 * 
	 * @return the new block
	 */
	@Override
	public Block clone(int x, int y) {
		return new StaticBlock(getID(), x, y, getRef());
	}
	
}
