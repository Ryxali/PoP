package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
	
	private static Save save;
	private ArrayList<String[]> fileData;
	
	private Save(){
		
	}
	
	public static Save get(){
		if(save == null){
			save = new Save();
		}
		return save;
	}
	
	public void loadFile(String ref){
		ArrayList <String[]> strs = new ArrayList<String[]>();
		try {
			Scanner indata = new Scanner(new File(ref));
			
			while(indata.hasNext()){
				strs.add(indata.nextLine().split("/"));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileData = strs;
	}
	public void print(){
		for(int i = 0; i < fileData.size(); i++){
			for(int j = 0; j < fileData.get(i).length; j++){
				System.out.println(fileData.get(i)[j] + " ");
			}
		}
	}
}
