package image;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class BackDrop implements Drawable{
	
	private ArrayList<ImageStore> clouds = new ArrayList<ImageStore>();
	
	private ArrayList<ImageStore> farDrop = new ArrayList<ImageStore>();
	private ArrayList<ImageStore> midDrop = new ArrayList<ImageStore>();
	private ArrayList<ImageStore> closeDrop = new ArrayList<ImageStore>();
	
	private static BackDrop backdrop;
	
	public static BackDrop get(){
		if(backdrop == null){
			backdrop = new BackDrop();
		}
		return backdrop;
	}
	
	private BackDrop(){
	}
	
	public void rebuild(String name){
		buildClouds(name);
		buildFar(name);
		buildMid(name);
		buildShort(name);
	}
	
	private void buildClouds(String name){
		
	}
	
	private void buildFar(String name){
		
	}
	
	private void buildMid(String name){
		
	}
	
	private void buildShort(String name){
		
	}
	@Override
	public void draw(Graphics g) {
		
		
	}
	public void update(Input input){
		
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	private static ArrayList<ImageStore> getImages(ImageStore... imgs){
		ArrayList<ImageStore> temp = new ArrayList<ImageStore>();
		for (int i = 0; i < imgs.length; i++) {
			temp.add(imgs[i]);
		}
		return temp;
	}

}
