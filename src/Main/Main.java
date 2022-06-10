package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import gamemode.GameModeAlias;

public class Main {
	
	static GameModeAlias gameMode;
	static Integer credit;
	static ArrayList<String> cmds = new ArrayList<String>();
	
	public static void main(String[] args) {
		try {
			getGameMode(args[0]);
			getCredit(args[1]);
			if(gameMode == GameModeAlias.DEBUG) {
				getCmds(args[2]);
				getDeck(args[3]);
			}else{
				
			}
		
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
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
		Integer c = Integer.parseInt(scredit);
	}
	
	private static void getDeck(String fileName) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(fileName))){
			while(sc.hasNext()) {
				String line = sc.next();
				String[] tokens = line.split(" ");
				for(int i = 0; i< tokens.length; i++) {
					cmds.add(tokens[i]);
				}
			}
		}
	}
	
	private static void getCmds(String fileName) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(fileName))){
			while(sc.hasNext()) {
				String line = sc.next();
				String[] tokens = line.split(" ");
				for(int i = 0; i< tokens.length; i++) {
					//get cards here
				}
			}
		}
	}
	
}