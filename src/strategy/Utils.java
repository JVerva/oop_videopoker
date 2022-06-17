package strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import card.Card;

public class Utils {
	
	public static boolean isFourOfAKind(String type, List<Card> hand) {
		
		HashMap<Integer,Integer> rankMap = new HashMap<>();
		
		for(Card card : hand){				
				if(!rankMap.containsKey(card.getRank().getValue()))
					rankMap.put(card.getRank().getValue(), 1);
				else{
	    			int value = rankMap.get(card.getRank().getValue());
	    			rankMap.put(card.getRank().getValue(), value+1);
	    		}

    	}
		
		for (Entry<Integer, Integer> entry : rankMap.entrySet()) {
            if (entry.getValue().equals(4) && type.equals("FOUR_ACES")) {
                return entry.getKey().equals(1);
            }
            if (entry.getValue().equals(4) && type.equals("FOUR_5_K")) {
                return entry.getKey()>=5;
            }
            if (entry.getValue().equals(4) && type.equals("FOUR_2_4")) {
                return entry.getKey()>=2 && entry.getKey()<=4;
            }
            
        }
		
		return false;
		
	}
	
	
public static boolean isFullHouse(List<Card> hand) {
	HashMap<Integer,Integer> map = new HashMap<>();

	for(Card card : hand){
		if(!map.containsKey(card.getRank().getValue())){
			map.put(card.getRank().getValue(), 1);
		}
		else{
			int value = map.get(card.getRank().getValue());
			map.put(card.getRank().getValue(), value+1);
		}
	}

	return map.containsValue(3) && map.containsValue(2);
}

public static boolean isFlush(String type, List<Card> hand) {
	
	List<Integer> list = new ArrayList<>();
	
	int suit = hand.get(0).getSuit().getValue();
	
	switch(type) {
	
	case "ROYAL_FLUSH":
		list = Arrays.asList(1,10,11,12,13);
		 break;
	case "STRAIGHT_FLUSH":
		 list = Arrays.asList(9,10,11,12,13);
		 break;
	case "FLUSH":
		for(Card card : hand){
			if(card.getSuit().getValue() != suit)
				return false;
		}

		return true;
	}	


	for(Card card : hand){
		if(card.getSuit().getValue() != suit || !list.contains(card.getRank().getValue()))
			return false;
	}

	return true;

	
}


public static boolean isThreeOfAKind(List<Card> hand){
	HashMap<Integer,Integer> rankMap = new HashMap<>();

	for(Card card : hand){
		if(!rankMap.containsKey(card.getRank().getValue())){
			rankMap.put(card.getRank().getValue(), 1);
		}
		else{
			int value = rankMap.get(card.getRank().getValue());
			rankMap.put(card.getRank().getValue(), value+1);
		}
	}

	return rankMap.containsValue(3);
}

public static boolean isTwoPair(List<Card> hand){
	HashMap<Integer,Integer> rankMap = new HashMap<>();
	int pairCounter = 0;

	for(Card card : hand){
		if(!rankMap.containsKey(card.getRank().getValue())){
			rankMap.put(card.getRank().getValue(), 1);
		}
		else{
			int value = rankMap.get(card.getRank().getValue());
			rankMap.put(card.getRank().getValue(), value+1);
			pairCounter++;
		}
	}

	return pairCounter == 2 && rankMap.containsValue(1);
}


public static boolean isJacksOrBetter(List<Card> hand){
	HashMap<Integer,Integer> rankMap = new HashMap<>();
	int pairCounter = 0;

	for(Card card : hand){
		if(!rankMap.containsKey(card.getRank().getValue())){
			rankMap.put(card.getRank().getValue(), 1);
		}
		else{
			int value = rankMap.get(card.getRank().getValue());
			rankMap.put(card.getRank().getValue(), value+1);
			if(card.getRank().getValue()>10)
				pairCounter++;
		}
	}

	return pairCounter == 1 && rankMap.containsValue(1);
}



public static List<Integer> fourToRoyalFlush(List<Card> hand){
	
	List<Integer> list = Arrays.asList(1,10,11,12,13);
	
	List<Integer> positionList = new ArrayList<>();
	
	int suit = 0;
	
	int position=0;
	
	
	for(Card card : hand){
			
		if(list.contains(card.getRank().getValue()))
			if(suit==0) {
				suit=card.getSuit().getValue();
				positionList.add(position);
			} else if(card.getSuit().getValue()==suit) {
				positionList.add(position);
			}
		position++;
	}
	
	if(positionList.size()<3) {
		positionList.clear();
		return positionList;
	}
	
	return positionList;
	
	
}


//public boolean isFourToRoyalFlush(List<Card> hand){
//	
//	int suit = hand.get(0).getSuit().getValue();
//	
//	for(Card card : hand){
//		if(card.getSuit().getValue() != suit || !list.contains(card.getRank().getValue()))
//			return false;
//	}
//}
//	
//	for(Card card : hand){
//		
//		if(list.contains(card.getRank().getValue()))
//			if(suit==0) {
//				suit=card.getSuit().getValue();
//				positionList.add(position);
//			} else if(card.getSuit().getValue()==suit) {
//				positionList.add(position);
//			}
//		position++;
//	}

}
