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
	
	public ArrayList<Block> getRow(int colN){
		return col.get(colN);
	}
	
	public Block getBlock(ArrayList<Block> row, int rowN){
		return row.get(rowN);
	}
	
	public Block getBlock(int colN, int rowN){
		return col.get(colN).get(rowN);
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
