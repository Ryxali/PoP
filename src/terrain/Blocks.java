package terrain;

import image.ImageStore;

public enum Blocks {
	EARTH_BLOCK(new StaticBlock(1, 0, 0, ImageStore.BLOCK_EARTH), 1),
	GRASS_BLOCK(new StaticBlock(2, 0, 0, ImageStore.BLOCK_GRASS), 2),
	GRAVEL_BLOCK(new StaticBlock(3, 0, 0, ImageStore.BLOCK_GRAVEL), 3),
	ROCK_BLOCK(new StaticBlock(4, 0, 0, ImageStore.BLOCK_ROCK), 4);
	
	private Blocks(Block block, int id){
		this.block = block;
		this.id = id;
	}
	
	private Block block;
	
	private int id;
	
	
	public Block getBlock(){
		return this.block;
	}
	
	public int getId(){
		return this.id;
	}
	public Block clone(int x, int y){
		return this.getBlock().clone(x, y);
	}
	
}
