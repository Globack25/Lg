package fr.globack.game;

public enum GameType {
	
	DAY,
	NIGHT;
	
	public static GameType state;
	
	public static void setType(GameType state) {
		GameType.state = state;
	}
	
	public static boolean isType(GameType states) {
		if(state == states) {
			return true;
		} else {
			return false;
		}
	}
	
}
