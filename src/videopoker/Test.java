package videopoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import card.Card;
import card.Rank;
import card.RankSort;
import card.Suit;
import strategy.PokerHand;

public class Test {
	
	public static void main(String[] args) {
		
//		System.out.println(Suit.DIAMONDS.getValue());
//		System.out.println(Suit.getSuit(1));
		
		List<Card> list = new ArrayList<>();
		List<Card> sortedList = new ArrayList<>();
		
		list = Arrays.asList(new Card(Rank.ACE,Suit.CLUBS),new Card(Rank.TEN,Suit.CLUBS),new Card(Rank.JACK,Suit.CLUBS),new Card(Rank.QUEEN,Suit.CLUBS),new Card(Rank.KING,Suit.CLUBS));
		 
		sortedList=new ArrayList<>(list);
		
		Collections.sort(sortedList, new Comparator<Card>() {
		    @Override
		    public int compare(Card card1, Card card2) {
		        int rank1 = card1.getRank().getValue();
		        int rank2 = card2.getRank().getValue();

		        if (rank1 > rank2)
		            return 1;

		        if (rank1 < rank2)
		            return -1;

		        return 0;
		    }
				
		});
		
		
		Collections.sort(sortedList,new RankSort());
		 
		System.out.println(PokerHand.valueOf("ROYAL_FLUSH").matches(sortedList));
		
		 for (PokerHand potential : PokerHand.values()) {
		        if (potential.matches(list))
		        	System.out.println(potential.name());
		    }
		
	}



}
