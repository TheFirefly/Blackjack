public class Hand {

	private Card[] cards;
	private int amountOfCards;

	public Hand(Card card) {
		this.cards = new Card[11];
		this.cards[0] = card;
		this.amountOfCards = 1;
	}

	public void addCard(Card c) {
		if (amountOfCards < 11) {
			cards[amountOfCards] = c;
			amountOfCards++;
		}
	}

	public Card[] getCards() {
		return this.cards;
	}

	public int getNumberOfCards() {
		return this.amountOfCards;
	}

}