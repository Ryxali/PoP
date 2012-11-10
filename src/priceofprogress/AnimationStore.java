package priceofprogress;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum AnimationStore {
	MENU_ANIMATION(new AnimatedImage("res/img/MenuAnimation/", "Menu", ".png", 20, 10, true)),
	MENU_FIRE(new AnimatedImage("res/img/FireAni/", "Layer3_", ".png", 10, 100, true)),
	MENU_LIGHT(new AnimatedImage("res/img/LightAni/", "Layer6_", ".png", 10, 100, true));
	
	private AnimatedImage anim;
	private int[] dur;
	private byte frames;
	private String filePath;
	private String fileName;
	private String fileEnding;
	
	private AnimationStore(AnimatedImage anim) {
		this.anim = anim;
		filePath = anim.getPath();
		fileName = anim.getFileName();
		fileEnding = anim.getFileEnding();
		frames = (byte) anim.getFrameCount();
		dur = anim.getDurations();
	}
	
	public void reload(){
		anim = new AnimatedImage(filePath, fileName, fileEnding, frames, dur, true);
	}
	public void unload(){
		anim = null;
	}
	

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

	public int[] getDuration() {
		return dur;
	}

	public Animation getAnimation() {
		return anim;
	}
}
