package gui;

import image.Drawable;
import image.ImageStore;

import org.newdawn.slick.Graphics;

public class LoadingInterface implements Drawable {
	private byte stage;
	private static LoadingInterface loadingI;

	public static LoadingInterface get() {
		if (loadingI == null) {
			loadingI = new LoadingInterface();
		}
		return loadingI;
	}

	public void stageIncrement(int value) {
		stage += value;
		if (stage < 0) {
			stage = 0;
			return;
		}
		if (100 < stage) {
			stage = 100;
			return;
		}
	}

	public boolean isDone() {
		if (stage >= 100) {
			return true;
		}
		return false;
	}

	public void stageReset() {
		stage = 0;
	}

	@Override
	public void draw() {
		ImageStore.LOADING_SCREEN.draw(0, 0);
		//System.out.println(stage);
		for (int i = 0; (i+1) * ImageStore.LOADING_BAR_FILL.getImage().getWidth() < (float)stage/100f
				* ImageStore.LOADING_BAR_FRAME.getImage().getWidth(); i++) {
			ImageStore.LOADING_BAR_FILL.draw(
					(int) (274 + ImageStore.LOADING_BAR_FILL.getImage()
							.getWidth() * i), (int) (989));
		}
		ImageStore.LOADING_BAR_FILL.draw(
				(int) (274 - ImageStore.LOADING_BAR_FILL.getImage().getWidth()
						+ ImageStore.LOADING_BAR_FRAME.getImage().getWidth()*(float)stage/100f), (int) (989));
		ImageStore.LOADING_BAR_FRAME.draw(274, 989);
	}
}
