package state;

import file.PPWDataLoader;
import image.AnimationStore;
import image.ImageLoader;
import image.ImageStore;
import image.Loadable;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import machines.Machine;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import button.Button;
import button.StandardButton;

import priceofprogress.Game;
import terrain.Block;
import terrain.Blocks;
import terrain.StaticBlock;
import terrain.Terrain;
import terrain.VoidBlock;
/**
 * The editor belonging to the game Price of Progress.
 * Is built into the system and simply has a separate startup class which allows us
 * to use the same systems for map-loading, map-object handling and visuals.
 * The editor can generate default maps, load maps, edit maps and save maps.
 * 
 * Machines is not yet placeable but will be in the future to come (several preparations have been done).
 * 
 * @author Lukas
 *
 */
public class StateEditor extends BasicMenuState {
	/** The default number of blocks created in an horizontal line when a new map is created */
	private int defaultLength = 30;
	/** The default number of blocks created in an vertical line when a new map is created */
	private int defaultLandHeight = 6;
	/** The horizontal position of the screen. */
	private int screenPosX = 0;
	/** The vertical position of the screen. */
	private int screenPosY = 0;
	/** The vertical position of the mouse. */
	private int mouseX;
	/** The horizontal position of the mouse. */
	private int mouseY;
	/** The height of the screen. */
	private int screenHeight;
	/**  */
	private int activeBlockTypeID = 0;
	/** The block that the mouse is currently hovering over. */
	private Block blockHovered = null;
	/** The block that has been marked. */
	private Block blockMarked = null;
	/** The machine that the mouse is currently hovering over. */
	private Machine machineHovered = null;
	/** The machine that has been marked. */
	private Machine machineMarked = null;
	/** A list holding all the buttons in the editor. */
	private ArrayList<Button> buttons = new ArrayList<Button>();
	/** Used when testing something that is only supposed to be done once
	 * and is called in the first run of the update method. */
	public boolean tested = false;
	
