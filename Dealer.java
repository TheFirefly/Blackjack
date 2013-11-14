public class Dealer extends Player {

	public Dealer(Card initialCard, int maxScore) {
		super("Dealer", initialCard, maxScore);
	}

	@Override
	public void resetHand(Card card) {
		card.setFaceDown(true);
		super.resetHand(card);
	}

}