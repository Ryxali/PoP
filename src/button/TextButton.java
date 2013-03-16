package button;

import org.newdawn.slick.Graphics;

import priceofprogress.Game;

import image.ImageStore;
/**
 * This is merely an extension of the
 * StandardButton simply adding on a text string onto it
 * 
 * @author Niklas L
 * 
 * @see button.StandardButton
 * @see image.ImageStore
 */
public class TextButton extends StandardButton{
	/**
	 * The string text of this button
	 */
	private String title;
	/**
	 * Creates a TextButton
	 * @param title the string text of this button
	 * @param x the absolute x position of this button
	 * @param y the absolute y position of this button
	 * @param idleImg the image representation of an idle button
	 * @param hoverImg the image representation of a hovered button
	 * @param pressedImg the image representation of a pressed button
	 */
	public TextButton(String title, int x, int y, ImageStore idleImg, ImageStore hoverImg,
			ImageStore pressedImg) {
		super(x, y, idleImg, hoverImg, pressedImg);
		this.title = title;
	}
	@Override
	public void draw(Graphics g){
		draw(getX(), getY(), g);
	}
	@Override
	public void draw(){
		draw(Game.getGameContainer().getGraphics());
	}
	@Override
	public void draw(int x, int y, Graphics g){
		getStoredImage().draw(x, y);
		g.drawString(title,
				x + getStoredImage().getImage().getWidth() /2 -
				g.getFont().getWidth(title),
				y + getStoredImage().getImage().getHeight() /2 -
				g.getFont().getHeight(title));
	}
	/**
	 * get the string text of this button.
	 * @return title
	 */
	public String getTitle(){
		return title;
	}
	/**
	 * sets the string text of this button.
	 * @param title the string text of this button
	 */
	public void setTitle(String title){
		this.title = title;
	}

}
