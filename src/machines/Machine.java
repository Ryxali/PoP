package machines;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import image.Drawable;
import physics.Physics;
import physics.Weight;

public class Machine implements Weight, Physics, Drawable{
	
	private Part[] parts = new Part[4];
	private boolean isDeployed;
	
	private int x;
	private int y;
	
	public Machine(int x, int y, Part p1, Part p2, Part p3, Part p4){
		this.x = x;
		this.y = y;
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

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		parts[0].draw(x, y, g);
		parts[1].draw(
				x+parts[0].getImage().getWidth(),
				y, g);
		parts[2].draw(x,
				y + parts[0].getImage().getHeight(),
				g);
		parts[3].draw(
				x + parts[1].getImage().getWidth(),
				y + parts[2].getImage().getHeight(),
				g);
		for (int i = 0; i < parts.length; i++) {
			parts[i].draw(x, y, g);
		}
		
	}
}
