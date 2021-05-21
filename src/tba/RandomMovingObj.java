package tba;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomMovingObj extends MovingObj implements Runnable {

	private int excX = 0, excY = 0, panelWforRandObj = 0, panelHforRandObj = 0; // limit of the X and Y  or weight and height
	private int jumpedCoordinate = 0; // coordinate for the jump of a object 
	private int randomDirection = 0; // choose the direction of the object 
	private boolean moveUpDown = true, moveForwardBack = true; // the flag determined move forward back or up and down

	public RandomMovingObj(int x, int y, int sizeW, int sizeH, int jumpedCoordinate, int panelWforRandObj,
			int panelHforRandObj) {
		// TODO Auto-generated constructor stub
		super(x, y, sizeW, sizeH, Color.BLUE);
		this.jumpedCoordinate = jumpedCoordinate;
		this.panelWforRandObj = panelWforRandObj;
		this.panelHforRandObj = panelHforRandObj;
		this.randomDirection();
	}

	/*
	 * random direction of the object due to initialization
	 * */
	private void randomDirection() {
		// 1 -> up, 2 -> down, 3 -> back, 4 -> forward
		List<Integer> actionList = Arrays.asList(1, 2, 3, 4);
		Random rand = new Random();
		this.randomDirection = actionList.get(rand.nextInt(actionList.size()));
	}

	/*
	 * Modify the coordinate based on object direction and movement
	 */
	private void up() {
		excX = 0;
		excY = (int) -this.jumpedCoordinate;
		getModifiedY(excY);
	}

	private void down() {
		excX = 0;
		excY = (int) this.jumpedCoordinate;
		getModifiedY(excY);
	}

	private void back() {
		excX = (int) -this.jumpedCoordinate;
		excY = 0;
		getModifiedX(excX);
	}

	private void forward() {
		excX = (int) this.jumpedCoordinate;
		excY = 0;
		getModifiedX(excX);
	}

	@Override
	public void getModifiedX(int excX) {
		// TODO Auto-generated method stub
		int tempX = super.x + excX;
		if (tempX > this.panelWforRandObj) {
			tempX = this.panelWforRandObj;
			this.moveForwardBack = true;
		}
		
		if (tempX < 10) {
			tempX = 10;
			this.moveForwardBack = false;
		}

		super.x = tempX;
	}

	@Override
	public void getModifiedY(int excY) {
		// TODO Auto-generated method stub
		int tempY = super.y + excY;
		if (tempY > this.panelHforRandObj) {
			tempY = this.panelHforRandObj;
			this.moveUpDown = true;
		}
		if (tempY < 60) {
			tempY = 60;
			this.moveUpDown = false;
		}
		super.y = tempY;

	}

	/*
	 * randomly moving object based on random direction
	 * object will also return if it is reached the most higher or most lower coordinate 
	 * */
	private void radomMovementInStraightLine() {
		/*
		 * move up until the limit, when limit reached then move down
		 * */
		if (this.randomDirection == 1 || this.randomDirection == 2) {
			if (this.moveUpDown) {
				this.up();
			} else {
				this.down();
			}
				
		}

		/*
		 * move forward until the limit, when limit reached then move back
		 * */
		if (this.randomDirection == 3 || this.randomDirection == 4) {
			if (this.moveForwardBack) {
				this.back();
			} else {
				this.forward();
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.radomMovementInStraightLine();

	}

}
