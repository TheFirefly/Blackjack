import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

	JButton hit;
	JButton stay;
	JButton newHand;
	JButton reset;
	JButton bet10;
	JButton bet100;
	JButton doubleDown;
	JButton split;
	JButton done;

	public ButtonPanel(boolean isSecondPanel) {
		String buttonPrefix = "";
		if (isSecondPanel) {
			buttonPrefix = "*";
		}

		this.hit = new JButton(buttonPrefix + "Hit");
		this.stay = new JButton(buttonPrefix + "Stay");
		this.newHand = new JButton(buttonPrefix + "New Hand");
		this.bet10 = new JButton(buttonPrefix + "Bet $10");
		this.bet100 = new JButton(buttonPrefix + "Bet $100");
		this.doubleDown = new JButton(buttonPrefix + "Double Down");
		this.split = new JButton(buttonPrefix + "Split Hand");
		this.done = new JButton(buttonPrefix + "Done Betting");
		this.reset = new JButton(buttonPrefix + "Reset");

		this.newHand.setEnabled(false);
		this.stay.setEnabled(false);
		this.hit.setEnabled(false);
		this.doubleDown.setEnabled(false);
		this.split.setEnabled(false);

		add(this.hit);
		add(this.stay);
		add(this.bet10);
		add(this.bet100);
		add(this.doubleDown);
		add(this.split);
		add(this.done);
		add(this.newHand);
		add(this.reset);

		this.setMaximumSize(new Dimension(1575, 100));
	}

	public void addListeners(Blackjack applet) {
		this.hit.addActionListener(applet);
		this.stay.addActionListener(applet);
		this.bet10.addActionListener(applet);
		this.bet100.addActionListener(applet);
		this.doubleDown.addActionListener(applet);
		this.split.addActionListener(applet);
		this.done.addActionListener(applet);
		this.newHand.addActionListener(applet);
		this.reset.addActionListener(applet);
	}

	public void split() {
		this.hit.setEnabled(true);
		this.stay.setEnabled(true);
		this.bet10.setEnabled(false);
		this.bet100.setEnabled(false);
		this.split.setEnabled(false);
		this.doubleDown.setEnabled(false);
	}

	public void setActive(boolean state) {
		this.hit.setEnabled(state);
		this.stay.setEnabled(state);
		this.bet10.setEnabled(state);
		this.bet100.setEnabled(state);
	}

	public void setAllActive(boolean state) {
		this.hit.setEnabled(state);
		this.stay.setEnabled(state);
		this.newHand.setEnabled(state);
		this.bet10.setEnabled(state);
		this.bet100.setEnabled(state);
		this.doubleDown.setEnabled(state);
		this.split.setEnabled(state);
		this.done.setEnabled(state);
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
		} else if (name.equalsIgnoreCase("bet10")) {
			return this.bet10;
		} else if (name.equalsIgnoreCase("bet100")) {
			return this.bet100;
		} else if (name.equalsIgnoreCase("split")) {
			return this.split;
		} else if (name.equalsIgnoreCase("double")) {
			return this.doubleDown;
		} else if (name.equalsIgnoreCase("done")) {
			return this.done;
		}
		return this.hit;
	}
}