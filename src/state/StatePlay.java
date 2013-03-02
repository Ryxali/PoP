package state;

import java.util.ArrayList;

import gui.Interface;
import gui.Inventory;
import gui.MachineCraftInterface;
import image.AnimationStore;
import image.BackDrop;
import image.Drawable;
import image.ImageCluster;
import image.ImageLoader;
import image.ImageStore;
import image.Loadable;

import machines.Machine;
import machines.Part;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.Game;

import entities.Characters;
import entities.Entities;

public class StatePlay extends BasicGeneralState {

	private boolean isCrafting = false;

	private int mouseX = 0;
	private int mouseY = 0;
	

	private Drawable heldItem;

	public StatePlay(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		BackDrop.get().rebuild(ImageCluster.FOREST);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.COMPANY_LOGO.draw(0, 0);
		BackDrop.get().draw(g);
		Characters.MAIN_CHAR.getCharacter().draw();
		Entities.WOUBLE.getEntity().draw();
		Inventory.get().draw(g);
		if (isCrafting) {
			MachineCraftInterface.get().draw(g);
		}
		if (heldItem != null) {
			heldItem.draw(mouseX, mouseY, g);
		}
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Game.updateDelta(gc.getTime());
		Characters.MAIN_CHAR.getCharacter().update(gc.getInput());
		BackDrop.get().update(gc.getInput());
		Entities.WOUBLE.getEntity().update();
		if (gc.getInput().isKeyDown(Input.KEY_E)) {
			Game.setGameSpeed(0.5f);
			isCrafting = true;
		} else {
			Game.setGameSpeed(1f);
			isCrafting = false;
		}
		Inventory.get().update(gc.getInput());
		heldItem = Inventory.get().checkPickups(gc.getInput(), heldItem);
		MachineCraftInterface.get().update(gc.getInput());
		if (heldItem instanceof Part || heldItem == null) {
			heldItem = MachineCraftInterface.get().checkPickups(gc.getInput(),
					(Part) heldItem);
		}
		mouseX = gc.getInput().getMouseX();
		mouseY = gc.getInput().getMouseY();

	}

	public Drawable getHeldPart() {
		return heldItem;
	}

	public void setHeldPart(Drawable dObj) {
		heldItem = dObj;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return State.STATE_PLAY_MAIN.getID();
	}

	@Override
	public void queueImagesViaImageLoader() {
		ImageLoader.get().queue(AnimationStore.CHARACTER_IDLE,
				ImageCluster.BACKDROP_FOREST_FAR,
				ImageCluster.BACKDROP_FOREST_MID,
				ImageCluster.BACKDROP_FOREST_SHORT,
				ImageCluster.BACKDROP_FOREST_SKY);

	}

	@Override
	public ArrayList<Loadable> getUsedResources() {
		ArrayList<Loadable> usd = new ArrayList<Loadable>();
		return usd;
	}

	@Override
	public void setStateChange(boolean isChanging) {
		// TODO Auto-generated method stub
		
	}
}
