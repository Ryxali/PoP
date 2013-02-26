package file;


/**
 * I am hesitant this will even be used in the end.
 * @author Pani
 *
 */
public class FileSaveSlot {
	
	private String filePath;
	private PPWDataLoader lastMapData;
	private String name;
	private FileSaveSlot(String filePath, String name){
		this.filePath = filePath;
		this.name = name;
	}
	
	public void loadData(){
		//TODO get that data loader done
	}
	
	public PPWDataLoader getData(){
		return lastMapData;
	}
	
	/*public FileSaveSlot getSlotByName(String name){
		FileSaveSlot [] s = values();
		for(int i = 0; i < s.length; i++){
			if(s[i].name.equals(name)){
				return s[i];
			}
		}
		return null;
	}*/
}
