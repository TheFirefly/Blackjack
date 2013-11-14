public class Player {
	private int maxScore;
	private HandPanel panel;
	private String name;

	public Player(String name, Card initialCard, int maxScore) {
		this.maxScore = maxScore;
		this.name = name;
		this.panel = new HandPanel(this, initialCard);

		System.out.println("Card dealt: " + initialCard);
	}

	public Card dealCard(Deck deck, boolean faceDown) {
		Card c = deck.dealCard();
		this.setScore(this.panel.getHand().addCard(c, faceDown, this.maxScore));
		System.out.println("Card dealt: " + c);
		System.out.println("Score: " + this.panel.getScore());
		
		return c;
	}

	public int getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(int score) {
		this.maxScore = score;
	}

	public void setScore(int score) {
		this.panel.setScore(score);
	}

	public int getScore() {
		return this.panel.getScore();
	}

	public void resetHand(Card card) {
		this.panel.setHand(new Hand(card));
	}

	public Hand getHand() {
		return this.panel.getHand();
	}

	public String getName() {
		return this.name;
	}

	public HandPanel getPanel() {
		return this.panel;
	}
}