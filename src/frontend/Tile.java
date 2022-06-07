package frontend;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
	
	private int cardinal, xPos, yPos; //xPos and yPos describe the graphical position.

	public Tile(int x, int y) {
		this.setWidth(x);
		this.setHeight(y);
		
		this.setFill(Color.LIGHTCYAN);
		this.setStroke(Color.BLACK);
	}

	public int getCardinal() {
		return cardinal;
	}

	public void setCardinal(int cardinal) {
		this.cardinal = cardinal;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
}
