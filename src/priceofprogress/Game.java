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

	public static final String GAME_NAME = "Price of Progress";
	public static AppGameContainer appgc;
	public static ArrayList<String> optionsData;

	public Game(String gameName) {
		super(gameName);
		addStates();

	}
	
	public static float[] getScales(){
		float [] r = {((float)appgc.getWidth()/(float)1920), ((float)appgc.getHeight()/(float)1200)};
		return(r);
	}

	public static int fetchIntegerFromOptions(String name) {
		if (optionsData.contains(name)) {
			return Integer
					.valueOf(optionsData.get(optionsData.indexOf(name) + 1));
		}else{
			return (Integer) null;
		}
	}
	public static String fetchStringFromOptions(String name){
		if(optionsData.contains(name)){
			return optionsData.get(optionsData.indexOf(name)+1);
		}else{
			return null;
		}
	}
	public static Boolean fetchBooleanFromOptions(String name){
		if(optionsData.contains(name)){
			return stringToBoolean(optionsData.get(optionsData.indexOf(name)+1));
		}else{
			return null;
		}
	}

	private static ArrayList<String> loadOptions() {
		ArrayList<String> res = new ArrayList<String>();
		try {
			Scanner indata = new Scanner(new File("options.txt"));

			while (indata.hasNext()) {
				res.add(indata.next());
			}
			/*
			 * winSizeX = Integer.parseInt(indata.next()); winSizeY =
			 * Integer.parseInt(indata.next()); fullScreen =
			 * Boolean.parseBoolean(indata.next()); moveLeft = indata.nextInt();
			 * moveRight = indata.nextInt(); jump = indata.nextInt(); fire =
			 * indata.nextInt(); System.out.println(fire); interract =
			 * indata.nextInt();
			 * soundHandler.setVolume(Float.valueOf(indata.next()));
			 * soundHandler.setSoundVolume(Float.valueOf(indata.next()));
			 */
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

	private void addStates() {
		// this.addState(new StateMainMenu(States.STATE_MENU_MAIN.getID()));
		ArrayList<State> states = new ArrayList<State>();
		Collections.addAll(states, State.values());
		for (int i = 0; i < states.size(); i++) {
			this.addState(states.get(i).getState());
		}
	}

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
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			appgc = new AppGameContainer(new Game(GAME_NAME));
			optionsData = loadOptions();
			//appgc.setTargetFrameRate(60);
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

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
