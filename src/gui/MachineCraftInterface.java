package gui;

import image.Drawable;
import image.ImageStore;
import machines.Machine;
import machines.Part;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import priceofprogress.Game;
import button.Button;

/**
 * This is the special gui used to combine machines. It needs to contain the
 * following:
 * <ul>
 * <li>Four Slots to put the machines in</li>
 * <li>Some sort of inventory to put machines and parts into</li>
 * <li>A disassemble button</li>
 * <li>The minecraft-esque finished product window</li>
 * <li>The good old back button</li>
 * </ul>
 * <br />
 * 
 * It's important to note that, since the idea of having to craft things on the
 * rush, it's imperative that the player is able to enter this ui quickly and
 * all things are easily accessed. We should, however, balance the difficulty by
 * slowing down the game as you are crafting.
 * 
 * @author Niklas L
 * 
 */
public class MachineCraftInterface extends Interface {

	private static MachineCraftInterface mc;
	private InventorySlot[] craftingSlots = new InventorySlot[4];
	private InventorySlot resultSlot;

	public static MachineCraftInterface get() {
		if (mc == null) {
			mc = new MachineCraftInterface();
		}
		return mc;
	}

	private MachineCraftInterface() {
		int ind = 0;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				craftingSlots[ind] = new InventorySlot(
						(int) (ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()/4*(i)
						+ ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getWidth()/2 + (int)getX()),
						(int) (ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()/4*(j*2)
						+ ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getHeight()/2 + (int)getY()),
						ImageStore.OVERLAY_CRAFTING_SLOT,
						ImageStore.INVENTORY_FRAME_SLOT_IDLE,
						ImageStore.INVENTORY_FRAME_SLOT_HOVER,
						ImageStore.INVENTORY_FRAME_SLOT_PRESSED);
				ind++;
			}
		}
		resultSlot = new InventorySlot(
				(int)(getX() + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()/4*3),
				(int)(getY() + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getHeight()/2),
				ImageStore.OVERLAY_CRAFTING_SLOT,
				ImageStore.INVENTORY_FRAME_SLOT_IDLE,
				ImageStore.INVENTORY_FRAME_SLOT_HOVER,
				ImageStore.INVENTORY_FRAME_SLOT_PRESSED);
	}

	@Override
	public void draw(Graphics g) {
		double x = (double) (Game.getGameContainer().getWidth() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getWidth()) / 2d;
		double y = (double) (Game.getGameContainer().getHeight() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getHeight()) / 2d;
		ImageStore.OVERLAY_CRAFTING_BACKGROUND.draw((int) getX(), (int) getY());
		for(int i = 0; i < craftingSlots.length; i++){
			craftingSlots[i].draw(g);
		}
		resultSlot.draw(g);
	}
	
	private double getX(){
		return (double) (Game.getGameContainer().getWidth() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getWidth()) / 2d;
	}
	private double getY(){
		return (double) (Game.getGameContainer().getHeight() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getHeight()) / 2d;
	}

	@Override
	public void update(Input input) {
		boolean full = true;
		for (int i = 0; i < craftingSlots.length; i++) {
			craftingSlots[i].buttonStateCheck(input);
			if(craftingSlots[i].isEmpty()){
				full = false;
			}
			
		}
		resultSlot.buttonStateCheck(input);
		if(full){
			resultSlot.put(new Machine((Part)craftingSlots[0].takeItem(),(Part) craftingSlots[1].takeItem(),
					(Part)craftingSlots[2].takeItem(), (Part)craftingSlots[3].takeItem()));
		}
	}
	public Drawable checkPickups(Input input, Part heldItem) {
		for (int i = 0; i < craftingSlots.length; i++) {
			if(craftingSlots[i].hasBeenClicked() == Button.PRESSED_TRUE){
				if(heldItem == null){
					return craftingSlots[i].slotContainCheck(heldItem);
				}else if(heldItem.canFit(i)){
					return craftingSlots[i].slotContainCheck(heldItem);
				}
				
			}
		}
		if(resultSlot.hasBeenClicked() == Button.PRESSED_TRUE){
			if(heldItem == null && !resultSlot.isEmpty()){
				return resultSlot.takeItem();
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
