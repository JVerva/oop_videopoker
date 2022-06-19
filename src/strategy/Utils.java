package strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import card.Card;
import card.Rank;

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
				if(card.getRank().getValue()>10 || card.getRank().getValue()==1)
					pairCounter++;
			}
		}
	
		return pairCounter == 1 && rankMap.containsValue(1);
	}

	
	public static List<Integer> straightFlush(List<Card> hand){
		if(Utils.isFlush("FLUSH", hand)) {
			List<Integer> positionList = new ArrayList<>();
			for (int i = 0; i<hand.size(); i++) {
				positionList.add(i+1);
			}
			return positionList;
		}else
			return null;
	}

	public static List<Integer> fourOfAKind(List<Card> hand){
		return null;
	}
	
	public static List<Integer> royalFlush(List<Card> hand){
		if(Utils.isFlush("ROYAL_FLUSH", hand)) {
			List<Integer> positionList = new ArrayList<>();
			for (int i = 0; i<hand.size(); i++) {
				positionList.add(i+1);
			}
			return positionList;
		}else
			return null;
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
					positionList.add(position+1);
				} else if(card.getSuit().getValue()==suit) {
					positionList.add(position+1);
				}
			position++;
		}
		
		if(positionList.size()!=4) {
			return null;
		}
		
		return positionList;
		
		
	}

	public static List<Integer> threeAces(List<Card> hand){
		
		int count=0;
		List<Integer> positionList = new ArrayList<>();
		
		for(int i = 0; i<hand.size(); i++) {
			if(hand.get(i).getRank().equals(Rank.ACE)) {
				positionList.add(i+1);
				count++;
			}
		}
		if(count == 3)
			return positionList;
		else
			return null;
	}
	
	public static List<Integer> straight(List<Card> hand){
		return null;
	}
	
	public static List<Integer> flush(List<Card> hand){
		if(Utils.isFlush("FLUSH",hand)) {
			List<Integer> positionList = new ArrayList<>();
			for (int i = 0; i<hand.size(); i++) {
				positionList.add(i+1);
			}
			return positionList;
		}else {
			return null;
		}
	}
	
	public static List<Integer> fullHouse(List<Card> hand){
		if(Utils.isFullHouse(hand)) {
			List<Integer> positionList = new ArrayList<>();
			for (int i = 0; i<hand.size(); i++) {
				positionList.add(i+1);
			}
			return positionList;
		}else
			return null;
	}

	public static List<Integer> threeOfAKindEA(List<Card> hand){
		int s;
		Integer[] seen = new Integer[] {0,0,0,0,0,0,0,0,0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			if(hand.get(i).getRank()!=Rank.ACE)
				seen[hand.get(i).getRank().getInt()-2]++;
		}
		for(int i = 0; i<seen.length; i++) {
			if(seen[i] == 3) {
				s = i+2;
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getRank().getInt()==s) {
						positionList.add(j+1);
					}
				}
				return positionList;
			}
		}
		return null;
	}
	
	public static List<Integer> fourToStraightFlush(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		
		if(count == 4) {
			return positionList;
		}else {
			return null;
		}
		
	}

	public static List<Integer> twoPair(List<Card> hand){
		int s1=0;
		int s2=0;
		int count=0;
		Integer[] seen = new Integer[] {0,0,0,0,0,0,0,0,0,0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			if(hand.get(i).getRank()!=Rank.ACE)
				seen[hand.get(i).getRank().getInt()]++;
		}
		for(int i = 0; i<seen.length; i++) {
			if(seen[i] == 2) {
				if(count==0) {
					s1 = i+2;
					count ++;
				}
				else {
					s2 = i+2;
					count ++;
				}
			}
		}
		if(count == 2) {
			List<Integer> positionList = new ArrayList<>();
			for(int j = 0; j<hand.size(); j++) {
				if(hand.get(j).getRank().getInt()==s1||hand.get(j).getRank().getInt()==s2) {
					positionList.add(j+1);
				}
			}
			return positionList;
		}
		return null;
	}
	
	public static List<Integer> highPair(List<Card> hand){
		int s=0;
		Integer[] seen = new Integer[] {0,0,0,0};
		for(int i = 0; i<hand.size(); i++) {
			switch(hand.get(i).getRank()) {
				case ACE :
					seen[3]++;
				break;
				case KING: 
					seen[2]++;
				break;
				case QUEEN:
					seen[1]++;
				break;
				case JACK:
					seen[0]++;
				break;
			default:
				break;
			}
		}
		
		for(int i = 0; i < seen.length; i++) {
			if(seen[i]==2) {
				if(i == 3) {
					s = 1;
				}else{
					s = i+11;
				}
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getRank().getInt()==s) {
						positionList.add(j+1);
					}
				}
					return positionList;
			}
		}
		return null;
	}
	
	public static List<Integer> fourToAFlush(List<Card> hand){
		int s;
		Integer[] seen = new Integer[] {0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			seen[hand.get(i).getSuit().getValue()-1]++;
		}
		for(int i = 0; i<seen.length; i++) {
			if(seen[i] == 4) {
				s = i+1;
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getSuit().getValue()==s) {
						positionList.add(j+1);
					}
				}
				return positionList;
			}
		}
		return null;
	}
	
	public static List<Integer> threeToRoyalFlush(List<Card> hand){
	
		List<Integer> list = Arrays.asList(1,10,11,12,13);
		
		List<Integer> positionList = new ArrayList<>();
		
		int suit = 0;
		
		int position=0;
		
		
		for(Card card : hand){
				
			if(list.contains(card.getRank().getValue()))
				if(suit==0) {
					suit=card.getSuit().getValue();
					positionList.add(position+1);
				} else if(card.getSuit().getValue()==suit) {
					positionList.add(position+1);
				}
			position++;
		}
		
		if(positionList.size()!=3) {
			return null;
		}
		
		return positionList;
	}
	
	public static List<Integer> lowPair(List<Card> hand){
		int s=0;
		Integer[] seen = new Integer[] {0,0,0,0,0,0,0,0,0};
		for(int i = 0; i<hand.size(); i++) {
			if(hand.get(i).getRank() != Rank.ACE && hand.get(i).getRank() != Rank.JACK && hand.get(i).getRank() != Rank.QUEEN && hand.get(i).getRank() != Rank.KING) {
				seen[hand.get(i).getRank().getInt()-2]++;
			}
		}
		
		for(int i = 0; i < seen.length; i++) {
			if(seen[i]==2) {
					s = i+2;
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getRank().getInt()==s) {
						positionList.add(j+1);
					}
				}
					return positionList;
			}
		}
		return null;
	}

	public static List<Integer> AKQJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(1,11,12,13);
		return Utils.getUnsuited(list, hand);
	}
	
	public static List<Integer> threeToStraightFlushType1(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
		int HighCard = 0;
		
		
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>2)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+3 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				holes ++;
				if (holes>2)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 3)
					return null;
			}
		}
		//check type 1
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)).getRank().getInt() > 10) {
				HighCard++;
			}
			if(hand.get(positionList.get(i)).getRank().getInt() == 1) {
				return null;
			}
		}
		if(hand.get(positionList.get(1)).getRank().getInt() == 2 && hand.get(positionList.get(2)).getRank().getInt() == 3 && hand.get(positionList.get(3)).getRank().getInt() == 4 ) {
			return null;
		}
		
		if(count == 3 && HighCard == 2) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	public static List<Integer> threeToStraightFlushType2(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
		int HighCard = 0;
		int sequence = 0;
		
		
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>2)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+3 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				holes ++;
				if (holes>2)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 3)
					return null;
			}
		}
		//check type 2
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)).getRank().getInt() > 10) {
				HighCard++;
			}
			if(hand.get(positionList.get(i)).getRank().getInt() == 1) {
				HighCard++;
			}
		}
		if(hand.get(positionList.get(1)).getRank().getInt() == 2 && hand.get(positionList.get(2)).getRank().getInt() == 3 && hand.get(positionList.get(3)).getRank().getInt() == 4 ) {
			sequence = 1;
		}
		
		if(count == 3 && (HighCard == 1 || sequence == 1)) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	public static List<Integer> threeToStraightFlushType3(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
		int HighCard = 0;		
		
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>2)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+3 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				holes ++;
				if (holes>2)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 3)
					return null;
			}
		}
		//check type 3
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)).getRank().getInt() > 10) {
				HighCard++;
			}
			if(hand.get(positionList.get(i)).getRank().getInt() == 1) {
				HighCard++;
			}
		}
		
		if(count == 3 && HighCard == 0) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	public static List<Integer> threeToAFlushWith2HighCards(List<Card> hand){
		
		int s;
		int highCard = 0;
		Integer[] seen = new Integer[] {0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			seen[hand.get(i).getSuit().getValue()-1]++;
		}
		for(int i = 0; i<seen.length; i++) {
			if(seen[i] == 3) {
				s = i+1;
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getSuit().getValue()==s) {
						positionList.add(j+1);
					}
					if(hand.get(j).getSuit().getValue()==s && (hand.get(j).getRank().getInt()== 1 || hand.get(j).getRank().getInt() > 10) ) {
						highCard ++;
					}
				}
				if(highCard == 2) {
					return positionList;
				}
				
			}
		}
		return null;
	}
	
	public static List<Integer> threeToAFlushWith1HighCard(List<Card> hand){
		
		int s;
		int highCard = 0;
		Integer[] seen = new Integer[] {0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			seen[hand.get(i).getSuit().getValue()-1]++;
		}
		for(int i = 0; i<seen.length; i++) {
			if(seen[i] == 3) {
				s = i+1;
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getSuit().getValue()==s) {
						positionList.add(j+1);
					}
					if(hand.get(j).getSuit().getValue()==s && (hand.get(j).getRank().getInt()== 1 || hand.get(j).getRank().getInt() > 10) ) {
						highCard ++;
					}
				}
				if(highCard == 1) {
					return positionList;
				}
				
			}
		}
		return null;
	}
	
	public static List<Integer> threeToAFlushWith0HighCard(List<Card> hand){
		
		int s;
		int highCard = 0;
		Integer[] seen = new Integer[] {0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			seen[hand.get(i).getSuit().getValue()-1]++;
		}
		for(int i = 0; i<seen.length; i++) {
			if(seen[i] == 3) {
				s = i+1;
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getSuit().getValue()==s) {
						positionList.add(j+1);
					}
					if(hand.get(j).getSuit().getValue()==s && (hand.get(j).getRank().getInt()== 1 || hand.get(j).getRank().getInt() > 10) ) {
						highCard ++;
					}
				}
				if(highCard == 0) {
					return positionList;
				}
				
			}
		}
		return null;
	}
	
	public static List<Integer> twoSuitedHighCards(List<Card> hand){
		
		int s;
		int highCard = 0;
		Integer[] seen = new Integer[] {0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			seen[hand.get(i).getSuit().getValue()-1]++;
		}
		for(int i = 0; i<seen.length; i++) {
			if(seen[i] == 2) {
				s = i+1;
				List<Integer> positionList = new ArrayList<>();
				for(int j = 0; j<hand.size(); j++) {
					if(hand.get(j).getSuit().getValue()==s && (hand.get(j).getRank().getInt()== 1 || hand.get(j).getRank().getInt() > 10) ) {
						positionList.add(j+1);
						highCard ++;
					}
				}
				if(highCard == 2) {
					return positionList;
				}
				
			}
		}
		return null;
	}
	
	public static List<Integer> KQJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(11,12,13);
		return Utils.getUnsuited(list, hand);
	}
	
	public static List<Integer> QJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(11,12);
		return Utils.getUnsuited(list, hand);
	}
	
	public static List<Integer> KQUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(13,12);
		return Utils.getUnsuited(list, hand);
	}
	
	public static List<Integer> KJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(13,11);
		return Utils.getUnsuited(list, hand);
	}

	public static List<Integer> QJSuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(12,11);
		return Utils.getSuited(list, hand);
	}
	
	public static List<Integer> JTSuited(List<Card> hand){
			
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(10,11);
		return Utils.getSuited(list, hand);
		
	}
	
	public static List<Integer> QTSuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(10,12);
		return Utils.getSuited(list, hand);
		
	}
	
	public static List<Integer> KTSuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(10,13);
		return Utils.getSuited(list, hand);
		
	}
		
	public static List<Integer> ace(List<Card> hand){
		return getCard(1, hand);
	}
	
	public static List<Integer> jack(List<Card> hand){
		return getCard(11, hand);
	}

	public static List<Integer> queen(List<Card> hand){
		return getCard(12, hand);
	}
	
	public static List<Integer> king(List<Card> hand){
		return getCard(13, hand);
	}
	
	public static List<Integer> fourToOutsideStraight(List<Card> hand){
		
		Boolean s = false;
		int count=0;
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank()!= Rank.ACE && sortedHand.get(i+1).getRank() != Rank.ACE && (sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt())) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		if(count == 4) {
			return positionList;
		}
		return null;
	}
	
	public static List<Integer> fourToInsideStraightWith3HighCards(List<Card> hand){
			
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
		int highCard = 0;
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		//Check 3 HighCards
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)).getRank().getInt() > 10 || hand.get(positionList.get(i)).getRank().getInt() == 1) {
				highCard++;
			}
		}
		
		if(count == 4 && holes ==1 && highCard == 3) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	public static List<Integer> fourToInsideStraightWith2HighCards(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
		int highCard = 0;
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		//Check 2 HighCards
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)).getRank().getInt() > 10 || hand.get(positionList.get(i)).getRank().getInt() == 1) {
				highCard++;
			}
		}
		
		if(count == 4 && holes == 1 && highCard == 2) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	public static List<Integer> fourToInsideStraightWith1HighCard(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
		int highCard = 0;
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		//Check 1 HighCard
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)).getRank().getInt() > 10 || hand.get(positionList.get(i)).getRank().getInt() == 1) {
				highCard++;
			}
		}
		
		if(count == 4 && holes ==1 && highCard == 3) {
			return positionList;
		}else {
			return null;
		}
		
	}

	public static List<Integer> fourToInsideStraightWith0HighCard(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(comparator);
		Boolean s = false;
		int count=0;
		int holes = 0;
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		
		if(count == 4 && holes ==1) {
			return positionList;
		}else {
			return null;
		}
		
	}

	
	private static List<Integer> getUnsuited(List<Integer> cardList, List<Card> hand) {
		
		List<Integer> positionList = new ArrayList<>();
		
		for(int i = 0; i<cardList.size(); i++) {
			for(int j=0; j<hand.size(); j++) {
				if(hand.get(j).getRank().getInt() == cardList.get(i)) {
					positionList.add(j+1);
				}
			}
		}
		if(positionList.size()==cardList.size()) {
			return positionList;
		}

		return null;
		
	}
	
	private static List<Integer> getSuited(List<Integer> cardList, List<Card> hand) {
		
		List<Integer> positionList = new ArrayList<>();
		positionList = getUnsuited(cardList, hand);
		if(positionList == null) {
			return null;
		}
		int s = hand.get(positionList.get(0)-1).getSuit().getValue();
		for(int i = 1; i<positionList.size(); i++) {
			if(s!=hand.get(positionList.get(i)-1).getSuit().getValue())
				return null;
		}

		return positionList;
		
	}
	
	private static List<Integer> getCard(int cardn, List<Card> hand){
		for(int i=0; i<hand.size(); i++) {
			if(hand.get(i).getRank().getInt()==cardn) {
				List<Integer> positionList = new ArrayList<Integer>();
				positionList.add(i+1);
				return positionList;
			} 
		}
		return null;
	}
	
	private static Integer getPos(List<Card> hand, Card card) {
		for(int i = 0; i<hand.size(); i++) {
			if(hand.get(i)==card)
				return i;
		}
		return null;
	}

	static Comparator<Card> comparator = new Comparator<Card>(){
	
		@Override
		public int compare(Card o1, Card o2) {
			
			if(o1.getRank() == o2.getRank())
				return 0;
			else if(o1.getRank().getInt()>o2.getRank().getInt())
				return 1;
			else
				return -1;
			
		}
	};
	
}
