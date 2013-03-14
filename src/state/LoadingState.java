package state;

import file.SaveLoader;
import image.ImageStore;
import image.Loadable;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingState extends BasicGeneralState{

	public LoadingState(int i) {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		ImageStore.COMPANY_LOGO.draw(0, 0);
		SaveLoader.get().draw(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if(SaveLoader.get().isDone()){
			State.enterState(arg1, State.STATE_PLAY_MAIN.getID());
		}
		if(!SaveLoader.get().isRunning()){
			//SaveLoader.get().loadSaveFile();
		}
		
	}

	@Override
	public void queueImagesViaImageLoader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
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

}
