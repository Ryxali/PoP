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
}
