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

	public Card(int faceNumber, String suit, int[] values) {
		this.faceNumber = faceNumber;
		this.values = values;
		this.suit = suit;
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

	private static Image loadImage(String name) {
		String path = null;
		Image image = null;

		try {
			path = "cards" + File.separator + name + ".png";
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Could not load card at path: " + path);
			e.printStackTrace();
		}

		return image;
	}

	public void draw(Graphics g, String name, Rectangle r) {
		g.drawImage(Card.loadImage(name), r.x, r.y, r.width, r.height, null);
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