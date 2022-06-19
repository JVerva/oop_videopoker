package cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import card.Card;
import deck.Deck;
import deck.Hand;
import gamemode.GameModeAlias;
import strategy.DificultHand;
import strategy.PokerHand;
import videopoker.Main;

public class Cmd {
	
	private static Integer stats[] = new Integer[] {0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	private static CmdAlias[] blockedCmds =  new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.HOLD, CmdAlias.DEAL};
	
	public static void execute(CmdAlias cmd, ArrayList<Integer> params) throws IllegalArgumentException, IndexOutOfBoundsException {
		
		for(int i = 0; i<blockedCmds.length; i++) {
			if (cmd == blockedCmds[i]) {
				System.err.println(String.valueOf(cmd.getAlias()) + ": illegal command");
				return;
			}
		}

		if(cmd == CmdAlias.HOLD) {
			hold(params);
		}else if(cmd == CmdAlias.BET)
			if (params.size()==1) {
				bet(params.get(0));
			}else {
				throw new IllegalArgumentException(String.valueOf(cmd.getAlias()) + "does not recieve multiple arguments");
			}
		else {
			throw new IllegalArgumentException(String.valueOf(cmd.getAlias()) + "command does not recieve arguments.");
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
			case HOLD:
				hold();
			break;
			default:
				throw new IllegalArgumentException(String.valueOf(cmd.getAlias()) + " has to recieve arguments.");
		}
	}
	
	public static void bet(Integer amount) {
		if(amount > 0 && amount < 6) {
			Hand.setBet(amount);
			if(Main.doPrint) 
				System.out.println("player is betting " + amount);
			Main.credit -= amount;
		}else {
			System.err.println("b: illegal amount");
		}
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.HOLD};
		if(Main.gameMode==GameModeAlias.SIMULATION) {
			 Cmd.blockedCmds = Arrays.copyOf(Cmd.blockedCmds,  Cmd.blockedCmds.length + 1);
			 Cmd.blockedCmds[Cmd.blockedCmds.length - 1] = CmdAlias.BET; 
		}
	}
	
	public static void credit() {
		System.out.println("player's credit is " + Main.credit);
	}
	
	public static void deal() throws IndexOutOfBoundsException{
		if(Main.gameMode == GameModeAlias.SIMULATION) {
			Deck.shuffle();
		}
		Main.sumofbets += Hand.getBet(); 
		ArrayList<Card> cList = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			cList.add(Deck.removeCard(0));
		}
		Hand.addCard(cList);
		if(Main.doPrint) {
			System.out.print("player's hand ");
			Hand.print();
			System.out.println();
		}
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.BET, CmdAlias.DEAL};
	}
	
	public static void hold(List<Integer> pos) throws IllegalArgumentException{
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.HOLD, CmdAlias.DEAL};
		
		Integer ipos[] = new Integer[Hand.getCardCount()-pos.size()];
		int j = 0;
		int k = 0;
		
		for(int i = 0; i<Hand.getCardCount(); i++){
			if(i!=pos.get(j)-1) {
				if(k<ipos.length)
					ipos[k] = i;
				k++;
			}else {
				if(j<pos.size()-1)
					j++;
			}
		}
			
		for(int i = 0; i<pos.size(); i++) {
			if(pos.get(i)>5) {
				throw new IllegalArgumentException("Invalid card position:" + pos.get(i).toString());
			}
		}
		for(int i = 0; i<ipos.length; i++) {
				Hand.setCard(ipos[i], Deck.removeCard(Integer.valueOf(0)));
		}
		if(Main.gameMode==GameModeAlias.SIMULATION) {
			 Cmd.blockedCmds = Arrays.copyOf(Cmd.blockedCmds,  Cmd.blockedCmds.length + 1);
			 Cmd.blockedCmds[Cmd.blockedCmds.length - 1] = CmdAlias.BET;
			 Deck.clear();
			 Deck.build();
		}
		
		PokerHand score = Hand.evaluate();
		Cmd.updateCredit(score);
		Cmd.addToStats(score);
		if(Main.doPrint) {
			Cmd.printScore(score);
			System.out.print("player's hand ");
			Hand.print();
			System.out.println();
		}
		Hand.clear();
	}
	
	public static void hold() throws IllegalArgumentException{
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.HOLD, CmdAlias.DEAL};
		
		if(Main.gameMode == GameModeAlias.DEBUG) {
			for(int i = 0; i<Hand.getCardCount(); i++) {
				Hand.setCard(i, Deck.removeCard(Integer.valueOf(0)));
			}
		}else {
			for(int i = 0; i<Hand.getCardCount(); i++) {
				Hand.setCard(i, Deck.removeCard(Integer.valueOf(0)));
			}
			 Cmd.blockedCmds = Arrays.copyOf(Cmd.blockedCmds,  Cmd.blockedCmds.length + 1);
			 Cmd.blockedCmds[Cmd.blockedCmds.length - 1] = CmdAlias.BET;
			 Deck.clear();
			 Deck.build();
		}
		
		PokerHand score = Hand.evaluate();
		Cmd.updateCredit(score);
		if(Main.doPrint) {
			System.out.print("player's hand ");
			Hand.print();
			System.out.println();
			Cmd.printScore(score);
		}
		Cmd.addToStats(score);
		Hand.clear();
	}
	
	public static List<Integer> advice() {
		Cmd.blockedCmds = new CmdAlias[] {CmdAlias.ADVICE, CmdAlias.BET, CmdAlias.DEAL};
		List<Integer> positionList = null;
		 for(DificultHand choice : DificultHand.values()) {
		      positionList=choice.matches(Hand.getCardList());
		      if(positionList!=null)
		    	  break;
		    }
		 if(Main.doPrint) {
			 if(positionList != null) {
				 System.out.print("Player should hold " );
				 for(int i = 0 ; i < positionList.size(); i++) {
					 System.out.print(positionList.get(i).toString() + " ");
				 }
				 System.out.println();
			 }else {
				 System.out.println("Player discard all cards");
			 }
		 }
		return positionList;
	}
	
	public static void statistics() {
		System.out.println("Hand			Nb");
		System.out.println("--------------------------");
		for(PokerHand potential : PokerHand.values()) {
			System.out.println(potential.toString() + "	    " + stats[potential.ordinal()].toString());
		}
		System.out.println("--------------------------");
		System.out.println("Total		" + stats[stats.length-1].toString());
		System.out.println("--------------------------");
		System.out.println("Credit		" + Main.credit.toString() + " " + Double.valueOf(Main.sumofgain)/Double.valueOf(Main.sumofbets)*100);
	}
	
	public static void printScore(PokerHand score) {
		if(score.equals(PokerHand.NONE))
			System.out.print("player loses ");
		else
			System.out.print("player wins with a " + score.toString() + " ");
		
		System.out.println("and his credit is " + Main.credit.toString());
	}
	
	private static void addToStats(PokerHand h) {
		Cmd.stats[h.ordinal()]++;
		Cmd.stats[12]++;
	}
	
	private static void updateCredit(PokerHand score) {
		if(score.equals(PokerHand.ROYAL_FLUSH)&&Hand.getBet().equals(5)) {
			Main.credit += 4000;
			Main.sumofgain += 4000;
		}else {
			Integer ret = Hand.getBet()*Main.multiplier[score.ordinal()];
			Main.credit += ret;
			Main.sumofgain += ret;
		}
	}
}
