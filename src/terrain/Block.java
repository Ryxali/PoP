package terrain;

import org.newdawn.slick.Graphics;

public abstract class Block {
	
	private int x;
	private int y;
	
	public abstract void draw(Graphics g);
	
}
