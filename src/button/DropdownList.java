package button;



import image.ImageStore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import priceofprogress.Game;





public enum DropdownList {
	RESOLUTION(Game.getValidResolutions(), 
			new StandardButton(
					ImageStore.BUTTON_LOADGAME_IDLE,
					ImageStore.BUTTON_LOADGAME_HOVER,
					ImageStore.BUTTON_LOADGAME_PRESSED), 
			5);
	/**
	 * The List items for the dropdown list
	 */
	private String[] items;
	/**
	 * The background image used as a frame for the list items.
	 * This will loop according to amount of list items.
	 */
	//private final StandardButton[] listBg;
	
	private ArrayList<StandardButton> bgButtons = new ArrayList<StandardButton>();
	/**
	 * 
	 * @param items The list items
	 * @param listBg
	 */
	private int listDispLength;
	private DropdownList(String[] items, StandardButton bgButton, int listDispLength){
		this.items = items;
		System.out.println(items.length + " <..---");
		for(int i = 0; i < items.length; i++){
			bgButtons.add(bgButton.copy());
		}
		
		this.listDispLength = listDispLength;
	}
	
	
	
	/**
	 * Neatly and efficiently packs all strings given and returns them as an array.
	 * This is only used in the constructor of the enums as you can't type string arrays
	 * there.
	 * @param str1 the mandatory first string
	 * @param strings all strings in succession
	 * @return a bundle of strings
	 */
	private static String[] strPack(String str1, String... strings){
		strings = Arrays.copyOf(strings, strings.length+1);
		for(int i = strings.length-1; i > 0; i--){
			strings[i] = strings[i-1];
		}
		strings[0] = str1;
		return strings;
	}
	
	public int getDisplayLength(){
		if(listDispLength > items.length){
			return items.length;
		}else{
			return listDispLength;
		}
	}
	/**
	 * 
	 * @return the list items as a string array
	 */
	public String[] getItems() {
		return items;
	}
	
	public String getItem(int index){
		return items[index];
	}
	public int[] getResValues(int index){
		String s = items[index];
		int[] i = { Integer.parseInt(items[index].substring(0, items[index].indexOf("x"))),
				Integer.parseInt(items[index].substring(items[index].indexOf("x")))};
		return i;
	}
	/**
	 * draws the list at the right side of the given button
	 * @param g the graphics context
	 * @param b the button to draw alongside with
	 */
	public void draw(Graphics g, ButtonStore b){
		int xDraw = b.getButton().getX()+b.getButton().getStoredImage().getImage().getWidth();
		for(int i = 0; i < items.length && i < listDispLength; i++){
			if(bgButtons.get(i)==null){
				bgButtons.set(i, bgButtons.get(0).copy());
			}
			
			int yDraw = b.getButton().getY() + bgButtons.get(i).getStoredImage().getImage().getHeight()*i;
			bgButtons.get(i).getStoredImage().draw(xDraw, yDraw);
			g.drawString(items[i], xDraw +bgButtons.get(i).getStoredImage().getImage().getWidth()/4, 
					yDraw + bgButtons.get(i).getStoredImage().getImage().getHeight()/2);
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
	public StandardButton getBgButton() {
		return bgButtons.get(0);
	}
	public StandardButton getBgButton(int index) {
		if(bgButtons.get(index) == null){
			bgButtons.set(index, bgButtons.get(0).copy());
		}
		return bgButtons.get(index);
	}
	public ArrayList<StandardButton> getBgButtons(){
		return bgButtons;
	}
}
