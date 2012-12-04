package priceofprogress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.newdawn.slick.Graphics;

public enum DropdownList {
	RESOLUTION(strPack("1990x1321", "MEGAPLOX"), ImageStore.BUTTON_LIST_ITEM_RESOLUTION);
	/**
	 * The List items for the dropdown list
	 */
	private String[] items;
	/**
	 * The background image used as a frame for the list items.
	 * This will loop according to amount of list items.
	 */
	private final ImageStore listBg;
	/**
	 * 
	 * @param items The list items
	 * @param listBg
	 */
	private DropdownList(String[] items, ImageStore listBg){
		this.items = items;
		this.listBg = listBg;
	}
	
	
	/**
	 * Neatly and efficiently packs all strings given and returns them as an array.
	 * This is only used in the constructor of the enums as you can't type string arrays
	 * there.
	 * @param str1 the mandetory first string
	 * @param strings all strings in succession
	 * @return a bundle of strings
	 */
	private static String[] strPack(String str1, String... strings){
		String [] str = Arrays.copyOf(strings, strings.length+1);
		str[str.length-1] = str1;
		return str;
	}
	/**
	 * 
	 * @return the list items as a string array
	 */
	public String[] getItems() {
		return items;
	}
	/**
	 * draws the list at the right side of the given button
	 * @param g the graphics context
	 * @param b the button to draw alongside with
	 */
	public void draw(Graphics g, ButtonStore b){
		int xDraw = b.getX()+b.getImage().getWidth();
		for(int i = 0; i < items.length; i++){
			int yDraw = b.getY() + listBg.getImage().getHeight()*i;
			listBg.getImage().draw(xDraw, yDraw);
			g.drawString(items[i], xDraw +listBg.getImage().getWidth()/4, 
					yDraw + listBg.getImage().getHeight()/2);
		}
	}
	
	
	/**
	 * Put an array here to set what list items this enum contains
	 * @param items
	 */
	public void setItems(String[] items) {
		this.items = items;
	}
	/**
	 * fetches the framing image for the list items. Note that this loops.
	 * @return the bg image
	 */
	public ImageStore getListBg() {
		return listBg;
	}
}
