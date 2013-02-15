package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import state.StateEditor;
import terrain.Block;
import terrain.Terrain;

public class PPWDataLoader implements Runnable{
	private static PPWDataLoader datLoad;
	private static ArrayList<String[]> fileData;
	
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
	 * @return
	 */
	private void chooseData(){
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
            	loadFile(choosenFile);
            }else{
            	System.out.println(choosenFile.getName() + " is not a PPW file.");
            }
        }else{
        	// we have not chosen anything.
        }
	}
	
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
		for (int i = 0; i < Terrain.get().size(); i++) {
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
	
	public static void saveTerrain(String ref, boolean newMap){
		PPWDataLoader.get().saveData(ref, newMap);
	}
	
	public static void loadTerrain(){
		PPWDataLoader.get().chooseData();
	}
	
	public static ArrayList<String[]> loadTerrain(File file){
		return PPWDataLoader.get().loadFile(file);
	}

	@Override
	public void run() {
		
	}
}