package strategy;

import java.util.List;

import card.Card;

public enum DificultHand {
	

	ROYAL_FLUSH {

		@Override
		public
		List<Integer> matches(List<Card> hand) {
			
			return Utils.flush("ROYAL_FLUSH", hand);
		}
	},
	

	STRAIGHT_FLUSH {
		
		@Override
		public List<Integer> matches(List<Card> hand) {
			
			return Utils.flush("STRAIGHT_FLUSH", hand);
		}
	},
	
	
	FOUR_ACES {
		
		@Override
		public
		List<Integer> matches(List<Card> hand) {

			return Utils.fourOfAKind("FOUR_ACES", hand);
		}
		
	},
	
	FOUR_5_K {
		
		@Override
		public
		List<Integer> matches(List<Card> hand) {

			return Utils.fourOfAKind("FOUR_5_K", hand);

		}
		
	},

	FOUR_2_4 {
		
		@Override
		public
		List<Integer> matches(List<Card> hand) {

			return Utils.fourOfAKind("FOUR_2_4", hand);

		}
		
	},
	
	FOUR_TO_ROYAL_FLUSH {
		
		@Override
		public
		List<Integer> matches(List<Card> hand) {

			return Utils.fourOfAKind("FOUR_TO_ROYAL_FLUSH", hand);

		}
		
	},
	
	THREE_ACES {
		
		@Override
		public
		List<Integer> matches(List<Card> hand) {

			return Utils.threeAces(hand);

		}
		
	},
	
	STRAIGHT {
		
		@Override
		public
		List<Integer> matches(List<Card> hand) {
			
			return Utils.straight(hand);
		}

	},
	
	FLUSH {
		
		@Override
		public
		List<Integer> matches(List<Card> hand) {

			return Utils.flush("FLUSH", hand);
		}
	},
	
	

		FULL_HOUSE {
		
			@Override
			public
			List<Integer> matches(List<Card> hand) {

			 return Utils.fullHouse(hand);
			}
		},


		THREE_OF_A_KIND {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.threeOfAKindEA(hand);
			}

		},
		
		//To advice
		FOUR_TO_A_STRAIGHT_FLUSH {
			
			@Override
			public
			List<Integer> matches(List<Card> hand) {

				return Utils.fourToStraightFlush(hand);

			}
			
		},

		TWO_PAIR {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.twoPair(hand);
			}

		},
		
		
		//To advice
		HIGH_PAIR {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.highPair(hand);
			}

		},
		//To advice
		FOUR_TO_A_FLUSH {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.fourToAFlush(hand);
			}

		},
		//To advice
		THREE_TO_A_ROYAL_FLUSH {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.threeToRoyalFlush(hand);
			}

		},
		//To advice
		FOUR_TO_AN_OUTSIDE_STRAIGHT {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.fourToOutsideStraight(hand);
			}

		},
		//To advice
		LOW_PAIR {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.lowPair(hand);
			}

		},
		//To advice
		AKQJ_UNSUITED {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.AKQJUnsuited(hand);
			}

		},
		//To advice
		THREE_TO_A_STRAIGHT_FLUSH_TYPE_1 {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.threeToAStraightFlush(1,hand);
			}

		},
		
		FOUR_TO_AN_INSIDE_STRAIGHT_3_HIGH_CARDS {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.fourToAnInsideStraight(3,hand);
			}

		},
		
		QJ_SUITED {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.QJSuited(hand);
			}

		},
		
		THREE_TO_A_FLUSH_WITH_2_HIGH_CARDS  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.threeToAStraightFlush(2,hand);
			}

		},
		
		TWO_SUITED_HIGH_CARDS  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.twoSuitedHighCards(hand);
			}

		},
		
		FOUR_TO_AN_INSIDE_STRAIGHT_WITH_TWO_HIGH_CARDS  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.fourToAnInsideStraight(2,hand);
			}

		},
		
		THREE_TO_A_STRAIGHT_FLUSH_TYPE_2  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.threeToAStraightFlush(2,hand);
			}

		},
		
		FOUR_TO_AN_INSIDE_STRAIGHT_WITH_ONE_HIGH_CARD  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.fourToAnInsideStraight(1,hand);
			}

		},
		
		 KQJ_UNSUITED  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.KQJUnsuited(hand);
			}

		},
		 
		 JT_SUITED  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.JTSuited(hand);
			}

		},
		 
		 QJ_UNSUITED  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.QJUnsuited(hand);
			}

		},
		 
		 THREE_TO_A_FLUSH_WITH_1_HIGH_CARD  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.threeToAStraightFlush(1,hand);
			}

		},
		 
		 QT_SUITED  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.QTSuited(hand);
			}

		},
		 
		 THREE_TO_A_STRAIGHT_FLUSH_TYPE_3  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.threeToAStraightFlush(3,hand);
			}

		},
		 
		 KQ_KJ_UNSUITED  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				List<Integer> list = Utils.KQUnsuited(hand);
				if(list == null) {
					list = Utils.KJUnsuited(hand);
				}
				return list;
			}

		},
		 
		 ACE  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.ace(hand);
			}

		},
		 
		 KT_SUITED  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.KTSuited(hand);
			}

		},
		 
		 JACK_QUEEN_KING  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				List<Integer> list = Utils.jack(hand);
				if(list == null) {
					list = Utils.queen(hand);
				}
				if(list == null) {
					list = Utils.king(hand);
				}
				return list;
			}

		},
		 
		 FOUR_TO_AN_INSIDE_STRAIGHT_WITH_NO_HIGH_CARDS  {

			@Override
			public
			List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return Utils.fourToAnInsideStraight(0,hand);
			}

		},
		 
		 THREE_TO_A_FLUSH_WITH_NO_HIGH_CARDS  {

				@Override
				public
				List<Integer> matches(List<Card> hand) {
					// TODO Auto-generated method stub
					return Utils.threeToAStraightFlush(0,hand);
				}

			},

		
		NONE {
			
			@Override
			public List<Integer> matches(List<Card> hand) {
				// TODO Auto-generated method stub
				return null;
			}
		};

			public abstract List<Integer> matches(List<Card> hand);
			
		}

