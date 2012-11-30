package priceofprogress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.newdawn.slick.Graphics;

public enum DropdownList {
	RESOLUTION(strPack("", ""), ButtonStore.RESOLUTION, ImageStore.BUTTON_SLIDER);
	
	private String[] items;
	private final ImageStore listBg;
	private ButtonStore srcButton;
	
	private DropdownList(String[] items, ButtonStore srcButton, ImageStore listBg){
		this.items = items;
		this.listBg = listBg;
		this.srcButton = srcButton;
	}
	
	
	
	private static String[] strPack(String str1, String... strings){
		String [] str;
		str = Arrays.copyOf(strings, strings.length+1);
		str[str.length-1] = str1;
		return str;
	}

	public String[] getItems() {
		return items;
	}
	
	public void draw(Graphics g){
		int xDraw = srcButton.getX()+srcButton.getImage().getWidth();
		for(int i = 0; i < items.length; i++){
			int yDraw = srcButton.getY() + listBg.getImage().getHeight()*i;
			listBg.getImage().draw(xDraw, yDraw);
			g.drawString(items[i], xDraw +listBg.getImage().getWidth()/2, 
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
