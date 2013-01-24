package priceofprogress;

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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.muffin.FileMuffin;
import org.newdawn.slick.state.StateBasedGame;

import file.OptionsFile;

import state.State;

public class EditorStartup extends Game{

	public static final String EDITOR_NAME = "Price of Progress Editor 1.00";
	
	
	
	public EditorStartup(String editorName){
		super(editorName);
	}
	
	protected static void setupAppgc() throws SlickException{
		setAppgc(new AppGameContainer(new EditorStartup(EDITOR_NAME)));
		getGameContainer().setTargetFrameRate(60);
		if (OptionsFile.get().contains("SizeX:", "SizeY:", "FullScreen:")) {
			getGameContainer().setDisplayMode(
					OptionsFile.get().fetchIntegerFromOptions("SizeX:"),
					OptionsFile.get().fetchIntegerFromOptions("SizeY:"),
					OptionsFile.get()
							.fetchBooleanFromOptions("FullScreen:"));
		} else {
			getGameContainer().setDisplayMode(getGameContainer().getScreenWidth(),
					getGameContainer().getScreenHeight(), true);
		}
	}

	private static void setAppgc(AppGameContainer appGameContainer) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		try {
			setupAppgc();
			getGameContainer().start();
		} catch (SlickException e) {
			try {
				getGameContainer().setDisplayMode(getGameContainer().getScreenWidth(),
						getGameContainer().getScreenHeight(), true);
				getGameContainer().start();
			} catch (SlickException e1) {
				e1.printStackTrace();
			}

		}catch(ExceptionInInitializerError e2){
			System.out.println("dafu");
			e2.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public void enterDefaultState(){
		this.enterState(State.STATE_EDITOR.getID());
	}
	
}
