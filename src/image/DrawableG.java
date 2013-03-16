package image;

import org.newdawn.slick.Graphics;
/**
 * An interface containing a draw method. To purpose of this
 * is to enable manipulation of different objects that can still
 * be drawn.
 * 
 * @author Niklas L
 * 
 * @see DrawableXYG
 *
 */
public interface DrawableG {
	/**
	 * Draw the contents of this object onto the screen.
	 * @param g the current graphics context
	 */
	public void draw(Graphics g);
}
