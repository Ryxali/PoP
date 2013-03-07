package terrain;

import image.ImageStore;

import org.newdawn.slick.Graphics;

import physics.Physics;
/**
 * A block that has a visual representation and is affected by physics.
 * 
 * @author Lukas
 *
 */
public class StaticBlock extends Block implements Physics{
	/**
	 * Creates a new static block with the specified values.
	 * 
	 * @param iD the id of t he new block.
	 * @param xPos the horizontal position of the new block.
	 * @param yPos the vertical position of the new block.
	 * @param ref the path to the image of the new block.
	 */
	public StaticBlock( int iD, int xPos, int yPos, ImageStore ref) {
		super(iD, xPos, yPos, ref);
	}
	/**
	 * Changes the positions of this block to new ones.
	 * 
	 * @param xPos the new horizontal position.
	 * @param yPos the new vertical position.
	 */
	public void addPoses(int xPos, int yPos){
		setXPos(xPos);
		setYPos(yPos);
	}
	/**
	 * Draws this block at its positions.
	 */
	@Override
	public void draw(Graphics g) {
		this.getImage().draw(this.getXPos(), this.getYPos());
	}

	/**
	 * Creates and returns a new block with the desired positions
	 * and the same block-subclass as this one (StaticBlock).
	 * 
	 * @param x the horizontal position of the new block.
	 * @param y the vertical position of the new block.
	 * 
	 * @return the new block.
	 */
	@Override
	public Block clone(int x, int y) {
		return new StaticBlock(getID(), x, y, getImage());
	}
	
	@Override
	public void doPhysics() {
		// TODO Auto-generated method stub
		
	}
	
}
