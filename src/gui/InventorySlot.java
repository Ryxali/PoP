package gui;

import org.newdawn.slick.Graphics;

import machines.Machine;
import machines.Part;
import image.ImageStore;
import button.StandardButton;

public class InventorySlot extends StandardButton{
	public InventorySlot(int x, int y, ImageStore slotImg, ImageStore idleImg, ImageStore hoverImg,
			ImageStore pressedImg) {
		super(x, y, idleImg, hoverImg, pressedImg);
		this.slotImg = slotImg;
	}
	private ImageStore slotImg;
	private boolean isEmpty = true;
	private Machine machine;
	private Part part;
	
	public boolean isEmpty(){
		return isEmpty;
	}
	public void update(){
		if(isClicked()){
			isEmpty = false;
		}else{
			isEmpty = true;
		}
	}
	@Override
	public void draw(Graphics g){
		slotImg.draw(x, y);
		getStoredImage().draw(x, y);
		if(machine != null){
			machine.draw(x, y, g);
		}
		if(part != null){
			part.draw(x, y, g);
		}
	}
	public void put(Machine machine){
		this.machine = machine;
	}
	public void put(Part part){
		this.part = part;
	}
	public Object take(){
		if(part != null){
			return part;
		}else if(machine != null){
			return machine;
		}
		return null;
	}
	public Part takePart(){
		if(part != null){
			return part;
		}else{
			return null;
		}
	}
	public Machine takeMachine(){
		if(machine != null){
			return machine;
		}else{ 
			return null;
		}
	}
}
