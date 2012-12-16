package priceofprogress;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OptionsFile {

	private static OptionsFile optionsFile;
	public ArrayList<?super Object> data;

	private static String path = "options.txt";

	public static OptionsFile get() {
		if (optionsFile == null) {
			optionsFile = new OptionsFile();
		}
		return optionsFile;
	}

	public ArrayList<?super Object> getData() {
		return data;
	}

	private OptionsFile() {
		data = loadData();
	}
	
	public Integer fetchIntegerFromOptions(String name) {
		if (data.contains(name)) {
			return (Integer) data.get(data.indexOf(name) + 1);
		}else{
			return null;
		}
	}
	public String fetchStringFromOptions(String name) {
		if (data.contains(name)) {
			return (String) data.get(data.indexOf(name) + 1);
		}else{
			return null;
		}
	}
	public Boolean fetchBooleanFromOptions(String name) {
		if (data.contains(name)) {
			return (Boolean) data.get(data.indexOf(name) + 1);
		}else{
			return null;
		}
	}
	/**
	 * 
	 * @param name
	 *            the option name
	 * @return an integer matching <b>name</b>
	 */
	/*public int fetchIntegerFromOptions(String name) throws Exception {
		if (data.contains(name)) {
			return Integer.valueOf(data.get(data.indexOf(name) + 1));
		} else {
			return (Integer) null;
		}
	}*/

	/**
	 * 
	 * @param name
	 *            the option name
	 * @return a string matching <b>name</b>
	 */
	/*public String fetchStringFromOptions(String name) {
		if (data.contains(name)) {
			return data.get(data.indexOf(name) + 1);
		} else {
			return null;
		}
	}*/

	/**
	 * 
	 * @param name
	 *            the option name
	 * @return a boolean matching <b>name</b>
	 */
	/*public Boolean fetchBooleanFromOptions(String name) throws Exception {
		if (data.contains(name)) {
			if (data.get(data.indexOf(name) + 1).equals("true")) {
				return true;
			} else if (data.get(data.indexOf(name) + 1).equals("false")) {
				return false;
			} else {
				throw new Exception();
			}
		} else {
			return null;
		}
	}*/
	/**
	 * this checks if the data list contains any number of specified strings.
	 * This is to be used for checking existing variable names in the current
	 * data.
	 * @param v1
	 * @param vMore
	 * @return
	 */
	public boolean contains(String v1, String... vMore) {
		if (data.contains(v1)) {
			for (int i = 0; i < vMore.length; i++) {
				if (!data.contains(vMore[i])) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Creates a fresh new options file using the current application settings
	 * as a base.
	 */
	private static void createFreshOptionFile() {
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(
					new FileWriter(path)));
			utdata.println("SizeX: " + Game.getGameContainer().getScreenWidth());
			utdata.println("SizeY: "
					+ Game.getGameContainer().getScreenHeight());
			utdata.println("FullScreen: " + true);
			utdata.println("MvLeft: " + 65);
			utdata.println("MvRight: " + 68);
			utdata.println("Jump: " + 32);
			utdata.println("Fire: " + 0);
			utdata.println("Interract: " + 2);
			utdata.println("MusicVolume: "
					+ Game.getGameContainer().getMusicVolume());
			utdata.println("SoundVolume: "
					+ Game.getGameContainer().getSoundVolume());
			utdata.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	/**
	 * adds one value to the list of proper type.
	 * @param list the list to add to
	 * @param indata the Scanner
	 */
	private static void addValue(ArrayList<?super Object> list, Scanner indata){
		if(indata.hasNextBoolean()){
			list.add(indata.nextBoolean());
		}else if(indata.hasNextByte()){
			list.add(indata.nextByte());
		}else if(indata.hasNextInt()){
			list.add(indata.nextInt());
		}else if(indata.hasNextLong()){
			list.add(indata.nextLong());
		}else if(indata.hasNextShort()){
			list.add(indata.nextShort());
		}else if(indata.hasNextDouble()){
			list.add(indata.nextDouble());
		}else if(indata.hasNextFloat()){
			list.add(indata.nextFloat());
		}else if(indata.hasNext()){
			list.add(indata.next());
		}
	}

	/**
	 * loads the option values
	 * 
	 * @return
	 */
	private static ArrayList<?super Object> loadData() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayList<?super Object> res = new ArrayList();
		try {
			Scanner indata = new Scanner(new File(path));

			while (indata.hasNext()) {
				addValue(res, indata);
			}
			indata.close();

		} catch (FileNotFoundException ex) {
			System.out.println("WRITING OTHER FILE");
			createFreshOptionFile();

			return loadData();
		} catch (java.util.NoSuchElementException exc) {
			System.out.println("FILE CORRUPT, MAKING ANOTHER");
			createFreshOptionFile();
			return loadData();
		}
		return res;
	}
}
