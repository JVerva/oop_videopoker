package strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import card.Card;

public enum PokerHand {


	ROYAL_FLUSH {

		@Override
		public
		boolean matches(List<Card> hand) {
			
			return Utils.isFlush("ROYAL_FLUSH", hand);
		}
	},
	

	STRAIGHT_FLUSH {
		
		@Override
		public boolean matches(List<Card> hand) {
			
			return Utils.isFlush("STRAIGHT_FLUSH", hand);
		}
	},
	
	
	FOUR_ACES {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isFourOfAKind("FOUR_ACES", hand);
		}
		
	},
	
	FOUR_5_K {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isFourOfAKind("FOUR_5_K", hand);

		}
		
	},

	FOUR_2_4 {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isFourOfAKind("FOUR_2_4", hand);

		}
		
	},

		FULL_HOUSE {
		
			@Override
			public
			boolean matches(List<Card> hand) {

			 return Utils.isFullHouse(hand);
			}
		},

		FLUSH {
			
			@Override
			public
			boolean matches(List<Card> hand) {

				return Utils.isFlush("FLUSH", hand);
			}
		},

		STRAIGHT {
			
			@Override
			public
			boolean matches(List<Card> hand) {

//		    	int firstCardSuit = playerHand.get(0).getSuit();
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
		},

		THREE_OF_A_KIND {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				Utils.isThreeOfAKind(hand);
				return false;
			}

		},

		TWO_PAIR {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				Utils.isTwoPair(hand);
				return false;
			}

		},

		JACKS_OR_BETTER {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				for(Card card : hand) {
					if(card.getRank().getValue()>10)
						return true;
				}
				return false;
					
			}
			

		},
		NONE {
			
			@Override
			public boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		public abstract boolean matches(List<Card> hand);
	}
