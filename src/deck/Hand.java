package deck;
import strategy.PokerHand;

public class Hand extends Deck{
	
	private static Hand instance = null;
	
	private Hand() {
	}
	
	public static Hand getInstance() {
		if(instance == null)
			instance = new Hand();
		return instance;
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
	
	public static PokerHand evaluate() {
		 for(PokerHand potential : PokerHand.values()) {
		        if (potential.matches(Hand.getInstance().cardList))
		        	return potential;
		    }
		 return null;
		}

	public static void printDeck() {
		
		
	}
	 
}
	