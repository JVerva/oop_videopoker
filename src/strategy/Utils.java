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
import card.Rank;
import card.RankSort;

/**
* <h1>Implements the checks and lists to hold for strategy</h1>
*
* @author  Group 43
* @version 1.0
* @since   2022-06-20
*/
public class Utils {
	
	/**
	 * <p>Checks if hand is a FourOfAKind
	 * </p>
	 * @param type represents the three types of four of a kind
	 * @param hand the hand list
	 * @return true if the hand is has four of a kind 
	 */
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
	
	/**
	 * <p>Checks if hand is a Full House
	 * </p>
	 * @param hand the hand list
	 * @return true if the hand is a full house
	 */
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

	/**
	 * <p>Checks if hand is a Straight
	 * </p>
	 * @param hand the hand list
	 * @return true if the hand is a Straight
	 */
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

	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is a Straight 
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> straight(List<Card> hand) {
		
		if(isStraight(hand)) {
			List<Integer> positionList = new ArrayList<>();
			for (int i = 0; i<hand.size(); i++) {
				positionList.add(i+1);
			}
			return positionList;
		}else
			return null;
		
	}
		
	/**
	 * <p>Checks if hand is a Flush
	 * </p>
	 * @param type The type of flush
	 * @param hand the hand list
	 * @return true if the hand is a Straight
	 */
	public static boolean isFlush(String type, List<Card> hand) {
		
		List<Integer> list = new ArrayList<>();
		
		int suit = hand.get(0).getSuit().getValue();
		
		switch(type) {
		
		case "ROYAL_FLUSH":
			list = Arrays.asList(1,10,11,12,13);
			for(Card card : hand){
				if(card.getSuit().getValue() != suit || !list.contains(card.getRank().getValue()))
					return false;
			}
			return true;
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
		return false;
	}

	/**
	 * <p>Checks if hand is a ThreeOfAKind 
	 * </p>
	 * @param hand The hand list
	 * @return true if the hand is ThreeOfAKind 
	 */
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

	
	/**
	 * <p>Checks if hand is TwoPair 
	 * </p>
	 * @param hand The hand list
	 * @return  true if the hand is TwoPair
	 */
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

	/**
	 * <p>Checks if hand is JacksOrBetter 
	 * </p>
	 * @param hand The hand list
	 * @return  true if the hand is JacksOrBetter
	 */
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

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is a Straight Flush 
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is a FourOfAKind
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> fourOfAKind(String type, List<Card> hand){
		if(isFourOfAKind(type, hand)) {
			int f = 0;
			List<Integer> positionList = new ArrayList<>();
			for(int i = 0; i<hand.size()-1; i++) {
				if(hand.get(i).getRank() == hand.get(i+1).getRank())
					f = hand.get(i).getRank().getInt();
			}
			for(int i = 0; i<hand.size(); i++) {
				if(hand.get(i).getRank().getInt()==f) {
					positionList.add(i+1);
				}
			}
			return positionList;
		}
		
		return null;
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is a RoyalFlush
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeAces
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is Flush
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> flush(String type,List<Card> hand){
		if(Utils.isFlush(type,hand)) {
			List<Integer> positionList = new ArrayList<>();
			for (int i = 0; i<hand.size(); i++) {
				positionList.add(i+1);
			}
			return positionList;
		}else {
			return null;
		}
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is a FullHouse
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is a ThreeOfAKindEA
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is a FourToStraightFlush
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> fourToStraightFlush(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
		
		Boolean s = false;
		int count=0;
		int holes = 0;
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
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

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is TwoPair
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> twoPair(List<Card> hand){
		int s1=0;
		int s2=0;
		int count=0;
		Integer[] seen = new Integer[] {0,0,0,0,0,0,0,0,0,0,0,0,0};
		for( int i = 0; i<hand.size(); i++ ) {
			if(hand.get(i).getRank()!=Rank.ACE)
				seen[hand.get(i).getRank().getInt()-2]++;
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is HighPair
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is FourToAFlush
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeToRoyalFlush
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is LowPair
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is AKQJUnsuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> AKQJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(1,11,12,13);
		return Utils.getUnsuited(list, hand);
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeToStraightFlushType1
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> threeToStraightFlushType1(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
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
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>2)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+3 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				holes ++;
				if (holes>2)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 3)
					return null;
			}
		}
		//check type 1
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)-1).getRank().getInt() > 10) {
				HighCard++;
			}
			if(hand.get(positionList.get(i)-1).getRank().getInt() == 1) {
				return null;
			}
		}
		if(positionList.size()>2) {
			if(hand.get(positionList.get(0)-1).getRank().getInt() == 2 && hand.get(positionList.get(1)-1).getRank().getInt() == 3 && hand.get(positionList.get(2)-1).getRank().getInt() == 4 ) {
				return null;
			}
		}
		
		if(count == 3 && HighCard == 2) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeToStraightFlushType2
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> threeToStraightFlushType2(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
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
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>2)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+3 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				holes ++;
				if (holes>2)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 3)
					return null;
			}
		}
		//check type 2
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)-1).getRank().getInt() > 10) {
				HighCard++;
			}
			if(hand.get(positionList.get(i)-1).getRank().getInt() == 1) {
				HighCard++;
			}
		}
		if(positionList.size()>2) {
			if(hand.get(positionList.get(0)-1).getRank().getInt() == 2 && hand.get(positionList.get(1)-1).getRank().getInt() == 3 && hand.get(positionList.get(2)-1).getRank().getInt() == 4 ) {
				sequence = 1;
			}
		}
		if(count == 3 && (HighCard == 1 || sequence == 1)) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeToStraightFlushType3
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> threeToStraightFlushType3(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
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
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				if (holes>2)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+3 == sortedHand.get(i+1).getRank().getInt() && sortedHand.get(i).getSuit() == sortedHand.get(i+1).getSuit()) {
				holes ++;
				holes ++;
				if (holes>2)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 3)
					return null;
			}
		}
		//check type 3
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)-1).getRank().getInt() > 10) {
				HighCard++;
			}
			if(hand.get(positionList.get(i)-1).getRank().getInt() == 1) {
				HighCard++;
			}
		}
		
		if(count == 3 && HighCard == 0) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeToAFlushWith2HighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeToAFlushWith1HighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is ThreeToAFlushWith0HighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is TwoSuitedHighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is KQJUnsuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> KQJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(11,12,13);
		return Utils.getUnsuited(list, hand);
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is QJUnsuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> QJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(11,12);
		return Utils.getUnsuited(list, hand);
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is KQUnsuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> KQUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(13,12);
		return Utils.getUnsuited(list, hand);
	}

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is KJUnsuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> KJUnsuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(13,11);
		return Utils.getUnsuited(list, hand);
	}

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is QJSuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> QJSuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(12,11);
		return Utils.getSuited(list, hand);
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is JTSuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> JTSuited(List<Card> hand){
			
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(10,11);
		return Utils.getSuited(list, hand);
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is QTSuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> QTSuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(10,12);
		return Utils.getSuited(list, hand);
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is KTSuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> KTSuited(List<Card> hand){
		
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(10,13);
		return Utils.getSuited(list, hand);
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is Ace
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> ace(List<Card> hand){
		return getCard(1, hand);
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is Jack
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> jack(List<Card> hand){
		return getCard(11, hand);
	}

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is Queen
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> queen(List<Card> hand){
		return getCard(12, hand);
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is King
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> king(List<Card> hand){
		return getCard(13, hand);
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is FourToOutsideStraight
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> fourToOutsideStraight(List<Card> hand){
		
		Boolean s = false;
		int count=0;
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
		
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank()!= Rank.ACE && sortedHand.get(i+1).getRank() != Rank.ACE && (sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt())) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
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
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is FourToInsideStraightWith3HighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> fourToInsideStraightWith3HighCards(List<Card> hand){
			
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
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
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		//Check 3 HighCards
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)-1).getRank().getInt() > 10 || hand.get(positionList.get(i)-1).getRank().getInt() == 1) {
				highCard++;
			}
		}
		
		if(count == 4 && holes ==1 && highCard == 3) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is FourToInsideStraightWith2HighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> fourToInsideStraightWith2HighCards(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
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
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		//Check 2 HighCards
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)-1).getRank().getInt() > 10 || hand.get(positionList.get(i)-1).getRank().getInt() == 1) {
				highCard++;
			}
		}
		
		if(count == 4 && holes == 1 && highCard == 2) {
			return positionList;
		}else {
			return null;
		}
		
	}
	
	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is FourToInsideStraightWith1HighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> fourToInsideStraightWith1HighCard(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
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
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else {
				if (s == true && count != 4)
					return null;
			}
		}
		//Check 1 HighCard
		for(int i = 0; i<positionList.size(); i++) {
			if(hand.get(positionList.get(i)-1).getRank().getInt() > 10 || hand.get(positionList.get(i)-1).getRank().getInt() == 1) {
				highCard++;
			}
		}
		
		if(count == 4 && holes ==1 && highCard == 3) {
			return positionList;
		}else {
			return null;
		}
		
	}

	/**
	 * <p>Obtains the list of positions to hold of the sorted hand if is FourToInsideStraightWith0HighCards
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
	public static List<Integer> fourToInsideStraightWith0HighCard(List<Card> hand){
		
		List<Card> sortedHand = hand;
		List<Integer> positionList = new ArrayList<>();
		sortedHand.sort(new RankSort());
		Boolean s = false;
		int count=0;
		int holes = 0;
	
		for(int i = 0; i<sortedHand.size()-1; i++){
			if(sortedHand.get(i).getRank().getInt()+1 == sortedHand.get(i+1).getRank().getInt()) {
				s = true;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
				positionList.add(getPos(hand, sortedHand.get(i+1))+1);
			}else if(sortedHand.get(i).getRank().getInt()+2 == sortedHand.get(i+1).getRank().getInt()) {
				holes ++;
				if (holes>1)
					return null;
				count++;
				if(count == 1) {
					positionList.add(getPos(hand, sortedHand.get(i))+1);
					count++;
				}
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
	
	/**
	 * <p>Obtains the list of positions to hold of the Unsuited
	 * </p>
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions to hold of the Suited
	 * </p>
	 * @param cardList the hand list
	 * @param hand the hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the list of positions of the card in the hand
	 * </p>
	 * @param cardn The card to find
	 * @param hand The hand list
	 * @return The list of positions to hold
	 */
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
	
	/**
	 * <p>Obtains the position of the card in the hand
	 * </p>
	 * @param hand The hand list
	 * @param cardn The card to find
	 * @return The list of positions to hold
	 */
	private static Integer getPos(List<Card> hand, Card card) {
		for(int i = 0; i<hand.size(); i++) {
			if(hand.get(i)==card)
				return i;
		}
		return null;
	}

}
