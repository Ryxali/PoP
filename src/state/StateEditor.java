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

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import priceofprogress.EditorStartup;
import priceofprogress.Game;
import terrain.Block;
import terrain.Blocks;
import terrain.StaticBlock;
import terrain.Terrain;
/**
 * 
 * @author Lukas
 *
 */
public class StateEditor extends BasicGeneralState {
	/** The default number of blocks created in an horizontal line when a new map is created */
	private int defaultBlockLength = 30;
	/** The default number of blocks created in an vertical line when a new map is created */
	private int defaultBlockHeight = 6;
	/** The horizontal position of the screen. */
	private int screenPosX = 0;
	/** The vertical position of the screen. */
	private int screenPosY = 0;
	/** The vertical position of the mouse. */
	private int mouseX;
	/** The horizontal position of the mouse. */
	private int mouseY;
	
	private Block blockHovered = null;
	private Block blockMarked = null;
	
	public boolean tested = false;
	
	public StateEditor(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//screenHeight = gc.getHeight();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//backdrop
		ImageStore.COMPANY_LOGO.draw(0, 0);
		//draws all blocks 
		for (int y = 0; y < Terrain.get().size(); y++) {
			for (int x = 0; x < Terrain.get().rowSize(y); x++) {
				Block block = Terrain.get().getBlock(x, y);
				if(block.getID() != 0){
					block.getImage().draw(screenPosX + block.getXPos(), screenPosY + block.getYPos());
					//System.out.println("x: "+(originPointX+block.getXPos())+" y: "+(originPointY+block.getYPos()));
				}
			}
		}
		// draw the kind of block that the mouse is hovering over.
		if(blockHovered != null){
			g.drawString(getBlockName(blockHovered.getID()), screenPosX+blockHovered.getXPos(), screenPosY+blockHovered.getYPos()-10);
		}
		// draw a border to the block that have been marked.
		if(blockMarked != null){
			ImageStore.MARKED_BLOCK_BORDER.draw(screenPosX+blockMarked.getXPos(), screenPosY+blockMarked.getYPos());
		}
		//g.drawString("X: "+mouseX+" Y: "+mouseY, 10, 40);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(!tested){
			tested = true;
			createNewMap();
			//loadMap();
			//saveMap();
		}
		
		//Input input = gc.getInput();
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		int hoverBlockPosX = mouseX - screenPosX;
		// mouse y values starts at the bottom and graphic y values start at the top.
		int hoverBlockPosY = (int) Game.getHeightScale()*1200 - (mouseY - screenPosY);
		
		while(hoverBlockPosX % 64 != 0){
			hoverBlockPosX += -1;
		}
		while(hoverBlockPosY % 64 != 0){
			hoverBlockPosY += -1;
		}
		System.out.println("x: "+hoverBlockPosX+" y: "+hoverBlockPosY);
		try{
			blockHovered = Terrain.get().getBlock(hoverBlockPosX, hoverBlockPosY);
			System.out.println("found ya");
		}catch(Exception e){
			blockHovered = null;
		}
		// right click
		if(Mouse.isButtonDown(0)){
			//markere blocksi
			
		// left click
		}else if(Mouse.isButtonDown(1)){
			moveScreen(Mouse.getDX(), Mouse.getDY());
		}
		
		//this.mouseClicked(button, x, y, 1);
	}
	
	private String getBlockName(int blockID){
		if(blockID == 0){
			return "Air Block";
		}else if(blockID == 1){
			return "Earh Block";
		}else if(blockID == 2){
			return "Grass Block";
		}else if(blockID == 3){
			return "Gravel Block";
		}else if(blockID == 4){
			return "Rock Block";
		}else{
			return "Unknown Block";
		}
	}
	
	private void moveScreen(int dx, int dy){
		screenPosX += dx;
		screenPosY += dy;
	}
	
	private void createNewMap(){
		// Make a default map
		for (int i = 0; i < defaultBlockHeight; i++) {
			ArrayList<Block> currentBlockRow = new ArrayList<Block>();
			for (int j = 0; j < defaultBlockLength; j++) {
				currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, (int) Game.getHeightScale()*1200-(i+1)*64));
				//System.out.println("x: "+j*64+" y: "+ ((int) Game.getHeightScale()*1200-(i+1)*64));
			}
			Terrain.get().addBlockRow(currentBlockRow);
		}
	}
	
	private void loadMap(){
		PPWDataLoader.get().loadTerrain();
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
