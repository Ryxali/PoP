package state;

import image.AnimationStore;
import image.ImageStore;
import image.Loadable;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.Game;

import button.ButtonStore;

public abstract class BasicMenuState extends BasicGeneralState {
	int x = 0;
	int y = 0;
	private boolean isChangingState = false;
	protected AnimationStore menuClutter;
	private ArrayList<Loadable> toLoad = new ArrayList<Loadable>();
	private ArrayList<ButtonStore> buttons = new ArrayList<ButtonStore>();

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.getImage().draw();
		menuClutter.getAnimation().draw();

		if (!isChangingState(sbg)) {
			for (int i = 0; i < buttons.size(); i++) {
				buttons.get(i).getButton().draw(g);
			}
		}
		//System.out.println("WEQW");
		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();

		g.drawString(x + ", " + y, 300, 50);
		preloadNextItem();
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		initialize(gc, sbg);
		menuClutter = setMenuClutter();
		setObjectsToLoad(toLoad);
		
	}
	
	
	
	public void preloadNextItem(){
		if(toLoad.size() > 0){
			toLoad.get(0).reload();
			System.out.println(toLoad.get(0).toString());
			toLoad.remove(0);
		}
	}
	public abstract void setObjectsToLoad(ArrayList<Loadable> objs);

	public abstract void initialize(GameContainer gc, StateBasedGame sbg)
			throws SlickException;

	public abstract AnimationStore setMenuClutter();

	/**
	 * A check based on the menu clutters fading animation. Note that this
	 * method will change state automatically on it's own.
	 * 
	 * @param sbg
	 *            the game itself
	 * @return true if it is about fade out, false if it's not
	 * 
	 */
	public boolean isChangingState(StateBasedGame sbg) {
		//System.out.println("epdert");
		if(!isChangingState){
			if(menuClutter.getAnimation().getFrame() != 0){
				int i = menuClutter.getAnimation().getFrame();
				menuClutter.getAnimation().update((long) -Game.getDelta());
				if(menuClutter.getAnimation().getFrame() > i){
					menuClutter.getAnimation().setCurrentFrame(0);
				}
			}
			return false;
		}
		
		if(menuClutter.getAnimation().getFrame() == menuClutter.getAnimation().getFrameCount()-1){
			enterState(sbg);
		}
		//System.out.println("updert");
		int i = menuClutter.getAnimation().getFrame();
		menuClutter.getAnimation().update((long) Game.getDelta());
		if(menuClutter.getAnimation().getFrame() < i){
			menuClutter.getAnimation().setCurrentFrame(menuClutter.getAnimation().getFrameCount()-1);
		}
		
		/*if (!menuClutter.isRegularDir()) {
			if (menuClutter.getAnimation().getFrame() == menuClutter
					.getAnimation().getFrameCount() - 1) {
				return false;
			}
		} else {
			if (menuClutter.getAnimation().getFrame() == menuClutter
					.getAnimation().getFrameCount() - 1) {
				menuClutter.setDir(AnimationStore.DIR_REVERSE);
				enterState(sbg);
			}
		}*/
		// State.getState(State.getNextState()).getState().queueImagesViaImageLoader();
		return true;
	}
	public void setStateChange(boolean isChanging){
		isChangingState = isChanging;
	}
	public void addButton(ButtonStore button) {
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
		isChangingState = false;
		menuClutter.getAnimation().setCurrentFrame(menuClutter.getAnimation().getFrameCount()-1);
		if (State.getNextState() == State.EXIT_GAME) {
			System.exit(0);
		} else {
			State.enterState(sbg, State.getNextState());
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Game.updateDelta(gc.getTime());
		Input input = gc.getInput();
		x = input.getMouseX();
		y = input.getMouseY();
		int nState = checkButtonsAndSetNextState(input);
		if (nState != 0 && nState != State.getNextState()) {
			isChangingState = true;
			State.setNextState(nState);
			//AnimationStore.MENU_MAIN_CLUTTER.setDir(true);
		}
	}

	public int checkButtonsAndSetNextState(Input input) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).getButton().buttonStateCheck(input);
		}
		return checkButtonStates(input);
	}

	public abstract int checkButtonStates(Input input);

	private void unloadUsedResources() {
		if (State.getNextState() != 3 && State.getNextState() != 2) {
			ImageStore.BACKGROUND_MENU_MAIN_STATIC.unload();
			ImageStore.BACKGROUND_MENU_LIGHT_STATIC.unload();
			ImageStore.BACKGROUND_MENU_SHADOW_STATIC.unload();
			AnimationStore.MENU_FIRE.unload();
			AnimationStore.MENU_LIGHT.unload();
		}
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).getButton().unload();
		}
		menuClutter.unload();
	}
	

	public void reloadUsedResources() {
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.reload();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.reload();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.reload();
		AnimationStore.MENU_FIRE.reload();
		AnimationStore.MENU_LIGHT.reload();
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).getButton().reload();
		}
		menuClutter.reload();
		menuClutter.setDir(AnimationStore.DIR_REVERSE);
		menuClutter.getAnimation().setLooping(false);
	}
}
