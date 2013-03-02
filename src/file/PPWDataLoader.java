package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import terrain.Block;
import terrain.Blocks;
import terrain.Terrain;
/**
 * 
 * @author Niklas Lindblad
 * @author Lukas Wahlquist
 */
public class PPWDataLoader implements Runnable{
	private static PPWDataLoader datLoad;
	private static ArrayList<String[]> fileData;
	private String mapRef = "maps/defaultMapRef.PPW";
	private Thread thread;
	/** The type of task that shall be performed. True represents save and false load. */
	public boolean task;
	/** True if the next map that shall be saved is a new one. */
	private boolean newSaveMap = false;
	/** The file that shall be loaded. */
	private File fileToBeLoaded = null;
	/** True while no task is assigned. */
	private boolean threadResting = true;
	
	public static PPWDataLoader get(){
		if(datLoad != null){
			return datLoad;
		}else{
			datLoad = new PPWDataLoader();
			return datLoad;
		}
	}
	
	public PPWDataLoader(){
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * 
	 * 
	 * @return the reference to the current map.
	 */
	public String getMapRef(){
		return this.mapRef;
	}
	/**
	 * 
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
	 * 
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
	 *
	 *
	 * @param ref
	 * @param newMap
	 */
	public void saveData(boolean newMap){
		if(newMap){
			String mapName = JOptionPane.showInputDialog("What shall this map be called?");
			mapRef = "maps/" + mapName + ".PPW";
			//check if it already exist???
		}
		
		ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
		ArrayList<String> tempRow = new ArrayList<String>();
		// highest row first.
		for (int i = Terrain.get().size()-1; i >= 0; i--) {
			tempRow.clear();
			for (int j = 0; j < Terrain.get().rowSize(i); j++) {
				try {
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
	 * Causes the thread of the data loader to load a map that will be choosen by the user.
	 */
	public void loadTerrain(){
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
		task = false;
		this.fileToBeLoaded = file;
		this.threadResting = false;
	}
	/**
	 * Loads a map and store it as a Terrain containing blocks based on the content of the file.
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
					if(loadedMap.get(i)[j].equals("1")){
						currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, 1200 - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].equals("2")){
						currentBlockRow.add(Blocks.GRASS_BLOCK.clone(j*64, 1200 - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].equals("3")){
						currentBlockRow.add(Blocks.GRAVEL_BLOCK.clone(j*64, 1200 - loadedMap.size()*64 + i*64));
					}else if(loadedMap.get(i)[j].equals("4")){
						currentBlockRow.add(Blocks.ROCK_BLOCK.clone(j*64, 1200 - loadedMap.size()*64 + i*64));
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
					System.out.println("Hejfel");
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
