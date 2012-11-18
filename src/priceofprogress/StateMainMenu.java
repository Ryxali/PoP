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
	int x = 0;
	int y = 0;
	
	private int nextState = 0;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		

	}

	private void unloadUsedResources() {
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
		AnimationStore.MENU_CLUTTER.getAnimation().draw();
		ButtonStore.NEW_GAME.draw();
		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();
		
		stateChangeCheck(sbg);
		
		g.drawString(x + ", " + y, 300, 50);
	}
	
	private void stateChangeCheck(StateBasedGame sbg){
		if(AnimationStore.MENU_CLUTTER.getAnimation().getFrame() == AnimationStore.MENU_CLUTTER.getAnimation().getFrameCount()-1){
			enterState(sbg, nextState);
			AnimationStore.MENU_CLUTTER.getAnimation().setAutoUpdate(false);
		}
	}
	
	private void enterState(StateBasedGame sbg, int state){
		unloadUsedResources();
		sbg.enterState(state);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		x = input.getMouseX();
		y = input.getMouseY();
		switch (checkButtons(input)) {
		case 0:
			break;
		case 1:
			nextState = State.STATE_PLAY_MAIN.getID();
			AnimationStore.MENU_CLUTTER.getAnimation().setAutoUpdate(true);
			break;
		default:
			break;
		}

		if (input.isKeyDown(Input.KEY_0)) {
			//setResolution(800, 600, gc, sbg);
			AnimationStore.MENU_CLUTTER.getAnimation().update(5);
		} else if (input.isKeyDown(Input.KEY_1)) {
			AnimationStore.MENU_ANIMATION.reload();
		}
		// TODO Auto-generated method stub

	}

	private int checkButtons(Input input) {
		ButtonStore.NEW_GAME.buttonStateCheck(input);
		if (ButtonStore.NEW_GAME.getState() == ButtonStore.STATE_HOVER
				&& ButtonStore.NEW_GAME.isClicked()) {
			return 1;
		}
		return 0;
	}

	public void setResolution(int newResWidth, int newResHeight,
			GameContainer gc, StateBasedGame sbg) throws SlickException {
		Game.appgc.setDisplayMode(newResWidth, newResHeight, true);
		ArrayList<AnimationStore> as = new ArrayList<AnimationStore>();
		Collections.addAll(as, AnimationStore.values());
		for (int i = 0; i < as.size(); i++) {
			as.get(i).reload();
		}
		ArrayList<ImageStore> is = new ArrayList<ImageStore>();
		Collections.addAll(is, ImageStore.values());
		for (int i = 0; i < is.size(); i++) {
			is.get(i).reload();
		}
		init(gc, sbg);
	}

	@Override
	public int getID() {
		return State.STATE_MENU_MAIN.getID();
	}
}
