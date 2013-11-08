import java.awt.Component.*;

public class HandPanel extends JPanel {

	private Hand hand;
	private JLabel score;
	private JLabel title;
	private Player player;

	public HandPanel(Player p) {
		this.hand = p.getHand();
		this.score = 0;
		this.title = p.getType() + "'s Hand";
		this.player = p;
	}

	public void updateScore() {
		this.score = p.getScore();
	}

}