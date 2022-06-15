package Main;
import java.io.FileNotFoundException;

import deck.DeckSim;
import gamemode.Debug;
import gamemode.GameModeAlias;

public class Main {
	
	public static GameModeAlias gameMode;
	public static Integer credit;
	
	public static void main(String[] args) {
		try {
			getGameMode(args[0]);
			getCredit(args[1]);
			if(gameMode == GameModeAlias.DEBUG) {
				Debug.play(args[2], args[3]);
			}else{
				DeckSim.getInstance().build();
			}
		
		}catch(IllegalArgumentException e){
			System.err.println(e.getMessage());
		}catch(FileNotFoundException e) {
			System.err.println(e.getMessage());
		}catch(IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static void getGameMode(String alias) throws IllegalArgumentException{
		if(alias.equals("-d")) {
			gameMode = GameModeAlias.DEBUG;
		}else if(alias.equals("-s")) {
			gameMode = GameModeAlias.SIMULATION;
		}else {
			throw new IllegalArgumentException(alias + " does not correspond to a game mode");
		}
	}
	
	private static void getCredit(String scredit) {
		credit = Integer.parseInt(scredit);
	}
	
}