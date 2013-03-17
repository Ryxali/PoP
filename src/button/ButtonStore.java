package button;


import file.Option;
import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * Contains all the static buttons used by the game.
 * This excludes inventory slots.
 * @author Niklas Lindblad
 * @see button.Button
 * @see image.ImageStore
 */
public enum ButtonStore {
	NEW_GAME(new StandardButton(247, 333,
			ImageStore.BUTTON_PLAY_IDLE, 
			ImageStore.BUTTON_PLAY_HOVER, 
			ImageStore.BUTTON_PLAY_PRESSED)),
	LOAD_GAME(new StandardButton(496, 604,
			ImageStore.BUTTON_LOADGAME_IDLE,
			ImageStore.BUTTON_LOADGAME_HOVER,
			ImageStore.BUTTON_LOADGAME_PRESSED)),
	OPTIONS(new StandardButton(943, 385,
			ImageStore.BUTTON_OPTIONS_IDLE,
			ImageStore.BUTTON_OPTIONS_HOVER,
			ImageStore.BUTTON_OPTIONS_PRESSED)),
	EXIT(new StandardButton(1597, 453,
			ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED)),
	RESOLUTION(new ListButton(385, 568,
			ImageStore.BUTTON_RESOLUTION_IDLE,
			ImageStore.BUTTON_RESOLUTION_HOVER,
			DropdownList.RESOLUTION)),
	KEY_CONFIG(new StandardButton(685, 380,
			ImageStore.BUTTON_KEYCONFIG_IDLE,
			ImageStore.BUTTON_KEYCONFIG_HOVER,
			ImageStore.BUTTON_KEYCONFIG_PRESSED)),
	VOLUME_SOUND(new SliderButton(1538, 362,
			ImageStore.BUTTON_SLIDER_BUTTON,
			ImageStore.BUTTON_SLIDER_VERTICAL,
			SliderButton.ALIGN_VERTICAL,
			Option.FIELD_VOLUME_SOUND.getName())),
	VOLUME_MUSIC(new SliderButton(1065, 670,
			ImageStore.BUTTON_SLIDER_BUTTON,
			ImageStore.BUTTON_SLIDER_HORIZONTAL,
			SliderButton.ALIGN_HORIZONTAL,
			Option.FIELD_VOLUME_MUSIC.getName())),
	BACK(new StandardButton(900, 300,
			ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED));
	
	//public static final int STATE_IDLE = 0;
	//public static final int STATE_HOVER = 1;
	
	//public static final int STATE_PRESSED = 2;
	/**
	 * This value indicates that this button function like a
	 * regular button.
	 */
	public static final int MODE_REGULAR = 0;
	/**
	 * This value indicates that this button functions like a
	 * dropdown button.
	 */
	public static final int MODE_DROPDOWN = 1;
	/**
	 * This value indicates that this button functions like a
	 * slider button.
	 */
	public static final int MODE_SLIDER = 2;
	/**
	 * The button object in this enumeration
	 */
	private Button button;
	
	private ButtonStore(Button button){
		this.button = button;
		
	}
	
	/**
	 * get the button object
	 * @return button
	 */
	public Button getButton(){
		return button;
	}
	/**
	 * Unloads all Image based resources used by these buttons.
	 */
	public static void unloadAll(){
		ButtonStore[] temp = values();
		for(int i = 0; i < temp.length; i++){
			temp[i].getButton().unload();
		}
	}
	
	
}
