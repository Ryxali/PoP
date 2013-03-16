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

public class SaveFileCreator {
	// TODO Create a save file

	public static void createNewFile(String path) {
		makeDir(path);
		createOldMapReferencesFile(path);
		createInventorySaveFile(path);
	}
	
	private static void createInventorySaveFile(String path){
		File f = new File(path + "/inv.pps");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void makeDir(String path) {
		File f = new File(path);
		if (f.mkdir() == false) {
			PoPError.get().safeExitWithPrompt(
					"The file path could not be created!");
			throw new SecurityException();
		}
	}

	private static void createOldMapReferencesFile(String path) {
		File f = new File(path + "/maps/hist.pps");
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
		copyMaps(path + "/maps");
	}

	private static void writeMapReferences(File f) {
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(
					new FileWriter(f)));
			utdata.println("firstWorld.PPW");
			utdata.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void copyMaps(String path) {
		copyMap(new File("maps/firstWorld.PPW"), new File(path+"/firstWorld.PPW"));
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
