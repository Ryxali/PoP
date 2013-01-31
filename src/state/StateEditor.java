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
	/** Keeps a list containing lists of blocks */
	private Object[] loadedMap = null;
	private boolean working = false;
	/** Price of Progress Map */
	String fileType = "ppm";
	/**  */
	int defaultBlockLength = 30;
	
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
		
		ImageStore.BLOCK_EARTH.getImage();
		// längd 30 höjd 3
		for (int height = 0; height < 3; height++) {
			for(int length = 0; length < defaultBlockLength; length++){
				ImageStore.BLOCK_EARTH.draw(length*64, 1200-64*height);
			}
		}
		
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
		
		// if we don not have a map choose to create a new one, load one or exit the editor
		/**if(loadedMap == null){
			String[] choices = {"New map", "Load map", "Exit"};
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
			String[] choices = {"Edit map", "New map", "Load map", "Exit"};
			int choice = JOptionPane.showOptionDialog(null, "What to do?", "Editor",
						JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE, null, choices, null);
			if(choice == 0){
				
			}
			else if(choice == 1){
				createNewMap();
			}
			else if(choice == 2){
				loadMap();
			}
			else if(choice == 3){
				// 0 = yes, 1 = 
				int choice2 = JOptionPane.showConfirmDialog(null, "Save map before exit?");
				if(choice2 == 0){
					saveMap();
					System.out.println("Exit");
					System.exit(0);
				}
				else if(choice2 == 1){
					
				}else if(choice2 == 2){
					// user changed his or her mind and does not want to exit anymore.
				}
			}
		}*/
		working = false;
	}
	
	private void createNewMap(){
		// autogen blocks with flat or random height
		String[] choices = {"Falt Terrain", "Randomly Shifting Terrain", "Cancel"};
		int choice = JOptionPane.showOptionDialog(null, "How do you want to create the map?", "New Map",
					JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE, null, choices, null);
		if(choice == 0){
			// flat blocks
			
		}
		else if(choice == 1){
			// random blockgen
		}
		else if(choice == 2){
			// user changed his or her mind and does not want to create a map anymore.
		}
	}
	
	private void loadMap(){
		// Create a file chooser
        JFileChooser fc = new JFileChooser();
        // Limit the file types that can be chosen.
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal = fc.showOpenDialog(null);
        // Check if file is of right type.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            
            if(file.getName().toLowerCase().endsWith("." + fileType)){
            	Scanner indata;
				try {
					indata = new Scanner(new File(file.getAbsolutePath()));
					while (indata.hasNext()) {
						
	                    //textList.add(indata.nextLine());
	                }
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
            }else{
            	JOptionPane.showMessageDialog(null, file.getName() + " is not a "
            			+ fileType + " file. Try again.", "File Error", JOptionPane.WARNING_MESSAGE, null);
            	saveMap();
            }
            
        } else {
            // user changed his or her mind and does not want to load a file anymore.
        }
	}
	
	private void saveMap(){
		
	}
	
	@Override
	public int getID() {
		return State.STATE_EDITOR.getID();
	}
}
