package file;


public enum FileSaveSlot {
	SLOT_1(null, "saves/slot_1", "Slot 1"),
	SLOT_2(null, "saves/slot_2", "Slot 2"),
	SLOT_3(null, "saves/slot_3", "Slot 3"),
	SLOT_4(null, "saves/slot_4", "Slot 4");
	
	private String filePath;
	private PPWData data;
	private String name;
	private FileSaveSlot(PPWData data, String filePath, String name){
		this.filePath = filePath;
		this.data = data;
		this.name = name;
	}
	
	public void loadData(){
		//TODO get that data loader done
	}
	
	public PPWData getData(){
		return data;
	}
}
