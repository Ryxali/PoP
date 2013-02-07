package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import state.StateEditor;
import terrain.Block;
import terrain.Terrain;

public class PPWDataLoader {
	
	private static ArrayList<String[]> fileData;
	
	
	
	public static ArrayList<String[]> loadData(String ref){
		ArrayList <String[]> strs = new ArrayList<String[]>();
		try {
			Scanner indata = new Scanner(new File(ref));
			
			while(indata.hasNext()){
				strs.add(indata.nextLine().split("/"));
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		fileData = strs;
		return strs;
	}
	
	public static void saveData(String ref){
		
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(new FileWriter(ref)));
			// sorterade block även dom som ofinns
			// kolla om en viss plats har block
			String nextSaveLine = "";
			
			int largestX = -1;
			int largestY = Terrain.get().size()*64;
			for (int i = 0; i < Terrain.get().size(); i++) {
				for (int j = 0; j < Terrain.get().rowSize(i); j++) {
					if(Terrain.get().getBlock(i, j).getYPos() > largestY){
						largestY = Terrain.get().getBlock(i, j).getYPos();
					}
				}
			}
			
			//tempList;
			
			
			/**for (int col = 0; col < 100; col++) {
				for (int row = 0; row < 20; row++) {
					try {
						Terrain.get().getBlock(col, row).getXPos();
						
						nextSaveLine = nextSaveLine + ;
						
					} catch (){
						
					}
				}
				utdata.println();
				
			}*/
				
			utdata.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadTerrain(Terrain terrain, String ref){
		ArrayList<String[]> loadedTerrain = loadData(ref);
		
	}
}