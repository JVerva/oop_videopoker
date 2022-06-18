package deck;

import java.util.ArrayList;

import card.Card;
import strategy.PokerHand;

public class Hand {
	private static Integer bet = 5;
	private static ArrayList<Card> cardList = new ArrayList<Card>();
	
	public static void addCard(Card card) {
		cardList.add(card);
	}
	
	public static  void addCard(ArrayList<Card> cards) {
		cardList.addAll(cards);
	}
	
	public static Card removeCard(Integer pos) throws IndexOutOfBoundsException {
		try {
			Card card = cardList.get(pos.intValue());
			cardList.remove(pos.intValue());
			return card;
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Hand ran out of cards.");
		}
	}
	
	public static ArrayList<Card> removeCard(Integer[] pos) {
		try {
			ArrayList<Card> card = new ArrayList<Card>();
			for(int i = 0; i<pos.length; i++) {	
				card.add(cardList.get(pos[i].intValue()));
				cardList.remove(pos[i].intValue());
			}
			return card;
		}catch(IndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("Hand ran out of cards.");
		}
	}

	/**
	 * @return the bet
	 */
	public static Integer getBet() {
		return Hand.bet;
	}

	/**
	 * @param bet the bet to set
	 */
	public static void setBet(Integer bet) {
		Hand.bet = bet;
	}
	
	public static PokerHand evaluate() {
		 for(PokerHand potential : PokerHand.values()) {
		        if (potential.matches(Hand.cardList))
		        	return potential;
		    }
		 return null;
	}

	public static void print() {
		for(int i = 0; i<Hand.getCardCount(); i++) {
			System.out.print(Hand.cardList.get(i).getRank().getChar().toString() + Hand.cardList.get(i).getSuit().getChar().toString()+ " ");
		}
	}

	public static int getCardCount() {
		return cardList.size();
	}

	public static void clear() {
		cardList.clear();
	}
	
	public static void setCard(int pos, Card newCard) {
		Hand.cardList.set(pos, newCard);
	}
	
	public static ArrayList<Card> getCardList() {
		return cardList;
	}
}
