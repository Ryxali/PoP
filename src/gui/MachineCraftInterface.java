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
	public static MachineCraftInterface get(){
		if(mc == null){
			mc = new MachineCraftInterface();
		}
		return mc;
	}
	private MachineCraftInterface(){
		
	}
	@Override
	public void draw(Graphics g) {
		double x = (double) (Game.getGameContainer().getWidth() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getWidth()) / 2d;
		double y = (double) (Game.getGameContainer().getHeight() - ImageStore.OVERLAY_CRAFTING_BACKGROUND
				.getImage().getHeight()) / 2d;
		ImageStore.OVERLAY_CRAFTING_BACKGROUND.draw((int)x, (int)y);
		ImageStore.OVERLAY_CRAFTING_SLOT.draw(
				(int)(x + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()*0.25 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getWidth()/2), 
				(int)(y + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getHeight()*0.25 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getHeight()/2));
		ImageStore.OVERLAY_CRAFTING_SLOT.draw(
				(int)(x + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()*0.75 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getWidth()/2), 
				(int)(y + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getHeight()*0.25 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getHeight()/2));
		ImageStore.OVERLAY_CRAFTING_SLOT.draw(
				(int)(x + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()*0.25 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getWidth()/2), 
				(int)(y + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getHeight()*0.75 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getHeight()/2));
		ImageStore.OVERLAY_CRAFTING_SLOT.draw(
				(int)(x + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getWidth()*0.75 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getWidth()/2), 
				(int)(y + ImageStore.OVERLAY_CRAFTING_BACKGROUND.getImage().getHeight()*0.75 
						- ImageStore.OVERLAY_CRAFTING_SLOT.getImage().getHeight()/2));
	}

	@Override
	public void update(Input input) {
		
	}

}
