package terrain;



import image.DrawableXYG;
import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Block implements DrawableXYG{
	
	private final int id;
	private final ImageStore image;
	/**
	 * @param id
	 * @param image
	 */
	public Block(int id, ImageStore image){
		this.id = id;
		this.image = image;
	}
	
	public int getID(){
		return id;
	}
	
	public ImageStore getImage(){
		return image;
	}
}