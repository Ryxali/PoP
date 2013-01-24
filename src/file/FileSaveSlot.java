package file;



public enum FileSaveSlot {
	SLOT_1(null, "saves/slot_1", "Slot 1"),
	SLOT_2(null, "saves/slot_2", "Slot 2"),
	SLOT_3(null, "saves/slot_3", "Slot 3"),
	SLOT_4(null, "saves/slot_4", "Slot 4");
	
	private String filePath;
	private PPWDataLoader data;
	private String name;
	private FileSaveSlot(PPWDataLoader data, String filePath, String name){
		this.filePath = filePath;
		this.data = data;
		this.name = name;
	}
	
	public void loadData(){
		//TODO get that data loader done
	}
	
	public PPWDataLoader getData(){
		return data;
	}
	
	public FileSaveSlot getSlotByName(String name){
		FileSaveSlot [] s = values();
		for(int i = 0; i < s.length; i++){
			if(s[i].name.equals(name)){
				return s[i];
			}
		}
		return null;
	}
}
