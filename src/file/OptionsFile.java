package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class OptionsFile {

	private static OptionsFile optionsFile;
	public ArrayList<? super Object> data;

	private static String path = "options.txt";

	public static OptionsFile get() {
		if (optionsFile == null) {
			optionsFile = new OptionsFile();
		}
		return optionsFile;
	}

	public ArrayList<? super Object> getData() {
		return data;
	}

	private OptionsFile() {
		data = loadData();
	}
	/**
	 * returns an integer value matching the option name
	 * @param name
	 * @return
	 */
	public int fetchIntegerFromOptions(String name) {
		if (data.contains(name)) {
			if(data.get(data.indexOf(name) + 1) instanceof Byte){
				return (Byte) data.get(data.indexOf(name) + 1);
			}else{
				return (Integer) data.get(data.indexOf(name) + 1);
			}
		} else {
			return (Integer) null;
		}
	}
	/**
	 * returns a string value matching the option name
	 * @param name
	 * @return
	 */
	public String fetchStringFromOptions(String name) {
		if (data.contains(name)) {
			return (String) data.get(data.indexOf(name) + 1);
		} else {
			return null;
		}
	}

	/**
	 * returns a boolean value matching the option name
	 * 
	 * @param name
	 * @return
	 */
	public boolean fetchBooleanFromOptions(String name) {
		if (data.contains(name)) {
			return (Boolean) data.get(data.indexOf(name) + 1);
		} else {
			return (Boolean) null;
		}
	}
	
	public double fetchDoubleFromOptions(String name){
		if(data.contains(name)){
			return (Double) data.get(data.indexOf(name) + 1);
		} else {
			return (Double) null;
		}
	}
	public Object fetchObjectFromOptions(String name){
		if(data.contains(name)){
			return data.get(data.indexOf(name) + 1);
		}else{
			return null;
		}
	}

	/**
	 * this checks if the data list contains any number of specified strings.
	 * This is to be used for checking existing variable names in the current
	 * data.
	 * 
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
			Option[] o = Option.values();
			for(int i = 0; i < o.length; i++){
				utdata.println(o[i].getPrintableNameAndValue());
			}
			utdata.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * adds one value to the list of proper type.
	 * 
	 * @param list
	 *            the list to add to
	 * @param indata
	 *            the Scanner
	 */
	private static void addValue(ArrayList<? super Object> list, Scanner indata) {
		if (indata.hasNextBoolean()) {
			list.add(indata.nextBoolean());
		} else if (indata.hasNextByte()) {
			list.add(indata.nextByte());
		} else if (indata.hasNextInt()) {
			list.add(indata.nextInt());
		} else if (indata.hasNextLong()) {
			list.add(indata.nextLong());
		} else if (indata.hasNextShort()) {
			list.add(indata.nextShort());
		} else if (indata.hasNextDouble()) {
			list.add(indata.nextDouble());
		} else if (indata.hasNextFloat()) {
			list.add(indata.nextFloat());
		} else if (indata.hasNext()) {
			String s = indata.next();
			if(s.length() == 1){
				list.add(s.charAt(0));
			}else{
				list.add(s);
			}
		}
	}

	/**
	 * loads the option values
	 * 
	 * @return a list of values of varying types(int, double, boolean, etc.)
	 */
	private static ArrayList<? super Object> loadData() {
		ArrayList<? super Object> res = new ArrayList<Object>();
		try {
			Scanner indata = new Scanner(new File(path));
			indata.useLocale(Locale.ENGLISH);
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
		for(int i = 0; i < res.size(); i++){
			System.out.println(res.get(i) + " " + res.get(i).getClass().getSimpleName());
		}
		return res;
	}
}
