package machines;

import org.newdawn.slick.Graphics;

import terrain.Terrain;

import image.AnimatedImage;
import image.AnimationStore;
import image.ImageStore;

public class PartVaccum extends Part{

	public PartVaccum(int iD, ImageStore img, AnimationStore animImg, int... posPoss) {
		super(iD, img, animImg, posPoss);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		img.draw(0, 0);
		
	}

	@Override
	public void deviceUpdate() {
		if(getMachine().isDeployed()){
			Terrain.get().getBlock(0, 0);//getMachine().getBlockPos();
		}
		
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		img.draw(x, y);
		
	}
	@Override
	public Part clone(int position) {
		return new PartFunnel(getID(), getImageStore(), getAnimationStore(), position);
	}
}
