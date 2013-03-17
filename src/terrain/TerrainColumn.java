package terrain;

import org.newdawn.slick.Graphics;

import priceofprogress.Game;

import image.DrawableG;

public class TerrainColumn{
	private Block[] column = new Block[20];
	private int index = 0;
	public TerrainColumn(){
		for (int i = 0; i < column.length; i++) {
			column[i] = Blocks.AIR_BLOCK.getBlock();
		}
	}
	public void add(Block block){
		System.out.println(index);
		if(column.length <= index){
			Block[] temp = column;
			column = new Block[index+20];
			for (int i = 0; i < column.length; i++) {
				column[i] = Blocks.AIR_BLOCK.getBlock();
			}
			index = 0;
			addValues(temp);
		}
		column[index] = block;
		index++;
	}
	
	public void addValues(Block[] blocks){
		for (int i = 0; i < blocks.length; i++) {
			add(blocks[i]);
		}
	}
	
	public Block get(int index){
		return column[index];
	}

	public void draw(int x, int baseY, Graphics g) {
		//System.out.println(" : " + baseY);
		for (int y = 0; y < column.length; y++) {
			//System.out.println(y);
			get(y).draw(x, (int)((baseY + y*Blocks.getBlockYDimension())), g);
		}
	}

	public int size() {
		return column.length;
	}
}
