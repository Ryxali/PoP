package gui;

import image.Drawable;
import image.DrawableXYG;
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
	//private static final int getFrameX = 300;
	private static final int buttonFrameY = 900;
	private Inventory() {
		slots = new ArrayList<InventorySlot>();
		int x = 8;
		for (int i = 1; i < x; i++) {
			slots.add(new InventorySlot(
					(int) (getFrameX() + ImageStore.INVENTORY_FRAME.getImage().getWidth()*i/x
							-ImageStore.INVENTORY_FRAME_SLOT.getImage().getWidth()/2),
					buttonFrameY + ImageStore.INVENTORY_FRAME.getImage().getHeight()/2
					- ImageStore.INVENTORY_FRAME_SLOT.getImage().getHeight()/2,
					ImageStore.INVENTORY_FRAME_SLOT,
					ImageStore.INVENTORY_FRAME_SLOT_IDLE,
					ImageStore.INVENTORY_FRAME_SLOT_HOVER,
					ImageStore.INVENTORY_FRAME_SLOT_PRESSED));

		}
		slots.get(0).put(Component.COG.getPart());
		slots.get(1).put(Component.DUST.getPart());
		slots.get(2).put(Component.STICK.getPart());
		slots.get(4).put(Component.GUN.getPart());
		//slots.get(6).put(Component.VACCUM.getPart());
	}

	public static Inventory get() {
		if (inv == null) {
			inv = new Inventory();
		}
		return inv;
	}
	
	private static int getFrameX(){
		return Game.getGameContainer().getWidth()/2 - ImageStore.INVENTORY_FRAME.getImage().getWidth()/2;
	}

	@Override
	public void draw(Graphics g) {
		ImageStore.INVENTORY_FRAME.draw(getFrameX(), buttonFrameY);
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

	public DrawableXYG checkPickups(Input input, DrawableXYG heldItem) {
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
