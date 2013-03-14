package entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;

import physics.Physics;
import physics.Weight;
import priceofprogress.Game;
import terrain.Terrain;
import image.AnimatedImage;
import image.ImageStore;

public abstract class Entity implements Physics, Weight{
	/**
	 * Keys:
	 * <ul>
	 * <li>Idle</li>
	 * <li>WalkRight</li>
	 * <li>WalkLeft</li>
	 * <li>Jump</li>
	 * <li>Interract</li>
	 */
	private HashMap<String, AnimatedImage> animImgs;
	private String curAnimation = "";
	private double x;
	private double y;
	
	private boolean falling = false;
	private double yForce = 0;
	
	
	public Entity(int x, int y, HashMap<String, AnimatedImage> animImgs){
		this.x = x;
		this.y = y;
		this.animImgs = animImgs;
	}
	public void setCurrentAnimation(String key){
		curAnimation = key;
		animImgs.get(curAnimation).setCurrentFrame(0);
	}
	
	protected abstract void setAnimations();
	
	public void draw(){
		try{
			animImgs.get(curAnimation).draw(getX(), getY());
		}catch(NullPointerException e){
			ImageStore.DEFAULT.draw(getX(), getY());
		}
	}
	public String getCurrentAnimation(){
		return curAnimation;
	}
	
	public abstract void update(Input input);
	public HashMap<String, AnimatedImage> getAnimations(){
		
		return animImgs;
	}
	public void changeYForce(double addForce){
		yForce =+ addForce;
	}
	public void doPhysics(){
		doGravity();
	}
	public void setFalling(boolean set){
		falling = set;
	}
	public boolean isFalling(){
		return falling;
	}
	
	private void doGravity(){
		if(falling){
			//System.out.println(yForce);
			yForce -= getMass()*(4)*Game.getDelta()/100d;//(9.81*9.81)
			if(y - yForce * Game.getDelta()/1000d <= 600){//Terrain.get().getTopBlock((int)x).getYPos()
				y -= yForce * Game.getDelta()/1000d;
			}else{
				falling = false;
				y =  600;//Terrain.get().getTopBlock((int)x).getYPos()
			}
		}
	}
	
	public abstract double getMass();
	
	public void moveEntity(double speedX, double speedY){
		this.x += speedX*Game.getDelta()/1000d;
		this.y += speedY*Game.getDelta()/1000d;
	}
	
	public int getX(){
		return (int) x;
	}
	public int getY(){
		return (int) y;
	}
	public void setX(double d){
		this.x = d;
	}
	public void setY(double y){
		this.y = y;
	}
}
