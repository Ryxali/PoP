package image;

import org.newdawn.slick.Graphics;
/**
 * An interface containing a draw method. To purpose of this
 * is to enable manipulation of different objects that can still
 * be drawn.
 * 
 * @author Niklas L
 * 
 * @deprecated it is good to be able to use the graphics object
 * for debug purposes, which is why all Drawable interfaces
 * should implement DrawableG instead.
 * 
 * @see DrawableG
 * @see DrawableXY
 * @see DrawableXYG
 */
public interface Drawable {
	/**
	 * Draw the contents of this object onto the screen.
	 * @deprecated this is part of a deprecated interface
	 */
	public void draw();
}
