package gui;

import image.Drawable;
import image.ImageStore;

import java.util.ArrayList;

import machines.Component;
import machines.PartVaccum;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import button.Button;
import button.StandardButton;

import priceofprogress.Game;

public class Inventory extends Interface {

	private final ArrayList<InventorySlot> slots;
	private static Inventory inv;

	private Inventory() {
		slots = new ArrayList<InventorySlot>();
		int x = 8;
		for (int i = 1; i < x; i++) {
			slots.add(new InventorySlot(
					(int) (i * 1920 * Game.getWidthScale() / (double) x),
					(int) (1200d * Game.getHeightScale() - ImageStore.INVENTORY_FRAME_SLOT
							.getImage().getHeight()),
					ImageStore.INVENTORY_FRAME_SLOT,
					ImageStore.INVENTORY_FRAME_SLOT_IDLE,
					ImageStore.INVENTORY_FRAME_SLOT_HOVER,
					ImageStore.INVENTORY_FRAME_SLOT_PRESSED));

		}
		slots.get(0).put(Component.VACCUM.getPart());
		slots.get(1).put(Component.VACCUM.getPart());
	}

	public static Inventory get() {
		if (inv == null) {
			inv = new Inventory();
		}
		return inv;
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < slots.size(); i++) {
			slots.get(i).draw(g);
		}
	}

	@Override
	public void update(Input input) {
		for (int i = 0; i < slots.size(); i++) {
			slots.get(i).buttonStateCheck(input);
			slots.get(i).update();
			/*
			 * if (!slots.get(i).isEmpty()) { if(slots.get(i).hasBeenClicked()
			 * == Button.PRESSED_TRUE){ slots.get(i).take(); } }
			 */
		}

	}

	public Drawable checkPickups(Input input, Drawable heldItem) {
		for (int i = 0; i < slots.size(); i++) {
			if(slots.get(i).hasBeenClicked() == Button.PRESSED_TRUE){
				return slots.get(i).slotContainCheck(heldItem);
			}
		}
		/*
		 * if (!slots.get(i).isEmpty(heldItem)) { if
		 * (slots.get(i).hasBeenClicked() == Button.PRESSED_TRUE) { return
		 * slots.get(i).take(); } }
		 */
		return heldItem;
	}
}
