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
	private ArrayList<Integer> sky = new ArrayList<Integer>();
	private ImageCluster skyPalette;
	private double skyX = 0;
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
		buildClouds(name);
		buildFar(name);
		buildMid(name);
		buildShort(name);
	}
	
	private void buildClouds(String name){
		//clouds = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.CLOUDS);
	}
	
	private void buildFar(String name){
		Random rand = new Random();
		int j = 0;
		for(int i = 0; i < 2200; j++){
			farPalette = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.FAR);
			farDrop.add(rand.nextInt(farPalette.getImages().length));
			i += farPalette.get(farDrop.get(j)).getImage().getWidth();
		}
		//farDrop = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.FAR).getImages();
	}
	
	private void buildMid(String name){
		Random rand = new Random();
		int j = 0;
		for(int i = 0; i < Game.getGameContainer().getWidth(); j++){
			midPalette = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.MID);
			midDrop.add(rand.nextInt(midPalette.getImages().length));
			i += midPalette.get(midDrop.get(j)).getImage().getWidth();
		}
	}
	
	private void buildShort(String name){
		Random rand = new Random();
		int j = 0;
		for(int i = 0; i < Game.getGameContainer().getWidth(); j++){
			closePalette = ImageCluster.getClusterByNameAndUsage(name, ImageCluster.SHORT);
			closeDrop.add(rand.nextInt(closePalette.getImages().length));
			i += closePalette.get(closeDrop.get(j)).getImage().getWidth();
		}
	}
	@Override
	public void draw(Graphics g) {
		drawTiled(farPalette, farDrop, farX);
		drawTiled(midPalette, midDrop, midX);
		drawTiled(closePalette, closeDrop, closeX);
	}
	
	private void drawTiled(ImageCluster cluster, ArrayList<Integer> seed, double baseX){
		int imgWidths = 0;
		for(int i = 0; i < seed.size(); i++){
			cluster.get(seed.get(i)).draw((int) (imgWidths + baseX), 0);
			imgWidths += cluster.get(seed.get(i)).getImage().getWidth();
		}
	}
	public void update(Input input){
		if(Characters.MAIN_CHAR.getCharacter().getX() < (double)Game.getGameContainer().getWidth()*0.25){
			moveSlices(Game.getGameContainer().getWidth()*0.25-Characters.MAIN_CHAR.getCharacter().getX());
			Characters.MAIN_CHAR.getCharacter().setX(Game.getGameContainer().getWidth()*0.25);
		}else if(Game.getGameContainer().getWidth()*0.75 < Characters.MAIN_CHAR.getCharacter().getX()){
			moveSlices(Game.getGameContainer().getWidth()*0.75-Characters.MAIN_CHAR.getCharacter().getX());
			Characters.MAIN_CHAR.getCharacter().setX(Game.getGameContainer().getWidth()*0.75);
		}
		//TODO add and remove slices as the game progresses forward();
	}
	private void moveSlices(double amount){
		farX += amount/3.0;
		midX += amount/1.5;
		closeX += amount;
	}
	
	private ImageStore getRandomTile(String name, String usage){
		Random rand = new Random();
		ImageCluster cluster = ImageCluster.getClusterByNameAndUsage(name, usage);
		return cluster.get(rand.nextInt(cluster.getImages().length));
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		
		
	}
	
	private static ArrayList<ImageStore> getImages(ImageStore... imgs){
		ArrayList<ImageStore> temp = new ArrayList<ImageStore>();
		for (int i = 0; i < imgs.length; i++) {
			temp.add(imgs[i]);
		}
		return temp;
	}

}
