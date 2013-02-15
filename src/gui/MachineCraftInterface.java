package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import priceofprogress.Game;
import image.ImageStore;

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
				System.out.println(i + " " + j);
				craftingSlots[ind] = new InventorySlot(
						ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()/4*(i*2),
						ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()/4*(j*2),
						ImageStore.OVERLAY_CRAFTING_SLOT,
						ImageStore.INVENTORY_FRAME_SLOT_IDLE,
						ImageStore.INVENTORY_FRAME_SLOT,
						ImageStore.INVENTORY_FRAME_SLOT_PRESSED);
				ind++;
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		double x = (double) (Game.getGameContainer().getWidth() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getWidth()) / 2d;
		double y = (double) (Game.getGameContainer().getHeight() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getHeight()) / 2d;
		ImageStore.OVERLAY_CRAFTING_BACKGROUND.draw((int) x, (int) y);
		for(int i = 0; i < craftingSlots.length; i++){
			craftingSlots[i].draw();
		}
	}

	@Override
	public void update(Input input) {
		for (int i = 0; i < craftingSlots.length; i++) {
			craftingSlots[i].buttonStateCheck(input);
			
		}
	}

}
