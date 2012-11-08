package priceofprogress;

import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum ImageStore {
	COMPANY_LOGO("res/img/companyLogo.png", fetchImg("res/img/companyLogo.png")),
	BUTTON_PLAY_STANDARD("res/img/buttonPlay.png", fetchImg("res/img/buttonPlay.png")),
	BUTTON_PLAY_PRESSED("res/img/buttonPlay_Pressed.png", fetchImg("res/img/buttonPlay_Pressed.png")),
	BUTTON_PLAY_HOVER("res/img/buttonPlay_Hover.png", fetchImg("res/img/buttonPlay_Hover.png")),
	BACKGROUND_MENU_STATIC("res/img/MenuStatic.png", fetchImg("res/img/MenuStatic.png")),
	TEST("res/img/MenuLightAndShadowTestoverlay.png", fetchImg("res/img/MenuLightAndShadowTestoverlay.png"));
	String ref;
	Image img;
	private ImageStore(String ref, Image img){
		this.img = img;
		this.ref = ref;
	}
	private static Image fetchImg(String ref){
		try{
			return new Image(ref);
		}catch (SlickException e){
			e.printStackTrace();
		}
		return null;
	}
	public String getRef(){
		return ref;
	}
	public Image getImage(){
		double widthScl = (double)Game.fetchIntegerFromOptions("SizeX:")/1920.0;
		double heightScl = (double)Game.fetchIntegerFromOptions("SizeY:")/1200.0;
		return img.getScaledCopy(
				(int)(img.getWidth()*widthScl),
				(int)(img.getHeight()*heightScl));
	}
}
