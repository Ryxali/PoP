package terrain;

import org.newdawn.slick.Graphics;

public class VoidBlock extends Block{

	public VoidBlock( int iD, int xPos, int yPos) {
		super(iD, xPos, yPos, null);
	}
	
	public void addPoses(int xPos, int yPos){
		setXPos(xPos);
		setYPos(yPos);
	}

	@Override
	public void draw(Graphics g) {
		// void blocks have now visual representation.
	}

	/**
	 * Creates and returns a new block with the desired positions
	 * and the same block-subclass as this one (VoidBlock).
	 * 
	 * @param x the horizontal position of the new block.
	 * @param y the vertical position of the new block.
	 * 
	 * @return the new block.
	 */
	@Override
	public Block clone(int x, int y) {
		return new VoidBlock(getID(), x, y);
	}
	
}
