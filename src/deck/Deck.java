package deck;

import java.util.ArrayList;

import card.Card;

public class Deck {
	protected static ArrayList<Card> cardList;
	protected static int cardCount;
	
	public static void addCard(Card card) {
		cardList.add(card);
	}
	
	public static void addCard(ArrayList<Card> cards) {
		cardList.addAll(cards);
	}
	
	public static void removeCard(int pos) {
		cardList.remove(pos);
	}
	
	public static void removeCard(int[] pos) {
		for(int i = 0; i<pos.length; i++)
		cardList.remove(pos[i]);
	}

	/**
	 * @return the cardCount
	 */
	public static int getCardCount() {
		return cardCount;
	}

	/**
	 * @param cardCount the cardCount to set
	 */
	public static void setCardCount(int cardCount) {
		Deck.cardCount = cardCount;
	}
}
