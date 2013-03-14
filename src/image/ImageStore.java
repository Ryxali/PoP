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
public enum ImageStore implements Loadable {
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
	
	LOADING_SCREEN("res/img/LoadingScreen/LoadingScreen.png", null),
	LOADING_BAR("res/img/LoadingScreen/Barpiece.png", null),
	
	OVERLAY_CRAFTING_BACKGROUND("res/img/CraftMenu/BG.png", null),
	OVERLAY_CRAFTING_SLOT("res/img/CraftMenu/BG_Slot_Empty.png", null),
	
	INVENTORY_FRAME_SLOT("res/img/Default/Def1.png", null),
	INVENTORY_FRAME_SLOT_IDLE("res/img/Inventory/SlotOverlay/Idle.png", null),
	INVENTORY_FRAME_SLOT_HOVER("res/img/Inventory/SlotOverlay/Hover.png", null),
	INVENTORY_FRAME_SLOT_PRESSED("res/img/Inventory/SlotOverlay/Pressed.png", null),
	
	//Machine parts (for crafting) here.
	CRAFTING_PART_VACCUM("res/img/CraftMenu/Parts/Vaccum.png", null),
	
	CRAFTING_PART_COG_ICON("res/img/CraftMenu/Parts/CogWheel64.png", null),
	CRAFTING_PART_COG_ITEM("res/img/CraftMenu/Parts/CogWheel16.png", null),
	
	CRAFTING_PART_DUST_ICON("res/img/CraftMenu/Parts/Dust64.png", null),
	CRAFTING_PART_DUST_ITEM("res/img/CraftMenu/Parts/Dust16.png", null),
	
	CRAFTING_PART_STICK_ICON("res/img/CraftMenu/Parts/Stick64.png", null),
	CRAFTING_PART_STICK_ITEM("res/img/CraftMenu/Parts/Stick16.png", null),
	
	//Character and Entity animation sheets below:
	CHAR_ANIM_SHEET_WALK_LEFT("res/img/Entities/Character/WalkLeftAniMainchar.png", null),
	CHAR_ANIM_SHEET_WALK_RIGHT("res/img/Entities/Character/WalkRightAniMainchar.png", null),
	CHAR_ANIM_SHEET_IDLE("res/img/Entities/Character/StandAnimation.png", null),
	CHAR_ANIM_SHEET_JUMP_RIGHT("res/img/Entities/Character/JumpRightAniMainchar.png", null),
	CHAR_ANIM_SHEET_JUMP_LEFT("res/img/Entities/Character/JumpLeftAniMainchar.png", null),
	
	//Blocks for world building
	BLOCK_EARTH("res/img/blocks/EarthBlock.png", null),
	BLOCK_GRASS("res/img/blocks/GrassBlock.png", null),
	BLOCK_GRAVEL("res/img/blocks/GravelBlock.png", null),
	BLOCK_ROCK("res/img/blocks/RockBlock.png", null),
	
	
	//Backdrop images
	ASTRAL_SUN("res/img/BackDrops/Astronomical/SunSprite.png", null),
	ASTRAL_MOON("res/img/BackDrops/Astronomical/MoonSprite.png", null),
	
	BACKDROP_BLANK("res/img/Backdrops/ForestWorld/Far/Mountain_Blank.png", null),
	
	FOREST_BACKDROP_FAR_1("res/img/Backdrops/ForestWorld/Far/Mountain_1.png", null),
	FOREST_BACKDROP_FAR_2("res/img/Backdrops/ForestWorld/Far/Mountain_2.png", null),
	FOREST_BACKDROP_FAR_3("res/img/Backdrops/ForestWorld/Far/Mountain_3.png", null),
	FOREST_BACKDROP_FAR_4("res/img/Backdrops/ForestWorld/Far/Mountain_6.png", null),
	FOREST_BACKDROP_FAR_5("res/img/Backdrops/ForestWorld/Far/Mountain_7.png", null),
	
	FOREST_BACKDROP_MID_1("res/img/Backdrops/ForestWorld/Mid/Tree_1.png", null),
	FOREST_BACKDROP_MID_2("res/img/Backdrops/ForestWorld/Mid/Tree_2.png", null),
	FOREST_BACKDROP_MID_3("res/img/Backdrops/ForestWorld/Mid/Tree_3.png", null),
	FOREST_BACKDROP_MID_4("res/img/Backdrops/ForestWorld/Mid/Tree_4.png", null),
	FOREST_BACKDROP_MID_5("res/img/Backdrops/ForestWorld/Mid/Tree_5.png", null),
	FOREST_BACKDROP_MID_6("res/img/Backdrops/ForestWorld/Mid/Tree_6.png", null),
	FOREST_BACKDROP_MID_7("res/img/Backdrops/ForestWorld/Mid/Tree_7.png", null),
	
	FOREST_BACKDROP_SHORT_1("res/img/Backdrops/ForestWorld/Short/Grass.png", null),
	FOREST_BACKDROP_SHORT_2("res/img/Backdrops/ForestWorld/Short/Grass_1.png", null),
	FOREST_BACKDROP_SHORT_3("res/img/Backdrops/ForestWorld/Short/Grass_2.png", null),
	FOREST_BACKDROP_SHORT_4("res/img/Backdrops/ForestWorld/Short/Grass_3.png", null),
	FOREST_BACKDROP_SHORT_5("res/img/Backdrops/ForestWorld/Short/Grass_4.png", null),
	
	FOREST_BACKDROP_SKY_1("res/img/Backdrops/ForestWorld/Sky/BackgroundSlice_1.png", null),
	FOREST_BACKDROP_SKY_2("res/img/Backdrops/ForestWorld/Sky/BackgroundSlice_2.png", null),
	FOREST_BACKDROP_SKY_3("res/img/Backdrops/ForestWorld/Sky/BackgroundSlice_3.png", null),
	FOREST_BACKDROP_SKY_4("res/img/Backdrops/ForestWorld/Sky/BackgroundSlice_4.png", null),
	FOREST_BACKDROP_SKY_5("res/img/Backdrops/ForestWorld/Sky/BackgroundSlice_5.png", null),
	FOREST_BACKDROP_SKY_6("res/img/Backdrops/ForestWorld/Sky/BackgroundSlice_6.png", null),
	FOREST_BACKDROP_SKY_7("res/img/Backdrops/ForestWorld/Sky/BackgroundSlice_7.png", null);
	
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
