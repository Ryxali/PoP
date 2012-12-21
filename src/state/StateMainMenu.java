package state;

import image.AnimationStore;
import image.ImageStore;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.BigImage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.Game;

import button.Button;
import button.ButtonStore;


public class StateMainMenu extends BasicMenuState {
	public StateMainMenu(int state) {

	}

	

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		AnimationStore.MENU_MAIN_CLUTTER.reload();
		AnimationStore.MENU_MAIN_CLUTTER.setDir(AnimationStore.DIR_REVERSE);
		AnimationStore.MENU_MAIN_CLUTTER.getAnimation().setLooping(false);
	}

	public void unloadUsedResources() {
		// Just a check since we want to keep these animations when we go into
		// options etc.
		if (State.getNextState() != 3 && State.getNextState() != 2) {
			ImageStore.BACKGROUND_MENU_MAIN_STATIC.unload();
			ImageStore.BACKGROUND_MENU_LIGHT_STATIC.unload();
			ImageStore.BACKGROUND_MENU_SHADOW_STATIC.unload();
			AnimationStore.MENU_FIRE.unload();
			AnimationStore.MENU_LIGHT.unload();
		}
		ButtonStore.NEW_GAME.getButton().unload();
		ButtonStore.EXIT.getButton().unload();
		ButtonStore.OPTIONS.getButton().unload();
		ButtonStore.LOAD_GAME.getButton().unload();
	}

	/**
	 * A check based on the menu clutters fading animation. Note that this
	 * method will change state automatically on it's own.
	 * 
	 * @param sbg the game itself
	 * @return true if it is about fade out, false if it's not
	 * 
	 */
	public boolean isChangingState(StateBasedGame sbg) {
		if (!AnimationStore.MENU_MAIN_CLUTTER.isRegularDir()) {
			if (AnimationStore.MENU_MAIN_CLUTTER.getAnimation().getFrame() == AnimationStore.MENU_MAIN_CLUTTER
					.getAnimation().getFrameCount() - 1) {
				return false;
			}
		} else {
			if (AnimationStore.MENU_MAIN_CLUTTER.getAnimation().getFrame() == AnimationStore.MENU_MAIN_CLUTTER
					.getAnimation().getFrameCount() - 1) {
				AnimationStore.MENU_MAIN_CLUTTER
						.setDir(AnimationStore.DIR_REVERSE);
				enterState(sbg);
			}
		}
		return true;
	}

	/**
	 * Jumps to warp 5 towards the next state
	 * 
	 * @param sbg
	 *            the game itself
	 */
	public void enterState(StateBasedGame sbg) {
		unloadUsedResources();
		if (State.getNextState() == State.EXIT_GAME) {
			System.exit(0);
		}
		State.enterState(sbg, State.getNextState());
	}
	/**
	 * This handles all actions the player takes while in the main menu,
	 * so everything button state changes go here.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		x = input.getMouseX();
		y = input.getMouseY();
		int nState = checkButtonsAndSetNextState(input);
		if (nState != 0 && nState != State.getNextState()) {
			State.setNextState(nState);
			AnimationStore.MENU_MAIN_CLUTTER.setDir(true);
		}

		if (input.isKeyDown(Input.KEY_0)) {
			// setResolution(800, 600, gc, sbg);
			AnimationStore.MENU_MAIN_CLUTTER.getAnimation().update(5);
		} else if (input.isKeyDown(Input.KEY_1)) {
			AnimationStore.MENU_ANIMATION.reload();
		}

	}
	@Override
	public void renderButtons(GameContainer gc, StateBasedGame sbg, Graphics g) {
		ButtonStore.NEW_GAME.getButton().draw(g);
		ButtonStore.LOAD_GAME.getButton().draw(g);
		ButtonStore.OPTIONS.getButton().draw(g);
		ButtonStore.EXIT.getButton().draw(g);
		
	}
	/**
	 * checks all active buttons if they're being hovered or clicked.
	 * @param input the current user input
	 * @return possibly the next state the game should go into. Either
	 * that or 0 for nothing or 9001 for exiting the game.
	 */
	public int checkButtonsAndSetNextState(Input input) {
		ButtonStore.NEW_GAME.getButton().buttonStateCheck(input);
		ButtonStore.LOAD_GAME.getButton().buttonStateCheck(input);
		ButtonStore.OPTIONS.getButton().buttonStateCheck(input);
		ButtonStore.EXIT.getButton().buttonStateCheck(input);
		if (ButtonStore.NEW_GAME.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_PLAY_MAIN.getID();
		} else if (ButtonStore.LOAD_GAME.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_MENU_LOADGAME.getID();
		} else if (ButtonStore.OPTIONS.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_MENU_OPTIONS.getID();
		} else if (ButtonStore.EXIT.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.EXIT_GAME;
		}
		return 0;
	}
	
	@Override
	public int getID() {
		return State.STATE_MENU_MAIN.getID();
	}



	
}
