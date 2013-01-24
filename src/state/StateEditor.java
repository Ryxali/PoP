package state;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import image.ImageStore;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.EditorStartup;

public class StateEditor extends BasicGeneralState {
	
	private Object[] loadedMap = null;
	private boolean working = false;
	/** Price of Progress Map */
	String fileType = "ppm";
	
	public StateEditor(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//System.out.println("It Lives!!!!!!!!!!!!!");
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		System.out.println("paint");
		ImageStore.COMPANY_LOGO.draw(0, 0);
		
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		System.out.println("updateur");
		if(working == false){
			workList();
		}
	}
	
	private void workList(){
		working = true;
		// pause
		EditorStartup.getGameContainer().pause();
		// if we don not have a map choose to create a new one, load one or exit the editor
		if(loadedMap == null){
			String[] choices = {"Create new map", "Load map", "Exit"};
			int theChoice = JOptionPane.showOptionDialog(null, "No map loaded", "Editor",
						JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE, null, choices, null);
			if(theChoice == 0){
				createNewMap();
			}
			else if(theChoice == 1){
				loadMap();
			}
			else if(theChoice == 2){
				System.out.println("Exit");
				System.exit(0);
			}
		}else{
			
		}
		// resume
		EditorStartup.getGameContainer().resume();
	}
	
	private void createNewMap(){
		
	}
	
	private void loadMap(){
		
	}
	
	private void saveMap() throws FileNotFoundException{
		// Create a file chooser
        JFileChooser fc = new JFileChooser();
        // Limit the file types that can be chosen
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal = fc.showOpenDialog(null);
        // Check if file is of right type
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            
            if(file.getName().toLowerCase().endsWith("." + fileType)){
            	Scanner indata = new Scanner(new File(file.getAbsolutePath()));
                while (indata.hasNext()) {
                    //textList.add(indata.nextLine());
                }
            	
            }else{
            	JOptionPane.showMessageDialog(null, file.getName() + " is not a "
            			+ fileType + " file. Try again.", "File Error", JOptionPane.WARNING_MESSAGE, null);
            	saveMap();
            }
            
        } else {
            // user changed his or her mind and does not want to load a file anymore
        }
	}
	
	@Override
	public int getID() {
		return State.STATE_EDITOR.getID();
	}
}
