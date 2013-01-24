package entities;

import java.util.HashMap;

import org.newdawn.slick.Input;

import priceofprogress.Game;

import file.Option;
import file.OptionsFile;

import image.AnimatedImage;
import image.AnimationStore;

public class EntityCharacter extends Entity {
	
	private int moveSpeed;
	public EntityCharacter(int x, int y, HashMap<String, AnimatedImage> animImgs) {
		super(x, y, animImgs);
		moveSpeed = 150;
	}

	@Override
	protected void setAnimations() {
		getAnimations().put("WalkRight",
				AnimationStore.CHARACTER_WALK_RIGHT.getAnimation());
		getAnimations().put("WalkLeft",
				AnimationStore.CHARACTER_WALK_LEFT.getAnimation());

	}

	@Override
	protected double setWeight() {
		return 0;
	}

	@Override
	public void update(Input input) {
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(input.isKeyDown(OptionsFile.get().fetchIntegerFromOptions(
					Option.FIELD_KEY_MOVELEFT.getName()))){
				setCurrentAnimation(Characters.JUMP_LEFT);
			}else{
				setCurrentAnimation(Characters.JUMP_RIGHT);
			}
			
		} else if (input.isKeyDown(OptionsFile.get().fetchIntegerFromOptions(
				Option.FIELD_KEY_MOVERIGHT.getName()))) {
			setCurrentAnimation(Characters.WALK_RIGHT);
			moveEntity(moveSpeed, 0);
		} else if (input.isKeyDown(OptionsFile.get().fetchIntegerFromOptions(
				Option.FIELD_KEY_MOVELEFT.getName()))) {
			setCurrentAnimation(Characters.WALK_LEFT);
			moveEntity(-moveSpeed, 0);
		} else {
			setCurrentAnimation(Characters.IDLE);
		}
		if (input.isKeyDown(Input.KEY_W)) {
			moveEntity(0, -50);
		} else if (input.isKeyDown(Input.KEY_S)) {
			moveEntity(0, 50);
		}

	}

}
