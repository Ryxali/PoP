package machines;

import org.newdawn.slick.Graphics;

import image.AnimationStore;
import image.ImageStore;

public class PartFunnel extends Part{

	public PartFunnel(ImageStore UIImg, AnimationStore AnimImg, int... posPoss) {
		super(UIImg, AnimImg, posPoss);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deviceUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		draw(0, 0, g);
		
	}

}
