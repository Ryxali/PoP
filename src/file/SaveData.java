package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class holds all the currently loaded savedata.
 * @author Niklas L
 * @see SaveFileCreator
 * @see SaveLoader
 * @see PPWDataLoader
 */
public class SaveData {
	private static SaveData saveData;
	private String lastMapRef;
	private SaveData(){
	}
	public static SaveData get(){
		if(saveData == null){
			saveData = new SaveData();
		}
		return saveData;
	}
	/**
	 * The 
	 * @param worldSaveFile
	 */
	public void loadMapReferences(File worldSaveFile){
		try {
			Scanner indata = new Scanner(worldSaveFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
