package machines;

import priceofprogress.Physics;
import priceofprogress.Weight;

public class Machine implements Weight, Physics{
	
	private Part[] parts = new Part[4];
	private boolean isDeployed;
	
	private int x;
	private int y;
	
	public Machine(Part p1, Part p2, Part p3, Part p4){
		parts[0] = p1;
		parts[1] = p2;
		parts[2] = p3;
		parts[3] = p4;
	}
	
	public void update(){
		doPhysics();
		for(int i = 0; i < 4; i++){
			parts[i].update();
		}
	}

	@Override
	public int getWeight() {
		return parts[0].getWeight() + parts[1].getWeight()
				+ parts[2].getWeight() + parts[3].getWeight();
	}

	@Override
	public void doPhysics() {
		// TODO Auto-generated method stub
		
	}
	public int getBlockPos(){
		return 0;
	}
	
	public boolean isDeployed(){
		return isDeployed;
	}
	public void toggleDeployed(){
		if(isDeployed){
			isDeployed = false;
		}else{
			isDeployed = true;
		}
	}
}
