package tba;

import java.awt.Color;

public class ControlledMovingObj extends MovingObj {
	private int excX = 0, excY = 0;
	private int jumpedCoordinate = 0;

	public ControlledMovingObj(int x, int y, int sizeW, int sizeH, int jumpedCoordinate) {
		super(x, y, sizeW, sizeH, Color.WHITE );
		this.jumpedCoordinate = jumpedCoordinate;
	}

	/*
	 * Modify the coordinate based on key pressed
	 */
	public void up() {
		excX = 0;
		excY = (int) -this.jumpedCoordinate;
		getModifiedY(excY);
	}

	public void down() {
		excX = 0;
		excY = (int) this.jumpedCoordinate;
		getModifiedY(excY);
	}

	public void left() {
		excX = (int) -this.jumpedCoordinate;
		excY = 0;
		getModifiedX(excX);
	}

	public void right() {
		excX = (int) this.jumpedCoordinate;
		excY = 0;
		getModifiedX(excX);
	}

	@Override
	public void getModifiedX(int excX) {
		// TODO Auto-generated method stub
		int tempX = super.x + excX;
		
		// reached the higher limit
		if (tempX > 780) {
			tempX = 780;
		}
		
		// reached the lower limit
		if (tempX < 10) {
			tempX = 10;
		}

		super.x = tempX;
	}

	@Override
	public void getModifiedY(int excY) {
		// TODO Auto-generated method stub
		int tempY = super.y + excY;
		
		// reached the higher limit
		if (tempY > 720) {
			tempY = 720;
		}
		
		// reached the lower limit
		if (tempY < 10) {
			tempY = 10;
		}
		super.y = tempY;
	}

}
