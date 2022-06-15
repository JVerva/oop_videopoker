package deck;

import card.Card;
import card.Rank;
import card.Suit;

public class DeckSim extends Deck {
	
	private static DeckSim instance = null;
	
	private DeckSim() {
	}
	
	public static DeckSim getInstance() {
		if(instance == null)
			instance = new DeckSim();
		return instance;
	}
	
	public void build() {
		for(int s=0;s<4;s++) {
			for(int r=0;r<13;r++) {
				instance.addCard(new Card(Rank.get(r),Suit.get(s)));
			}
		}
	}
	
}
