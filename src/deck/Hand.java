package deck;

import card.Card;
import card.Rank;
import card.Suit;

public class Hand extends Deck{
	
	private static Hand instance = null;
	
	private Hand() {
	}
	
	public static Hand getInstance() {
		if(instance == null)
			instance = new Hand();
		return instance;
	}
	
	public void build() {
		for(int s=0;s<4;s++) {
			for(int r=0;r<13;r++) {
				instance.addCard(new Card(Rank.get(r),Suit.get(s)));
			}
		}
	}
	
	private static Integer bet = 5;

	/**
	 * @return the bet
	 */
	public static Integer getBet() {
		return bet;
	}

	/**
	 * @param bet the bet to set
	 */
	public static void setBet(Integer bet) {
		Hand.bet = bet;
	}
	
	public static int evaluate() {
		return 0;
		}
	 
}
	