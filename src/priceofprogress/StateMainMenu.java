package priceofprogress;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.BigImage;
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


	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		AnimationStore.MENU_MAIN_CLUTTER.reload();
		AnimationStore.MENU_MAIN_CLUTTER.setDir(AnimationStore.DIR_REVERSE);
		AnimationStore.MENU_MAIN_CLUTTER.getAnimation().setLooping(false);
	}

	private void unloadUsedResources() {
		// Just a check since we want to keep these animations when we go into
		// options etc.
		if (State.getNextState() != 3 && State.getNextState() != 2) {
			ImageStore.BACKGROUND_MENU_MAIN_STATIC.unload();
			ImageStore.BACKGROUND_MENU_LIGHT_STATIC.unload();
			ImageStore.BACKGROUND_MENU_SHADOW_STATIC.unload();
			AnimationStore.MENU_FIRE.unload();
			AnimationStore.MENU_LIGHT.unload();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.getImage().draw();
		AnimationStore.MENU_MAIN_CLUTTER.getAnimation().draw();
		if (!isChangingState(sbg)) {
			ButtonStore.NEW_GAME.draw(g);
			ButtonStore.LOAD_GAME.draw(g);
			ButtonStore.OPTIONS.draw(g);
			ButtonStore.EXIT.draw(g);
		}

		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();

		g.drawString(x + ", " + y, 300, 50);
	}

	private boolean isChangingState(StateBasedGame sbg) {
		if (!AnimationStore.MENU_MAIN_CLUTTER.isRegularDir()) {
			if (AnimationStore.MENU_MAIN_CLUTTER.getAnimation().getFrame() == AnimationStore.MENU_MAIN_CLUTTER
					.getAnimation().getFrameCount() - 1) {
				return false;
			}
		} else {
			if (AnimationStore.MENU_MAIN_CLUTTER.isRegularDir()) {
				if (AnimationStore.MENU_MAIN_CLUTTER.getAnimation().getFrame() == AnimationStore.MENU_MAIN_CLUTTER
						.getAnimation().getFrameCount() - 1) {
					AnimationStore.MENU_MAIN_CLUTTER.setDir(AnimationStore.DIR_REVERSE);
					enterState(sbg);
				}
			}
		}
		return true;
	}

	private void enterState(StateBasedGame sbg) {
		unloadUsedResources();
		if (State.getNextState() == 9001) {
			System.exit(0);
		}
		State.enterState(sbg, State.getNextState());
	}

	/**
	 * true == in, false == out
	 * 
	 * @param phase
	 */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		x = input.getMouseX();
		y = input.getMouseY();
		int res = checkButtons(input);
		if (res != 0 && res != State.getNextState()) {
			State.setNextState(res);
			AnimationStore.MENU_MAIN_CLUTTER.setDir(true);
		}

		if (input.isKeyDown(Input.KEY_0)) {
			// setResolution(800, 600, gc, sbg);
			AnimationStore.MENU_MAIN_CLUTTER.getAnimation().update(5);
		} else if (input.isKeyDown(Input.KEY_1)) {
			AnimationStore.MENU_ANIMATION.reload();
		}

	}

	private int checkButtons(Input input) {
		ButtonStore.NEW_GAME.buttonStateCheck(input);
		ButtonStore.LOAD_GAME.buttonStateCheck(input);
		ButtonStore.OPTIONS.buttonStateCheck(input);
		ButtonStore.EXIT.buttonStateCheck(input);
		if (ButtonStore.NEW_GAME.getState() == ButtonStore.STATE_HOVER
				&& ButtonStore.NEW_GAME.isClicked()) {
			return State.STATE_PLAY_MAIN.getID();
		} else if (ButtonStore.LOAD_GAME.getState() == ButtonStore.STATE_HOVER
				&& ButtonStore.OPTIONS.isClicked()) {
			return State.STATE_MENU_LOADGAME.getID();
		} else if (ButtonStore.OPTIONS.getState() == ButtonStore.STATE_HOVER
				&& ButtonStore.OPTIONS.isClicked()) {
			return State.STATE_MENU_OPTIONS.getID();
		} else if (ButtonStore.EXIT.getState() == ButtonStore.STATE_HOVER
				&& ButtonStore.EXIT.isClicked()) {
			return 9001;
		}
		return 0;
	}

	public void setResolution(int newResWidth, int newResHeight,
			GameContainer gc, StateBasedGame sbg) throws SlickException {
		Game.getGameContainer().setDisplayMode(newResWidth, newResHeight, true);
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
