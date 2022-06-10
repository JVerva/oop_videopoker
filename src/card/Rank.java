package card;

public enum Rank {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13);
	
	Rank(int ranki) {
		
	}
	
	public static Rank getRank(Character c) throws IllegalArgumentException{
		switch(c) {
		case 'A':
			return Rank.ACE;
		case '2':
			return Rank.TWO;
		case '3':
			return Rank.THREE;
		case '4':
			return Rank.FOUR;
		case '5':
			return Rank.FIVE;
		case '6':
			return Rank.SIX;
		case '7':
			return Rank.SEVEN;
		case '8':
			return Rank.EIGHT;
		case '9':
			return Rank.NINE;
		case 'T':
			return Rank.TEN;
		case 'J':
			return Rank.JACK;
		case 'Q':
			return Rank.QUEEN;
		case 'K':
			return Rank.KING;
		default :
			throw new IllegalArgumentException(c + " does not correspond to a rank");
		}
	}
}
