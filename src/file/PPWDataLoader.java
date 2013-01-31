package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
		//TODO save.
	}
	
	public static void loadTerrain(Terrain terrain, String ref){
		ArrayList<String[]> loadedTerrain = loadData(ref);
		
	}
}