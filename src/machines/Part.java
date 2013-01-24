package machines;



import image.AnimationStore;
import image.ImageStore;

import java.util.Arrays;

import org.newdawn.slick.Graphics;

import priceofprogress.Physics;
import priceofprogress.Weight;

public abstract class Part implements Weight{
	private int[] possPos;
	private ImageStore img;
	private Machine machine;
	
	public Part(Machine machine, ImageStore UIImg, AnimationStore AnimImg, int possPos1, int... posPoss){
		this.machine = machine;
		this.img = UIImg;
		this.possPos = toArray(possPos1, posPoss);
	}
	
	private static int[] toArray(int i, int[] iN) {
		iN = Arrays.copyOf(iN, iN.length + 1);
		for (int j = iN.length-1; j > 0; j--) {
			iN[i] = iN[i - 1];
		}
		iN[0] = i;
		return iN;
	}
	
	public void update(){
		deviceUpdate();
	}
	
	public Machine getMachine(){
		return machine;
	}
	
	public abstract void deviceUpdate();
	public abstract void draw(Graphics g);
	
	
	
}
