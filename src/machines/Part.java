package machines;



import image.AnimationStore;
import image.Drawable;
import image.DrawableG;
import image.DrawableXYG;
import image.ImageStore;

import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import physics.Physics;
import physics.Weight;

public abstract class Part implements Weight, DrawableG, DrawableXYG{
	
	private int[] possPos;
	protected ImageStore img;
	private Machine machine;
	public static final int POSITION_1 = 0;
	public static final int POSITION_2 = 1;
	public static final int POSITION_3 = 2;
	public static final int POSITION_4 = 3;
	
	public Part(ImageStore UIImg, AnimationStore AnimImg, int... posPoss){
		//this.machine = machine;
		this.img = UIImg;
		this.possPos = posPoss;
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
	public Image getImage(){
		return img.getImage();
	}
	public abstract void deviceUpdate();

	public boolean canFit(int i) {
		for (int j = 0; j < possPos.length; j++) {
			if(possPos[j] == i){
				return true;
			}
		}
		return false;
	}
	
	
	
}
