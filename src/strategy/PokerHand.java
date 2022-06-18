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
	
	FOUR_TO_ROYAL_FLUSH {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isFourOfAKind("FOUR_2_4", hand);

		}
		
	},
	
	THREE_ACES {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isThreeAces(hand);

		}
		
	},
	
	STRAIGHT {
		
		@Override
		public
		boolean matches(List<Card> hand) {

//	    	int firstCardSuit = playerHand.get(0).getSuit();
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
	
	FLUSH {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isFlush("FLUSH", hand);
		}
	},
	
	

		FULL_HOUSE {
		
			@Override
			public
			boolean matches(List<Card> hand) {

			 return Utils.isFullHouse(hand);
			}
		},


		THREE_OF_A_KIND {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isThreeOfAKind(hand);
			}

		},
		
		//To advice
		FOUR_TO_A_STRAIGHT_FLUSH {
			
			@Override
			public
			boolean matches(List<Card> hand) {

				return Utils.isFourToAStraigthFlush(hand);

			}
			
		},

		TWO_PAIR {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isTwoPair(hand);
			}

		},
		
		JACKS_OR_BETTER {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isJacksOrBetter(hand);
				
//				for(Card card : hand) {
//					if(card.getRank().getValue()>10)
//						return true;
//				}
//				return false;
					
			}
			

		},
		
		//To advice
		HIGH_PAIR {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isHighPair(hand);
			}

		},
		//To advice
		FOUR_TO_A_FLUSH {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isFourToAFlush(hand);
			}

		},
		//To advice
		THREE_TO_A_ROYAL_FLUSH {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isThreeToARoyalFlush(hand);
			}

		},
		//To advice
		FOUR_TO_AN_OUTSIDE_STRAIGHT {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isFourToAnOutsideStraigth(hand);
			}

		},
		//To advice
		LOW_PAIR {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isLowPair(hand);
			}

		},
		//To advice
		AKQJ_UNSUITED {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isAKQJUnsuited(hand);
			}

		},
		//To advice
		THREE_TO_A_STRAIGHT_FLUSH_TYPE_1 {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isThreeToAStraightFlush(1,hand);
			}

		},
		
		FOUR_TO_AN_INSIDE_STRAIGHT_3_HIGH_CARDS {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isFourToAnInsideStraight(3,hand);
			}

		},
		
		QJ_SUITED {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isQJSuited(hand);
			}

		},
		
		THREE_TO_A_FLUSH_WITH_2_HIGH_CARDS  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isThreeToAFlush(2,hand);
			}

		},
		
		TWO_SUITED_HIGH_CARDS  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isTwoSuitedHighCards(hand);
			}

		},
		
		FOUR_TO_AN_INSIDE_STRAIGHT_WITH_TWO_HIGH_CARDS  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isFourToAnInsideStraight(2,hand);
			}

		},
		
		THREE_TO_A_STRAIGHT_FLUSH_TYPE_2  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isThreeToAStraightFlush(2,hand);
			}

		},
		
		FOUR_TO_AN_INSIDE_STRAIGHT_WITH_ONE_HIGH_CARD  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isFourToAnInsideStraight(1,hand);
			}

		},
		
		 KQJ_UNSUITED  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isKQJUnsuited(hand);
			}

		},
		 
		 JT_SUITED  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isJTSuited(hand);
			}

		},
		 
		 QJ_UNSUITED  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isQJUnsuited(hand);
			}

		},
		 
		 THREE_TO_A_FLUSH_WITH_1_HIGH_CARD  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isThreeToAFlush(1,hand);
			}

		},
		 
		 QT_SUITED  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isQTSuited(hand);
			}

		},
		 
		 THREE_TO_A_STRAIGHT_FLUSH_TYPE_3  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isThreeToAStraightFlush(3,hand);
			}

		},
		 
		 KQ_KJ_UNSUITED  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isKQOrKJUnsuited(hand);
			}

		},
		 
		 ACE  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isAce(hand);
			}

		},
		 
		 KT_SUITED  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isKTSuited(hand);
			}

		},
		 
		 JACK_QUEEN_KING  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isJQK(hand);
			}

		},
		 
		 FOUR_TO_AN_INSIDE_STRAIGHT_WITH_NO_HIGH_CARDS  {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.isFourToAnInsideStraight(0,hand);
			}

		},
		 
		 THREE_TO_A_FLUSH_WITH_NO_HIGH_CARDS  {

				@Override
				public
				boolean matches(List<Card> hand) {
					// TODO Auto-generated method stub
					return Utils.isThreeToAFlush(0,hand);
				}

			},
		 
		 DISCARD_EVERYTHING  {

				@Override
				public
				boolean matches(List<Card> hand) {
					// TODO Auto-generated method stub
					return Utils.isDiscardEverything(hand);
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
