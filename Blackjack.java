import java.awt.*;
import java.applet.*;

public class Blackjack extends Applet {

	private Deck deck;
	private Player p;
	private Player dealer;

	public void init() {

		this.deck = new Deck();

		deck.shuffle();

		p = new Player("Player", deck.dealCard(), 17);
		dealer = new Player("Dealer", deck.dealCard(), 17);

		while(p.getScore() < p.getMaxScore()) {
			p.dealCard(deck);
		}

		if (p.getScore() > 21) {
			dealer.setMaxScore(11);
		} else {
			dealer.setMaxScore(p.getScore());
		}

		while(dealer.getScore() < dealer.getMaxScore()) {
			dealer.dealCard(deck);
		}
	}

	public void paint(Graphics g) {
		p.getHand().draw(50, g);
		dealer.getHand().draw(375, g);
	}

}