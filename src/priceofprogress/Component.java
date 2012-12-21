package priceofprogress;

import java.util.Arrays;

public enum Component{
	//TODO add object to represent these units
	VACCUM("Vaccum Unit", toArray(0));

	private final String title;
	private final int[] possLocations;
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

	private Component(String title, int[] possLocations) {
		this.title = title;
		this.possLocations = possLocations;
	}
	
	public String getTitle(){
		return title;
	}
	public int[] getValidPositions(){
		return possLocations;
	}
	public boolean canFit(int pos){
		for(int i = 0; i < possLocations.length; i++){
			if(possLocations[i] == pos){
				return true;
			}
		}
		return false;
	}

	

	
}
