import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.List;
import java.util.ArrayList;

public class Hand extends JPanel {

	private Card[] cards;
	private int amountOfCards;

	public Hand(Card card) {
		this.cards = new Card[11];
		this.cards[0] = card;
		this.amountOfCards = 1;

		add(card);

		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void setAllFaceUp() {
		for (int i = 0 ; i < amountOfCards ; i++) {
			this.cards[i].setFaceDown(false);
		}
	}

	//Adds card to hand
	public int addCard(Card c, boolean faceDown, int maxScore) {
		if (amountOfCards < 11) {
			c.setFaceDown(faceDown);
			cards[amountOfCards] = c;
			amountOfCards++;

			add(c);
		}

		return calculateScore(c, maxScore);
	}

	public int calculateScore(Card newCard, int maxScore) {

		int newScore = 0;
		List<Card> aces = new ArrayList<Card>(4);

		for (int i = 0 ; i < getNumberOfCards() ; i++) {
			if (getCards()[i].getValues().length == 2) {
				//Ace, save for later
				aces.add(getCards()[i]);
			} else {
				newScore += getCards()[i].getValues()[0];
			}
		}

		//Now calculate each ace value so that ace value is 11 until it makes it > 21
		for (Card ace : aces) {
			if (newScore + ace.getValues()[1] <= 21 && newScore + ace.getValues()[1] != maxScore) {
				newScore += ace.getValues()[1];
			} else {
				newScore += ace.getValues()[0];
			}
		}

		return newScore;
	}

	public Card[] getCards() {
		return this.cards;
	}

	public int getNumberOfCards() {
		return this.amountOfCards;
	}

	@Override
	public String toString() {
		String output = "";
		for (Card c : this.cards) {
			output += c + " ";
		}

		return output;
	}

}