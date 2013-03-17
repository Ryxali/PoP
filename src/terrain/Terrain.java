package terrain;

import image.DrawableG;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import priceofprogress.Game;

public class Terrain implements DrawableG{
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
	
	private TerrainColumns columns = new TerrainColumns();
	
	public TerrainColumns getColumns(){
		return columns;
	}
	
	public Block getBlockOnScreen(int x, int y) {
		return getColumnOnScreen(x).get((int) ((int)(y-baseY)/Blocks.getBlockYDimension()));
	}
	
	private TerrainColumn getColumnOnScreen(int screenX){
		return columns.get((int) (((screenX-baseX)/Blocks.getBlockXDimension())));
	}
	
	public int getTopBlockY(int blockX){
		TerrainColumn column = getColumnOnScreen(blockX);
		for(int y = 0; y < column.size();y++){
			if(column.get(y).getID() != Blocks.AIR_BLOCK.getBlock().getID()){
				//System.out.println(y);
				//System.out.println(y*Blocks.getBlockYDimension()*Game.getHeightScale());
				return (int) ((columns.getY(y))*Game.getHeightScale());
			}else{
			}
		}
		return 0;
	}

	public void move(double d, double e) {
		baseX += d;
		baseY += e;
	}
	@Override
	public void draw(Graphics g) {
		System.out.println(baseX + " ; " + baseY);
		columns.draw((int)baseX, (int)
				baseY, g);
	}
}
