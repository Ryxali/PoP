package state;


import image.AnimationStore;

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
		/*if (ButtonStore.BACK.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_PLAY_MAIN.getID();
		} else if (ButtonStore.KEY_CONFIG.getButton().hasBeenClicked() == Button.PRESSED_TRUE) {
			return State.STATE_MENU_KEYCONFIG.getID();
		} else{
			int res = DropdownList.RESOLUTION.getBgButton(ButtonStore.RESOLUTION.getButton().hasBeenClicked()).hasBeenClicked();
			
			
		}*/
		return State.STATE_MENU_OPTIONS.getID();
	}

	public int checkNextState(Input input) {
		return State.NO_CHANGE;
	}
	
}
