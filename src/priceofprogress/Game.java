package priceofprogress;

import image.ImageLoader;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
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
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.muffin.FileMuffin;
import org.newdawn.slick.state.StateBasedGame;

import state.State;

import file.OptionsFile;
import file.Save;

public class Game extends StateBasedGame {
	public static long delta = 0;
	public static long lastRunTime = 0;
	private static float gameSpeed = 1;

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
	
	public static void setGameSpeed(float speed){
		gameSpeed = speed;
	}
	
	public static String[] getValidResolutions() {
		String[] str;
		ArrayList<String> strings = new ArrayList<String>();
		GraphicsEnvironment gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		DisplayMode[] displayModes = gc.getDefaultScreenDevice().getDisplayModes();
		int sizeX = gc.getDefaultScreenDevice().getDisplayMode().getWidth();
		int sizeY = gc.getDefaultScreenDevice().getDisplayMode().getHeight();
		for (int z = 0; z < displayModes.length; z++) {
			// System.out.println(displayModes[z].getWidth() + "x"
			// + displayModes[z].getHeight());
			if ((double)displayModes[z].getWidth() / (double)displayModes[z].getHeight() ==
					(double)sizeX / (double)sizeY

					&& displayModes[z].getRefreshRate() == gc
							.getDefaultScreenDevice().getDisplayMode()
							.getRefreshRate()
					&& displayModes[z].getBitDepth() == gc
							.getDefaultScreenDevice().getDisplayMode()
							.getBitDepth()) {
				strings.add(displayModes[z].getWidth() + "x"
						+ displayModes[z].getHeight());

			}
		}
		str = new String[strings.size()];
		for (int d = 0; d < str.length; d++) {
			str[d] = strings.get(d);
			System.out.println(strings.get(d));
		}
		return str;
	}

	/**
	 * mostly used for fetching the current graphics options
	 * 
	 * @return the game container of this game
	 */
	public static AppGameContainer getGameContainer() {
		return appgc;
	}
	
	public static void setGameContainer(AppGameContainer newGameContainer){
		appgc = newGameContainer;
	}

	public static void updateDelta(long curRunTime) {
		delta = (long) ((curRunTime - lastRunTime)*gameSpeed);
		lastRunTime = curRunTime;
		if(delta > 200){
			delta = 10;
		}
	}

	public static double getDelta() {
		return (double)delta;
	}

	public static float getWidthScale() {
		return (float) appgc.getWidth() / (float) 1920;
	}

	public static float getHeightScale() {
		return (float) appgc.getHeight() / (float) 1200;
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
	 * 
	 * @throws SlickException
	 */
	private static void setupAppgc() throws SlickException {
		appgc = new AppGameContainer(new Game(GAME_NAME));
		appgc.setTargetFrameRate(60);
		if (OptionsFile.get().contains("SizeX:", "SizeY:", "FullScreen:")) {
			appgc.setDisplayMode(
					OptionsFile.get().fetchIntegerFromOptions("SizeX:"),
					OptionsFile.get().fetchIntegerFromOptions("SizeY:"),
					OptionsFile.get().fetchBooleanFromOptions("FullScreen:"));
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
		System.out.println(Input.KEY_A + " " + Input.KEY_D);
		
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

		} catch (ExceptionInInitializerError e2) {
			System.out.println("dafer");
			e2.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Initiates all the states of the game and causes the game to enter the
	 * default state which is the Main Menu state for normal startup.
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		State[] states = State.values();
		for (int i = 0; i < states.length; i++) {
			this.getState(states[i].getID()).init(gc, this);
		}
		enterDefaultState();
	}

	/**
	 * Causes the game to enter the default state.
	 * This method exists so it can be called from normal startup and overridden in editor startup
	 */
	public void enterDefaultState() {
		this.enterState(State.STATE_MENU_MAIN.getID());
	}

}
