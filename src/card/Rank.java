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
	
	private int value;
	
	private Rank(int value) {
        this.value = value; 
    }
	
	public static Rank get(Character c) throws IllegalArgumentException{
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
	
	public static Rank get(int i) throws IllegalArgumentException{
		switch(i) {
		case 0:
			return Rank.ACE;
		case 1:
			return Rank.TWO;
		case 2:
			return Rank.THREE;
		case 3:
			return Rank.FOUR;
		case 4:
			return Rank.FIVE;
		case 5:
			return Rank.SIX;
		case 6:
			return Rank.SEVEN;
		case 7:
			return Rank.EIGHT;
		case 8:
			return Rank.NINE;
		case 9:
			return Rank.TEN;
		case 10:
			return Rank.JACK;
		case 11:
			return Rank.QUEEN;
		case 12:
			return Rank.KING;
		default :
			throw new IllegalArgumentException(i + " does not correspond to a rank");
		}
	}
	
	public int getInt(){
		switch(this) {
		case ACE:
			return 1;
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case TEN:
			return 10;
		case JACK:
			return 11;
		case QUEEN:
			return 12;
		case KING:
			return 13;
		}
		return 0;
	}
	
	public Character getChar(){
		switch(this) {
		case ACE:
			return 'A';
		case TWO:
			return '2';
		case THREE:
			return '3';
		case FOUR:
			return '4';
		case FIVE:
			return '5';
		case SIX:
			return '6';
		case SEVEN:
			return '7';
		case EIGHT:
			return '8';
		case NINE:
			return '9';
		case TEN:
			return 'T';
		case JACK:
			return 'J';
		case QUEEN:
			return 'Q';
		case KING:
			return 'K';
		}
		return 0;
	}
	
	public int getValue() {
        return value;
	}
}
