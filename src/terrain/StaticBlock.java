package terrain;



import image.ImageStore;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import priceofprogress.Game;

public class StaticBlock extends Block{
	
	public StaticBlock(int id, ImageStore image){
		super(id, image);
	}
	
	

	@Override
	public void draw(int x, int y, Graphics g) {
		//System.out.println(x + " " + y);
		//System.out.println(":" + x*Game.getWidthScale() + " " + y*Game.getHeightScale() + ":");
		if(getImage().equals(ImageStore.BLOCK_EARTH)){
			g.setColor(Color.black);
			g.fillRect(x*Game.getWidthScale(), y*Game.getHeightScale(), Blocks.getBlockXDimension(), Blocks.getBlockYDimension());
			return;
		}
		getImage().draw(x, y);//
		//g.drawString(String.valueOf(y*Game.getHeightScale()), x*Game.getWidthScale(), y*Game.getHeightScale());
	}

	
}
