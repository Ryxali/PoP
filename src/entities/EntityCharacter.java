package entities;

import file.Option;
import file.OptionsFile;
import image.AnimatedImage;
import image.AnimationStore;

import java.util.HashMap;

import org.newdawn.slick.Input;

import priceofprogress.Game;

public class EntityCharacter extends Entity {

	private int moveSpeed;
	/**
	 * -1 for left, 0 for middle, 1 for right
	 */
	private byte facing = 3;
	private long fallTime = 0;
	private final int JUMP_CD = 100;
	private int timeSinceJump = 0;

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
	public double getMass() {
		return 65;
	}

	private void fallInputCheck(Input input) {
		fallTime += Game.getDelta();
		if (input.isKeyDown(OptionsFile.get().fetchIntegerFromOptions(
				Option.FIELD_KEY_MOVERIGHT.getName()))) {
			if (facing != 1) {
				setCurrentAnimation(Characters.JUMP_RIGHT);
				facing = 1;
			}
		} else if (input.isKeyDown(OptionsFile.get().fetchIntegerFromOptions(
				Option.FIELD_KEY_MOVELEFT.getName()))) {
			if (facing != -1) {
				setCurrentAnimation(Characters.JUMP_LEFT);
				facing = -1;
			}
		}
	}

	private void checkInput(Input input) {
		if (isFalling()) {
			fallInputCheck(input);
			return;
		} else if (input.isKeyDown(Input.KEY_SPACE) && timeSinceJump >= JUMP_CD) {// Jump Triggered
			changeYForce(1000);
			setFalling(true);
			timeSinceJump = 0;
		} else if (input.isKeyDown(OptionsFile.get().fetchIntegerFromOptions(
				Option.FIELD_KEY_MOVERIGHT.getName()))) {// Right move action
			facing = 1;
		} else if (input.isKeyDown(OptionsFile.get().fetchIntegerFromOptions(
				Option.FIELD_KEY_MOVELEFT.getName()))) {// Left move action
			facing = -1;
		} else {// Idle otherwise
			facing = 0;
			
		}
		fallTime = 0;
		timeSinceJump += Game.getDelta();

	}

	private void characterMovement() {
		if (facing == -1) {
			moveEntity(-moveSpeed, 0);
		} else if (facing == 1) {
			moveEntity(moveSpeed, 0);
		}
	}

	private void setProperFallingAnimation() {
		if (facing == -1) {
			if (fallTime <= 600) {
				if (!getCurrentAnimation().equals(Characters.JUMP_LEFT)) {
					setCurrentAnimation(Characters.JUMP_LEFT);
				}
				return;
			}
			if (!getCurrentAnimation().equals(Characters.IDLE)) {
				setCurrentAnimation(Characters.IDLE);
			}
			return;
		}
		if (fallTime <= 600) {
			if (!getCurrentAnimation().equals(Characters.JUMP_RIGHT)) {
				setCurrentAnimation(Characters.JUMP_RIGHT);
			}
			return;
		}
		if (!getCurrentAnimation().equals(Characters.IDLE)) {
			setCurrentAnimation(Characters.IDLE);
		}
		/*if (facing == -1 && isFalling()) {
			if (fallTime <= 600) {
				if (!getCurrentAnimation().equals(Characters.JUMP_LEFT)) {
					setCurrentAnimation(Characters.JUMP_LEFT);
				}
			} else {
				if (!getCurrentAnimation().equals(Characters.IDLE)) {
					setCurrentAnimation(Characters.IDLE);
				}
			}

		} else if (facing == -1 && isFalling()) {
			if (fallTime <= 600) {

				if (!getCurrentAnimation().equals(Characters.JUMP_LEFT)) {
					setCurrentAnimation(Characters.JUMP_LEFT);
				}
			} else {
				if (!getCurrentAnimation().equals(Characters.IDLE)) {
					setCurrentAnimation(Characters.IDLE);
				}
			}
		}*/
	}

	private void setProperAnimation() {
		if (isFalling()) {
			setProperFallingAnimation();
			return;
		}

		if (facing == 1) {
			if (!getCurrentAnimation().equals(Characters.WALK_RIGHT)) {
				setCurrentAnimation(Characters.WALK_RIGHT);
			}
			return;
		}
		if (facing == -1) {
			if (!getCurrentAnimation().equals(Characters.WALK_LEFT)) {
				setCurrentAnimation(Characters.WALK_LEFT);
			}
			return;
		}
		if (!getCurrentAnimation().equals(Characters.IDLE)) {
			setCurrentAnimation(Characters.IDLE);
		}
		return;
	}

	@Override
	public void update(Input input) {
		checkInput(input);
		setProperAnimation();
		characterMovement();
		doPhysics();
	}

}
