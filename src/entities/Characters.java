package entities;

import java.util.HashMap;

import image.AnimatedImage;
import image.AnimationStore;

public enum Characters {
	//note that the number of strings must match the number of animated images.
	MAIN_CHAR(new EntityCharacter(0, 0, makeHashMap(
			toStringArray(
					"WalkLeft", 
					"WalkRight",
					"JumpRight",
					"JumpLeft",
					"Idle"
					),
			toAnimImgArray(
					AnimationStore.CHARACTER_WALK_LEFT.getAnimation(),
					AnimationStore.CHARACTER_WALK_RIGHT.getAnimation(),
					AnimationStore.CHARACTER_JUMP_RIGHT.getAnimation(),
					AnimationStore.CHARACTER_JUMP_LEFT.getAnimation(),
					AnimationStore.CHARACTER_IDLE.getAnimation()
					)))),
	EDGAR(null);
	
	public static final String WALK_LEFT = "WalkLeft";
	public static final String WALK_RIGHT = "WalkRight";
	public static final String JUMP_RIGHT = "JumpRight";
	public static final String JUMP_LEFT = "JumpLeft";
	public static final String IDLE = "Idle";
	
	private EntityCharacter character;
	private Characters(EntityCharacter character){
		this.character = character;
	}
	
	public EntityCharacter getCharacter(){
		return character;
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
