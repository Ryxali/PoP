package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import priceofprogress.PoPError;
/**
 * This is a class of static methods called when creating a fresh
 * save file.
 * @author Niklas L
 * 
 * @see file.SaveLoader
 * @see file.SaveData
 * @see file.PPWDataLoader
 *
 */
public class SaveFileCreator {
	public static final String SAVE_LOC = "saves/";
	public static final String MAPS_LOC = "maps/";
	/**
	 * Creates a new save file with the given name
	 * @param saveName the name of the new save file
	 */
	public static void createNewFile(String saveName) {
		makeDir(saveName);
		createOldMapReferencesFile(saveName);
		copyMaps(saveName + "/maps");
		createInventorySaveFile(saveName);
	}
	/**
	 * Creates a savefile containing the character's inventory items.
	 * @param saveName the name of the save file.
	 */
	private static void createInventorySaveFile(String saveName){
		File f = new File(SAVE_LOC + saveName + "/inv.pps");
		try {
			f.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Creates the directory that contains the save files.
	 * @param saveName the name of the save file.
	 */
	private static void makeDir(String saveName) {
		File f = new File(SAVE_LOC + saveName);
		if (f.mkdir() == false) {
			PoPError.get().safeExitWithPrompt(
					"The file path could not be created!");
			throw new SecurityException();
		}
	}
	/**
	 * Creates the file containing old map references.
	 * @param saveName the name of the save file
	 */
	private static void createOldMapReferencesFile(String saveName) {
		File f = new File(SAVE_LOC + saveName + "/maps/hist.pps");
		if (f.mkdirs() == false) {
			PoPError.get().safeExitWithPrompt(
					"The file path could not be created!");
			throw new SecurityException();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeMapReferences(f);
		
	}
	/**
	 * Writes the map references to the old map references file
	 * @param f the old map references file.
	 */
	private static void writeMapReferences(File f) {
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(
					new FileWriter(f)));
			utdata.println("firstWorld.PPW");
			utdata.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * copy all maps from the maps/ template folder into the save folder.
	 * @param saveName the name of the save file
	 */
	private static void copyMaps(String saveName) {
		copyMap(new File(MAPS_LOC + "firstWorld.PPW"), new File(SAVE_LOC + saveName + "/firstWorld.PPW"));
	}

	/**
	 * Retrieve all data from a source file and write it to another file.
	 * No file is harmed in this process.
	 * @param fSrc the source file
	 * @param fTo the destination file
	 * @see file.SaveFileCreator.copyMaps(String path)
	 */
	private static void copyMap(File fSrc, File fTo) {
		ArrayList<String> s = new ArrayList<String>();
		try {
			Scanner indata = new Scanner(fSrc);
			while (indata.hasNext()) {
				s.add(indata.nextLine());
			}
			indata.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(
					new FileWriter(fTo)));
			for (int i = 0; i < s.size(); i++) {
				utdata.println(s.get(i));
			}
			utdata.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
