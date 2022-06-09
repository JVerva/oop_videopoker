package card;

public class Card {
	private Suit suit;
	private Rank rank;
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
}
