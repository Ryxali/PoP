package priceofprogress;

import image.ImageStore;

import java.util.Arrays;

import org.newdawn.slick.Graphics;

public abstract class Part {
	private int[] possPos;
	private ImageStore img;
	public Part(ImageStore img, int possPos1, int... posPoss){
		this.img = img;
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
	public abstract void draw(Graphics g);
	
	
	
}
