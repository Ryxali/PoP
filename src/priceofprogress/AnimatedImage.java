package priceofprogress;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimatedImage extends Animation {
	private String path;
	private String fileName;
	private String fileEnding;
	private int durs[];
	/**
	 * 
	 */
	public AnimatedImage(){
		//super();
	}
	public AnimatedImage(String path, String fileName, String fileEnding,
			int numImgs, int dur, boolean autoUpdate) {
		super(fetchImages(path, fileName, fileEnding, numImgs), dur, autoUpdate);
		this.path = path;
		this.fileName = fileName;
		this.fileEnding = fileEnding;
	}

	public AnimatedImage(String path, String fileName, String fileEnding,
			int numImgs, int[] dur, boolean autoUpdate) {
		super(fetchImages(path, fileName, fileEnding, numImgs), dur, autoUpdate);
		this.path = path;
		this.fileName = fileName;
		this.fileEnding = fileEnding;
	}
	
	public String getPath(){
		return path;
	}
	public String getFileName(){
		return fileName;
	}
	public String getFileEnding(){
		return fileEnding;
	}

	private static Image[] fetchImages(String path, String fileBaseName,
			String fileType, int quantity) {
		Image[] imgs = new Image[quantity];
		float[] scls = Game.getScales();
		for (int i = 0; i < quantity; i++) {
			try {
				imgs[i] = new Image(path + fileBaseName + (i + 1) + fileType);
				int sclX = (int) (imgs[i].getWidth() * scls[0]);
				int sclY = (int) (imgs[i].getHeight() * scls[1]);
				imgs[i] = imgs[i].getScaledCopy(sclX, sclY);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return imgs;
	}

	public void recreate() {
		AnimatedImage anim = this;
		anim = new AnimatedImage(path, fileName, fileEnding,
				anim.getFrameCount(), anim.getDurations(), !this.isStopped());
	}

	/**
	 * Draw the animation to the screen
	 */
	/*
	 * @Override public void draw() { double widthScl = (double)
	 * Game.appgc.getWidth() / 1920.0; double heightScl = (double)
	 * Game.appgc.getHeight() / 1200.0; this.getCurrentFrame().getScaledCopy(
	 * (int)(this.getCurrentFrame().getWidth()*widthScl),
	 * (int)(this.getCurrentFrame().getHeight()*heightScl)) .draw(); }
	 */
}
