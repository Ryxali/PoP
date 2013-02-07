package state;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import image.ImageStore;

import javax.swing.JFileChooser;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.EditorStartup;
import terrain.Block;
import terrain.Blocks;
import terrain.StaticBlock;
import terrain.Terrain;

public class StateEditor extends BasicGeneralState {
	/** The URL of the current map. */
	private String mapRef = "defaultMapRef";
	/** Keeps a list containing lists of blocks */
	private ArrayList<ArrayList<Block>> blockMap = new ArrayList<ArrayList<Block>>();
	/** The file-type supported by this editor. Price of Progress Map. */
	private String fileType = "PPW";
	/** The default number of blocks created in an horizontal line when a new map is created */
	int defaultBlockLength = 30;
	
	private boolean working = false;
	
	public StateEditor(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// start with a new map
		createNewMap();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		System.out.println("render");
		//backdrop
		ImageStore.COMPANY_LOGO.draw(0, 0);
		//draws all blocks 
		for (int i = 0; i < blockMap.size(); i++) {
			for (int j = 0; j < blockMap.get(i).size(); j++) {
				blockMap.get(i).get(j).getRef().draw(blockMap.get(i).get(j).getXPos(), blockMap.get(i).get(j).getYPos());
			}
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("update");
		
		/**if(working == false){
			workList();
		}*/
	}
	
	private void workList(){
		working = true;
		
		// if we don not have a map choose to create a new one, load one or exit the editor
		
		working = false;
	}
	
	private void createNewMap(){
		// Create an empty map
		Terrain.get();
		// Make a default map
		for (int i = 0; i < 6; i++) {
			ArrayList<Block> currentBlockRow = new ArrayList<Block>();
			for (int j = 0; j < defaultBlockLength; j++) {
				
				currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, 1200-i*64));
			}
			blockMap.add(currentBlockRow);
		}
	}
	
	private void loadMap(){
		// Create a file chooser
        JFileChooser fc = new JFileChooser();
        // Limit the file types that can be chosen.
        fc.setAcceptAllFileFilterUsed(false);
        
        //WONGWONGWONGWONGWONGWONGWONGWONGWONGWONGWONG
        int returnVal = fc.showOpenDialog(null);
        
        // Check if file is of right type.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File choosenFile = fc.getSelectedFile();
            
            if(choosenFile.getName().toLowerCase().endsWith("." + fileType)){
				// load the file
            	file.PPWDataLoader.loadTerrain(Terrain.get(), choosenFile.getName());
            }else{
            	System.out.println(choosenFile.getName() + " is not a " + fileType + " file.");
            }
        }
	}
	
	private void saveMap(){
		if(mapRef == "defaultMapRef"){
			//välja namn
			
			mapRef = "res/" + mapRef;
		}
		file.PPWDataLoader.saveData(mapRef);
	}
	
	@Override
	public int getID() {
		return State.STATE_EDITOR.getID();
	}
}
