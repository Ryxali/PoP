package file;

import image.ImageStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Graphics;

import priceofprogress.Game;

import button.StandardButton;

/**
 * This class is responsible for loading and saving the user's progress. The
 * structure of such a save file is as follows:
 * <ol>
 * <li>Old World References <br />
 * (Separated by ".ppw")</li>
 * <li>Current Map Reference <br />
 * (on separate line, ending with ".ppw")</li>
 * <li>Character Position in world<br />
 * (Written as "{blockX; blockY}")</li>
 * <li>Character Inventory <br />
 * (Separated by ", ")</li>
 * </ol>
 * 
 * @author Niklas Lindblad
 * 
 */
public class SaveLoader implements Runnable {
	private String saveRef;
	private static SaveLoader save;
	private String[] saveFiles;
	private StandardButton[] saveFileButtons;
	private Thread thread;
	private boolean isDone;
	private int stage = 0;
	public static int STAGE_MAX = 20;

	private SaveLoader() {
		
		thread = new Thread(this);
	}

	public static SaveLoader get() {
		if (save == null) {
			save = new SaveLoader();
		}
		return save;
	}

	private void loadOldMapReferences(Scanner indata) {

	}

	private void loadCurrentMap(Scanner indata) {
		/*
		 * while (indata.hasNext()) { ArrayList<String[]> strs = null;
		 * strs.add(indata.nextLine().split("/")); }
		 */
	}

	private void loadInventory(Scanner indata) {

	}

	private void loadCharacterPosition(Scanner indata) {

	}
	
	public void setFileToLoad(String ref){
		saveRef = ref;
	}
	
	public boolean isRunning(){
		return !thread.isInterrupted();
	}
	
	public int getStage(){
		return stage;
	}
	
	public boolean isDone(){
		boolean b = isDone;
		isDone = false;
		return b;
	}

	public void loadSaveFile() {
		System.out.println("LES GO");
		if(saveRef == null){
			throw new NullPointerException();
		}
		if (!thread.isAlive()) {
			thread.start();
		} else if(!thread.isInterrupted()){
			thread.run();
		}else{
			System.err.println(this.getClass().toString()
					+ " is already in use!");
		}
		/*
		 * ArrayList <String[]> strs = new ArrayList<String[]>(); fileData =
		 * strs;
		 */
	}

	public void draw(Graphics g) {
		ImageStore.LOADING_SCREEN.draw(0, 0);
		for(int i = 0; i < stage; i++){
			ImageStore.LOADING_BAR.draw(
					(int)(350*Game.getWidthScale()
							+ ImageStore.LOADING_BAR.getImage().getWidth()*i),
							(int)(1125*Game.getHeightScale()));
		}
	}

	/*
	 * public void print() { for (int i = 0; i < fileData.size(); i++) { for
	 * (int j = 0; j < fileData.get(i).length; j++) {
	 * System.out.println(fileData.get(i)[j] + " "); } } }
	 */

	@Override
	public void run() {
		try {
			//Scanner indata = new Scanner(new File(saveRef));
			//loadOldMapReferences(indata);
			Thread.sleep(1000);
			System.out.println("PROGRESS");
			stage++;
			//loadCurrentMap(indata);
			Thread.sleep(1000);
			System.out.println("PROGRESS");
			stage++;
			//loadInventory(indata);
			Thread.sleep(1000);
			System.out.println("PROGRESS");
			stage++;
			//loadCharacterPosition(indata);
			Thread.sleep(1000);
			System.out.println("PROGRESS");
			stage++;
			for(;stage < STAGE_MAX; stage++){
				Thread.sleep(400);
			}
		//} catch (FileNotFoundException e) {
		//	e.printStackTrace();
		} catch(InterruptedException ex) {
			
		}
		isDone = true;
		stage = 0;
		thread.interrupt();
	}
}
