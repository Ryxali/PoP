package priceofprogress;

import org.newdawn.slick.state.BasicGameState;

public enum State {
	STATE_MENU_MAIN(0, new StateMainMenu(0)),
	STATE_MENU_OPTIONS(1, new StateOptionsMenu(1)),
	STATE_MENU_KEYCONFIG(2, new StateKeyconfigMenu(2)),
	STATE_MENU_LOADGAME(3, new StateLoadgame(3)),
	STATE_PLAY_MAIN(5, new StatePlay(5));
	private int id;
	private BasicGameState state;
	private State(int id, BasicGameState state){
		this.id = id;
		this.state = state;
	}
	public int getID(){
		return id;
	}
	public BasicGameState getState(){
		return state;
	}
}
