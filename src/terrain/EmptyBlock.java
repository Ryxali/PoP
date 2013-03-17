package terrain;

import org.newdawn.slick.Graphics;

import priceofprogress.Game;

import image.ImageStore;

public class EmptyBlock extends Block{

	public EmptyBlock(int id) {
		super(id, null);
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		//g.drawString(String.valueOf(y*Game.getHeightScale()), x*Game.getWidthScale(), y*Game.getHeightScale());
		return;
	}
	
	@Override
	public ImageStore getImage(){
		return null;
	}

}
