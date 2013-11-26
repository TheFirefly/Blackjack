import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class HandPanel extends JPanel {

	private Hand hand;
	private int score;
	private boolean isScoreHidden;
	private JLabel scoreLabel;
	private ButtonPanel buttonPanel;
	private String scorePrefix;
	private int money;
	private String moneyPrefix;
	private int moneyBet;
	public int cardHeight;
	public int cardWidth;

	public HandPanel(Player p, boolean hasButtons, boolean hasMoney, int startingMoney, boolean isSecondPanel) {
		this.hand = new Hand();
		this.isScoreHidden = false;
		this.money = startingMoney;
		this.moneyBet = 0;
		this.score = 0;
		this.cardHeight = 300;
		this.cardWidth = 200;

		if (hasMoney) {
			this.moneyPrefix = "($" + money + ") ";
		} else {
			this.moneyPrefix = "";
		}

		this.scorePrefix = p.getName() + "'s Score: ";
		this.scoreLabel = new JLabel(moneyPrefix + scorePrefix + this.score);
		
		if (hasButtons) {
			this.buttonPanel = new ButtonPanel(isSecondPanel);
		}

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(scoreLabel);
		if (hasButtons) {
			add(buttonPanel);
		}
		add(hand);

		validate();
	}

	public void resizePanel(int x, int y) {
		for (int i = 0 ; i < this.hand.getNumberOfCards() ; i++) {
			Card c = this.hand.getCards()[i];
			this.hand.remove(c);
			c.setIcon(new ImageIcon(c.loadImage(c.toString(), false, x, y)));
			this.hand.add(c);
			this.cardHeight = y;
			this.cardWidth = x;
		}
	}

	public void calculateEarnings(int gameResult) {
		if (gameResult == 0) {
			//win
			updateMoney(this.moneyBet * 2);
		} else if (gameResult == 2) {
			//push
			updateMoney(this.moneyBet);
		}

		this.moneyBet = 0;
	}

	public void updateAfterSplit(int newScore) {
		this.score = newScore;
		this.scoreLabel.setText(this.moneyPrefix + this.scorePrefix + this.score);
		this.buttonPanel.split();
	}

	public int getMoney() {
		return this.money;
	}

	public int getMoneyBet() {
		return this.moneyBet;	
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int updateMoney(int moneyGained) {
		this.money += moneyGained;
		this.moneyPrefix = "($" + this.money + ") ";
		this.scoreLabel.setText(this.moneyPrefix + this.scorePrefix + this.score);

		if (moneyGained < 0) {
			betMoney(moneyGained * -1);
		}

		validate();

		return this.money;
	}

	public void betMoney(int moneyBet) {
		this.moneyBet += moneyBet;
	}

	public ButtonPanel getButtons() {
		return this.buttonPanel;
	}

	public void changeScoreHidden(boolean state) {
		if (state) {
			this.scoreLabel.setText(this.moneyPrefix + this.scorePrefix + "???");
		} else {
			this.scoreLabel.setText(this.moneyPrefix + this.scorePrefix + this.score);
		}

		validate();
	}

	public void setScore(int score) {
		this.score = score;
		this.scoreLabel.setText(this.moneyPrefix + this.scorePrefix + this.score);
	} 

	public int getScore() {
		return this.score;
	}

	public Hand getHand() {
		return this.hand;
	}

	public void setHand(Hand hand) {
		remove(this.hand);
		this.hand = hand;
		add(this.hand);
		validate();
	}
}