package priceofprogress;

import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * <p>The storage for all the images in the game. It's 
 * important to note that most (if not all) images 
 * will not be preloaded by default to shorten 
 * startup time.</p>
 * <p>Also, on a side note, since images here are a 
 * single reference; it might be worthwile to clone
 * these for multiple uses.</p>
 * @author Niklas Lindblad
 *
 */
public enum ImageStore {
	DEFAULT("res/img/Default/Def1.png", fetchImg("res/img/Default/Def1.png")),
	COMPANY_LOGO("res/img/companyLogo.png", null),
	
	BUTTON_PLAY_IDLE("res/img/NewGameButton/NewGameIdle.png", null),
	BUTTON_PLAY_HOVER("res/img/NewGameButton/NewGameHover.png", null),
	BUTTON_PLAY_PRESSED("res/img/NewGameButton/NewGameClick.png",null),
	BUTTON_EXIT_IDLE("res/img/ExitButton/ExitIdle.png", null),
	BUTTON_EXIT_HOVER("res/img/ExitButton/ExitHover.png", null),
	BUTTON_EXIT_PRESSED("res/img/ExitButton/ExitClick.png", null),
	BUTTON_LOADGAME_IDLE("res/img/LoadGameButton/LoadGameIdle.png", null),
	BUTTON_LOADGAME_HOVER("res/img/LoadGameButton/LoadGameHover.png", null),
	BUTTON_LOADGAME_PRESSED("res/img/LoadGameButton/LoadGameClick.png", null),
	BUTTON_OPTIONS_IDLE("res/img/OptionsButton/OptionsIdle.png", null),
	BUTTON_OPTIONS_HOVER("res/img/OptionsButton/OptionsHover.png", null),
	BUTTON_OPTIONS_PRESSED("res/img/OptionsButton/OptionsClick.png", null),
	
	BACKGROUND_MENU_MAIN_STATIC("res/img/MenuStatic.png", null),
	BACKGROUND_MENU_LIGHT_STATIC("res/img/StaticLight.png", null),
	BACKGROUND_MENU_SHADOW_STATIC("res/img/StaticShadow.png", null),
	TEST("res/img/MenuLightAndShadowTestoverlay.png", null);
	/**
	 * The String reference to the image location on disk
	 */
	private final String ref;
	/**
	 * The image itself
	 */
	private Image img;
	/**
	 * Constructor for the enums
	 * @param ref
	 * @param img
	 */
	private ImageStore(String ref, Image img) {
		this.img = img;
		this.ref = ref;
	}
	
	/**
	 * loads the image data to ram
	 */
	public void reload(){
		img = fetchImg(ref);
	}
	
	/**
	 * unloads the image data
	 */
	public void unload(){
		if(img != DEFAULT.img){
			img = null;
		}
	}
	/**
	 * safely fetches an image based on ref
	 * @param ref
	 * @return the image
	 */
	private static Image fetchImg(String ref) {
		try {
			Image i = new Image(ref);
			float[] f = Game.getScales();
			return i.getScaledCopy((int)(i.getWidth()*f[0]), (int)(i.getHeight()*f[1]));
		} catch(Exception e){
			return DEFAULT.getImage();
		}
	}
	
	/**
	 * 
	 * @return the ref of the image
	 */
	public String getRef() {
		return ref;
	}
	
	public float getImageLeftmostX(){
		return getImage().getCenterOfRotationX()-getImage().getWidth()/2;
	}
	public float getImageTopmostY(){
		return getImage().getCenterOfRotationY()-getImage().getHeight()/2;
	}

	/**
	 * 
	 * @return the image object
	 */
	public Image getImage() {
		if(img != null){
			return img;
		}else{
			return fetchImg(ref);
		}
		
	}
}
