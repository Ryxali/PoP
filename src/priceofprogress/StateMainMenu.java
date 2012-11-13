package priceofprogress;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateMainMenu extends BasicGameState {
	public StateMainMenu(int state) {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		loadResources();
		
	}
	private void loadResources(){
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.reload();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.reload();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.reload();
		AnimationStore.MENU_FIRE.reload();
		AnimationStore.MENU_LIGHT.reload();
	}
	
	private void unloadResources(){
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.unload();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.unload();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.unload();
		AnimationStore.MENU_FIRE.unload();
		AnimationStore.MENU_LIGHT.unload();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.getImage().draw();
		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_0)){
			setResolution(800, 600, gc, sbg);
		}else if(input.isKeyDown(Input.KEY_1)){
			AnimationStore.MENU_ANIMATION.reload();
		}
		// TODO Auto-generated method stub

	}
	
	public void setResolution(int newResWidth, int newResHeight,
			GameContainer gc, StateBasedGame sbg) throws SlickException{
		Game.appgc.setDisplayMode(newResWidth, newResHeight, true);
		ArrayList<AnimationStore> as = new ArrayList<AnimationStore>();
		Collections.addAll(as, AnimationStore.values());
		for(int i = 0; i < as.size(); i++){
			as.get(i).reload();
		}
		ArrayList<ImageStore> is = new ArrayList<ImageStore>();
		Collections.addAll(is, ImageStore.values());
		for(int i = 0; i < is.size(); i++){
			is.get(i).reload();
		}
		init(gc, sbg);
	}
	
	

	@Override
	public int getID() {
		return State.STATE_MENU_MAIN.getID();
	}
}
