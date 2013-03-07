package terrain;

import image.ImageStore;

import org.newdawn.slick.Graphics;
/**
 * A block is a square of an element in the game.
 * Blocks are used to build a map and are saved as numbers in maps.
 * Different blocks have different values and functions.
 * 
 * @author Lukas
 *
 */
public abstract class Block {
	/** The id of this block */
	private int iD;
	/** The horizontal position of this block. */
	private int x;
	/** The vertical position of this block. */
	private int y;
	/** The path to the image of this block. */
	private ImageStore ref;
	
	public Block( int iD, int xPos, int yPos, ImageStore ref){
		this.iD = iD;
		this.x = xPos;
		this.y = yPos;
		this.ref = ref;
	}
	/** Specified where it is not abstract. */
	public abstract void draw(Graphics g);
	/**
	 * Fetch the id of this block.
	 * 
	 * @return the id of this block.
	 */
	public int getID(){
		return this.iD;
	}
	/**
	 * Fetch the horizontal position of this block.
	 * 
	 * @return the horizontal position of this block.
	 */
	public int getXPos(){
		return this.x;
	}
	/**
	 * Fetch the vertical position of this block.
	 * 
	 * @return the vertical position of this block.
	 */
	public int getYPos(){
		return this.y;
	}
	/**
	 * Fetch the path to the image of this block.
	 * 
	 * @return the path to the image of this block.
	 */
	public ImageStore getImage(){
		return this.ref;
	}
	/**
	 * Sets the horizontal of this block to a new one.
	 * 
	 * @param x the new horizontal position.
	 */
	public void setXPos(int x){
		this.x = x;
	}
	/**
	 * Sets the vertical of this block to a new one.
	 * 
	 * @param x the new vertical position.
	 */
	public void setYPos(int y){
		this.y = y;
	}
	/** Specified where it is not abstract. */
	public abstract Block clone(int x, int y);
}