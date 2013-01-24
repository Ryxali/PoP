package state;

import gui.MachineCraftInterface;
import image.ImageStore;

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
		if(isCrafting){
			MachineCraftInterface.get().draw();
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
		
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return State.STATE_PLAY_MAIN.getID();
	}
}
