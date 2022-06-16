package strategy;

import java.util.List;

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

				for (int i = 1; i < hand.size(); i++) {
					if (
							hand.get(i).getRank().getValue() != hand.get(i - 1).getRank().getValue() + 1
							) {
						return false;
					}
				}

				return true;
			}
		},

		THREE_OF_A_KIND {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return false;
			}

		},

		TWO_PAIR {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return false;
			}

		},

		JACKS_OR_BETTER {

			@Override
			public
			boolean matches(List<Card> hand) {
				// TODO Auto-generated method stub
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
