package priceofprogress;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum AnimationStore {
	MENU_ANIMATION(null, "res/img/MenuAnimation/", "Menu", ".png", 20, 10, true),
	MENU_FIRE(null, "res/img/FireAni/", "Layer3_", ".png", 10, 100, true),
	MENU_LIGHT(null, "res/img/LightAni/", "Layer6_", ".png", 10, 100, true),
	TEST(null, "res/img/testAnim/", "test_", ".png", 7, 500, true);
	
	
	private AnimatedImage anim;
	private int dur;
	private byte frames;
	private String filePath;
	private String fileName;
	private String fileEnding;
	private boolean autoRefresh;
	
	private AnimationStore(AnimatedImage anim, String filePath, String fileName, String fileEnding, int frames, int dur, boolean autoRefresh) {
		this.anim = anim;
		//filePath = anim.getPath();
		//fileName = anim.getFileName();
		//fileEnding = anim.getFileEnding();
		//frames = (byte) anim.getFrameCount();
		//dur = anim.getDurations();
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileEnding = fileEnding;
		this.frames = (byte) frames;
		this.dur = dur;
		this.autoRefresh = autoRefresh;
	}
	public boolean isAutoRefresh(){
		return autoRefresh;
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

	public int getDuration() {
		return dur;
	}

	public Animation getAnimation() {
		return anim;
	}
}
