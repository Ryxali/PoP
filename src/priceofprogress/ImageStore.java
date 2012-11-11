package priceofprogress;

import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum ImageStore {
	COMPANY_LOGO("res/img/companyLogo.png", null),
	BUTTON_PLAY_STANDARD("res/img/buttonPlay.png", null),
	BUTTON_PLAY_PRESSED("res/img/buttonPlay_Pressed.png",null),
	BUTTON_PLAY_HOVER("res/img/buttonPlay_Hover.png", null),
	BACKGROUND_MENU_STATIC("res/img/MenuStatic.png", null),
	BACKGROUND_MENU_LIGHT_STATIC("res/img/StaticLight.png", null),
	BACKGROUND_MENU_SHADOW_STATIC("res/img/StaticShadow.png", null),
	TEST("res/img/MenuLightAndShadowTestoverlay.png", null);
	String ref;
	Image img;

	private ImageStore(String ref, Image img) {
		this.img = img;
		this.ref = ref;
	}

	public void reload(){
		img = fetchImg(ref);
	}
	public void unload(){
		img = null;
	}

	private static Image fetchImg(String ref) {
		try {
			Image i = new Image(ref);
			float[] f = Game.getScales();
			return i.getScaledCopy((int)(i.getWidth()*f[0]), (int)(i.getHeight()*f[1]));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRef() {
		return ref;
	}

	public Image getImage() {
		return img;
	}
}
