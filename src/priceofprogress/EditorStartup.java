package priceofprogress;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import state.State;
import file.OptionsFile;
/**
 * 
 * @author Lukas
 *
 */
public class EditorStartup extends Game{

	public static final String EDITOR_NAME = "Price of Progress Editor 1.00";
	
	
	
	public EditorStartup(String editorName){
		super(editorName);
	}
	
	protected static void setupAppgc() throws SlickException{
		setGameContainer(new AppGameContainer(new EditorStartup(EDITOR_NAME)));
		getGameContainer().setTargetFrameRate(60);
		if (OptionsFile.get().contains("SizeX:", "SizeY:", "FullScreen:")) {
			getGameContainer().setDisplayMode(
					OptionsFile.get().fetchIntegerFromOptions("SizeX:"),
					OptionsFile.get().fetchIntegerFromOptions("SizeY:"),
					OptionsFile.get().fetchBooleanFromOptions("FullScreen:"));
		} else {
			getGameContainer().setDisplayMode(getGameContainer().getScreenWidth(),
					getGameContainer().getScreenHeight(), true);
		}
	}

	public static void main(String[] args) {
		try {
			setupAppgc();
			getGameContainer().start();
		} catch (SlickException e) {
			try {
				getGameContainer().setDisplayMode(getGameContainer().getScreenWidth(),
						getGameContainer().getScreenHeight(), true);
				getGameContainer().start();
			} catch (SlickException e1) {
				e1.printStackTrace();
			}

		}catch(ExceptionInInitializerError e2){
			System.out.println("dafu");
			e2.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public void enterDefaultState(){
		this.enterState(State.STATE_EDITOR.getID());
	}
	
}
