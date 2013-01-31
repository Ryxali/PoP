package machines;

import org.newdawn.slick.Graphics;

import terrain.Terrain;

import image.AnimatedImage;
import image.AnimationStore;
import image.ImageStore;

public class PartVaccum extends Part{

	public PartVaccum(Machine machine, ImageStore img, AnimationStore animImg, int possPos1, int[] posPoss) {
		super(machine, img, animImg, possPos1, posPoss);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deviceUpdate() {
		if(getMachine().isDeployed()){
			Terrain.get().getBlock(0, 0);//getMachine().getBlockPos();
		}
		
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
