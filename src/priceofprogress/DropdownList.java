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
	
	
	
	private static String[] strPack(String str1, String... strings){
		String [] str = Arrays.copyOf(strings, strings.length+1);
		str[str.length-1] = str1;
		return str;
	}

	public String[] getItems() {
		return items;
	}
	
	public void draw(Graphics g, ButtonStore b){
		int xDraw = b.getX()+b.getImage().getWidth();
		for(int i = 0; i < items.length; i++){
			int yDraw = b.getY() + listBg.getImage().getHeight()*i;
			listBg.getImage().draw(xDraw, yDraw);
			g.drawString(items[i], xDraw +listBg.getImage().getWidth()/4, 
					yDraw + listBg.getImage().getHeight()/2);
		}
	}
	
	

	public void setItems(String[] items) {
		this.items = items;
	}

	public ImageStore getListBg() {
		return listBg;
	}
}
