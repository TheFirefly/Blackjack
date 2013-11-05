import java.awt.Graphics;
import java.awt.Rectangle;

public class Hand {

	private Card[] cards;
	private int amountOfCards;

	public Hand(Card card) {
		this.cards = new Card[11];
		this.cards[0] = card;
		this.amountOfCards = 1;
	}

	//Adds card to hand
	public void addCard(Card c) {
		if (amountOfCards < 11) {
			cards[amountOfCards] = c;
			amountOfCards++;
		}
	}

	public void draw(Graphics g) {
		int x = 50;
		
		for (int i = 0 ; i < amountOfCards ; i++) {
			this.cards[i].draw(g, this.cards[i].toString(), new Rectangle(x, 50, 200, 300));
			x += 100;
		}
	}

	public Card[] getCards() {
		return this.cards;
	}

	public int getNumberOfCards() {
		return this.amountOfCards;
	}

}