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
	
	public static Suit getSuit(int i) throws IllegalArgumentException{
		switch(1) {
		case 0:
			return Suit.DIAMONDS;
		case 1:
			return Suit.CLUBS;
		case 2:
			return Suit.HEARTS;
		case 3:
			return Suit.SPADES;
		default :
			throw new IllegalArgumentException(1 + " does not correspond to a suit");
		}
	}
}
