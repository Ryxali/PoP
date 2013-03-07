package image;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import priceofprogress.Game;


public class AnimatedImage extends Animation {
	private String path;
	private String fileName;
	private String fileEnding;
	private int durs[];

	/**
	 * 
	 */
	public AnimatedImage() {
		// super();
	}
	

	public AnimatedImage(String path, String fileName, String fileEnding,
			int numImgs, int dur, boolean autoUpdate, int stepSize) {
		super(fetchImages(path, fileName, fileEnding, numImgs, stepSize), dur,
				autoUpdate);
		this.path = path;
		this.fileName = fileName;
		this.fileEnding = fileEnding;
	}

	public AnimatedImage(String path, String fileName, String fileEnding,
			int numImgs, int[] dur, boolean autoUpdate, int stepSize) {
		super(fetchImages(path, fileName, fileEnding, numImgs, stepSize), dur,
				autoUpdate);
		this.path = path;
		this.fileName = fileName;
		this.fileEnding = fileEnding;
	}

	public AnimatedImage(ImageStore srcImg, byte frames, int dur) {
		super(makeSheet(srcImg.getImage(), frames), dur);
	}
	private static SpriteSheet makeSheet(Image srcImg, int frames){
		try {
			return new SpriteSheet(srcImg, srcImg.getWidth()/frames, srcImg.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	private static Image[] extract(String filePath, int frames){
		Image imgs[] = new Image[frames];
		Image tempImg;
		try {
			tempImg = new Image(filePath);
			for(int i = 0; i < frames; i++){
				
				imgs[i] = tempImg.getSubImage(
						(int)((double)tempImg.getWidth()*((double)i/(double)frames)),
						0,
						(int) (((double)(i+1)/(double)frames)*(double)tempImg.getWidth()),
						tempImg.getHeight()
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return imgs;
	}
	public String getPath() {
		return path;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileEnding() {
		return fileEnding;
	}
	
	public void destroy() throws SlickException{
		for(int i = 0; i < getFrameCount(); i++){
			getImage(i).destroy();
		}
	}

	private static Image[] fetchImages(String path, String fileBaseName,
			String fileType, int quantity, int stepSize) {
		Image[] imgs = new Image[quantity];
		if (stepSize == 1) {
			for (int i = 0; i < quantity; i += 1) {
				try {
					imgs[i] = new Image(path + fileBaseName + (i + 1)
							+ fileType);
					int sclX = (int) (imgs[i].getWidth() * Game.getWidthScale());
					int sclY = (int) (imgs[i].getHeight() * Game.getHeightScale());
					imgs[i] = imgs[i].getScaledCopy(sclX, sclY);
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		} else {
			for (int i = 0; i < quantity; i += 1) {
				try {
					imgs[i] = new Image(path + fileBaseName + (quantity-i)
							+ fileType);
					int sclX = (int) (imgs[i].getWidth() * Game.getWidthScale());
					int sclY = (int) (imgs[i].getHeight() * Game.getHeightScale());
					imgs[i] = imgs[i].getScaledCopy(sclX, sclY);
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}

		return imgs;
	}

	public void recreate() {
		AnimatedImage anim = this;
		anim = new AnimatedImage(path, fileName, fileEnding,
				anim.getFrameCount(), anim.getDurations(), !this.isStopped(), 1);
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
