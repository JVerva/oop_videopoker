package gamemode;

import java.io.IOException;
import java.util.List;

import cmd.Cmd;
import deck.Deck;
import videopoker.Main;

public class Simulation {

	public static void play(String sbet, String snbets) throws IOException {
		Main.doPrint = false;
		Integer bet = Integer.parseInt(sbet);
		Integer nbets = Integer.parseInt(snbets);
		Deck.build();
		List<Integer> params = null;
		
		for(int i = 0; i < nbets; i++) {
			Cmd.bet(bet);
			Deck.shuffle();
			Cmd.deal();
			params = Cmd.advice();
			if (params == null) {
				Cmd.hold();
			}else {
				Cmd.hold(params);
			}
		}
		Cmd.statistics();
	}
}
