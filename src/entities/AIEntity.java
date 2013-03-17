package entities;

import image.AnimatedImage;

import java.util.HashMap;

import org.newdawn.slick.Input;

public abstract class AIEntity extends Entity{
	
	private int moveTargetX;
	private double moveSpeed;
	private boolean isAtTarget = true;

	public AIEntity(int x, int y, HashMap<String, AnimatedImage> animImgs, double moveSpeed) {
		super(x, y, animImgs);
		this.moveSpeed = moveSpeed;
	}
	
	public abstract void logicAI();
	
	public boolean isAtTarget(){
		return isAtTarget;
	}
	@Override
	protected void update(Input input){
		if(moveTargetX < getX()){
			moveEntity(-moveSpeed, 0);
		}else if(getX() < moveTargetX){
			moveEntity(moveSpeed, 0);
		}else{
			isAtTarget = true;
		}
		AIUpdate();
	}
	public abstract void AIUpdate();
	
	public void moveTo(int targetX){
		moveTargetX = targetX;
		isAtTarget = false;
	}
	public void jump(int y){
		
	}
	public void jump(double force){
		
	}
}
