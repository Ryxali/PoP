package state;

import java.util.ArrayList;
import java.util.HashMap;

import image.AnimationStore;
import image.ImageStore;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import button.ButtonStore;

public abstract class BasicMenuState extends BasicGeneralState{
	int x = 0;
	int y = 0;
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.getImage().draw();
		AnimationStore.MENU_MAIN_CLUTTER.getAnimation().draw();
		
		if (!isChangingState(sbg)) {
			renderButtons(gc, sbg, g);
		}

		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();

		g.drawString(x + ", " + y, 300, 50);
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
		}else{
			State.enterState(sbg, State.getNextState());
		}
	}
	public abstract int checkButtonsAndSetNextState(Input input);
	public abstract void unloadUsedResources();
	public abstract void renderButtons(GameContainer gc, StateBasedGame sbg, Graphics g);
}
