public class Card {

	private int faceNumber;
	private int[] values;
	private String suit;

	public Card(int faceNumber, String suit) {
		this(faceNumber, suit, new int[]{faceNumber});
	}

	public Card(int faceNumber, String suit, int[] values) {
		this.faceNumber = faceNumber;
		this.values = values;
		this.suit = suit;
	}

	public int getFaceNumber() {
		return this.faceNumber;
	}

	public int[] getValues() {
		return this.values;
	}

	public String getSuit() {
		return this.suit;
	}

	public String getCardFace() {
		switch(this.faceNumber) {
			case 1:
				return "Ace";
			case 11:
				return "Jack";
			case 12:
				return "Queen";
			case 13:
				return "King";
			default:
				return ("" + this.faceNumber);
		}
	}

	@Override
	public String toString() {
		return this.getCardFace() + " of " + this.getSuit(); 
	}

}