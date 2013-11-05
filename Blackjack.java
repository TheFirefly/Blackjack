import java.awt.*;
import java.applet.*;

public class Blackjack extends Applet {

	private Deck deck;
	private Player p;

	public void init() {

		this.deck = new Deck();

		deck.shuffle();

		p = new Player("Player", deck.dealCard());

		while(p.getScore() < 17) {
			p.dealCard(deck);
		}
	}

	public void paint(Graphics g) {
		p.getHand().draw(g);
	}

}