public class Deck {

	private Card[] cards;

	public Deck() {
		//Hearts = 0, Diamonds = 1, Clubs = 2, Spades = 3
		this.cards = new Card[52];
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

	public static void main(String[] args) {
		Deck d = new Deck();

		d.printDeck();
	}

	public void printDeck() {
		for (int i = 0 ; i < this.cards.length ; i++) {
			System.out.println(this.cards[i]);
		}
	}
}