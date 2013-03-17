package state;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import file.PPWDataLoader;
import image.ImageLoader;
import image.ImageStore;
import image.Loadable;

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
	/** The default number of blocks created in an horizontal line when a new map is created */
	int defaultBlockLength = 30;
	/** The default number of blocks created in an vertical line when a new map is created */
	int defaultBlockHeight = 6;
	
	public boolean notSaved = true;
	
	public StateEditor(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//backdrop
		ImageStore.COMPANY_LOGO.draw(0, 0);
		//draws all blocks 
	//	for (int y = 0; y < Terrain.get().size(); y++) {
	//		for (int x = 0; x < Terrain.get().rowSize(y); x++) {
				//Terrain.get().getBlock(x, y).getImage().draw(Terrain.get().getBlock(x, y).getXPos(), Terrain.get().getBlock(x, y).getYPos());
				//System.out.println("x: "+Terrain.get().getBlock(x, y).getXPos()+" y: "+ Terrain.get().getBlock(x, y).getYPos());
	//		}
		//}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		// save what we has
		if(notSaved){
			notSaved = false;
			//createNewMap();
			loadMap();
			//saveMap();
		}
	}
	
	private void createNewMap(){
		// Make a default map
		for (int i = 0; i < defaultBlockHeight; i++) {
			ArrayList<Block> currentBlockRow = new ArrayList<Block>();
			for (int j = 0; j < defaultBlockLength; j++) {
				//currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, 1200-(i+1)*64));
				//System.out.println("x: "+j*64+" y: "+ (1200-(i+1)*64));
			}
		//	Terrain.get().addBlockRow(currentBlockRow);
		}
	}
	
	private void loadMap(){
		PPWDataLoader.get().loadTerrain();
		//this.notify();
	}
	
	private void saveMap(){
		if(PPWDataLoader.get().getMapRef() == "maps/defaultMapRef.PPW"){
			PPWDataLoader.get().saveTerrain(true);
		}else{
			PPWDataLoader.get().saveTerrain(false);
		}
		
	}
	
	@Override
	public int getID() {
		return State.STATE_EDITOR.getID();
	}
	@Override
	public void queueImagesViaImageLoader() {
		ImageLoader.get().queue(null);
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
