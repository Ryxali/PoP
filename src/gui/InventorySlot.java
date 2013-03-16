package gui;

import org.newdawn.slick.Graphics;

import state.State;

import machines.Machine;
import machines.Part;
import image.Drawable;
import image.DrawableXYG;
import image.ImageStore;
import button.Button;
import button.StandardButton;

public class InventorySlot extends StandardButton {
	public InventorySlot(int x, int y, ImageStore slotImg, ImageStore idleImg,
			ImageStore hoverImg, ImageStore pressedImg) {
		super(x, y, idleImg, hoverImg, pressedImg);
		this.slotImg = slotImg;
	}

	private ImageStore slotImg;
	private DrawableXYG storedItem = null;

	public boolean isEmpty() {
		if(storedItem == null){
			return true;
		}
		return false;

	}

	public void update() {
		/*
		 * if (storedItem == null && machine == null) { if (hasBeenClicked() ==
		 * Button.PRESSED_TRUE) { if (Inventory.get().g) {
		 * 
		 * } } else { isEmpty = true; } } else { isEmpty = false; }
		 */
	}

	@Override
	public void draw(Graphics g) {
		slotImg.draw(x, y);
		getStoredImage().draw(x, y);
		if (storedItem != null) {
			//System.out.println(x + " " + y);
			storedItem.draw(x, y, g);
			g.drawString("ITEM!", x, y);
		}

	}

	public void put(DrawableXYG part) {
		//System.out.println("AWW YEAH " + x + " " + y);
		storedItem = part;
		//part = null;
		//System.out.println(storedItem.toString());
	}

	public DrawableXYG takeItem() {
		DrawableXYG obj;
		if (storedItem != null) {
			obj = storedItem;
			storedItem = null;
			return obj;
		}
		return null;
	}

	public DrawableXYG slotContainCheck(DrawableXYG heldItem) {
		if (heldItem != null) {
			if (storedItem == null) {
				put(heldItem);
				return null;
			} else {
				DrawableXYG obj = storedItem;
				storedItem = heldItem;
				return obj;
			}
		} else {
			return takeItem();
		}
	}
}
