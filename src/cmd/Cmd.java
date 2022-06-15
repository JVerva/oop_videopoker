package cmd;

import java.util.Random;

import Main.Main;
import deck.DeckDebug;
import deck.DeckSim;
import deck.Hand;
import gamemode.GameModeAlias;

public class Cmd {
	
	private static CmdAlias[] blockedCmds =  new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.HOLD, CmdAlias.DEAL};
	
	public static void execute(CmdAlias cmd, Integer[] params) throws IllegalArgumentException, IndexOutOfBoundsException {
		
		for(int i = 0; i<blockedCmds.length; i++) {
			if (cmd == blockedCmds[i]) {
				System.err.println(String.valueOf(cmd.getAlias()) + ": illegal command");
				return;
			}
		}

		if(cmd == CmdAlias.HOLD)
			try {
				hold(params);
			}catch(IndexOutOfBoundsException e){
				throw e;
			}
		else {
			throw new IllegalArgumentException(String.valueOf(cmd.getAlias()) + "command does not recieve multiple arguments.");
		}
		
	}
	
	public static void execute(CmdAlias cmd, Integer param) throws IllegalArgumentException {
		
		for(int i = 0; i<blockedCmds.length; i++) {
			if (cmd == blockedCmds[i]) {
				System.err.println(String.valueOf(cmd.getAlias()) + ": illegal command");
			}
		}
		
		if(cmd == CmdAlias.BET)
			bet(param);
		else {
			throw new IllegalArgumentException(String.valueOf(cmd.getAlias()) + " command does not recieve one argument.");
		}
		
	}
	
	public static void execute(CmdAlias cmd) throws IllegalArgumentException {
		
		for(int i = 0; i<blockedCmds.length; i++) {
			if (cmd == blockedCmds[i]) {
				System.err.println(String.valueOf(cmd.getAlias()) + ": illegal command");
			}
		}
		
		switch(cmd) {
		case BET:
			bet(5);
		break;
		case CREDIT:
			credit();
		break;
		case DEAL:
			deal();
		break;
		case ADVICE:
			advice();
		break;
		case STATISTICS:
			statistics();
		break;
		default:
			throw new IllegalArgumentException(String.valueOf(cmd.getAlias()) + " has to recieve arguments.");
		}
	}
	
	public static void bet(Integer amount) {
		System.out.println("exectuting bet");
		if(amount > 0 && amount < 6) {
			Hand.setBet(amount);
		}
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.HOLD};
	}
	
	public static void credit() {
		System.out.println("exectuting credit");
		System.out.println(Main.credit);
	}
	
	public static void deal() throws IndexOutOfBoundsException{
		System.out.println("exectuting deal");
		if(Main.gameMode == GameModeAlias.DEBUG) {
			Hand.getInstance().cardList = DeckDebug.getInstance().removeCard(new Integer[] {0,1,2,3,4});
		}else {
			Random rn = new Random();
			int n = DeckSim.getInstance().getCardCount() + 1;
			Integer[] rand = new Integer[] {rn.nextInt(n), rn.nextInt(n), rn.nextInt(n), rn.nextInt(n), rn.nextInt(n)};
			Hand.getInstance().cardList = DeckSim.getInstance().removeCard(rand);
		}
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.BET, CmdAlias.DEAL};
	}
	
	public static void hold(Integer[] pos) throws IllegalArgumentException{
		System.out.println("exectuting hold");
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.HOLD, CmdAlias.DEAL};
		if(Main.gameMode == GameModeAlias.DEBUG) {
			for(int i = 0; i<pos.length; i++) {
				if(pos[i]>4) {
					throw new IllegalArgumentException("Invalid card position:" + pos[i].toString());
				}
				Hand.getInstance().cardList.set(pos[i]-1, DeckDebug.getInstance().removeCard(Integer.valueOf(0)));
			}
		}else {
			for(int i = 0; i<pos.length; i++) {
				if(pos[i]>4) {
					throw new IllegalArgumentException("Invalid card position:" + pos[i].toString());
				}
				Random rn = new Random();
				int n = DeckSim.getInstance().getCardCount() + 1;
				Hand.getInstance().cardList.set(pos[i], DeckDebug.getInstance().removeCard(Integer.valueOf(rn.nextInt(n))));
			}
		}
		Hand.evaluate();
	}
	
	public static void advice() {
		System.out.println("exectuting advice");
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.BET, CmdAlias.DEAL};
	}
	
	public static void statistics() {
		System.out.println("exectuting statistics");
	}
}
