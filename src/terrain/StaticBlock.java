package terrain;

import image.ImageStore;

import org.newdawn.slick.Graphics;

import physics.Physics;

public class StaticBlock extends Block implements Physics{

	public StaticBlock( int iD, int xPos, int yPos, ImageStore ref) {
		super(iD, xPos, yPos, ref);
	}
	
	public void addPoses(int xPos, int yPos){
		setXPos(xPos);
		setYPos(yPos);
	}

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
