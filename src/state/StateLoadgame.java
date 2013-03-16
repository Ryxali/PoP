package state;
import file.SaveLoader;
import image.ImageStore;
import image.Loadable;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.Game;

import button.Button;
import button.TextButton;


/**
 * This state is responsible for showing the loadable games.
 * @author Niklas L
 *
 */
public class StateLoadgame extends BasicGeneralState{
	public TextButton[] saveFileButtons;
	public StateLoadgame(int id){
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		File f = new File("saves");
		String[] saveFiles = f.list();
		saveFileButtons =  new TextButton[saveFiles.length];
		for (int i = 0; i < saveFiles.length; i++) {
			System.out.println(saveFiles[i]);
			saveFileButtons[i] = new TextButton(saveFiles[i],
					Game.getGameContainer().getWidth() / 4,
					Game.getGameContainer().getHeight() / 4
					+ ImageStore.BUTTON_LOADGAME_IDLE.getImage().getHeight()
					* i, ImageStore.BUTTON_LOADGAME_IDLE,
					ImageStore.BUTTON_LOADGAME_HOVER, 
					ImageStore.BUTTON_LOADGAME_PRESSED);
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		ImageStore.COMPANY_LOGO.draw(0, 0);
		for (int i = 0; i < saveFileButtons.length; i++) {
			saveFileButtons[i].draw(arg2);
		}
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		for (int i = 0; i < saveFileButtons.length; i++) {
			saveFileButtons[i].buttonStateCheck(arg0.getInput());
			if(saveFileButtons[i].hasBeenClicked() == Button.PRESSED_TRUE){
				SaveLoader.get().setFileToLoad(saveFileButtons[i].getTitle());
				SaveLoader.get().loadSaveFile();
				State.enterState(arg1, State.STATE_LOADING.getID());
			}
		}
		
	}

	@Override
	public void queueImagesViaImageLoader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public ArrayList<Loadable> getUsedResources() {
		ArrayList<Loadable> usd = new ArrayList<Loadable>();
		return usd;
	}

	@Override
	public void setStateChange(boolean isChanging) {
		// TODO Auto-generated method stub
		
	}

}
