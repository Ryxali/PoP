package terrain;

import org.newdawn.slick.Graphics;

import priceofprogress.Game;

import image.DrawableXYG;

public class TerrainColumns{
	private TerrainColumn[] columnColumns = new TerrainColumn[20];
	
	public TerrainColumns() {
		for (int i = 0; i < columnColumns.length; i++) {
			columnColumns[i] = new TerrainColumn();
		}
	}
	
	public double getX(int cIndex){
		return cIndex*Blocks.getBlockXDimension();
	}
	
	public double getY(int rIndex){
		return rIndex*Blocks.getBlockYDimension();
	}
	

	public TerrainColumn get(int index) {
		if (columnColumns.length <= index) {
			TerrainColumn[] temp = columnColumns;
			columnColumns = new TerrainColumn[temp.length + 20];
			for (int i = 0; i < temp.length; i++) {
				columnColumns[i] = temp[i];
			}
			for (int i = index; i < columnColumns.length; i++) {
				columnColumns[i] = new TerrainColumn();
			}
		}
		//System.out.println(columnRows[index]);
		return columnColumns[index];
	}

	public void draw(int baseX, int baseY, Graphics g) {
		for (int x = (int) (-baseX/Blocks.getBlockXDimension()) ; x < (-baseX+1920)/Blocks.getBlockXDimension() && x < columnColumns.length; x++) {
			if(x >= 0){
				columnColumns[x].draw((int)((baseX + getX(x))), baseY, g);
			}
		}
	}

}
