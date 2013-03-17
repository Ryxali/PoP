package machines;

import org.newdawn.slick.Graphics;

import terrain.Terrain;

import image.AnimatedImage;
import image.AnimationStore;
import image.ImageStore;

public class PartVaccum extends Part{

	public PartVaccum(ImageStore img, AnimationStore animImg, int... posPoss) {
		super(img, animImg, posPoss);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deviceUpdate() {
		if(getMachine().isDeployed()){
			//Terrain.get().getBlock(0, 0);//getMachine().getBlockPos();
		}
		
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		img.draw(x, y);
		
	}

	@Override
	public void draw(Graphics g) {
		draw(0, 0, g);
		
	}

}
