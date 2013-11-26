public class Dealer extends Player {

	public Dealer(int maxScore) {
		super("Dealer", maxScore, false, false, 0);
	}

	public void automizeHand(Deck d) {
		getPanel().changeScoreHidden(false);

		getHand().setAllFaceUp();
		getHand().validate();

		while (getScore() < getMaxScore()) {
			dealCard(d, false, false);
		}
	}

}