package terrain;

import image.ImageStore;
/**
 * Contains all block types and their constructors.
 * 
 * @author Lukas
 *
 */
public enum Blocks {
	AIR_BLOCK(new VoidBlock(0, 0, 0), 0),
	EARTH_BLOCK(new StaticBlock(1, 0, 0, ImageStore.BLOCK_EARTH), 1),
	GRASS_BLOCK(new StaticBlock(2, 0, 0, ImageStore.BLOCK_GRASS), 2),
	GRAVEL_BLOCK(new StaticBlock(3, 0, 0, ImageStore.BLOCK_GRAVEL), 3),
	ROCK_BLOCK(new StaticBlock(4, 0, 0, ImageStore.BLOCK_ROCK), 4);
	
	/**
	 * Creates a new enum with a specified block and id.
	 * 
	 * @param block the block of the new enum.
	 * @param id the id of the new enum.
	 */
	private Blocks(Block block, int id){
		this.block = block;
		this.id = id;
	}
	/** The block belonging to this enum. */
	private Block block;
	/** The id of this block.
	 * Determines what visual kind of block it is. */
	private int id;
	/**
	 * Fetch the block belonging to this enum.
	 * 
	 * @return the block belonging to this enum.
	 */
	public Block getBlock(){
		return this.block;
	}
	/**
	 * Fetch the id belonging to this enum.
	 * 
	 * @return the id belonging to this enum.
	 */
	public int getId(){
		return this.id;
	}
	/**
	 * Clone the block belonging to this enum.
	 * 
	 * @param x the horizontal position of the new block.
	 * @param y the horizontal position of the new block.
	 * @return a new block with the desired positions.
	 */
	public Block clone(int x, int y){
		return this.getBlock().clone(x, y);
	}
	
}
