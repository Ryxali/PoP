package machines;

import image.ImageStore;

import java.util.Arrays;

public enum Component{
	//TODO add object to represent these units
	VACCUM("Vaccum Unit", new PartVaccum(ImageStore.CRAFTING_PART_VACCUM, null, 0)),
	FURNACE("Furnace Unit", new PartFurnace(ImageStore.CRAFTING_PART_VACCUM, null, 1, 2)),
	FUNNEL("Funnel Unit", new PartFurnace(ImageStore.CRAFTING_PART_VACCUM, null, 3));

	private final String title;
	private Part part;
	public final int POSITION_1 = 0;
	public final int POSITION_2 = 1;
	public final int POSITION_3 = 2;
	public final int POSITION_4 = 3;
	
	private static int[] toArray(int i, int... iN) {
		iN = Arrays.copyOf(iN, iN.length + 1);
		for (int j = iN.length-1; j > 0; j--) {
			iN[i] = iN[i - 1];
		}
		iN[0] = i;
		return iN;

	}
	public Part getPart(){
		return part;
	}

	private Component(String title, Part part) {
		this.title = title;
		this.part = part;
	}
	
	public String getTitle(){
		return title;
	}
	/*public int[] getValidPositions(){
		return possLocations;
	}
	public boolean canFit(int pos){
		for(int i = 0; i < possLocations.length; i++){
			if(possLocations[i] == pos){
				return true;
			}
		}
		return false;
	}*/

	

	
}
