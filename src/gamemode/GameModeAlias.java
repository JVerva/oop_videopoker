package gamemode;

public enum GameModeAlias {
	DEBUG("-d"),
	SIMULATION("-s");
	
	private final String alias;
	
	GameModeAlias(String al) {
		this.alias = al;
		// TODO Auto-generated constructor stub
	}
	
	public String getAlias() {
		return this.alias;
	}
}
