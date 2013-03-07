package terrain;

import java.util.ArrayList;

import machines.Machine;

/**
 * Stores the current map and allows calibration of it.
 * 
 * @author Lukas
 *
 */
public class Terrain {
	/** An object containing this Terrain. */
	private static Terrain curMap;
	/**
	 * Makes a new object containing this Terrain or returns the one that already exist.
	 * 
	 * @return an object containing this Terrain.
	 */
	public static Terrain get() {
		if (curMap != null) {
			return curMap;
		} else {
			curMap = new Terrain();
			return curMap;
		}
	}
	/** The modification of the horizontal view. */
	private double baseX = 0;
	/** The modification of the vertical view. */
	private double baseY = 0;
	/** A list containing all the blocks in the active map */
	private ArrayList<ArrayList<Block>> col;
	/** A list containing all the machines in the active map */
	private ArrayList<Machine> machines;
	/**
	 * Converts screen position values to acquire a block in the list
	 * at positions based on the screen positions.
	 * 
	 * @param x the horizontal position to be converted.
	 * @param y the verical position to be converted.
	 * @return the block with the specified positions.
	 */
	public Block getBlockOnScreen(int x, int y) {
		return getBlock((int) ((x - baseX) / Blocks.EARTH_BLOCK.getBlock()
				.getImage().getImage().getWidth()),
				(int) ((y - baseY) / Blocks.EARTH_BLOCK.getBlock().getImage()
						.getImage().getHeight()));
	}
	/**
	 * Starts this Terrain.
	 */
	public Terrain() {
		col = new ArrayList<ArrayList<Block>>();
		machines = new ArrayList<Machine>();
	}
	/**
	 * 
	 * 
	 * @param blockX
	 * @return the block at the top position in the lists.
	 */
	public Block getTopBlock(int blockX){
		for (int i = 0; i < col.get(blockX/Blocks.EARTH_BLOCK.getBlock().getImage().getImage().getWidth()).size(); i++) {
			if(col.get(blockX/Blocks.EARTH_BLOCK.getBlock().getImage().getImage().getWidth()).get(i).getID() != Blocks.GRAVEL_BLOCK.getId()){
				return col.get(blockX/Blocks.EARTH_BLOCK.getBlock().getImage().getImage().getWidth()).get(i);
			}
		}
		return null;
	}
	/**
	 * Draws all blocks in the current map.
	 */
	public void draw() {
		for (int y = 0; y < size(); y++) {
			for (int x = 0; x < rowSize(y); x++) {
				if(getBlock(x, y).getImage() != null){
					getBlock(x, y).getImage().draw(
							(int) (baseX + getBlock(x, y).getXPos()),
							(int) (baseY + getBlock(x, y).getYPos()));
				}
			}
		}
	}
	/**
	 * Adds a row of blocks to the current map.
	 * 
	 * @param blockRow the row of blocks to be added.
	 */
	public void addBlockRow(ArrayList<Block> blockRow) {
		col.add(blockRow);
		
	}
	/**
	 * Adds a machine to the current map.
	 * 
	 * @param machine the machine to be added.
	 */
	public void addMachine(Machine machine){
		machines.add(machine);
	}
	/**
	 * Fetch a row of blocks from the map.
	 * 
	 * @param rowN the index of the row to be fetched.
	 * @return the row with the specified index.
	 */
	public ArrayList<Block> getRow(int rowN) {
		return col.get(rowN);
	}
	/**
	 * Changes a specified block in the list to a new one.
	 * 
	 * @param colN the column of the block to be changed.
	 * @param row the row of the block to be changed.
	 * @param newBlock the new block that we shall become.
	 */
	public void setBlock(int colN, int row, Block newBlock){
		//col.get(row).remove(colN);
		ArrayList<Block> tempBlocks = col.get(row);
		tempBlocks.set(colN, newBlock);
		col.set(row, tempBlocks);
		//System.out.println("ID: "+col.get(row).get(colN).getID()+" image: "+col.get(row).get(colN).getImage());
	}
	/**
	 * Fetch a block with a specified index in a specified row.
	 * 
	 * @param colN the column the block is in.
	 * @param row the row the block is in.
	 * @return
	 */
	public Block getBlock(int colN, ArrayList<Block> row) {
		return row.get(colN);
	}
	/**
	 * Fetch the block at a specified position in the map.
	 * 
	 * @param colN the column of the block (block x should be colN*64).
	 * @param rowN the row of the block (block y should be 1200-rowN*64).
	 * @return the block at the specified position.
	 */
	public Block getBlock(int rowN, int colN){
		return col.get(colN).get(rowN);
	}
	/**
	 * Fetch the block at a specified index
	 * 
	 * @param index the number of the machine in the list.
	 */
	public Machine getMachine(int index){
		return machines.get(index);
	}
	/**
	 * Returns the number rows of blocks in the current map.
	 * 
	 * @return the number of rows of the current map.
	 */
	public int size() {
		return col.size();
	}
	/**
	 * Returns the number machines in the machine-list.
	 * 
	 * @return the number machines in the machine-list.
	 */
	public int mashinesSize(){
		return machines.size();
	}
	/**
	 * Returns the number of blocks on a specified row in the current map.
	 * 
	 * @param row the row whose size shall be measured.
	 * @return the number of blocks in the row.
	 */
	public int rowSize(int row) {
		return col.get(row).size();
	}
	/**
	 * Modifies the values that determines where the screen is looking
	 * (the values change where the objects of the map are drawn).
	 * 
	 * @param d the horizontal modification of the screen view.
	 * @param e the vertalic modification of the screen view.
	 */
	public void move(double d, double e) {
		baseX += d;
		baseY += e;
	}
}
