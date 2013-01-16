package state;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public enum State {
	STATE_MENU_MAIN(0, new StateMainMenu(0)),
	STATE_MENU_OPTIONS(1, new StateOptionsMenu(1)),
	STATE_MENU_KEYCONFIG(2, new StateKeyconfigMenu(2)),
	STATE_MENU_LOADGAME(3, new StateLoadgame(3)),
	STATE_PLAY_MAIN(5, new StatePlay(5));
	/**
	 * The numeric ID of the game
	 */
	private final int id;
	/**
	 * The state itself
	 */
	private final BasicGameState state;
	/**
	 * the state the game opts to go into next
	 */
	private static int nextState = 0;
	public static final int EXIT_GAME = 9001;
	public static final int NO_CHANGE = 0;
	/**
	 * 
	 * @param id the id of the state
	 * @param state the state itself
	 */
	private State(int id, BasicGameState state){
		this.id = id;
		this.state = state;
	}
	/**
	 * 
	 * @return the state ID
	 */
	public int getID(){
		return id;
	}
	/**
	 * 
	 * @return the state
	 */
	public BasicGameState getState(){
		return state;
	}
	/**
	 * 
	 * @return the state this state aims towards
	 */
	public static int getNextState(){
		return nextState;
	}
	/**
	 * 
	 * @param state the next game state to go into
	 */
	public static void setNextState(int state){
		nextState = state;
	}
	/**
	 * 
	 * @param sbg the game itself
	 * @param state the state th game should go into
	 */
	public static void enterState(StateBasedGame sbg, int state){
		sbg.enterState(state);
	}
}
