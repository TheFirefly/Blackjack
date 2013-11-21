import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class Card extends JLabel {

	private int faceNumber;
	private int[] values;
	private String suit;
	private boolean faceDown;

	public Card(int faceNumber, String suit, int[] values) {
		this.faceNumber = faceNumber;
		this.values = values;
		this.suit = suit;
		this.faceDown = false;

		this.setIcon(new ImageIcon(this.loadImage(this.toString(), this.faceDown, 200, 300)));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	public boolean isFacedown() {
		return this.faceDown;
	}

	public void setFaceDown(boolean state) {
		this.faceDown = state;
		this.setIcon(new ImageIcon(this.loadImage(this.toString(), state, 200, 300)));
	}

	public int getFaceNumber() {
		return this.faceNumber;
	}

	public int[] getValues() {
		return this.values;
	}

	public String getSuit() {
		return this.suit;
	}

	private static Image loadImage(String name, boolean faceDown, int width, int height) {
		String path = null;
		Image image = null;
		Image scaled = null;

		try {
			if (faceDown) {
				path = "cards" + File.separator + "back-blue.png"; 
			} else {
				path = "cards" + File.separator + name + ".png";
			}
			image = ImageIO.read(new File(path));
			scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("Could not load card at path: " + path);
			e.printStackTrace();
		}

		return scaled;
	}

	public String getCardFace() {
		switch(this.faceNumber) {
			case 1:
				return "A";
			case 11:
				return "J";
			case 12:
				return "Q";
			case 13:
				return "K";
			default:
				return ("" + this.faceNumber);
		}
	}

	@Override
	public String toString() {
		return this.getCardFace() + this.getSuit(); 
	}

}