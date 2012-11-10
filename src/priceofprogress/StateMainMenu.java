package priceofprogress;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateMainMenu extends BasicGameState {
	public AnimatedImage menuAnimation;
	public StateMainMenu(int state) {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		ArrayList <AnimationStore> animgs = new ArrayList<AnimationStore>();
		Collections.addAll(animgs, AnimationStore.values());
		for(int i = 0; i < animgs.size(); i++){
			animgs.get(i).unload();
		}
		ArrayList <ImageStore> imgs = new ArrayList<ImageStore>();
		Collections.addAll(imgs, ImageStore.values());
		for(int i = 0; i < imgs.size(); i++){
			imgs.get(i).unload();
		}
		loadResources();
		//menuAnimation = new AnimatedImage("res/img/MenuAnimation/", "Menu", ".png", 1, 20, true);
		
	}
	private void loadResources(){
		ImageStore.BACKGROUND_MENU_STATIC.reload();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.reload();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.reload();
		AnimationStore.MENU_FIRE.reload();
		AnimationStore.MENU_LIGHT.reload();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		ImageStore.BACKGROUND_MENU_STATIC.getImage().draw();
		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_0)){
			setResolution(800, 600, gc, sbg);
		}else if(input.isKeyDown(Input.KEY_1)){
			AnimationStore.MENU_ANIMATION.reload();
		}
		// TODO Auto-generated method stub

	}
	
	public void setResolution(int newResWidth, int newResHeight,
			GameContainer gc, StateBasedGame sbg) throws SlickException{
		Game.appgc.setDisplayMode(newResWidth, newResHeight, true);
		ArrayList<AnimationStore> as = new ArrayList<AnimationStore>();
		Collections.addAll(as, AnimationStore.values());
		for(int i = 0; i < as.size(); i++){
			as.get(i).reload();
		}
		ArrayList<ImageStore> is = new ArrayList<ImageStore>();
		Collections.addAll(is, ImageStore.values());
		for(int i = 0; i < is.size(); i++){
			is.get(i).reload();
		}
		init(gc, sbg);
	}
	
	

	@Override
	public int getID() {
		return State.STATE_MENU_MAIN.getID();
	}
}
