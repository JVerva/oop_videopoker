package card;

public class Card {
	private Rank rank;
	private Suit suit;
	/**
	 * @return the suit
	 */
	public Suit getSuit() {
		return suit;
	}
	/**
	 * @param suit the suit to set
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	/**
	 * @return the rank
	 */
	public Rank getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public Card(Rank rank, Suit suit) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public static Card stringToCard(String alias) {
		Character r = Character.valueOf(alias.charAt(0));
		Character s = Character.valueOf(alias.charAt(1));
		
		return new Card(Rank.get(r), Suit.get(s));
	}
	
}
