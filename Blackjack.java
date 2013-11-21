import java.awt.*;
import java.applet.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Blackjack extends Applet implements ActionListener {

	private Deck deck;
	private Player p;
	private Player dealer;
	private JLabel cards;

	public void init() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.deck = new Deck();

		deck.shuffle();

		this.cards = new JLabel();

		add(this.cards);

		p = new Human("Test Player", deck.dealCard(), 20, true);
		Card c = deck.dealCard();
		c.setFaceDown(true);
		dealer = new Dealer(c, 17);

		p.getButtons().addListeners(this);

		add(p.getPanel());
		add(dealer.getPanel());

		p.dealCard(deck, false);
		dealer.dealCard(deck, false);

		dealer.getPanel().changeScoreHidden(true);

		if (p.getScore() >= 21) {
			playDealer();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();

		if (source.getText().equals("Hit")) {
			p.dealCard(deck, false);
			validate();

			if (p.getScore() >= 21) {
				playDealer();
			}
			return;
		} else if (source.getText().equals("Stay")) {
			playDealer();
			return;
		} else if (source.getText().equals("Reset")) {
			reset();
			return;
		} else if (source.getText().equals("New Hand")) {
			p.getButtons().setActive(true);
			p.getButtons().getButtonByName("New Hand").setEnabled(false);
			p.resetHand(deck.dealCard());
			dealer.resetHand(deck.dealCard());
			p.dealCard(deck, false);
			dealer.dealCard(deck, false);

			dealer.getPanel().changeScoreHidden(true);

			validate();

		}
	}

	public void playDealer() {
		p.getButtons().setActive(false);
		dealer.automizeHand(this.deck);
		p.getButtons().getButtonByName("New Hand").setEnabled(true);
	}

	public void reset() {
		p.getButtons().setActive(true);
		deck = new Deck();
		deck.shuffle();
		p.setScore(0);
		p.resetHand(deck.dealCard());
		dealer.setScore(0);
		dealer.resetHand(deck.dealCard());
		dealer.setMaxScore(17);

		p.dealCard(deck, false);
		dealer.dealCard(deck, false);

		dealer.getPanel().changeScoreHidden(true);

		validate();
	}

}