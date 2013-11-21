import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

	JButton hit;
	JButton stay;
	JButton newHand;
	JButton reset;

	public ButtonPanel() {
		this.hit = new JButton("Hit");
		this.stay = new JButton("Stay");
		this.newHand = new JButton("New Hand");
		this.reset = new JButton("Reset");

		this.newHand.setEnabled(false);

		add(this.hit);
		add(this.stay);
		add(this.newHand);
		add(this.reset);

		this.setMaximumSize(new Dimension(650, 100));

		//this.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void addListeners(Blackjack applet) {
		this.hit.addActionListener(applet);
		this.stay.addActionListener(applet);
		this.newHand.addActionListener(applet);
		this.reset.addActionListener(applet);
	}

	public void setActive(boolean state) {
		this.hit.setEnabled(state);
		this.stay.setEnabled(state);
	}

	public JButton getButtonByName(String name) {
		if (name.equalsIgnoreCase("hit")) {
			return this.hit;
		} else if (name.equalsIgnoreCase("stay")) {
			return this.stay;
		} else if (name.equalsIgnoreCase("new hand")) {
			return this.newHand;
		} else if (name.equalsIgnoreCase("reset")) {
			return this.reset;
		}
		return this.hit;
	}
}