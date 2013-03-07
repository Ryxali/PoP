package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import machines.Component;
import machines.Machine;
import priceofprogress.Game;
import terrain.Block;
import terrain.Blocks;
import terrain.Terrain;
/**
 * Handles saving and loading of ppw maps.
 * Stores loaded maps as Terrain.
 * 
 * Block ID is currently bugged and all blocks are therefore saved as earth blocks.
 * 
 * @author Niklas Lindblad
 * @author Lukas Wahlquist
 */
public class PPWDataLoader implements Runnable{
	/** An object containing this PPWDataLoader. */
	private static PPWDataLoader datLoad;
	/** A list of all data loaded from a file. */
	private static ArrayList<String[]> fileData;
	private String mapRef = "maps/defaultMapRef.PPW";
	/** The thread used by the data loader to work apart from the main systems. */
	private Thread thread;
	/** The type of task that shall be performed. True represents save and false load. */
	public boolean task;
	/** True if the next map that shall be saved is a new one. */
	private boolean newSaveMap = false;
	/** The file that shall be loaded. */
	private File fileToBeLoaded = null;
	/** True while no task is assigned. */
	private boolean threadResting = true;
	/** The height of the screen. */
	private int screenHeight = 0;
	/**
	 * Makes a new object containing this PPWDataLoader or returns the one that already exist.
	 * 
	 * @return an object containing this PPWDataLoader.
	 */
	public static PPWDataLoader get(){
		if(datLoad != null){
			return datLoad;
		}else{
			datLoad = new PPWDataLoader();
			return datLoad;
		}
	}
	/**
	 * Starts this PPWDataLoader.
	 */
	public PPWDataLoader(){
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * Fetch the path of the current map.
	 * 
	 * @return the path to the current map.
	 */
	public String getMapRef(){
		return this.mapRef;
	}
	/**
	 * Lets the user choose a file to be loaded.
	 * If it's not a ppw file, we will kindly ask them to choose again.
	 * 
	 * @return the chosen file.
	 */
	private File chooseData(){
		// Create a file chooser
        JFileChooser fc = new JFileChooser();
        // Limit the file types that can be chosen.
        fc.setAcceptAllFileFilterUsed(false);
        fc.changeToParentDirectory();
        boolean choosingFile = true;
        while(choosingFile){
	        int returnVal = fc.showOpenDialog(null);
	        // Check if file is of right type.
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File choosenFile = fc.getSelectedFile();
	            if(choosenFile.getName().toLowerCase().endsWith(".ppw")){
	            	return choosenFile;
	            }else{
	            	System.out.println(choosenFile.getName() + " is not a PPW file.");
	            }
	        }else{
	        	// we have not chosen anything.
	        	choosingFile = false;
	        }
        }
        return null;
	}
	/**
	 * Loads a specified ppw map file.
	 * 
	 * @param file the file to be loaded.
	 * @return a list of all lines in the loaded file. The first list contains only the file path.
	 */
	private ArrayList<String[]> loadFile(File file){
		String[] filePath = new String[1];
    	filePath[0] = file.getAbsolutePath();
		ArrayList <String[]> strs = new ArrayList<String[]>();
		strs.add(filePath);
		try {
			Scanner indata = new Scanner(file);
			while(indata.hasNext()){
				strs.add(indata.nextLine().split("/"));
			}
			indata.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		fileData = strs;
		return fileData;
	}
	/**
	 * Saves the active map as a ppw map file with numbers representing the
	 * different kinds of blocks.
	 *
	 * @param newMap determines if the user will choose a name for the ppw-file.
	 */
	public void saveData(boolean newMap){
		if(newMap){
			String mapName = JOptionPane.showInputDialog("What shall this map be called?");
			mapRef = "maps/" + mapName + ".PPW";
			// here we should check if it already exist but doesn't yet.
		}
		
		ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
		ArrayList<String> tempRow = new ArrayList<String>();
		// highest row first.
		for (int i = Terrain.get().size()-1; i >= 0; i--) {
			tempRow.clear();
			for (int j = 0; j < Terrain.get().rowSize(i); j++) {
				try {
					// this is supposed to get the ID but it seems that all blocks have ID 1 (they are saved as earth blocks).
					System.out.println("ID: "+(Terrain.get().getBlock(j, i).getID()));
					tempRow.add(Terrain.get().getBlock(j, i).getID() + "");
				} catch (IndexOutOfBoundsException e){
					tempRow.add("0");
				}
			}
			tempList.add(tempRow);
		}
			
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(new FileWriter(mapRef)));
			String nextSaveLine = "";
			for (int row = 0; row < tempList.size(); row++) {
				nextSaveLine = "";
				for (int col = 0; col < tempList.get(row).size(); col++) {
					System.out.println("va: "+tempList.get(row).get(col));
					if(col == tempList.get(row).size() - 1){
						nextSaveLine = nextSaveLine + tempList.get(row).get(col);
					}else{
						nextSaveLine = nextSaveLine + tempList.get(row).get(col) + "/";
					}
				}
				utdata.println(nextSaveLine);
			}
			utdata.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Causes the thread of the data loader to save the current map.
	 * 
	 * @param newSaveMap true if the map that shall be saved is a new one.
	 */
	public void saveTerrain(boolean newSaveMap){
		task = true;
		this.newSaveMap = newSaveMap;
		this.threadResting = false;
	}
	/**
	 * Causes the thread of the data loader to load a map that will be chosen by the user.
	 */
	public void loadTerrain(){
		checkScreenHeight();
		task = false;
		this.fileToBeLoaded = null;
		this.threadResting = false;
	}
	/**
	 * Causes the thread of the data loader to load a map.
	 * 
	 * @param file the file to be loaded.
	 */
	public void loadTerrain(File file){
		checkScreenHeight();
		task = false;
		this.fileToBeLoaded = file;
		this.threadResting = false;
	}
	/**
	 * If the screen height has not been checked it is fetched and stored.
	 */
	private void checkScreenHeight(){
		if(screenHeight == 0){
			if(Game.getGameContainer().isFullscreen()){
				//screenHeight = Game.getGameContainer().getScreenHeight();
				screenHeight = 1200;
			}else{
				screenHeight = (int) (Game.getHeightScale()*Game.getGameContainer().getScreenHeight());
			}
		}
	}
	/**
	 * Loads a map and store it as a Terrain containing blocks and machines based on the content of the file.
	 * 
	 * @param file the file to be loaded and stored.
	 */
	public void storeLoadedTerrain(File file){
		ArrayList<String[]> loadedMap = loadFile(file);
		// get the file path.
		mapRef = loadedMap.get(loadedMap.size()-1)[0];
		loadedMap.remove(loadedMap.size()-1);
		if(loadedMap.isEmpty()){
			// map is empty
		}else{
			// lowest row first.
			for (int i = loadedMap.size()-1; i >= 0; i--) {
				ArrayList<Block> currentBlockRow = new ArrayList<Block>();
				for (int j = 0; j < loadedMap.get(i).length; j++) {
					if(loadedMap.get(i)[j].equals("0")){
						currentBlockRow.add(Blocks.AIR_BLOCK.clone(j*64, screenHeight - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].equals("1")){
						currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, screenHeight - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].equals("2")){
						currentBlockRow.add(Blocks.GRASS_BLOCK.clone(j*64, screenHeight - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].equals("3")){
						currentBlockRow.add(Blocks.GRAVEL_BLOCK.clone(j*64, screenHeight - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].equals("4")){
						currentBlockRow.add(Blocks.ROCK_BLOCK.clone(j*64, screenHeight - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].charAt(0) == '{'){
						Component[] parts = new Component[4];
						int l = 0;
						for(int k = 1; k < loadedMap.get(i)[j].length()-1; k++){
							if(loadedMap.get(i)[j].charAt(k) == '1'){
								parts[l] = Component.VACCUM;
							}else if(loadedMap.get(i)[j].charAt(k) == '2'){
								parts[l] = Component.FURNACE;
							}else if(loadedMap.get(i)[j].charAt(k) == '3'){
								parts[l] = Component.STICK;
							}else if(loadedMap.get(i)[j].charAt(k) == '4'){
								parts[l] = Component.DUST;
							}else if(loadedMap.get(i)[j].charAt(k) == '5'){
								parts[l] = Component.COG;
							}else if(loadedMap.get(i)[j].charAt(k) == '6'){
								parts[l] = Component.FUNNEL;
							}else if(loadedMap.get(i)[j].charAt(k) == '7'){
								parts[l] = Component.FUSE;
							}else if(loadedMap.get(i)[j].charAt(k) == ' ' || loadedMap.get(i)[j].charAt(k) == ','){
								l--;
							}
							l++;
						}
						Terrain.get().addMachine(new Machine(j*64, screenHeight - loadedMap.size()*64 + i*64,
								parts[0].clone(0), parts[1].clone(1), parts[2].clone(2), parts[3].clone(3)));
					}
				}
				Terrain.get().addBlockRow(currentBlockRow);
			}
		}
	}
	
	@Override
	public void run() {
		try {
			thread.sleep(10);
			if(!threadResting){
				if(task){
					saveData(newSaveMap);
					
				}else if(!task){
					if(this.fileToBeLoaded == null){
						File fileToBeStored = chooseData();
						if(fileToBeStored != null){
							storeLoadedTerrain(fileToBeStored);
						}
						
					}else{
						storeLoadedTerrain(fileToBeLoaded);
						
					}
				}
				threadResting = true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
