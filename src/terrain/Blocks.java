package terrain;

import priceofprogress.Game;
import image.ImageStore;

public enum Blocks {
	AIR_BLOCK(new EmptyBlock(0)),
	EARTH_BLOCK(new StaticBlock(1, ImageStore.BLOCK_EARTH)),
	GRASS_BLOCK(new StaticBlock(2, ImageStore.BLOCK_GRASS)),
	GRAVEL_BLOCK(new StaticBlock(3, ImageStore.BLOCK_GRAVEL)),
	ROCK_BLOCK(new StaticBlock(4, ImageStore.BLOCK_ROCK));
	private static final float BLOCK_DIMENSION = 64f;
	private Blocks(Block block){
		this.block = block;
	}
	
	private final Block block;
	
	/**
	 * @return
	 */
	public Block getBlock(){
		return this.block;
	}
	
	public static Blocks getByID(int id){
		Blocks[] temp = values();
		for (int i = 0; i < temp.length; i++) {
			if(temp[i].getBlock().getID() == id){
				return temp[i];
			}
		}
		return AIR_BLOCK;
	}
	
	public static float getBlockYDimension(){
		return BLOCK_DIMENSION*Game.getHeightScale();
	}
	public static float getBlockXDimension(){
		return BLOCK_DIMENSION*Game.getWidthScale();
	}
	
}
