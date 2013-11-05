import java.util.Random;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Deck {

	private Card[] cards;
	private int cardsDealt;

	public Deck() {
		//Hearts = 0, Diamonds = 1, Clubs = 2, Spades = 3
		this.cards = new Card[52];
		this.cardsDealt = 0;
		String suit = "Hearts";
		int index = 0;

		for (int s = 1 ; s <= 4 ; s++) {
			switch (s) {
				case 1:
					suit = "Hearts";
					break;
				case 2:
					suit = "Diamonds";
					break;
				case 3:
					suit = "Clubs";
					break;
				case 4:
					suit = "Spades";
					break;
			}
			for (int i = 1 ; i <= 13 ; i++) {
				int[] values = new int[]{i};
				if (i > 10) {
					values = new int[]{10};
				} else if (i == 1) {
					values = new int[]{1, 11};
				}
				Card card = new Card(i, suit, values);
				//System.out.println("New card: " + card);
				this.cards[index] = card;
				index++;
			}
		}
	}

	public void shuffle() {
		Random r = new Random();
		int card = r.nextInt(52);

		for (int i = this.cards.length - 1 ; i >= 0 ; i--) {
			card = r.nextInt(i + 1);
			Card temp = this.cards[card];
			this.cards[card] = this.cards[i];
			this.cards[i] = temp;
		}
	}

	public void draw(Graphics g) {
		int x = 50;
		for (int i = 0 ; i < this.cards.length ; i++) {
			this.cards[i].draw(g, this.cards[i].toString(), new Rectangle(x, 50, 200, 300));
			x += 225;
		}
	}

	public Card dealCard() {
		cardsDealt++;
		return this.cards[this.cardsDealt - 1];
	}
}