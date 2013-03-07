package machines;



import image.AnimationStore;
import image.Drawable;
import image.ImageStore;

import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import physics.Weight;

public abstract class Part implements Weight, Drawable{
	
	private int iD;
	private int[] possPos;
	protected ImageStore img;
	protected AnimationStore ani;
	private Machine machine;
	public static final int POSITION_1 = 0;
	public static final int POSITION_2 = 1;
	public static final int POSITION_3 = 2;
	public static final int POSITION_4 = 3;
	
	public Part(int iD, ImageStore UIImg, AnimationStore AnimImg, int... posPoss){
		//this.machine = machine;
		this.iD = iD;
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
	public int getID(){
		return this.iD;
	}
	public Machine getMachine(){
		return machine;
	}
	public Image getImage(){
		return img.getImage();
	}
	public Animation getAnimation(){
		return ani.getAnimation();
	}
	public ImageStore getImageStore(){
		return img;
	}
	public AnimationStore getAnimationStore(){
		return  ani;
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
	
	public abstract Part clone(int position);
}
