package image;

import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import priceofprogress.Game;


/**
 * <p>The storage for all the images in the game. It's 
 * important to note that most (if not all) images 
 * will not be preloaded by default to shorten 
 * startup time.</p>
 * <p>Also, on a side note, since images here are a 
 * single reference; it might be worthwhile to clone
 * these for multiple uses.</p>
 * @author Niklas Lindblad
 *
 */
public enum ImageStore {
	DEFAULT("res/img/Default/Def1.png", fetchImg("res/img/Default/Def1.png")),
	COMPANY_LOGO("res/img/companyLogo.png", null),
	
	//Button Block
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
	
	BUTTON_KEYCONFIG_IDLE("res/img/Keyconfig/KeyConfigIdle.png", null),
	BUTTON_KEYCONFIG_HOVER("res/img/Keyconfig/KeyConfigHover.png", null),
	BUTTON_KEYCONFIG_PRESSED("res/img/Keyconfig/KeyConfigClick.png", null),
	
	BUTTON_RESOLUTION_IDLE("res/img/Resolution/ResolutionIdle.png", null),
	BUTTON_RESOLUTION_HOVER("res/img/Resolution/ResolutionHover.png", null),
	
	BUTTON_SLIDER_HORIZONTAL("res/img/Sliders/HorizontalSlider.png", null),
	BUTTON_SLIDER_VERTICAL("res/img/Sliders/VerticalSlider.png", null),
	BUTTON_SLIDER_BUTTON("res/img/Sliders/SliderButton.png", null),
	BUTTON_LIST_ITEM_RESOLUTION("res/img/LoadGameButton/LoadGameHover.png", null),
	
	//Menu Items
	BACKGROUND_MENU_MAIN_STATIC("res/img/MenuStatic.png", null),
	BACKGROUND_MENU_LIGHT_STATIC("res/img/StaticLight.png", null),
	BACKGROUND_MENU_SHADOW_STATIC("res/img/StaticShadow.png", null),
	
	OVERLAY_CRAFTING_BACKGROUND("res/img/CraftMenu/BG.png", null),
	OVERLAY_CRAFTING_SLOT("res/img/CraftMenu/BG_Slot_Empty.png", null),
	
	INVENTORY_FRAME_SLOT("res/img/Default/Def1.png", null),
	
	//Machine parts (for crafting) here.
	CRAFTING_PART_VACCUM("res/img/CraftMenu/Parts/Vaccum.png", null),
	
	//Character and Entity animation sheets below:
	CHAR_ANIM_SHEET_WALK_LEFT("res/img/Entities/Character/WalkLeftAniMainChar.png", null),
	CHAR_ANIM_SHEET_WALK_RIGHT("res/img/Entities/Character/WalkRightAniMainChar.png", null),
	CHAR_ANIM_SHEET_IDLE("res/img/Entities/Character/AnimationTest/StandAnimation.png", null),
	CHAR_ANIM_SHEET_JUMP_RIGHT("res/img/Entities/Character/JumpRightAniMainChar.png", null),
	CHAR_ANIM_SHEET_JUMP_LEFT("res/img/Entities/Character/JumpLeftAniMainChar.png", null),
	
	//Blocks for world building
	BLOCK_EARTH("res/img/blocks/EarthBlock.png", null),
	BLOCK_GRASSres("res/img/blocks/EarthBlock.png", null),
	BLOCK_GRAVEL("res/img/blocks/EarthBlock.png", null),
	BLOCK_ROCK("res/img/blocks/EarthBlock.png", null);
	
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
			try {
				if(img != null){
					img.destroy();
					img = null;
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void draw(int x, int y){
		getImage().draw((float) x * Game.getWidthScale(), (float) y * Game.getHeightScale());
	}
	/**
	 * safely fetches an image based on ref
	 * @param ref
	 * @return the image
	 */
	private static Image fetchImg(String ref) {
		try {
			Image i = new Image(ref);
			return i.getScaledCopy((int)(i.getWidth()*Game.getWidthScale()),
					(int)(i.getHeight()*Game.getHeightScale()));
		} catch(Exception e){
			System.out.println(ref);
			e.printStackTrace();
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
	public static void unloadAll(){
		ImageStore [] temp = values();
		for(int i = 0; i < temp.length; i++){
			temp[i].unload();
		}
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
