package image;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import entities.Characters;

import priceofprogress.Game;

public class BackDrop implements Drawable{
	
	private ArrayList<Integer> clouds = new ArrayList<Integer>();
	private ImageCluster cloudPalette;
	private ImageStore sky;
	private ArrayList<Integer> farDrop = new ArrayList<Integer>();
	private ImageCluster farPalette;
	private double farX = 0;
	private ArrayList<Integer> midDrop = new ArrayList<Integer>();
	private ImageCluster midPalette;
	private double midX = 0;
	private ArrayList<Integer> closeDrop = new ArrayList<Integer>();
	private ImageCluster closePalette;
	private double closeX = 0;
	
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
		//buildClouds(name);
		buildSky(name);
		buildFar(name);
		buildMid(name);
		buildShort(name);
	}
	
	private void buildSky(String name){
		Random rand = new Random();
		int j = 0;
		sky = ImageStore.FOREST_BACKDROP_SKY;
		//for(int i = 0; i < Game.getGameContainer().getWidth(); j++){
			
			//sky.add(rand.nextInt(skyPalette.getImages().length));
			//i += skyPalette.get(sky.get(j)).getImage().getWidth();
		//}
	}
	
	private void buildClouds(String name){
		//clouds = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.CLOUDS);
	}
	
	private void buildFar(String name){
		Random rand = new Random();
		int j = 0;
		farPalette = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.FAR);
		for(int i = 0; i < 15; i++){
			
			farDrop.add(rand.nextInt(farPalette.getImages().length));
		//	i += farPalette.get(farDrop.get(j)).getImage().getWidth();
		}
		//farDrop = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.FAR).getImages();
	}
	
	private void buildMid(String name){
		Random rand = new Random();
		int j = 0;
		midPalette = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.MID);
		for(int i = 0; i < 15; i++){
			
			midDrop.add(rand.nextInt(midPalette.getImages().length));
		//	i += midPalette.get(midDrop.get(j)).getImage().getWidth();
		}
	}
	
	private void buildShort(String name){
		Random rand = new Random();
		int j = 0;
		closePalette = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.SHORT);
		for(int i = 0; i < 15; i++){
			closeDrop.add(rand.nextInt(closePalette.getImages().length));
		//	i += closePalette.get(closeDrop.get(j)).getImage().getWidth();
		}
	}
	@Override
	public void draw() {
		sky.draw(0, 0);
		ImageStore.ASTRAL_SUN.draw(400, 100);
		drawTiled(farPalette, farDrop, farX);
		drawTiled(midPalette, midDrop, midX);
		drawTiled(closePalette, closeDrop, closeX);
		
	}
	
	private void drawTiled(ImageCluster cluster, ArrayList<Integer> seed, double baseX){
		int imgWidths = 0;
		for(int i = 0; i < seed.size(); i++){
			if(imgWidths + baseX + cluster.get(0).getImage().getWidth() > 0){
				cluster.get(seed.get(i)).draw((int) (imgWidths + baseX), 0);
			}
			imgWidths += cluster.get(seed.get(i)).getImage().getWidth();
		}
	}
	public void update(Input input){
		if(Characters.MAIN_CHAR.getCharacter().getX() < (double)Game.getGameContainer().getWidth()*0.25){
			moveSlices((double)Game.getGameContainer().getWidth()*0.25-Characters.MAIN_CHAR.getCharacter().getX());
			Characters.MAIN_CHAR.getCharacter().setX(Game.getGameContainer().getWidth()*0.25);
		}else if((double)Game.getGameContainer().getWidth()*0.75 < Characters.MAIN_CHAR.getCharacter().getX()){
			moveSlices((double)Game.getGameContainer().getWidth()*0.75-Characters.MAIN_CHAR.getCharacter().getX());
			Characters.MAIN_CHAR.getCharacter().setX(Game.getGameContainer().getWidth()*0.75);
		}
		//TODO add and remove slices as the game progresses forward();
	}
	private void moveSlices(double amount){
		farX += amount/3.0;
		sliceBoundsCheck(farX, farDrop, farPalette);
		midX += amount/1.5;
		sliceBoundsCheck(midX, midDrop, midPalette);
		closeX += amount;
		sliceBoundsCheck(closeX, closeDrop, closePalette);
	}
	
	private void sliceBoundsCheck(double x, ArrayList<Integer> seedList, ImageCluster seedSrc){
		//System.out.println(x);
		if(-x+Game.getGameContainer().getWidth() > seedList.size()*seedSrc.get(0).getImage().getWidth()){
			//System.out.println("Slice ADDED!");
			Random r = new Random();
			seedList.add(r.nextInt(seedSrc.getImages().length));
		}
	}
	
	private ImageStore getRandomTile(String name, String usage){
		Random rand = new Random();
		ImageCluster cluster = ImageCluster.getClusterByNameAndUsage(name, usage);
		return cluster.get(rand.nextInt(cluster.getImages().length));
	}
	
	private static ArrayList<ImageStore> getImages(ImageStore... imgs){
		ArrayList<ImageStore> temp = new ArrayList<ImageStore>();
		for (int i = 0; i < imgs.length; i++) {
			temp.add(imgs[i]);
		}
		return temp;
	}

}
