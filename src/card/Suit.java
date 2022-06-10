package card;

public enum Suit {
	DIAMONDS(1),
	CLUBS(2),
	HEARTS(3),
	SPADES(4);
	
	Suit(int suit) {
		
	}
	
	public static Suit getSuit(Character c) throws IllegalArgumentException{
		switch(c) {
		case 'D':
			return Suit.DIAMONDS;
		case 'C':
			return Suit.CLUBS;
		case 'H':
			return Suit.HEARTS;
		case 'S':
			return Suit.SPADES;

		default :
			throw new IllegalArgumentException(c + " does not correspond to a suit");
		}
	}
}
