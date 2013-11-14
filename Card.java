import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Card {

	private int faceNumber;
	private int[] values;
	private String suit;
	private boolean faceDown;

	public Card(int faceNumber, String suit, int[] values) {
		this.faceNumber = faceNumber;
		this.values = values;
		this.suit = suit;
		this.faceDown = false;
	}

	public void setFaceDown(boolean state) {
		this.faceDown = state;
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

	private static Image loadImage(String name, boolean faceDown) {
		String path = null;
		Image image = null;

		try {
			if (faceDown) {
				path = "cards" + File.separator + "back-blue.png"; 
			} else {
				path = "cards" + File.separator + name + ".png";
			}
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Could not load card at path: " + path);
			e.printStackTrace();
		}

		return image;
	}

	public void draw(Graphics g, String name, Rectangle r) {
		g.drawImage(Card.loadImage(name, this.faceDown), r.x, r.y, r.width, r.height, null);
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