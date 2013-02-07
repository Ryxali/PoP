package gui;

import machines.Machine;
import machines.Part;
import image.ImageStore;
import button.StandardButton;

public class InventorySlot extends StandardButton{
	public InventorySlot(int x, int y, ImageStore idleImg, ImageStore hoverImg,
			ImageStore pressedImg) {
		super(x, y, idleImg, hoverImg, pressedImg);
	}
	
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
