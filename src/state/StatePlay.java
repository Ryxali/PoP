package state;

import gui.Interface;
import gui.Inventory;
import gui.MachineCraftInterface;
import image.Drawable;
import image.ImageStore;

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


public class StatePlay extends BasicGameState {
	
	private boolean isCrafting = false;
	
	private int mouseX = 0;
	private int mouseY = 0;
	
	private Drawable heldItem;
	
	public StatePlay(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		ImageStore.COMPANY_LOGO.draw(0, 0);
		Characters.MAIN_CHAR.getCharacter().draw();
		Entities.WOUBLE.getEntity().draw();
		Inventory.get().draw();
		if(isCrafting){
			MachineCraftInterface.get().draw();
		}
		if(heldItem != null){
			heldItem.draw(mouseX, mouseY, g);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Game.updateDelta(gc.getTime());
		Characters.MAIN_CHAR.getCharacter().update(gc.getInput());
		Entities.WOUBLE.getEntity().update();
		if(gc.getInput().isKeyDown(Input.KEY_E)){
			isCrafting = true;
		}else{
			isCrafting = false;
		}
		Inventory.get().update(gc.getInput());
		Object obj = Inventory.get().checkPickups();
		if(!obj.equals(0) && obj instanceof Drawable){
			heldItem = (Drawable) obj;
		}
		mouseX = gc.getInput().getMouseX();
		mouseY = gc.getInput().getMouseY();
		
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return State.STATE_PLAY_MAIN.getID();
	}
}
