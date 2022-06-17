package gamemode;

import java.io.IOException;
import java.util.ArrayList;

import cmd.Cmd;
import deck.Deck;

public class Simulation {

	public static void play(String sbet, String snbets) throws IOException {
		Integer bet = Integer.parseInt(sbet);
		Integer nbets = Integer.parseInt(snbets);
		Deck.build();
		ArrayList<Integer> params = null;
		
		for(int i = 0; i < nbets; i++) {
			Cmd.bet(bet);
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
