package terrain;

import java.util.ArrayList;

public class Terrain {
	private static Terrain curMap;
	public static Terrain get(){
		if(curMap != null){
			return curMap;
		}else{
			curMap = new Terrain();
			return curMap;
		}
	}
	private ArrayList<ArrayList<Block>> col;
	public Terrain(){
		col = new ArrayList<ArrayList<Block>>();
	}
	
	public void addBlockRow(ArrayList<Block> blockRow){
		col.add(blockRow);
	}
	
	public ArrayList<Block> getRow(int rowN){
		return col.get(rowN);
	}
	
	public Block getBlock(int colN, ArrayList<Block> row){
		return row.get(colN);
	}
	/**
	 * Fetch the block at a specified position.
	 * 
	 * @param colN the column of the block (block x should be colN*64).
	 * @param rowN the row of the block (block y should be 1200-rowN*64).
	 * @return the block at the specified position.
	 */
	public Block getBlock(int colN, int rowN){
		return col.get(rowN).get(colN);
	}
	/**
	 * Returns the number rows of blocks in the current map.
	 * 
	 * @return the number of rows of the current map.
	 */
	public int size(){
		return col.size();
	}
	/**
	 * Returns the number of blocks on a specified row in the current map.
	 * 
	 * @param row the row whose size shall be measured.
	 * @return the number of blocks in the row.
	 */
	public int rowSize(int row){
		return col.get(row).size();
	}
}
