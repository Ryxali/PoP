package priceofprogress;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum AnimationStore {
	MENU_ANIMATION(new Animation(fetchImages("res/img/MenuAnimation/", "Menu",
			".png", 20), 10, true), 10);
	private Animation anim;
	private int dur;

	private AnimationStore(Animation anim, int dur) {
		this.anim = anim;
		this.dur = dur;
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