	/**
	 * Only here to please the class-hierarchy and is never used.
	 */
	public StateEditor(int state){
	}
	/**
	 * Is called when the editor starts.
	 * Starts an extra thread, turns off fps showing, prepares the screen height
	 * and sets up our buttons at the top of the screen.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		Game.getGameContainer().setShowFPS(false);
		screenHeight = 1200;
		// function buttons.
		buttons.add(new StandardButton(0, 0, ImageStore.BUTTON_EDITOR_SAVE,
				ImageStore.BUTTON_EDITOR_SAVE, ImageStore.BUTTON_EDITOR_SAVE));
		buttons.add(new StandardButton(128, 0, ImageStore.BUTTON_EDITOR_LOAD,
				ImageStore.BUTTON_EDITOR_LOAD, ImageStore.BUTTON_EDITOR_LOAD));
		buttons.add(new StandardButton(256, 0, ImageStore.BUTTON_EDITOR_EDIT,
				ImageStore.BUTTON_EDITOR_EDIT, ImageStore.BUTTON_EDITOR_EDIT));
		buttons.add(new StandardButton(384, 0, ImageStore.BUTTON_EDITOR_NEW,
				ImageStore.BUTTON_EDITOR_NEW, ImageStore.BUTTON_EDITOR_NEW));
		// active block buttons.
		buttons.add(new StandardButton(512, 0, ImageStore.BLOCK_BLANC,
				ImageStore.BLOCK_BLANC, ImageStore.BLOCK_BLANC));
		buttons.add(new StandardButton(576, 0, ImageStore.BLOCK_EARTH,
				ImageStore.BLOCK_EARTH, ImageStore.BLOCK_EARTH));
		buttons.add(new StandardButton(640, 0, ImageStore.BLOCK_GRASS,
				ImageStore.BLOCK_GRASS, ImageStore.BLOCK_GRASS));
		buttons.add(new StandardButton(704, 0, ImageStore.BLOCK_GRAVEL,
				ImageStore.BLOCK_GRAVEL, ImageStore.BLOCK_GRAVEL));
		buttons.add(new StandardButton(768, 0, ImageStore.BLOCK_ROCK,
				ImageStore.BLOCK_ROCK, ImageStore.BLOCK_ROCK));
	}
	/**
	 * Draws all objects in the active map.
	 * Tells the kind of the block that the mouse is hovering over.
	 * Marks the block that the user has clicked.
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//backdrop
		ImageStore.COMPANY_LOGO.draw(0, 0);
		//draws all blocks 
		for (int y = 0; y < Terrain.get().size(); y++) {
			for (int x = 0; x < Terrain.get().rowSize(y); x++) {
				Block block = Terrain.get().getBlock(x, y);
				if(block.getID() != 0){
					block.getImage().draw(screenPosX + block.getXPos(), screenPosY + block.getYPos());
				}
			}
		}
		//draw all machines
		for (int i = 0; i < Terrain.get().mashinesSize(); i++) {
			Terrain.get().getMachine(i).draw(g);
		}
		// draw the kind of block that the mouse is hovering over.
		if(blockHovered != null){
			//g.drawString(getBlockName(blockHovered.getID()), screenPosX+blockHovered.getXPos(), screenPosY+blockHovered.getYPos());
			g.drawString(getBlockName(blockHovered.getID()), 50, screenHeight-200);
		}
		// draw a mark to the block that have been marked.
		if(blockMarked != null){
			ImageStore.BLOCK_MARKER.draw(screenPosX+blockMarked.getXPos(), screenPosY+blockMarked.getYPos());
		}
		// draw the buttons
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
		// draw a mark to the block type button that have been marked.
		ImageStore.BLOCK_MARKER.draw(512+activeBlockTypeID*64, 0);
	}
	/**
	 * Handles input and acts accordingly.
	 * Allows marking of objects in the map and the lets the user edit them by calling the desired method.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(!tested){
			tested = true;
			//createDefaultMap();
			//createNewMap();
			//loadMap();
			//saveMap();
		}
		Input input = gc.getInput();
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).buttonStateCheck(input);
		}
		// Save
		if(buttons.get(0).isClicked()){
			saveMap();
		// Load
		}else if(buttons.get(1).isClicked()){
			loadMap();
		// Edit
		}else if(buttons.get(2).isClicked()){
			if(machineMarked != null){
				// editmachine
			}else if(blockMarked != null){
				changeMarkedBlock(activeBlockTypeID);
			}
		// New
		}else if(buttons.get(3).isClicked()){
			createDefaultMap();
		// Active Block Type
		}else if(buttons.get(4).isClicked()){
			activeBlockTypeID = 0;
		}else if(buttons.get(5).isClicked()){
			activeBlockTypeID = 1;
		}else if(buttons.get(6).isClicked()){
			activeBlockTypeID = 2;
		}else if(buttons.get(7).isClicked()){
			activeBlockTypeID = 3;
		}else if(buttons.get(8).isClicked()){
			activeBlockTypeID = 4;
		}else{
			if(Mouse.isInsideWindow()){
				mouseX = Mouse.getX();
				mouseY = Mouse.getY();
			}
			// The methods that fetch the scalings  will return a useful value because the editor will always run in full screen mode.
			int hoverBlockX = (int) ((mouseX/Game.getWidthScale()) - screenPosX);
			int hoverBlockY = (int) ((mouseY/Game.getHeightScale()) + screenPosY);
			hoverBlockX = makeDivideableBy64(hoverBlockX);
			hoverBlockY = makeDivideableBy64(hoverBlockY);
			// check if a block is hovered
			try{
				blockHovered = Terrain.get().getBlock(hoverBlockX/64, hoverBlockY/64);
			}catch(Exception e){
				blockHovered = null;
			}
			// right click
			if(Mouse.isButtonDown(0)){
				blockMarked = blockHovered;
				//System.out.println("mark x: "+(hoverBlockX/64)+" mark y: "+(hoverBlockY/64));
				//System.out.println("markPOSx: "+blockMarked.getXPos()+" markPOSy: "+blockMarked.getYPos());
				
			// left click
			}else if(Mouse.isButtonDown(1)){
				moveScreen(Mouse.getDX(), Mouse.getDY());
			}
		}
	}
	/**
	 * Tells the name of a block based on it's ID.
	 * For example: 1 is the ID of EARTH_BLOCK and will return the name "Earth Block".
	 * Block ID values are defined in the Blocks enum.
	 * 
	 * @param blockID the ID of the block.
	 * @return the name of blocks with the specified ID.
	 */
	private String getBlockName(int blockID){
		if(blockID == 0){
			return "Air Block";
		}else if(blockID == 1){
			return "Earth Block";
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
	/**
	 * 
	 */
	private int makeDivideableBy64(int number){
		while(number % 64 != 0){
			number += -1;
		}
		return number;
	}
	/**
	 * Changes the marked block, if any, to another type based on the specified ID.
	 * Block ID is defined in the blocks enum.
	 * 
	 * @param iD the iD of the type of block that the marked block shall become.
	 */
	private void changeMarkedBlock(int iD){
		if(blockMarked != null){
			//System.out.println("Try to change "+activeBlockTypeID);
			int height =  makeDivideableBy64(screenHeight);
			if(iD == 0){
				Terrain.get().setBlock(blockMarked.getXPos()/64, (height-blockMarked.getYPos())/64,
						new VoidBlock(0, blockMarked.getXPos(), blockMarked.getYPos()));
			}else if(iD == 1){
				Terrain.get().setBlock(blockMarked.getXPos()/64, (height-blockMarked.getYPos())/64,
						new StaticBlock(0, blockMarked.getXPos(), blockMarked.getYPos(), ImageStore.BLOCK_EARTH));
			}else if(iD == 2){
				Terrain.get().setBlock(blockMarked.getXPos()/64, (height-blockMarked.getYPos())/64,
						new StaticBlock(0, blockMarked.getXPos(), blockMarked.getYPos(), ImageStore.BLOCK_GRASS));
			}else if(iD == 3){
				Terrain.get().setBlock(blockMarked.getXPos()/64, (height-blockMarked.getYPos())/64,
						new StaticBlock(0, blockMarked.getXPos(), blockMarked.getYPos(), ImageStore.BLOCK_GRAVEL));
			}else if(iD == 4){
				Terrain.get().setBlock(blockMarked.getXPos()/64, (height-blockMarked.getYPos())/64,
						new StaticBlock(0, blockMarked.getXPos(), blockMarked.getYPos(), ImageStore.BLOCK_ROCK));
			}
		}
	}
	/**
	 * Determines where things will be draw in the editor by changing the values
	 * that determines where we are looking instead of changing the individual positions of the objects.
	 * 
	 * @param dx the horizontal alteration of shown objects.
	 * Is meant to be collected from the horizontal movement of the mouse.
	 * @param dy the vertical alteration of shown objects.
	 * Is meant to be collected from the vertical movement of the mouse.
	 */
	private void moveScreen(int dx, int dy){
		// mouse y values starts at the bottom and graphic y values start at the top.
		screenPosX -= dx;
		screenPosY += dy;
	}
	/**
	 * Generates a new map based on the default length (30 blocks) and the default land height (6 blocks).
	 * The new map is stored in Terrain.
	 */
	private void createDefaultMap(){
		//if map already exist we should handle that first (will add this later).
		
		// Make a default map
		for (int i = 0; i < 20; i++) {
			ArrayList<Block> currentBlockRow = new ArrayList<Block>();
			for (int j = 0; j < defaultLength; j++) {
				if(i < defaultLandHeight){
					currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, screenHeight -(i+1)*64));
				}else{
					currentBlockRow.add(Blocks.AIR_BLOCK.clone(j*64, screenHeight -(i+1)*64));
				}
			}
			Terrain.get().addBlockRow(currentBlockRow);
		}
	}
	/**
	 * Generates a new map based on input from the user.
	 * The new map is stored in Terrain.
	 * 
	 * Not finished!
	 * 
	 */
	private void createNewMap(int x, int y, int landHight){
		
	}
	/**
	 * 
	 */
	private void loadMap(){
		//if map already exist we should handle that first (will add this later).
		
		PPWDataLoader.get().loadTerrain();
	}
	/**
	 * 
	 */
	private void saveMap(){
		if(PPWDataLoader.get().getMapRef() == "maps/defaultMapRef.PPW"){
			PPWDataLoader.get().saveTerrain(true);
		}else{
			PPWDataLoader.get().saveTerrain(false);
		}
		
	}
	/**
	 * Fetch the ID of this editor.
	 * 
	 * @return the ID of this editor.
	 */
	@Override
	public int getID() {
		return State.STATE_EDITOR.getID();
	}
	// The following overrides are basically there to please the class-hierarchy and are never used.
	@Override
	public void queueImagesViaImageLoader() {
		ImageLoader.get().queue(null);
	}
	@Override
	public ArrayList<Loadable> getUsedResources() {
		return null;
	}
	@Override
	public void setStateChange(boolean isChanging) {
	}
	@Override
	public void initialize(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	}
	@Override
	public AnimationStore setMenuClutter() {
		return null;
	}
	@Override
	public int checkButtonStates(Input input) {
		return 0;
	}
}