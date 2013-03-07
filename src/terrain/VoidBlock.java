package terrain;

import org.newdawn.slick.Graphics;
/**
 * A block that has a no visual representation and is not affected by physics.
 * 
 * @author Lukas
 *
 */
public class VoidBlock extends Block{
	/**
	 * Creates a new void block with the specified values.
	 * 
	 * @param iD the id of t he new block.
	 * @param xPos the horizontal position of the new block.
	 * @param yPos the vertical position of the new block.
	 */
	public VoidBlock( int iD, int xPos, int yPos) {
		super(iD, xPos, yPos, null);
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
