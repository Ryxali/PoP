package machines;

import image.AnimationStore;
import image.ImageStore;

import java.util.Arrays;

public enum Component{
	//TODO add object to represent these units
	VACCUM("Vaccum Unit", new PartVaccum(1, ImageStore.CRAFTING_PART_VACCUM, null, Part.POSITION_1), 1),
	FURNACE("Furnace Unit", new PartFurnace(2, ImageStore.CRAFTING_PART_VACCUM, null, Part.POSITION_2, Part.POSITION_3), 2),
	STICK("Ballistics Enhancer", new PartFurnace(3, ImageStore.CRAFTING_PART_STICK_ICON, AnimationStore.PART_STICK, Part.POSITION_2, Part.POSITION_3), 3),
	DUST("Volatition Enhancer", new PartFurnace(4, ImageStore.CRAFTING_PART_DUST_ICON, AnimationStore.PART_DUST, Part.POSITION_2, Part.POSITION_3), 4),
	COG("Particle Accellery", new PartFurnace(5, ImageStore.CRAFTING_PART_COG_ICON, AnimationStore.PART_COG, Part.POSITION_2, Part.POSITION_3), 5),
	FUNNEL("Funnel Unit", new PartFurnace(6, ImageStore.CRAFTING_PART_VACCUM, null, Part.POSITION_4), 6),
	FUSE("Ignition Unit", new PartFurnace(7, null, null, Part.POSITION_2, Part.POSITION_3, Part.POSITION_4), 7);

	private final String title;
	private Part part;
	private int id;
	
	
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

	private Component(String title, Part part, int id) {
		this.title = title;
		this.part = part;
		this.id = id;
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

	public Part clone(int position){
		return this.getPart().clone(position);
	}

	
}
