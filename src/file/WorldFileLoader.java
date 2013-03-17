package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import terrain.Terrain;
import terrain.Blocks;

public class WorldFileLoader {
	public static void loadWorld(String fileName){
		try {
			Scanner indata = new Scanner(new File(fileName));
			while(indata.hasNextLine()){
				splitAndAdd(indata.nextLine());
			}
			indata.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void splitAndAdd(String line){
		String [] values = line.split("/");
		for (int i = 0; i < values.length; i++) {
			try{
			Terrain.get().getColumns().get(i).add(Blocks.getByID(Integer.valueOf(values[i])).getBlock());
			}catch(NumberFormatException e){
				
			}
		}
	}
	

}

