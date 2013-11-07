import java.awt.*;
import java.applet.*;

public class Blackjack extends Applet {

	private Deck deck;
	private Deck deck2;
	private Player p;
	private Player dealer;

	public void init() {

		this.deck = new Deck();
		this.deck2 = new Deck();

		deck.shuffle();

		p = new Player("Player", deck.dealCard(), 20);
		dealer = new Player("Dealer", deck.dealCard(), 17);

		p.dealCard(deck);
		while (p.getScore() < p.getMaxScore()) {
			p.dealCard(deck);
		}

		if (p.getScore() > 21) {
			dealer.setMaxScore(11);
		}

		dealer.dealCard(deck);
		while (dealer.getScore() < dealer.getMaxScore()) {
			dealer.dealCard(deck);
		}
	}

	public void paint(Graphics g) {

		deck.draw(g, 375);
		deck2.draw(g, 50);


		// p.getHand().draw(50, g);
		// dealer.getHand().draw(375, g);
	}

}