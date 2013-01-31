package gui;

import image.ImageStore;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import button.StandardButton;

import priceofprogress.Game;

public class Inventory extends Interface{
	
	private final ArrayList<InventorySlot> slots;
	private static Inventory inv;
	
	private Inventory(){
		slots = new ArrayList<InventorySlot>();
		int x = 8;
		for(int i = 1; i < x; i++){
			slots.add(new InventorySlot((int) (i*1920*Game.getWidthScale()/(double)x),
					(int)(1200d*Game.getHeightScale()-ImageStore.INVENTORY_FRAME_SLOT.getImage().getHeight()),
					ImageStore.INVENTORY_FRAME_SLOT,
					ImageStore.INVENTORY_FRAME_SLOT,
					ImageStore.INVENTORY_FRAME_SLOT));
		}
	}
	public static Inventory get(){
		if(inv == null){
			inv = new Inventory();
		}
		return inv;
	}
	@Override
	public void draw() {
		for(int i = 0; i < slots.size(); i++){
			slots.get(i).getButton().draw();
		}
	}

	@Override
	public void update(Input input) {
		/*for (int i = 0; i < slots.size(); i++) {
			slots.get(i).getButton().buttonStateCheck(input);
			slots.get(i).update();
			if(!slots.get(i).isEmpty()){
				
			}
		}*/
		
	}
	public Object checkPickups(){
		for (int i = 0; i < slots.size(); i++) {
			if(slots.get(i).isEmpty()){
				return slots.get(i).take();
			}
		}
		return 0;
	}

}
