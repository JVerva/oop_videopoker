package strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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


public static boolean isStraight(List<Card> hand) {
	

//	int firstCardSuit = playerHand.get(0).getSuit();
	List<Integer> sortedCardRanks = new ArrayList<>();
	List<Integer> cardSuits = new ArrayList<>();

	//Create an Integer list containing all the player's ranks
	for(Card card : hand){
		sortedCardRanks.add(card.getRank().getValue());
		cardSuits.add(card.getSuit().getValue());
	}

	//Sort the ranks
	Collections.sort(sortedCardRanks);

	//Check to see that all card suits are not identical
	Set<Integer> suitSet = new HashSet<>(cardSuits);

	//If set size is smaller, there are duplicates, meaning more than one suit, which a Straight requires
	if(suitSet.size() > cardSuits.size())
		return false;

	//Go Step by step to see if the next card's rank is only 1 more than it
	for(int i = 0; i < 4; i++){
		if(!(sortedCardRanks.get(i) == (sortedCardRanks.get(i+1) - 1)))
			return false;
	}

	return true;
}

public static List<Integer> straight(List<Card> hand) {
	

//	int firstCardSuit = playerHand.get(0).getSuit();
	List<Integer> sortedCardRanks = new ArrayList<>();
	List<Integer> cardSuits = new ArrayList<>();
	List<Integer> positionList = new ArrayList<>();
	int count = 0;
	int pos = 0;

	//Create an Integer list containing all the player's ranks
	for(Card card : hand){
		sortedCardRanks.add(card.getRank().getValue());
		cardSuits.add(card.getSuit().getValue());
	}

	//Sort the ranks
	Collections.sort(sortedCardRanks);

	//Check to see that all card suits are not identical
	Set<Integer> suitSet = new HashSet<>(cardSuits);

	//If set size is smaller, there are duplicates, meaning more than one suit, which a Straight requires
	if(suitSet.size() > cardSuits.size())
		return null;

	//Go Step by step to see if the next card's rank is only 1 more than it
	for(int i = 0; i < 4; i++){
		
		if(!(sortedCardRanks.get(i) == (sortedCardRanks.get(i+1) - 1)))
			count++;
	}
	
	if(count==5) {
		for(int i=0;i<5;i++) {
			positionList.add(i);
		}
	}
		return null;
	
}
	


public static boolean isFlush(String type, List<Card> hand) {
	
	List<Integer> list = new ArrayList<>();
	
	int suit = hand.get(0).getSuit().getValue();
	
	switch(type) {
	
	case "ROYAL_FLUSH":
		list = Arrays.asList(1,10,11,12,13);
		 break;
	case "STRAIGHT_FLUSH":
		if(isStraight(hand)) {
			for(Card card : hand){
				if(card.getSuit().getValue() != suit)
					return false;
			}
			return true;
		}
		 
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
			if(card.getRank().getValue()>10 || card.getRank().getValue()==1)
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
			
		if(list.contains(card.getRank().getValue())) {
			if(suit==0) {
				suit=card.getSuit().getValue();
				positionList.add(position);
			} else if(card.getSuit().getValue()==suit) {
				positionList.add(position);
				position++;
			}
		
	}
	}
	
	if(positionList.size()<4) {	
		return null;
	}
	
	return positionList;
	
	
}


public static boolean isFourToRoyalFlush(List<Card> hand){
	
	List<Integer> list = Arrays.asList(1,10,11,12,13);
	
	int suit = 0;
	
	int position=0;
	
	
	for(Card card : hand){
			
		if(list.contains(card.getRank().getValue())) {
			if(suit==0) {
				suit=card.getSuit().getValue();
			} else if(card.getSuit().getValue()==suit) {
				position++;
			}
		
	}
	}
	
	if(position==4) {
		return true;
	}
	
	return false;
	
	
}


public static boolean isThreeAces(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isFourToAStraigthFlush(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isHighPair(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isFourToAFlush(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isThreeToARoyalFlush(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isFourToAnOutsideStraigth(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isLowPair(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isAKQJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isThreeToAStraightFlush(int type,List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}




public static boolean isFourToAnInsideStraight(int type,List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isQJSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isThreeToAFlush(int type,List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isTwoSuitedHighCards(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isKQJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isJTSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isQJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isQTSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isKQOrKJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isAce(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isKTSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isJQK(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static boolean isDiscardEverything(List<Card> hand) {
	// TODO Auto-generated method stub
	return false;
}


public static List<Integer> discardEverything(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> threeToAFlush(int i, List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> fourToAnInsideStraight(int i, List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> JQK(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> KTSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> Ace(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> KQOrKJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> threeToAStraightFlush(int i, List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> QTSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> JTSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> QJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> KQJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> twoSuitedHighCards(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> QJSuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> AKQJUnsuited(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> lowPair(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> fourToAnOutsideStraigth(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> threeToARoyalFlush(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> fourToAFlush(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> highPair(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> jacksOrBetter(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> twoPair(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> fourToAStraigthFlush(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> threeOfAKind(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> fullHouse(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> flush(String string, List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> threeAces(List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
}


public static List<Integer> fourOfAKind(String string, List<Card> hand) {
	// TODO Auto-generated method stub
	return null;
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
