import java.util.List;
import java.util.ArrayList;

public class Player {

	private Hand hand;
	private String type;
	private int score;
	private int maxScore;

	public Player(String type, Card initialCard, int maxScore) {
		this.type = type;
		this.score = 0;
		this.hand = new Hand(initialCard);
		this.updateScore(initialCard);
		this.maxScore = maxScore;

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
		List<Card> aces = new ArrayList<Card>(4);

		for (int i = 0 ; i < hand.getNumberOfCards() ; i++) {
			if (hand.getCards()[i].getValues().length == 2) {
				//Ace, save for later
				aces.add(hand.getCards()[i]);
			} else {
				newScore += hand.getCards()[i].getValues()[0];
			}
		}

		//Now calculate each ace value so that ace value is 11 until it makes it > 21
		for (Card ace : aces) {
			if (newScore + ace.getValues()[1] <= 21 && newScore + ace.getValues()[1] != this.maxScore) {
				newScore += ace.getValues()[1];
			} else {
				newScore += ace.getValues()[0];
			}
		}

		this.score = newScore;

		return this.score;
	}

	public int getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(int score) {
		this.maxScore = score;
	}

	public int getScore() {
		return this.score;
	}

	public Hand getHand() {
		return this.hand;
	}
}