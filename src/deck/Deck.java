package deck;

import java.util.ArrayList;

import card.Card;

public class Deck {
	protected static ArrayList<Card> cardList = new ArrayList<Card>();
	protected static Integer cardCount = 0;
	
	public static void addCard(Card card) {
		cardList.add(card);
		cardCount++;
	}
	
	public static void addCard(ArrayList<Card> cards) {
		cardList.addAll(cards);
		cardCount += cards.size();
	}
	
	public static void removeCard(Integer pos) {
		cardList.remove(pos.intValue());
		cardCount--;
	}
	
	public static void removeCard(Integer[] pos) {
		for(int i = 0; i<pos.length; i++)
		cardList.remove(pos[i].intValue());
		cardCount -= pos.length;
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
	public static void setCardCount(Integer cardCount) {
		Deck.cardCount = cardCount;
	}
}
