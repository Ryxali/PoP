
package entities;

import image.AnimatedImage;
import image.AnimationStore;

import java.util.HashMap;

public enum Entities {
	WOUBLE(new EntityWouble(0, 300, makeHashMap(
			toStringArray(
					"Idle",
					"WalkLeft",
					"WalkRight"
					),
			toAnimImgArray(
					AnimationStore.CHARACTER_IDLE.getAnimation(),
					AnimationStore.CHARACTER_WALK_LEFT.getAnimation(),
					AnimationStore.CHARACTER_WALK_RIGHT.getAnimation()
					)
			)));
	
	private AIEntity entity;
	private Entities(AIEntity entity){
		this.entity = entity;
	}
	
	public AIEntity getEntity(){
		return entity;
	}
	
	private static String[] toStringArray(String... strings){
		return strings;
	}
	private static AnimatedImage[] toAnimImgArray(AnimatedImage... animImgs){
		return animImgs;
	}
	private static HashMap<String, AnimatedImage> makeHashMap(String[] strings, AnimatedImage[] animImgs){
		if(strings.length != animImgs.length){
			throw new IndexOutOfBoundsException("The number of Strings and animations must match!");
		}
		HashMap<String, AnimatedImage> hm = new HashMap<String, AnimatedImage>();
		for(int i = 0; i < strings.length; i++){
			hm.put(strings[i], animImgs[i]);
		}
		return hm;
	}
}
