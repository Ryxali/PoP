package file;

import gui.LoadingInterface;
import image.BackDrop;
import image.ImageCluster;
import image.ImageStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Graphics;

import priceofprogress.Game;
import terrain.Terrain;

import button.StandardButton;

/**
 * This class is responsible for loading and saving the user's progress. The
 * structure of such a save folder is as follows:
 * <ol>
 * 
 * <li>
 * Old World References File (.pps)
 * <ol>
 * <li>Old World References <br />
 * (Separated by ".ppw")</li>
 * <li>Current Map Reference <br />
 * (on separate line, ending with ".ppw")</li>
 * <li>Character Position in current world<br />
 * (Written as "{blockX; blockY}")</li>
 * </ol>
 * </li>
 * 
 * <li>
 * Character Inventory File (.pps) <br />
 * (Item ids separated by ", ")
 * </li>
 * </ol>
 * 
 * @author Niklas Lindblad
 * @see SaveFileCreator
 * @see LoadingInterface
 * @see PPWDataLoader
 * 
 */
public class SaveLoader implements Runnable {
	private String saveRef;
	private static SaveLoader save;
	private String[] saveFiles;
	private StandardButton[] saveFileButtons;
	private Thread thread;
	private boolean isDone;
	private float stage = 0f;
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

	private void loadOldMapReferences() {
		
	}

	private void loadCurrentMap() {
		PPWDataLoader.get().loadTerrain(new File(saveRef + "/"));
		/*
		 * while (indata.hasNext()) { ArrayList<String[]> strs = null;
		 * strs.add(indata.nextLine().split("/")); }
		 */
	}

	private void loadInventory() {

	}

	private void loadCharacterPosition() {

	}

	public void setFileToLoad(String ref) {
		saveRef = ref;
	}

	public boolean isRunning() {
		return !thread.isInterrupted();
	}

	public float getStage() {
		return stage;
	}

	public boolean isDone() {
		boolean b = isDone;
		isDone = false;
		return b;
	}

	public void loadSaveFile() {
		System.out.println("LES GO");
		if (saveRef == null) {
			throw new NullPointerException();
		}
		if (!thread.isAlive()) {
			thread.start();
		} else if (!thread.isInterrupted()) {
			thread.run();
		} else {
			System.err.println(this.getClass().toString()
					+ " is already in use!");
		}
		/*
		 * ArrayList <String[]> strs = new ArrayList<String[]>(); fileData =
		 * strs;
		 */
	}
	/**
	 * 
	 * @param g
	 * @deprecated
	 */
	public void draw(Graphics g) {
		ImageStore.LOADING_SCREEN.draw(0, 0);
		
		for (int i = 0; i * ImageStore.LOADING_BAR_FILL.getImage().getWidth() < stage
				* ImageStore.LOADING_BAR_FRAME.getImage().getWidth(); i++) {
			ImageStore.LOADING_BAR_FILL.draw(
					(int) (274 + ImageStore.LOADING_BAR_FILL.getImage()
							.getWidth() * i), (int) (989));
		}
		ImageStore.LOADING_BAR_FRAME.draw(274, 989);
	}

	/*
	 * public void print() { for (int i = 0; i < fileData.size(); i++) { for
	 * (int j = 0; j < fileData.get(i).length; j++) {
	 * System.out.println(fileData.get(i)[j] + " "); } } }
	 */

	@Override
	public void run() {
		try {
			WorldFileLoader.loadWorld("maps/op.PPW");
			BackDrop.get().rebuild(ImageCluster.FOREST);
			for(int i = 0; i < 100; i++){
				LoadingInterface.get().stageIncrement(1);
				Thread.sleep(100);
			}
			// Scanner indata = new Scanner(new File(saveRef));
			/*LoadingInterface.get().stageIncrement(0.2);
			loadOldMapReferences();
			Thread.sleep(1000);
			LoadingInterface.get().stageIncrement(0.2);
			loadCurrentMap();
			Thread.sleep(1000);
			LoadingInterface.get().stageIncrement(0.2);
			loadInventory();
			Thread.sleep(1000);
			LoadingInterface.get().stageIncrement(0.2);
			loadCharacterPosition();
			Thread.sleep(1000);
			LoadingInterface.get().stageIncrement(0.2);
			Thread.sleep(300);*/
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			LoadingInterface.get().stageIncrement(100);
		} catch (InterruptedException ex) {

		}
		thread.interrupt();
	}
}
