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

	/**
	 * creates a game
	 * 
	 * @param gameName
	 */
	public Game(String gameName) {
		super(gameName);
		addStates();
	}

	/**
	 * mostly used for fetching the current graphics options
	 * 
	 * @return the game container of this game
	 */
	public static AppGameContainer getGameContainer() {
		return appgc;
	}

	public static void updateDelta(long curRunTime) {
		delta = curRunTime - lastRunTime;
		lastRunTime = curRunTime;
	}

	public static long getDelta() {
		return delta;
	}

	/**
	 * 
	 * @return an array of the width and height scales described as a decimal
	 */
	public static float[] getScales() {
		float[] r = { ((float) appgc.getWidth() / (float) 1920),
				((float) appgc.getHeight() / (float) 1200) };
		return (r);
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
	 * sets upp the application according to either the options settings (if
	 * they exist), or the system default (with fullscreen enabled).
	 * @throws SlickException
	 */
	private static void setupAppgc() throws SlickException{
		appgc = new AppGameContainer(new Game(GAME_NAME));
		appgc.setTargetFrameRate(60);
		if (OptionsFile.get().contains("SizeX:", "SizeY:", "FullScreen:")) {
			appgc.setDisplayMode(
					OptionsFile.get().fetchIntegerFromOptions("SizeX:"),
					OptionsFile.get().fetchIntegerFromOptions("SizeY:"),
					OptionsFile.get()
							.fetchBooleanFromOptions("FullScreen:"));
		} else {
			appgc.setDisplayMode(appgc.getScreenWidth(),
					appgc.getScreenHeight(), true);
		}
	}

	/**
	 * sets up the game container and sets it sailin'
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			setupAppgc();
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
	 * Initiates all the states of the game and causes the game to enter the
	 * main menu state.
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		State[] states = State.values();
		for (int i = 0; i < states.length; i++) {
			this.getState(states[i].getID()).init(gc, this);
		}
		this.enterState(State.STATE_MENU_MAIN.getID());
	}

}
