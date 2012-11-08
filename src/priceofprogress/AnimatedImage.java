package priceofprogress;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimatedImage extends Animation {
	private String path;
	private String fileName;
	private String fileEnding;

	public AnimatedImage(String path, String fileName, String fileEnding,
			int numImgs, int dur) {
		super(fetchImages(path, fileName, fileEnding, numImgs), dur);
		this.path = path;
		this.fileName = fileName;
		this.fileEnding = fileEnding;
	}

	private static Image[] fetchImages(String path, String fileBaseName,
			String fileType, int quantity) {
		Image[] imgs = new Image[20];
		double widthScl = (double) Game.appgc.getWidth() / 1920.0;
		double heightScl = (double) Game.appgc.getHeight() / 1200.0;
		for (int i = 0; i < 20; i++) {
			try {
				imgs[i] = new Image(path + fileBaseName + (i + 1) + fileType);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return imgs;
	}

	public void recreate(AnimatedImage anim, double widthScale,
			double heightScale) {

		anim = new AnimatedImage(path, fileName, fileEnding,
				anim.getFrameCount(), anim.getDuration(0));
	}

	
	/**
	 * Draw the animation to the screen
	 */
	@Override
	public void draw() {
		double widthScl = (double) Game.appgc.getWidth() / 1920.0;
		double heightScl = (double) Game.appgc.getHeight() / 1200.0;
		this.getCurrentFrame().getScaledCopy(
				(int)(this.getCurrentFrame().getWidth()*widthScl), 
				(int)(this.getCurrentFrame().getHeight()*heightScl))
				.draw();
	}
}
