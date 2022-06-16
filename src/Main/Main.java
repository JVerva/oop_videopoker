package Main;
import java.io.FileNotFoundException;
import java.io.IOException;

import gamemode.Debug;
import gamemode.GameModeAlias;
import gamemode.Simulation;

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
				Simulation.play(args[2], args[3]);
			}
		
		}catch(IllegalArgumentException e){
			System.err.println(e.getMessage());
		}catch(FileNotFoundException e) {
			System.err.println(e.getMessage());
		
		}catch(IOException e) {
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