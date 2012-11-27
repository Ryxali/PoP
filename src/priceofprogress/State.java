package priceofprogress;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public enum State {
	STATE_MENU_MAIN(0, new StateMainMenu(0)),
	STATE_MENU_OPTIONS(1, new StateOptionsMenu(1)),
	STATE_MENU_KEYCONFIG(2, new StateKeyconfigMenu(2)),
	STATE_MENU_LOADGAME(3, new StateLoadgame(3)),
	STATE_PLAY_MAIN(5, new StatePlay(5));
	private final int id;
	private final BasicGameState state;
	private static int nextState = 0;
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
	public static int getNextState(){
		return nextState;
	}
	public static void setNextState(int state){
		nextState = state;
	}
	public static void enterState(StateBasedGame sbg, int state){
		sbg.enterState(state);
	}
}
