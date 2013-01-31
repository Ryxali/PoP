package terrain;

import image.ImageStore;

import org.newdawn.slick.Graphics;

public abstract class Block {
	
	private int x;
	private int y;
	private ImageStore ref;
	
	public Block(int xPos, int yPos, ImageStore ref){
		this.x = xPos;
		this.y = yPos;
		this.ref = ref;
	}
	
	public abstract void draw(Graphics g);
	
	public int getXPos(){
		return this.x;
	}
	
	public int getYPos(){
		return this.y;
	}
	
	public ImageStore getRef(){
		return this.ref;
	}
	
	public void setXPos(int x){
		this.x = x;
	}
	
	public void setYPos(int y){
		this.y = y;
	}
	
	public abstract Block clone(int x, int y);
}