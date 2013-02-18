package state;

import image.AnimationStore;
import image.ImageStore;

import java.util.ArrayList;
import java.util.HashMap;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import button.ButtonStore;




public abstract class BasicMenuState extends BasicGeneralState{
	int x = 0;
	int y = 0;
	protected AnimationStore menuClutter;
	private ArrayList<ButtonStore> buttons = new ArrayList<ButtonStore>();
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.getImage().draw();
		menuClutter.getAnimation().draw();
		
		if (!isChangingState(sbg)) {
			for(int i = 0; i < buttons.size(); i++){
				buttons.get(i).getButton().draw(g);
			}
		}

		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();

		g.drawString(x + ", " + y, 300, 50);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		initialize(gc, sbg);
		menuClutter = setMenuClutter();
		
	}
	
	public abstract void initialize(GameContainer gc, StateBasedGame sbg)
			throws SlickException;
	public abstract AnimationStore setMenuClutter();
	
	/**
	 * A check based on the menu clutters fading animation. Note that this
	 * method will change state automatically on it's own.
	 * 
	 * @param sbg the game itself
	 * @return true if it is about fade out, false if it's not
	 * 
	 */
	public boolean isChangingState(StateBasedGame sbg) {
		if (!menuClutter.isRegularDir()) {
			if (menuClutter.getAnimation().getFrame() == menuClutter
					.getAnimation().getFrameCount() - 1) {
				return false;
			}
		} else {
			if (menuClutter.getAnimation().getFrame() == menuClutter
					.getAnimation().getFrameCount() - 1) {
				menuClutter
						.setDir(AnimationStore.DIR_REVERSE);
				enterState(sbg);
			}
		}
		//State.getState(State.getNextState()).getState().queueImagesViaImageLoader();
		return true;
	}
	
	public void addButton(ButtonStore button){
		buttons.add(button);
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
	}
	
	public int checkButtonsAndSetNextState(Input input){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).getButton().buttonStateCheck(input);
		}
		return checkButtonStates(input);
	}
	
	public abstract int checkButtonStates(Input input);
	
	private void unloadUsedResources(){
		if (State.getNextState() != 3 && State.getNextState() != 2) {
			ImageStore.BACKGROUND_MENU_MAIN_STATIC.unload();
			ImageStore.BACKGROUND_MENU_LIGHT_STATIC.unload();
			ImageStore.BACKGROUND_MENU_SHADOW_STATIC.unload();
			AnimationStore.MENU_FIRE.unload();
			AnimationStore.MENU_LIGHT.unload();
		}
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).getButton().unload();
		}
		menuClutter.unload();
	}
	
	public void reloadUsedResources(){
			ImageStore.BACKGROUND_MENU_MAIN_STATIC.reload();
			ImageStore.BACKGROUND_MENU_LIGHT_STATIC.reload();
			ImageStore.BACKGROUND_MENU_SHADOW_STATIC.reload();
			AnimationStore.MENU_FIRE.reload();
			AnimationStore.MENU_LIGHT.reload();
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).getButton().reload();
		}
		menuClutter.reload();
		menuClutter.setDir(AnimationStore.DIR_REVERSE);
		menuClutter.getAnimation().setLooping(false);
	}
}
