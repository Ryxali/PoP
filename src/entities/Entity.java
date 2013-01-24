package entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;

import priceofprogress.Game;
import priceofprogress.Physics;
import image.AnimatedImage;
import image.ImageStore;

public abstract class Entity implements Physics{
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
	private String curAnimation;
	private double x;
	private double y;
	private double weight;
	public Entity(int x, int y, HashMap<String, AnimatedImage> animImgs){
		this.x = x;
		this.y = y;
		weight = setWeight();
		this.animImgs = animImgs;
	}
	public void setCurrentAnimation(String key){
		curAnimation = key;
	}
	protected abstract void setAnimations();
	
	protected abstract double setWeight();
	
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
	public void doPhysics(){
		
	}
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
