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

	private SaveLoader() {
		File f = new File("saves");
		saveFiles = f.list();
		for (int i = 0; i < saveFiles.length; i++) {
			saveFileButtons[i] = new StandardButton(Game.getGameContainer()
					.getWidth() / 4, Game.getGameContainer().getHeight() / 4
					+ ImageStore.BUTTON_LOADGAME_IDLE.getImage().getHeight()
					* i, ImageStore.BUTTON_LOADGAME_IDLE,
					ImageStore.BUTTON_LOADGAME_HOVER, 
					ImageStore.BUTTON_LOADGAME_PRESSED);
		}
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

	public void loadSaveFile(String ref) {
		if (!thread.isInterrupted()) {
			saveRef = ref;
			thread.start();
		} else {
			System.err.println(this.getClass().toString()
					+ " is already in use!");
		}
		/*
		 * ArrayList <String[]> strs = new ArrayList<String[]>(); fileData =
		 * strs;
		 */
	}

	public void draw(Graphics g) {
		for (int i = 0; i < saveFileButtons.length; i++) {
			saveFileButtons[i].draw();
			g.drawString(saveFiles[i], 
					saveFileButtons[i].getX()
					+saveFileButtons[i].getStoredImage()
					.getImage().getWidth()/2
					-g.getFont().getWidth(saveFiles[i])/2, 
					saveFileButtons[i].getY()
					+saveFileButtons[i].getStoredImage()
					.getImage().getHeight()/2
					-g.getFont().getHeight(saveFiles[i])/2);
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
			Scanner indata = new Scanner(new File(saveRef));
			loadOldMapReferences(indata);
			loadCurrentMap(indata);
			loadInventory(indata);
			loadCharacterPosition(indata);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
