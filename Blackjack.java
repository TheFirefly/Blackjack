import java.awt.*;
import java.applet.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Blackjack extends Applet implements ActionListener {

	private Deck deck;
	private Player p;
	private Player dealer;
	private Button hit;
	private Button stay;
	private Button reset;

	public void init() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		repaint();

		hit = new Button("Hit");
		stay = new Button("Stay");
		reset = new Button("Reset");
		hit.addActionListener(this);
		stay.addActionListener(this);
		reset.addActionListener(this);
		hit.setMaximumSize(new Dimension(100, 20));
		stay.setMaximumSize(new Dimension(100, 20));
		reset.setMaximumSize(new Dimension(100, 20));
		add(hit);
		add(stay);
		add(reset);

		this.deck = new Deck();

		deck.shuffle();

		p = new Human("Test Player", deck.dealCard(), 20);
		Card c = deck.dealCard();
		c.setFaceDown(true);
		dealer = new Dealer(c, 17);

		add(p.getPanel());
		add(dealer.getPanel());

		p.dealCard(deck, false);
		dealer.dealCard(deck, false);

		if (p.getScore() >= 21) {
			playDealer();
		}
	}

	public void paint(Graphics g) {

		p.getPanel().paint(g);
		dealer.getPanel().paint(g);

		// p.getHand().draw(50, g);
		// dealer.getHand().draw(375, g);
	}

	public void actionPerformed(ActionEvent e) {
		Button source = (Button) e.getSource();

		if (source.getLabel().equals("Hit")) {
			System.out.println("HIT");
			p.dealCard(deck, false);
			repaint();

			if (p.getScore() >= 21) {
				playDealer();
			}
			return;
		} else if (source.getLabel().equals("Stay")) {
			playDealer();
			return;
		} else if (source.getLabel().equals("Reset")) {
			hit.setEnabled(true);
			stay.setEnabled(true);
			reset();
			return;
		}
	}

	public void playDealer() {
		hit.setEnabled(false);
		stay.setEnabled(false);

		if (p.getScore() > 21) {
			dealer.setMaxScore(11);
		}

		dealer.getHand().setAllFaceUp();
		repaint();

		while (dealer.getScore() < dealer.getMaxScore()) {
			dealer.dealCard(deck, false);
		}
	}

	public void reset() {
		deck = new Deck();
		deck.shuffle();
		p.setScore(0);
		p.resetHand(deck.dealCard());
		dealer.setScore(0);
		dealer.resetHand(deck.dealCard());
		dealer.setMaxScore(17);

		p.dealCard(deck, false);
		dealer.dealCard(deck, false);

		repaint();
	}

}