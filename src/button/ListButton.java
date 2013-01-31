package button;



import image.ImageStore;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;


public class ListButton extends Button {
	private ImageStore idleImg;
	private ImageStore hoverImg;
	private DropdownList dList;
	private boolean hovered = false;
	private boolean isRevealed = false;

	private int[] states;

	public ListButton(int x, int y, ImageStore idleImg, ImageStore hoverImg,
			DropdownList dList) {
		super(x, y);
		this.idleImg = idleImg;
		this.hoverImg = hoverImg;
		this.dList = dList;
	}

	public void setDList(DropdownList dList) {
		this.dList = dList;
		states = new int[dList.getItems().length];
	}
	@Override
	public DropdownList getDList(){
		return dList;
	}

	@Override
	public void draw(Graphics g) {
		getStoredImage().draw(x, y);
		if (isRevealed) {
			for (int i = 0; i < dList.getDisplayLength(); i++) {
				dList.getBgButton(i).draw(
						x + getStoredImage().getImage().getWidth(),
						y + dList.getBgButton().getStoredImage()
										.getImage().getHeight() * i, g);
				g.drawString(dList.getItem(i), x + getStoredImage().getImage().getWidth(),
						y + dList.getBgButton().getStoredImage()
						.getImage().getHeight() * i);
			}
		}
	}

	@Override
	public void buttonStateCheck(Input input) {
		int mX = input.getMouseX();
		int mY = input.getMouseY();
		int farX = getX() + getStoredImage().getImage().getWidth();
		int farY = getY() + getStoredImage().getImage().getHeight();
		if (pointContains(getX(), mX, farX) && pointContains(getY(), mY, farY)) {
			hovered = true;
			setState(STATE_HOVER);
			isRevealed = true;
			for (int i = 0; i < dList.getDisplayLength(); i++) {
				dList.getBgButton(i).setState(STATE_IDLE);
			}
		} else {
			hovered = false;
			setState(STATE_IDLE);
			if (isRevealed
					&& pointContains(farX, mX, farX
							+ dList.getBgButton().getStoredImage().getImage()
									.getWidth())
					&& pointContains(getY(), mY, getY() + dList.getBgButton()
							.getStoredImage().getImage().getHeight()
							* dList.getDisplayLength())) {

				for (int i = 0; i < dList.getDisplayLength(); i++) {
					if (pointContains(getY()
							+ dList.getBgButton().getStoredImage().getImage()
									.getHeight() * i, mY, getY()
							+ dList.getBgButton().getStoredImage().getImage()
									.getHeight() * (i + 1))) {
						if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
							dList.getBgButton(i).setState(STATE_PRESSED);
							dList.getBgButton(i).setClicked(true);
						} else {
							dList.getBgButton(i).setState(STATE_HOVER);
						}
					}else{
						dList.getBgButton(i).setState(STATE_IDLE);
						dList.getBgButton(i).setClicked(false);
					}
				}
			} else {
				isRevealed = false;
			}
		}

	}
	
	@Override
	public int hasBeenClicked(){
		for(int i = 0; i < dList.getDisplayLength(); i++){
			if(dList.getBgButtons().get(i).hasBeenClicked() == Button.PRESSED_TRUE){
				return i;
			}
		}
		return -1;
	}

	@Override
	public ImageStore getStoredImage() {
		if (getState() == STATE_IDLE) {
			return idleImg;
		} else if (getState() == STATE_HOVER) {
			return hoverImg;
		}
		return null;
	}

	@Override
	public ListButton copy() {
		try {
			return (ListButton) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getType() {
		return ButtonStore.MODE_DROPDOWN;
	}
	
	@Override
	public void unload(){
		idleImg.unload();
		hoverImg.unload();
		dList.getBgButton().getStoredImage().unload();
	}

	@Override
	public void reload() {
		idleImg.reload();
		hoverImg.reload();
		dList.getBgButton().getStoredImage().reload();
		
	}

}
