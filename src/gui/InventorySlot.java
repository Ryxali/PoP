package gui;

import org.newdawn.slick.Graphics;

import state.State;

import machines.Machine;
import machines.Part;
import image.Drawable;
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
	private boolean isEmpty = true;
	private Drawable storedItem = null;

	public boolean isEmpty(Object heldItem) {

		return true;

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
			storedItem.draw(x, y, g);
		}

	}

	public void put(Drawable part) {
		this.storedItem = part;
		part = null;
	}

	public Drawable takeItem() {
		Drawable obj;
		if (storedItem != null) {
			obj = storedItem;
			storedItem = null;
			return obj;
		}
		return null;
	}

	public Drawable slotContainCheck(Drawable heldItem) {
		if (heldItem != null) {
			if (storedItem == null) {
				put(heldItem);
				return null;
			} else {
				Drawable obj = storedItem;
				storedItem = heldItem;
				return obj;
			}
		} else {
			return takeItem();
		}
	}
}
