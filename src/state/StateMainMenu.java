package state;


import image.AnimationStore;
import image.ImageLoader;
import image.ImageStore;
import image.Loadable;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import button.Button;
import button.ButtonStore;




public class StateMainMenu extends BasicMenuState {
	public StateMainMenu(int state) {

	}

	

	@Override
	public void initialize(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		AnimationStore.MENU_MAIN_CLUTTER.reload();
		AnimationStore.MENU_MAIN_CLUTTER.setDir(AnimationStore.DIR_REVERSE);
		AnimationStore.MENU_MAIN_CLUTTER.getAnimation().setLooping(false);
		addButton(ButtonStore.NEW_GAME);
		addButton(ButtonStore.LOAD_GAME);
		addButton(ButtonStore.OPTIONS);
		addButton(ButtonStore.EXIT);
	}
	
	@Override
	public AnimationStore setMenuClutter(){
		return AnimationStore.MENU_MAIN_CLUTTER;
	}
	
	/**
	 * checks all active buttons if they're being hovered or clicked.
	 * @param input the current user input
	 * @return possibly the next state the game should go into. Either
	 * that or 0 for nothing or 9001 for exiting the game.
	 */
	@Override
	public int checkButtonStates(Input input) {
		if (ButtonStore.NEW_GAME.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_PLAY_MAIN.getID();
		} else if (ButtonStore.LOAD_GAME.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_MENU_LOADGAME.getID();
		} else if (ButtonStore.OPTIONS.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_MENU_OPTIONS.getID();
		} else if (ButtonStore.EXIT.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.EXIT_GAME;
		}
		return State.NO_CHANGE;
	}
	
	@Override
	public int getID() {
		return State.STATE_MENU_MAIN.getID();
	}



	@Override
	public void queueImagesViaImageLoader() {
		ImageLoader.get().queue(
				AnimationStore.MENU_ANIMATION, AnimationStore.MENU_FIRE,
				AnimationStore.MENU_LIGHT, AnimationStore.MENU_MAIN_CLUTTER,
				ImageStore.BUTTON_EXIT_IDLE, ImageStore.BUTTON_LOADGAME_IDLE,
				ImageStore.BUTTON_OPTIONS_IDLE, ImageStore.BUTTON_PLAY_IDLE);
	}



	@Override
	public ArrayList<Loadable> getUsedResources() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setStateChange(boolean isChanging) {
		// TODO Auto-generated method stub
		
	}



	
}
