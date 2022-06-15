package deck;

import java.util.ArrayList;

import card.Card;
import card.Rank;
import card.Suit;

public class Deck {
	public ArrayList<Card> cardList = new ArrayList<Card>();
	protected Integer cardCount = 0;
		
	public void  build() {
			for(int s=0;s<4;s++) {
				for(int r=0;r<13;r++) {
					this.addCard(new Card(Rank.get(r),Suit.get(s)));
				}
			}
		}
	
	public void addCard(Card card) {
		cardList.add(card);
		cardCount++;
	}
	
	public void addCard(ArrayList<Card> cards) {
		cardList.addAll(cards);
		cardCount += cards.size();
	}
	
	public Card removeCard(Integer pos) throws IndexOutOfBoundsException {
		try {
			Card card = cardList.get(pos.intValue());
			cardList.remove(pos.intValue());
			cardCount--;
			return card;
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Deck ran out of cards.");
		}
	}
	
	public ArrayList<Card> removeCard(Integer[] pos) {
		try {
			ArrayList<Card> card = new ArrayList<Card>();
			for(int i = 0; i<pos.length; i++) {	
				card.add(cardList.get(pos[i].intValue()));
				cardList.remove(pos[i].intValue());
			}
			cardCount -= pos.length;
			return card;
		}catch(IndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("Deck ran out of cards.");
		}
	}

	/**
	 * @return the cardCount
	 */
	public int getCardCount() {
		return this.cardCount;
	}
}
