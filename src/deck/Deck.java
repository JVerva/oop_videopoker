package deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import card.Card;
import card.Rank;
import card.Suit;

public class Deck {
		
	private static ArrayList<Card> cardList = new ArrayList<Card>();
		
	public static void build() {
		for(int s=0;s<4;s++) {
			for(int r=0;r<13;r++) {
				addCard(new Card(Rank.get(r),Suit.get(s)));
			}
		}
	}
	
	public static void build(String fileName) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(fileName))){
			while(sc.hasNext()) {
				String line = sc.next();
				String[] tokens = line.split(" ");
				for(int i = 0; i < tokens.length; i++) {
					addCard(Card.stringToCard(tokens[i]));
				}
			}
		}
	}
	
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
			throw new IndexOutOfBoundsException("Deck ran out of cards.");
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
				throw new IndexOutOfBoundsException("Deck ran out of cards.");
		}
	}

	public static void shuffle() {
		Collections.shuffle(cardList);
	}
	
	/**
	 * @return the cardCount
	 */
	public static int getCardCount() {
		return cardList.size();
	}
	
	public static void clear() {
		cardList.clear();
	}
	
}
