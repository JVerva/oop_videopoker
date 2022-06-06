package cmd;

public class Cmd {
	
	public static void execute(CmdAlias alias, int[] params) throws IllegalArgumentException {

		if(alias == CmdAlias.HOLD)
			hold(params);
		else {
			throw new IllegalArgumentException(alias + "command does not recieve multiple arguments.");
		}
		
	}
	
	public static void execute(CmdAlias alias, int param) throws IllegalArgumentException {
		
		if(alias == CmdAlias.BET)
			bet(param);
		else {
			throw new IllegalArgumentException(alias + "command does not recieve one argument.");
		}
		
	}
	
	public static void execute(CmdAlias alias) throws IllegalArgumentException {
		switch(alias) {
		case BET:
			bet(5);
		case CREDIT:
			credit();
		case DEAL:
			deal();
		case ADVICE:
			advice();
		case STATISTICS:
			statistics();
		default:
			throw new IllegalArgumentException(alias + "has to recieve arguments.");
		}
	}
	
	public static void bet(int amount) {
		
	}
	
	public static void credit() {
		
	}
	
	public static void deal() {
		
	}
	
	public static void hold(int[] pos) {
		
	}
	
	public static void advice() {
		
	}
	
	public static void statistics() {
		
	}
}
