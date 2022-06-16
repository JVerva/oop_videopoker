package strategy;

import java.util.Comparator;

import card.Card;

public class RankSort implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		// TODO Auto-generated method stub
		  return o2.getRank().getValue() - o1.getRank().getValue();
		
	}
	


}
