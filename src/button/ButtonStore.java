package button;

import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import priceofprogress.Game;

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
	RESOLUTION(new ListButton(400, 553,
			ImageStore.BUTTON_RESOLUTION_IDLE,
			ImageStore.BUTTON_RESOLUTION_HOVER,
			DropdownList.RESOLUTION)),
	KEY_CONFIG(new StandardButton(600, 300,
			ImageStore.BUTTON_KEYCONFIG_IDLE,
			ImageStore.BUTTON_KEYCONFIG_HOVER,
			ImageStore.BUTTON_KEYCONFIG_PRESSED)),
	VOLUME_SOUND(new SliderButton(600, 500,
			ImageStore.BUTTON_SLIDER_BUTTON,
			ImageStore.BUTTON_SLIDER_VERTICAL,
			SliderButton.FACING_VERTICAL)),
	VOLUME_MUSIC(new SliderButton(600, 700,
			ImageStore.BUTTON_SLIDER_BUTTON,
			ImageStore.BUTTON_SLIDER_HORIZONTAL,
			SliderButton.FACING_HORIZONTAL)),
	BACK(new StandardButton(900, 300,
			ImageStore.BUTTON_EXIT_IDLE,
			ImageStore.BUTTON_EXIT_HOVER,
			ImageStore.BUTTON_EXIT_PRESSED));
	
	public static final int STATE_IDLE = 0;
	public static final int STATE_HOVER = 1;
	public static final int STATE_PRESSED = 2;
	
	public static final int MODE_REGULAR = 0;
	public static final int MODE_DROPDOWN = 1;
	public static final int MODE_SLIDER = 2;
	
	private Button button;
	
	private ButtonStore(Button button){
		this.button = button;
	}
	
	public Button getButton(){
		return button;
	}
	
	public static void unloadAll(){
		ButtonStore[] temp = values();
		for(int i = 0; i < temp.length; i++){
			temp[i].getButton().unload();
		}
	}
	
	
}
