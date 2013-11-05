public class Player {

	private Hand hand;
	private String type;
	private int score;

	public Player(String type, Card initialCard) {
		this.type = type;
		this.score = 0;
		this.hand = new Hand(initialCard);
		this.updateScore(initialCard);

		System.out.println("Card dealt: " + initialCard);
	}

	public Card dealCard(Deck deck) {
		Card c = deck.dealCard();
		hand.addCard(c);

		this.updateScore(c);
		System.out.println("Card dealt: " + c);

		System.out.println("Score: " + this.score);
		
		return c;
	}

	public int updateScore(Card newCard) {

		int newScore = 0;
		Card[] aces = new Card[4];
		int num = 0;

		for (int i = 0 ; i < hand.getNumberOfCards() ; i++) {
			if (hand.getCards()[i].getValues().length == 2) {
				//Ace, save for later
				aces[0] = hand.getCards()[i];
				num++;
			} else {
				newScore += hand.getCards()[i].getValues()[0];
			}
		}

		//Now calculate each ace value;
		for (int i = 0 ; i < num ; i++) {
			if (newScore + aces[i].getValues()[1] <= 21) {
				newScore += aces[i].getValues()[1];
			} else {
				newScore += aces[i].getValues()[0];
			}
		}

		this.score = newScore;

		return this.score;
	}

	public int getScore() {
		return this.score;
	}
}