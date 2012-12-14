package priceofprogress;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.muffin.FileMuffin;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	public static long delta = 0;
	public static long lastRunTime = 0;

	public static final String GAME_NAME = "Price of Progress";
	private static AppGameContainer appgc;
	public static ArrayList<String> optionsData;
	/**
	 * creates a game
	 * @param gameName
	 */
	public Game(String gameName) {
		super(gameName);
		addStates();
	}
	/**
	 * mostly used for fetching the current graphics options
	 * @return the game container of this game
	 */
	public static AppGameContainer getGameContainer(){
		return appgc;
	}
	public static void updateDelta(long curRunTime){
		delta = curRunTime - lastRunTime;
		lastRunTime = curRunTime;
	}
	public static long getDelta(){
		return delta;
	}
	/**
	 * 
	 * @return an array of the width and height scales described as a decimal
	 */
	public static float[] getScales(){
		float [] r = {((float)appgc.getWidth()/(float)1920), ((float)appgc.getHeight()/(float)1200)};
		return(r);
	}
	/**
	 * 
	 * @param name the option name
	 * @return an integer matching <b>name</b>
	 */
	public static int fetchIntegerFromOptions(String name) {
		if (optionsData.contains(name)) {
			return Integer
					.valueOf(optionsData.get(optionsData.indexOf(name) + 1));
		}else{
			return (Integer) null;
		}
	}
	/**
	 * 
	 * @param name the option name
	 * @return a string matching <b>name</b>
	 */
	public static String fetchStringFromOptions(String name){
		if(optionsData.contains(name)){
			return optionsData.get(optionsData.indexOf(name)+1);
		}else{
			return null;
		}
	}
	/**
	 * 
	 * @param name the option name
	 * @return a boolean matching <b>name</b>
	 */
	public static Boolean fetchBooleanFromOptions(String name){
		if(optionsData.contains(name)){
			return stringToBoolean(optionsData.get(optionsData.indexOf(name)+1));
		}else{
			return null;
		}
	}
	/**
	 * loads the option values
	 * @return
	 */
	private static ArrayList<String> loadOptions() {
		ArrayList<String> res = new ArrayList<String>();
		try {
			Scanner indata = new Scanner(new File("options.txt"));

			while (indata.hasNext()) {
				res.add(indata.next());
			}
			indata.close();

		} catch (FileNotFoundException ex) {
			System.out.println("WRITING OTHER FILE");
			createOptionFile();

			return loadOptions();
		} catch (java.util.NoSuchElementException exc) {
			System.out.println("FILE CORRUPT, MAKING ANOTHER");
			createOptionFile();
			return loadOptions();
		}
		return res;
	}

	public static void createOptionFile() {
		try {
			PrintWriter utdata = new PrintWriter(new BufferedWriter(
					new FileWriter("options.txt")));
			utdata.println("SizeX: " + appgc.getScreenWidth());
			utdata.println("SizeY: " + appgc.getScreenHeight());
			utdata.println("FullScreen: " + true);
			utdata.println("MvLeft: " + 65);
			utdata.println("MvRight: " + 68);
			utdata.println("Jump: " + 32);
			utdata.println("Fire: " + 0);
			utdata.println("Interract: " + 2);
			utdata.println("MusicVolume: " + appgc.getMusicVolume());
			utdata.println("SoundVolume: " + appgc.getSoundVolume());
			utdata.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	/**
	 * adds the game states to the game
	 */
	private void addStates() {
		// this.addState(new StateMainMenu(States.STATE_MENU_MAIN.getID()));
		ArrayList<State> states = new ArrayList<State>();
		Collections.addAll(states, State.values());
		for (int i = 0; i < states.size(); i++) {
			this.addState(states.get(i).getState());
		}
	}
	/**
	 * 
	 * @param line
	 * @return
	 */
	private static Boolean stringToBoolean(String line) {
		if (line.equals("true")) {
			return true;
		} else if (line.equals("false")) {
			return false;
		} else {
			return null;
		}
	}

	/**
	 * sets up the game container and sets it sailin'
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			appgc = new AppGameContainer(new Game(GAME_NAME));
			optionsData = loadOptions();
			appgc.setTargetFrameRate(60);
			if (optionsData.contains("SizeX:")
					&& optionsData.contains("SizeY:")) {
				appgc.setDisplayMode(Integer.parseInt(optionsData
						.get(optionsData.indexOf("SizeX:") + 1)),
						Integer.parseInt(optionsData.get(optionsData
								.indexOf("SizeY:") + 1)),
						stringToBoolean(optionsData.get(optionsData
								.indexOf("FullScreen:") + 1)));
			} else {
				appgc.setDisplayMode(appgc.getScreenWidth(),
						appgc.getScreenHeight(), true);
			}
			appgc.start();
		} catch (SlickException e) {
			try {
				appgc.setDisplayMode(appgc.getScreenWidth(),
						appgc.getScreenHeight(), true);
				appgc.start();
			} catch (SlickException e1) {
				e1.printStackTrace();
			}

		}
	
	}
	/**
	 * Initiates all the states of the game and causes the game
	 * to enter the main menu state.
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		ArrayList<State> states = new ArrayList<State>();
		Collections.addAll(states, State.values());
		for (int i = 0; i < states.size(); i++) {
			this.getState(states.get(i).getID()).init(gc, this);
		}
		this.enterState(State.STATE_MENU_MAIN.getID());
	}

}
