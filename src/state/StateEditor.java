package state;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import file.PPWDataLoader;
import image.ImageLoader;
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
	private String mapRef = "maps/defaultMapRef.PPW";
	/** The default number of blocks created in an horizontal line when a new map is created */
	int defaultBlockLength = 30;
	
	public boolean notSaved = true;
	
	public StateEditor(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// start with a new map
		//createNewMap();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//backdrop
		ImageStore.COMPANY_LOGO.draw(0, 0);
		//draws all blocks 
		for (int i = 0; i < Terrain.get().size(); i++) {
			for (int j = 0; j < Terrain.get().rowSize(i); j++) {
				Terrain.get().getBlock(i, j).getRef().draw(Terrain.get().getBlock(i, j).getXPos(), Terrain.get().getBlock(i, j).getYPos());
			}
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("update");
		
		// save what we has
		if(notSaved){
			notSaved = false;
			//saveMap();
			loadMap();
		}
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
			Terrain.get().addBlockRow(currentBlockRow);
		}
	}
	
	private void loadMap(){
		PPWDataLoader.get().loadTerrain();
	}
	
	private void saveMap(){
		if(mapRef == "maps/defaultMapRef.PPW"){
			PPWDataLoader.saveTerrain(mapRef, true);
		}else{
			PPWDataLoader.saveTerrain(mapRef, false);
		}
		
	}
	
	@Override
	public int getID() {
		return State.STATE_EDITOR.getID();
	}
	@Override
	public void queueImagesViaImageLoader() {
		//TODO add needed resources here
		ImageLoader.get().queue(null);
	}
}
