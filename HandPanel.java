import java.awt.*;

public class HandPanel extends Panel {

	private Hand hand;
	private int score;
	private Label scoreLabel;
	private Label title;

	public HandPanel(Player p, Card initialCard) {
		this.hand = new Hand(initialCard);
		this.score = this.hand.calculateScore(initialCard, p.getMaxScore());
		this.scoreLabel = new Label("" + this.score);
		this.title = new Label(p.getName() + "'s Hand");

		add(title);
		add(scoreLabel);
	}

	public void setScore(int score) {
		this.score = score;
		this.scoreLabel.setText("" + this.score);
	} 

	public int getScore() {
		return this.score;
	}

	public void paint(Graphics g) {
		this.hand.draw(getY(), g);
		System.out.println("Painted hand: " + this.hand + " Y: " + getY());
	}

	public Hand getHand() {
		return this.hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

}