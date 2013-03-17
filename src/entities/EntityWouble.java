package entities;

import image.AnimatedImage;

import java.util.HashMap;

import org.newdawn.slick.Input;

public class EntityWouble extends AIEntity{
	private boolean alternate = true;

	public EntityWouble(int x, int y, HashMap<String, AnimatedImage> animImgs) {
		super(x, y, animImgs, 35);
	}

	@Override
	public void logicAI() {
		
	}

	@Override
	public void AIUpdate() {
		if(isAtTarget()){
			if(alternate){
				moveTo(getX() + 300);
				setCurrentAnimation(Characters.WALK_RIGHT);
				alternate = false;
			}else{
				alternate = true;
				moveTo(getX() - 300);
				setCurrentAnimation(Characters.WALK_LEFT);
			}
		}
	}

	@Override
	protected void setAnimations() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
