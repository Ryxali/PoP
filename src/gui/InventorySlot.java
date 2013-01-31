package gui;

import button.StandardButton;

public class InventorySlot {
	private StandardButton button;
	private boolean isEmpty;
	public InventorySlot(StandardButton button){
		this.button = button;
	}
	public StandardButton getButton(){
		return button;
	}
	
}
