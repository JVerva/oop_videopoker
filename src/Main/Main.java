package Main;
import java.io.FileNotFoundException;
import java.io.IOException;

import gamemode.Debug;
import gamemode.GameModeAlias;
import gamemode.Simulation;

public class Main {
	
	public static GameModeAlias gameMode;
	public static Integer credit;
	public static Integer baseCredit;
	public static Integer[] multiplier = new Integer[] {250, 50, 160, 80, 50, 10, 7, 5, 3, 1, 1, 0};
	public static Boolean doPrint = true;
	
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
		for(GameModeAlias potential : GameModeAlias.values()) {
			if(potential.getAlias().equals(alias)) {
				gameMode = potential;
				return;
			}
		}
			throw new IllegalArgumentException(alias + " does not correspond to a game mode");
	}
	
	private static void getCredit(String scredit) {
		credit = Integer.parseInt(scredit);
		baseCredit = credit;
	}
	
}