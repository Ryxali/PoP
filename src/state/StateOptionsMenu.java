package state;

import file.OptionsFile;
import image.AnimationStore;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.Game;

import button.ButtonStore;
import button.ListButton;

public class StateOptionsMenu extends BasicMenuState {

	public StateOptionsMenu(int state) {

	}

	@Override
	public void initialize(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		AnimationStore.MENU_OPTIONS_CLUTTER.reload();
		AnimationStore.MENU_OPTIONS_CLUTTER.setDir(AnimationStore.DIR_REVERSE);
		AnimationStore.MENU_OPTIONS_CLUTTER.getAnimation().setLooping(false);
		addButton(ButtonStore.RESOLUTION);
		addButton(ButtonStore.VOLUME_MUSIC);
		addButton(ButtonStore.VOLUME_SOUND);
		addButton(ButtonStore.KEY_CONFIG);
		addButton(ButtonStore.BACK);
	}

	@Override
	public AnimationStore setMenuClutter() {
		return AnimationStore.MENU_OPTIONS_CLUTTER;
	}

	@Override
	public int getID() {
		/*
		 * if (ButtonStore.BACK.getButton().hasBeenClicked() ==
		 * Button.PRESSED_TRUE) { return State.STATE_PLAY_MAIN.getID(); } else
		 * if (ButtonStore.KEY_CONFIG.getButton().hasBeenClicked() ==
		 * Button.PRESSED_TRUE) { return State.STATE_MENU_KEYCONFIG.getID(); }
		 * else{ int res =
		 * DropdownList.RESOLUTION.getBgButton(ButtonStore.RESOLUTION
		 * .getButton().hasBeenClicked()).hasBeenClicked();
		 * 
		 * 
		 * }
		 */
		return State.STATE_MENU_OPTIONS.getID();
	}

	public int checkButtonStates(Input input) {
		int checkResChange = ButtonStore.RESOLUTION.getButton()
				.hasBeenClicked();
		if(checkResChange != -1){
			int[] r = ((ListButton) ButtonStore.RESOLUTION.getButton()).getDList().getResValues(checkResChange);
			
			try {
				Game.getGameContainer().setDisplayMode(r[0], r[1], true);
				reloadUsedResources();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return State.NO_CHANGE;
	}

	@Override
	public void queueImagesViaImageLoader() {
		// TODO Auto-generated method stub
		
	}
}
