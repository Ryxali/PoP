package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import terrain.Block;
import terrain.Blocks;
import terrain.Terrain;

public class PPWDataLoader implements Runnable{
	private static PPWDataLoader datLoad;
	private static ArrayList<String[]> fileData;
	private String mapRef = "maps/defaultMapRef.PPW";
	
	public static PPWDataLoader get(){
		if(datLoad != null){
			return datLoad;
		}else{
			datLoad = new PPWDataLoader();
			return datLoad;
		}
	}
	
	public PPWDataLoader(){
		run();
	}
	/**
	 * 
	 * 
	 * @return the loaded version of the chosen file with the last array containing the file path.
	 */
	private ArrayList<String[]> chooseData(){
		// Create a file chooser
        JFileChooser fc = new JFileChooser();
        // Limit the file types that can be chosen.
        fc.setAcceptAllFileFilterUsed(false);
        fc.changeToParentDirectory();
        int returnVal = fc.showOpenDialog(null);
        // Check if file is of right type.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File choosenFile = fc.getSelectedFile();
            if(choosenFile.getName().toLowerCase().endsWith(".ppw")){
            	String[] filePath = new String[1];
            	filePath[0] = choosenFile.getAbsolutePath();
            	ArrayList<String[]> loadedFile = loadFile(choosenFile);
            	loadedFile.add(filePath);
            	return loadedFile;
            }else{
            	System.out.println(choosenFile.getName() + " is not a PPW file.");
            	return new ArrayList<String[]>();
            }
        }else{
        	// we have not chosen anything.
        	return new ArrayList<String[]>();
        }
	}
	/**
	 * 
	 * 
	 * @param file the file to be loaded.
	 * @return
	 */
	private ArrayList<String[]> loadFile(File file){
		ArrayList <String[]> strs = new ArrayList<String[]>();
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
	public void saveData(String ref, boolean newMap){
		System.out.println("saving");
		if(newMap){
			String mapName = JOptionPane.showInputDialog("What shall this map be called?");
			ref = "maps/" + mapName + ".PPW";
		}
		
		ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
		ArrayList<String> tempRow = new ArrayList<String>();
		// highest row first.
		for (int i = Terrain.get().size()-1; i >= 0; i--) {
			tempRow.clear();
			for (int j = 0; j < Terrain.get().rowSize(i); j++) {
				try {
					tempRow.add(Terrain.get().getBlock(i, j).getID() + "");
				} catch (IndexOutOfBoundsException e){
					tempRow.add("0");
				}
			}
			tempList.add(tempRow);
		}
			
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(new FileWriter(ref)));
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
	
	public void saveTerrain(String ref, boolean newMap){
		saveData(ref, newMap);
	}
	
	public String loadTerrain(){
		ArrayList<String[]> loadedMap = chooseData();
		if(loadedMap == new ArrayList<String[]>()){
			// map is empty
		}else{
			// get the file path.
			mapRef = loadedMap.get(loadedMap.size()-1)[0];
			loadedMap.remove(loadedMap.size()-1);
			
			ArrayList<Block> currentBlockRow = new ArrayList<Block>();
			// lowest row first.
			for (int i = loadedMap.size()-1; i >= 0; i--) {
				currentBlockRow.clear();
				for (int j = 0; j < loadedMap.get(i).length; j++) {
					if(loadedMap.get(i)[j].equals("1")){
						currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, 1200 - loadedMap.size()*64 + i*64));
						//System.out.println("x: "+j*64+" y: "+ (1200 - loadedMap.size()*64 + i*64));
					}
					// add em other blocksis
				}
				Terrain.get().addBlockRow(currentBlockRow);
			}
		}
		return mapRef;
	}
	
	public String loadTerrain(File file){
		ArrayList<String[]> loadedMap = loadFile(file);
		if(loadedMap == new ArrayList<String[]>()){
			// map is empty
		}else{
			// get the file path.
			mapRef = loadedMap.get(loadedMap.size()-1)[0];
			loadedMap.remove(loadedMap.size()-1);
			
			ArrayList<Block> currentBlockRow = new ArrayList<Block>();
			// lowest row first.
			for (int i = loadedMap.size()-1; i >= 0; i--) {
				currentBlockRow.clear();
				for (int j = 0; j < loadedMap.get(i).length; j++) {
					if(loadedMap.get(i)[j].equals("1")){
						currentBlockRow.add(Blocks.EARTH_BLOCK.clone(j*64, 1200 - loadedMap.size()*64 + i*64));
					}
					// add em other blocksis
				}
				Terrain.get().addBlockRow(currentBlockRow);
			}
		}
		return mapRef;
	}

	@Override
	public void run() {
		
	}
}