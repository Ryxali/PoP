package state;

import gui.LoadingInterface;
import image.Loadable;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NewGameState extends BasicGeneralState{

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		LoadingInterface.get().draw();
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if(LoadingInterface.get().isDone()){
			LoadingInterface.get().stageReset();
			State.enterState(arg1, State.STATE_PLAY_MAIN.getID());
		}
		
	}

	@Override
	public void queueImagesViaImageLoader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Loadable> getUsedResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStateChange(boolean isChanging) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
