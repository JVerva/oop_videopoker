package strategy;

import java.util.List;

import card.Card;
/**
 *  Poker Hands Combinations Double bonus 10/7
 */
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
	
	FOUR_2_4 {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isFourOfAKind("FOUR_2_4", hand);

		}
		
	},
	
	FOUR_5_K {
		
		@Override
		public
		boolean matches(List<Card> hand) {

			return Utils.isFourOfAKind("FOUR_5_K", hand);

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
		public boolean matches(List<Card> hand) {
			return Utils.isStraight(hand);
		}
	},


	THREE_OF_A_KIND {

		@Override
		public
		boolean matches(List<Card> hand) {
			return Utils.isThreeOfAKind(hand);
		}
	},

	TWO_PAIR {

		@Override
		public
		boolean matches(List<Card> hand) {
			return Utils.isTwoPair(hand);
		}

	},

		
		JACKS_OR_BETTER {

		@Override
		public
		boolean matches(List<Card> hand) {
			return Utils.isJacksOrBetter(hand);
			}

		},
		 
		
		NONE {
			
			@Override
			public boolean matches(List<Card> hand) {
				return true;
			}
		};

		public abstract boolean matches(List<Card> hand);
}

