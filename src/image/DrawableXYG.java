package image;

import org.newdawn.slick.Graphics;
/**
 * Similar to DrawableG, this interface is designed to link together
 * different objects for more slick usage. This in particular allows 
 * for drawing with x, y parameters.
 * @author Niklas L
 * @see DrawableG
 */
public interface DrawableXYG {
	/**
	 * Draw the contents of this object onto the screen.
	 * @param g the current graphics context
	 * @param x the x position for this object to reference from
	 * @param y the y position for this object to reference from
	 */
	public void draw(int x, int y, Graphics g);
}
