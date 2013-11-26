import java.awt.*;
import java.applet.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Blackjack extends Applet implements ActionListener {

	private Deck deck;
	private Player p;
	private Player dealer;
	private JLabel gameInfo;
	private int initalMoney = 1000;
	private boolean hasSecondPanel = false;

	public void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.deck = new Deck();

		deck.shuffle();

		p = new Human("Player", 20, true, true, initalMoney);
		dealer = new Dealer(17);

		this.gameInfo = new JLabel("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Playing");

		add(gameInfo);
		add(p.getPanel());
		add(dealer.getPanel());

		p.getButtons().addListeners(this);

		validate();
	}

	public void split() {
		//if (p.hasPair()) {
			remove(p.getPanel());
			remove(dealer.getPanel());

			HandPanel split = p.split();
			this.hasSecondPanel = true;

			split.getButtons().addListeners(this);

			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Playing");

			add(p.getPanel());
			add(split);
			add(dealer.getPanel());

			validate();
		//}
	}

	public void dealFirstHand() {
		p.getButtons().getButtonByName("stay").setEnabled(true);

		p.dealCard(deck, false, false);
		dealer.dealCard(deck, true, false);

		p.dealCard(deck, false, false);
		dealer.dealCard(deck, false, false);

		dealer.getPanel().changeScoreHidden(true);

		// if (!p.hasPair() || p.getMoney() < p.getMoneyBet()) {
		// 	p.getButtons().getButtonByName("split").setEnabled(false);
		// } else {
		// 	p.getButtons().getButtonByName("split").setEnabled(true);
		// }

		p.getButtons().getButtonByName("split").setEnabled(true);

		if (p.getScore() >= 21) {
			playDealer();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		boolean isSecondPanel = false;

		if (source.getText().startsWith("*")) {
			isSecondPanel = true;
		}

		if (source.getText().contains("Hit")) {
			p.getButtons().getButtonByName("double").setEnabled(false);
			p.getButtons().getButtonByName("split").setEnabled(false);
			p.dealCard(deck, false, isSecondPanel);
			validate();

			if (p.getScore() >= 21) {
				if (!hasSecondPanel) {
					playDealer();
				} else {
					p.getSecondPanel().getButtons().split();
					p.getPanel().getButtons().setAllActive(false);
				} 
			}
			return;
		} else if (source.getText().contains("Stay")) {
			if (hasSecondPanel && !isSecondPanel) {
				//Play other panel now
				p.getSecondPanel().getButtons().split();
				p.getPanel().getButtons().setAllActive(false);
				return;
			} else {
				p.getSecondPanel().getButtons().setAllActive(false);
				playDealer();
			}
			return;
		} else if (source.getText().equals("Reset")) {
			reset();
			return;
		} else if (source.getText().equals("New Hand")) {
			p.getButtons().setActive(true);
			p.getButtons().getButtonByName("New Hand").setEnabled(false);
			p.getButtons().getButtonByName("done").setEnabled(true);
			p.getButtons().getButtonByName("bet10").setEnabled(true);
			p.getButtons().getButtonByName("hit").setEnabled(false);
			p.getButtons().getButtonByName("stay").setEnabled(false);
			p.getButtons().getButtonByName("double").setEnabled(false);
			p.getButtons().getButtonByName("split").setEnabled(false);
			p.setScore(0, p.getPanel());
			p.betMoney(p.getMoneyBet() * -1);

			if (hasSecondPanel) {
				remove(p.getSecondPanel());
				p.getPanel().cardWidth = 200;
				p.getPanel().cardHeight = 300;
			}

			dealer.setScore(0, dealer.getPanel());
			p.resetHand();
			dealer.resetHand();

			dealer.getPanel().changeScoreHidden(true);

			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Playing");
			validate();
		} else if (source.getText().equals("Bet $10")) {
			p.betMoney(10);
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Playing");
		} else if (source.getText().equals("Bet $100")) {
			p.betMoney(100);
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Playing");
		} else if (source.getText().equals("Done Betting")) {
			p.getButtons().getButtonByName("done").setEnabled(false);
			p.getButtons().getButtonByName("bet10").setEnabled(false);
			p.getButtons().getButtonByName("bet100").setEnabled(false);
			p.getButtons().getButtonByName("hit").setEnabled(true);
			p.getButtons().getButtonByName("stay").setEnabled(true);
			p.getButtons().getButtonByName("split").setEnabled(true);
			dealFirstHand();
			p.getButtons().getButtonByName("double").setEnabled(true);
		} else if (source.getText().equals("Double Down")) {
			p.doubleDown();
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Playing");
			p.dealCard(deck, false, false);
			p.getButtons().getButtonByName("double").setEnabled(false);
			playDealer();
		} else if (source.getText().equals("Split Hand")) {
			p.getButtons().getButtonByName("split").setEnabled(false);
			split();
		}
		validate();
	}

	public void calculateWin() {

		if (dealer.getScore() > 21 && p.getScore() > 21) {
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Push!");
			p.calculateEarnings(1);
		}

		if (dealer.getScore() > 21) {
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: You Win!");
			p.calculateEarnings(0);
			return;
		} else if (p.getScore() > 21) {
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: You Lose!");
			return;
		}

		if (p.getScore() > dealer.getScore()) {
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: You Win!");
			p.calculateEarnings(0);
		} else if (p.getScore() < dealer.getScore()) {
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: You Lose!");
		} else {
			this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Push!");
			p.calculateEarnings(1);
		}

		validate();
	}

	public void playDealer() {
		p.getButtons().setActive(false);
		dealer.automizeHand(this.deck);
		p.getButtons().getButtonByName("New Hand").setEnabled(true);

		calculateWin();
	}

	public void reset() {
		p.getButtons().setActive(true);
		p.getButtons().getButtonByName("double").setEnabled(false);
		p.getButtons().getButtonByName("done").setEnabled(true);
		deck = new Deck();
		deck.shuffle();
		p.setScore(0, p.getPanel());
		p.setMoney(initalMoney);
		p.betMoney(p.getMoneyBet() * -1);
		p.resetHand();
		dealer.setScore(0, dealer.getPanel());
		dealer.resetHand();
		dealer.setMaxScore(17);

		dealer.getPanel().changeScoreHidden(true);

		if (hasSecondPanel) {
			remove(p.getSecondPanel());
			p.getPanel().cardWidth = 200;
			p.getPanel().cardHeight = 300;
		}

		this.gameInfo.setText("Money in the pot: $" + p.getPanel().getMoneyBet() + " - Game Progess: Playing");
		validate();
	}

}