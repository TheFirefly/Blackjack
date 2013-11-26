public class Player {
	private int maxScore;
	private HandPanel panel;
	private HandPanel splitPanel;
	private String name;

	public Player(String name, int maxScore, boolean hasButtons, boolean hasMoney, int startingMoney) {
		this.maxScore = maxScore;
		this.name = name;
		this.panel = new HandPanel(this, hasButtons, hasMoney, startingMoney, false);
		this.splitPanel = new HandPanel(this, hasButtons, hasMoney, startingMoney, true);
	}

	public Card dealCard(Deck deck, boolean faceDown, boolean isSecondPanel) {
		HandPanel workingPanel = this.panel;
		if (isSecondPanel) {
			workingPanel = this.splitPanel;
		}
		Card c = deck.dealCard();
		this.setScore(workingPanel.getHand().addCard(c, faceDown, this.maxScore, workingPanel.cardWidth, workingPanel.cardHeight), workingPanel);
		workingPanel.validate();
		
		return c;
	}

	public boolean hasPair() {
		if (this.panel.getHand().getNumberOfCards() == 2) {
			Card[] hand = this.panel.getHand().getCards();
			if (hand[0].getFaceNumber() == hand[1].getFaceNumber()) {
				return true;
			}
		}

		return false;
	}

	public HandPanel split() {
		// if (!hasPair()) {
		// 	return null;
		// }

		Card[] hand1 = new Card[11];
		Card[] hand2 = new Card[11];
		hand1[0] = this.panel.getHand().getCards()[0];
		hand2[0] = this.panel.getHand().getCards()[1];

		this.panel.setHand(this.panel.getHand().setCards(hand1, 1));
		this.splitPanel.setHand(new Hand().setCards(hand2, 1));

		this.panel.updateAfterSplit(hand1[0].getValues()[0]);
		this.splitPanel.updateAfterSplit(hand2[0].getValues()[0]);
		this.splitPanel.getButtons().setAllActive(false);

		this.splitPanel.setMoney(this.panel.getMoney());
		this.panel.betMoney(this.panel.getMoneyBet());

		this.panel.resizePanel(133, 200);
		this.splitPanel.resizePanel(133, 200);

		return splitPanel;
	}

	public void calculateEarnings(int gameResult) {
		this.panel.calculateEarnings(gameResult);
	}

	public int getMoneyBet() {
		return this.panel.getMoneyBet();
	}

	public int getMoney() {
		return this.panel.getMoney();
	}

	public void setMoney(int money) {
		this.panel.setMoney(money);
	}

	public int betMoney(int money) {
		if (getMoney() - money < 0) {
			return -1;
		}

		return this.panel.updateMoney(money * -1);
	}

	public void doubleDown() {
		betMoney(this.panel.getMoneyBet());
	}

	public void automizeHand(Deck d) {}

	public ButtonPanel getButtons() {
		return this.panel.getButtons();
	}

	public int getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(int score) {
		this.maxScore = score;
	}

	public void setScore(int score, HandPanel workingPanel) {
		workingPanel.setScore(score);
	}

	public int getScore() {
		return this.panel.getScore();
	}

	public void resetHand() {
		this.panel.setHand(new Hand());
		this.splitPanel.setHand(new Hand());
		this.panel.validate();
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

	public HandPanel getSecondPanel() {
		return this.splitPanel;
	}

	public void setSecondPanel(HandPanel panel) {
		this.splitPanel = panel;
	}
}