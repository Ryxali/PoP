package state;

import java.io.File;
import java.util.ArrayList;

import file.PPWDataLoader;
import gui.Interface;
import gui.Inventory;
import gui.MachineCraftInterface;
import image.AnimationStore;
import image.BackDrop;
import image.Drawable;
import image.DrawableXYG;
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
import terrain.Terrain;

import entities.Characters;
import entities.Entities;

public class StatePlay extends BasicGeneralState {

	private boolean isCrafting = false;

	private int mouseX = 0;
	private int mouseY = 0;
	

	private DrawableXYG heldItem;

	public StatePlay(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		BackDrop.get().rebuild(ImageCluster.FOREST);
		PPWDataLoader.get().loadTerrain(new File("maps/op.PPW"));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		ImageStore.COMPANY_LOGO.draw(0, 0);
		BackDrop.get().draw();
		//Terrain.get().draw();
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
	
	private void terrainUpdate(Input input){
		if(Characters.MAIN_CHAR.getCharacter().getX() < (double)Game.getGameContainer().getWidth()*0.25){
			Terrain.get().move((double)Game.getGameContainer().getWidth()*0.25-Characters.MAIN_CHAR.getCharacter().getX(), 0d);
			//Characters.MAIN_CHAR.getCharacter().setX(Game.getGameContainer().getWidth()*0.25);
		}else if((double)Game.getGameContainer().getWidth()*0.75 < Characters.MAIN_CHAR.getCharacter().getX()){
			Terrain.get().move((double)Game.getGameContainer().getWidth()*0.75-Characters.MAIN_CHAR.getCharacter().getX(), 0d);
			//Characters.MAIN_CHAR.getCharacter().setX(Game.getGameContainer().getWidth()*0.75);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Game.updateDelta(gc.getTime());
		Characters.MAIN_CHAR.getCharacter().update(gc.getInput());
		//terrainUpdate(gc.getInput());
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

	public DrawableXYG getHeldPart() {
		return heldItem;
	}

	public void setHeldPart(DrawableXYG dObj) {
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
