package image;

/**
 * 
 * @author Niklas L
 * 
 * @deprecated This interface falls before the same issue as Drawable
 * and is therefore discouraged.
 * 
 * @see Drawable
 * @see DrawableXYG
 * @see DrawableG
 *
 */
public interface DrawableXY {
	/**
	 * Draw the contents of this object onto the screen.
	 * @param x the x position for this object to reference from
	 * @param y the y position for this object to reference from
	 * @deprecated this method is part of a deprecated class
	 */
	public void draw(int x, int y);
}
