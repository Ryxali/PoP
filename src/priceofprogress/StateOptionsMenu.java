package priceofprogress;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateOptionsMenu extends BasicGameState{
public StateOptionsMenu(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		ImageStore.BACKGROUND_MENU_MAIN_STATIC.getImage().draw();
		AnimationStore.MENU_CLUTTER.getAnimation().draw();

		AnimationStore.MENU_FIRE.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_LIGHT_STATIC.getImage().draw();
		AnimationStore.MENU_LIGHT.getAnimation().draw();
		ImageStore.BACKGROUND_MENU_SHADOW_STATIC.getImage().draw();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return State.STATE_MENU_OPTIONS.getID();
	}
}
