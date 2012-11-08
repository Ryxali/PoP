package priceofprogress;

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
		menuAnimation = new AnimatedImage("res/img/MenuAnimation/", "Menu", ".png", 1, 20);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//g.drawImage(ImageStore.COMPANY_LOGO.getImage(), 0, 0);
		AnimationStore.MENU_ANIMATION.getAnimation().draw(0, 0);
		//menuAnimation.draw();
		//g.drawImage(ImageStore.BACKGROUND_MENU_STATIC.getImage(), 0, 0);
		
		// g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);

		// g.drawImage(ImageStore.TEST.getImage(), 0, 0);

		// g.setDrawMode(Graphics.MODE_NORMAL);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_0)){
			
		}
		// TODO Auto-generated method stub

	}
	
	public void setResolution(int newResWidth, int newResHeight,
			GameContainer gc, StateBasedGame sbg) throws SlickException{
		Game.appgc.setDisplayMode(newResWidth, newResHeight, true);
		init(gc, sbg);
	}
	
	

	@Override
	public int getID() {
		return State.STATE_MENU_MAIN.getID();
	}
}
