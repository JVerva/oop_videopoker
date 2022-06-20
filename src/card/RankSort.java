package card;

import java.util.Comparator;

public class RankSort implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		// TODO Auto-generated method stub
		  return o1.getRank().getValue() - o2.getRank().getValue();
		
	}
	


}
