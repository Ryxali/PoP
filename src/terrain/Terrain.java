package terrain;

import java.util.ArrayList;
import machines.Machine;


public class Terrain {
	private static Terrain curMap;

	public static Terrain get() {
		if (curMap != null) {
			return curMap;
		} else {
			curMap = new Terrain();
			return curMap;
		}
	}

	private double baseX = 0;
	private double baseY = 0;
	
	private ArrayList<ArrayList<Block>> col;
	private ArrayList<Machine> machines;
	public Block getBlockOnScreen(int x, int y) {
		return getBlock((int) ((x - baseX) / Blocks.EARTH_BLOCK.getBlock()
				.getImage().getImage().getWidth()),
				(int) ((y - baseY) / Blocks.EARTH_BLOCK.getBlock().getImage()
						.getImage().getHeight()));
	}
	public Terrain() {
		col = new ArrayList<ArrayList<Block>>();
		machines = new ArrayList<Machine>();
	}
	
	
	public Block getTopBlock(int blockX){
		for (int i = 0; i < col.get(blockX/Blocks.EARTH_BLOCK.getBlock().getImage().getImage().getWidth()).size(); i++) {
			if(col.get(blockX/Blocks.EARTH_BLOCK.getBlock().getImage().getImage().getWidth()).get(i).getID() != Blocks.GRAVEL_BLOCK.getId()){
				return col.get(blockX/Blocks.EARTH_BLOCK.getBlock().getImage().getImage().getWidth()).get(i);
			}
		}
		return null;
	}

	public void draw() {
		// System.out.println("Derr");
		for (int y = 0; y < size(); y++) {
			for (int x = 0; x < rowSize(y); x++) {
				getBlock(x, y).getImage().draw(
						(int) (baseX + getBlock(x, y).getXPos()),
						(int) (baseY + getBlock(x, y).getYPos()));
				// System.out.println("x: "+Terrain.get().getBlock(x,
				// y).getXPos()+" y: "+ Terrain.get().getBlock(x, y).getYPos());
			}
		}
	}
	public void addBlockRow(ArrayList<Block> blockRow) {
		col.add(blockRow);

	}
	public void addMachine(Machine machine){
		machines.add(machine);
	}
	
	
	public ArrayList<Block> getRow(int rowN) {
		return col.get(rowN);
	}

	public Block getBlock(int colN, ArrayList<Block> row) {
		return row.get(colN);
	}

	/**
	 * Fetch the block at a specified position.
	 * 
	 * @param colN
	 *            the column of the block (block x should be colN*64).
	 * @param rowN
	 *            the row of the block (block y should be 1200-rowN*64).
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
	 * @param row
	 *            the row whose size shall be measured.
	 * @return the number of blocks in the row.
	 */
	public int rowSize(int row) {
		return col.get(row).size();
	}

	public void move(double d, double e) {
		baseX += d;
		baseY += e;
	}
}
