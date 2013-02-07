package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public abstract class Interface {
	public abstract void draw(Graphics g);
	public abstract void update(Input input);
}
