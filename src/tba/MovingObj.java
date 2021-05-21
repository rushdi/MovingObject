package tba;

import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingObj {
	public int x, y; // coordinate of the object
	private int sizeW, sizeH; // size of the object
	private Color color; // color of the object

	public MovingObj(int x, int y, int sizeW, int sizeH, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.sizeW = sizeW;
		this.sizeH = sizeH;
	}

	/*
	 * Draw the Oval
	 */
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x, this.y, sizeW, sizeH);
	}

	/*
	 * check the movement limit based on panel size in X direction
	 */
	public abstract void getModifiedX(int excX);

	/*
	 * check the movement limit based on panel size in Y direction
	 */
	public abstract void getModifiedY(int excY);
}
