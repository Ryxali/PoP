package state;

import image.AnimationStore;
import image.ImageStore;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import button.ButtonStore;


public class StateOptionsMenu extends BasicMenuState {

	public StateOptionsMenu(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		AnimationStore.MENU_OPTIONS_CLUTTER.reload();
		AnimationStore.MENU_OPTIONS_CLUTTER.setDir(AnimationStore.DIR_REVERSE);
		AnimationStore.MENU_OPTIONS_CLUTTER.getAnimation().setLooping(false);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.getImage().draw();
		AnimationStore.MENU_OPTIONS_CLUTTER.getAnimation().draw();
		if(!isChangingState(sbg)){
			ButtonStore.RESOLUTION.getButton().draw(g);
			ButtonStore.KEY_CONFIG.getButton().draw(g);
			ButtonStore.VOLUME_MUSIC.getButton().draw(g);
			ButtonStore.VOLUME_SOUND.getButton().draw(g);
		}
		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		ButtonStore.RESOLUTION.getButton().buttonStateCheck(gc.getInput());
		// TODO Auto-generated method stub

	}
	
	public boolean isChangingState(StateBasedGame sbg) {
		if (!AnimationStore.MENU_OPTIONS_CLUTTER.isRegularDir()) {
			if (AnimationStore.MENU_OPTIONS_CLUTTER.getAnimation().getFrame() == AnimationStore.MENU_OPTIONS_CLUTTER
					.getAnimation().getFrameCount() - 1) {
				return false;
			}
		} else {
			if (AnimationStore.MENU_OPTIONS_CLUTTER.isRegularDir()) {
				if (AnimationStore.MENU_OPTIONS_CLUTTER.getAnimation().getFrame() == AnimationStore.MENU_OPTIONS_CLUTTER
						.getAnimation().getFrameCount() - 1) {
					AnimationStore.MENU_OPTIONS_CLUTTER
							.setDir(AnimationStore.DIR_REVERSE);
					State.enterState(sbg, State.getNextState());
				}
			}
		}
		return true;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return State.STATE_MENU_OPTIONS.getID();
	}

	public int checkButtonsAndSetNextState(Input input) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void enterState(StateBasedGame sbg) {
		// TODO Auto-generated method stub
		
	}

	public void setResolution(int newResWidth, int newResHeight,
			GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	public void unloadUsedResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderButtons(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
