package priceofprogress;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import state.State;
import file.OptionsFile;
/**
 * A separate startup of the game that simply enters the editor state.
 * Thanks to that we can use the same systems for map-loading, map-object handling and visuals.
 * Always runs in full screen mode.
 * 
 * @author Lukas
 *
 */
public class EditorStartup extends Game{
	/** The name of the editor. */
	public static final String EDITOR_NAME = "Price of Progress Editor 1.00";
	
	
	/**
	 * Constructs the editor.
	 * 
	 * @param editorName the name of this editor.
	 */
	public EditorStartup(String editorName){
		super(editorName);
	}
	/**
	 * Sets up the game container.
	 * 
	 * @throws SlickException if set up is unsuccessful.
	 */
	protected static void setupAppgc() throws SlickException{
		setGameContainer(new AppGameContainer(new EditorStartup(EDITOR_NAME)));
		getGameContainer().setTargetFrameRate(60);
		if (OptionsFile.get().contains("SizeX:", "SizeY:", "FullScreen:")) {
			getGameContainer().setDisplayMode(
					OptionsFile.get().fetchIntegerFromOptions("SizeX:"),
					OptionsFile.get().fetchIntegerFromOptions("SizeY:"),
					//OptionsFile.get().fetchBooleanFromOptions("FullScreen:")
					true);
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
	/**
	 * Enters the editor state.
	 */
	@Override
	public void enterDefaultState(){
		this.enterState(State.STATE_EDITOR.getID());
	}
	
}
