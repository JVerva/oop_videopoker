package deck;

import card.Card;
import card.Rank;
import card.Suit;

public class DeckSim extends Deck {
	
	public static void  build() {
		for(int s=0;s<4;s++) {
			for(int r=0;r<13;r++) {
				DeckSim.addCard(new Card(Rank.getRank(r),Suit.getSuit(s)));
			}
		}
	}
	
}
