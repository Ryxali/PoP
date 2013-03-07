package state;


import image.AnimationStore;
import image.ImageStore;
import image.Loadable;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.Game;


public abstract class BasicGeneralState extends BasicGameState{
	/**
	 * 
	 */
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
	
	public abstract void queueImagesViaImageLoader();
	
	public abstract ArrayList<Loadable> getUsedResources();
	
	public abstract void setStateChange(boolean isChanging);
	
}
