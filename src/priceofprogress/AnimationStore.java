package priceofprogress;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * <p>The storage for Animations in the game. These, along with
 * the images is initially not loaded to save starting time.</p>
 * 
 * <p>You should also clone the animations when using it on 
 * multiple instances (e.g. creatures) to prevent them from
 * walking in unison.</p>
 * @author Niklas Lindblad
 *
 */
public enum AnimationStore {
	DEFAULT(new AnimatedImage("res/img/Default/", "Def", ".png", 1, 1000, false),
			"res/img/Default/", "Def", ".png", 1, 1000, false),
	MENU_ANIMATION(null, "res/img/MenuAnimation/", "Menu", ".png", 20, 10, true),
	MENU_FIRE(null, "res/img/FireAni/", "Layer3_", ".png", 10, 100, true),
	MENU_LIGHT(null, "res/img/LightAni/", "Layer6_", ".png", 10, 100, true),
	MENU_CLUTTER(null, "res/img/BlueprintClutter/", "Blueprintclutter1_", ".png", 6, 100, false),
	TEST(null, "res/img/testAnim/", "test_", ".png", 7, 500, true);
	
	/**
	 * The AnimatedImage object of the animation.
	 */
	private AnimatedImage anim;
	/**
	 * The duration of each frame.
	 */
	private int dur;
	/**
	 * The number of frames
	 */
	private byte frames;
	/**
	 * The location of the images
	 */
	private final String filePath;
	/**
	 * the file name
	 */
	private final String fileName;
	/**
	 * .png in most cases.
	 */
	private final String fileEnding;
	/**
	 * Used for checking if the animation is being looped
	 * automatically.
	 */
	private boolean autoRefresh;
	
	/**
	 * Self explanatory builder of the enums. Does nothing special.
	 * @param anim
	 * @param filePath
	 * @param fileName
	 * @param fileEnding
	 * @param frames
	 * @param dur
	 * @param autoRefresh
	 */
	private AnimationStore(AnimatedImage anim, String filePath, String fileName, String fileEnding, int frames, int dur, boolean autoRefresh) {
		this.anim = anim;;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileEnding = fileEnding;
		this.frames = (byte) frames;
		this.dur = dur;
		this.autoRefresh = autoRefresh;
	}
	
	/**
	 * 
	 * @return true if the animation is looped automatically.
	 */
	public boolean isAutoRefresh(){
		return autoRefresh;
	}
	
	/**
	 * Reloads the animation into the memory, fetching the image
	 * from disk.
	 */
	public void reload(){
		anim = new AnimatedImage(filePath, fileName, fileEnding, frames, dur, autoRefresh);
	}
	
	/**
	 * Frees up space by removing the pointers to the animation.
	 */
	public void unload(){
		if(!anim.equals(DEFAULT.anim)){
			anim = null;
		}
	}
	
	/**
	 * Used for creating and returning a set of images.
	 * @param path
	 * @param fileBaseName
	 * @param fileType
	 * @param quantity
	 * @return
	 */
	private static Image[] fetchImages(String path, String fileBaseName,
			String fileType, int quantity) {
		Image[] imgs = new Image[quantity];
		for (int i = 0; i < quantity; i++) {
			try {
				imgs[i] = new Image(path + fileBaseName + (i + 1) + fileType);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return imgs;
	}
	
	/**
	 * 
	 * @return the duration of each animation image
	 */
	public int getDuration() {
		return dur;
	}
	
	/**
	 * 
	 * @return returns the animation object
	 */
	public Animation getAnimation() {
		if(anim != null){
			return anim;
		}else{
			reload();
			return anim;
		}
	}
}
