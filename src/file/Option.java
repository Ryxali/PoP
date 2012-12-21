package file;

import priceofprogress.Game;

public enum Option {
	FIELD_RESOLUTION_X("SizeX:", String.valueOf(Game.getGameContainer().getScreenWidth())),
	FIELD_RESOLUTION_Y("SizeY:", String.valueOf(Game.getGameContainer().getScreenHeight())),
	FIELD_KEY_MOVELEFT("MvLeft:", "65"),
	FIELD_KEY_MOVERIGHT("MvRight:", "68"),
	FIELD_KEY_JUMP("Jump:", "32"),
	FIELD_KEY_FIRE("Fire:", "0"),
	FIELD_KEY_INTERACT("Interact:", "2"),
	FIELD_VOLUME_MUSIC("MusicVolume:", "1.0"),
	FIELD_VOLUME_SOUND("SoundVolume:", "1.0");
	private final String name;
	private final String defValue;
	private Option(String name, String defValue){
		this.name= name;
		this.defValue = defValue;
	}
	public String getName(){
		return name;
	}
	public String getPrintableNameAndValue(){
		return name + " " + defValue;
	}
	public String getPrintableName(){
		return name + " ";
	}
	public String getDefValue(){
		return defValue;
	}
	
}
