package tba;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * initialize value for the moving object
	 */
	private int controlledObjStartingCoordinateX = 400, controlledObjStartingCoordinateY = 720, objectSizeWidth = 15,
			objectSizeHeight = 15, jumpedCoordinate = 10, panelWforRandObj = 780, panelHforRandObj = 680;

	Random rand = new Random();
	private int minRandomObjNo = 20, maxRandomObjNo = 30,
			noOfRandObj = rand.nextInt((maxRandomObjNo - minRandomObjNo) + 1) + minRandomObjNo;

	private ControlledMovingObj controlledMovingObj;

	private List<RandomMovingObj> randomMovingObjList = new ArrayList<RandomMovingObj>();

	private boolean showCollisionDialog = false, showWiningDialog = false;

	/*
	 * initialization of the timer for repaint
	 */
	javax.swing.Timer timer = new javax.swing.Timer(30, this);

	/*
	 * initialization of the keys and disable others keys initialize the key
	 * listener
	 */
	public MainPanel() {
		controlledMovingObj = new ControlledMovingObj(controlledObjStartingCoordinateX,
				controlledObjStartingCoordinateY, objectSizeWidth, objectSizeHeight, jumpedCoordinate);

		for (int i = 0; i < this.noOfRandObj; i++) {
			RandomMovingObj randomMovingObj = new RandomMovingObj((int) (Math.random() * this.panelWforRandObj),
					(int) (Math.random() * this.panelHforRandObj), objectSizeWidth, objectSizeHeight, jumpedCoordinate,
					panelWforRandObj, panelHforRandObj);
			randomMovingObjList.add(randomMovingObj);
		}

		timer.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setBackground(Color.black);
	}

	/*
	 * collision detection 
	 * */
	private void objectCollisionDetection() {

		Shape controlledOval = new Ellipse2D.Double(this.controlledMovingObj.x, this.controlledMovingObj.y,
				this.objectSizeWidth, this.objectSizeHeight);
		for (RandomMovingObj randomMovingObj : randomMovingObjList) {

			Shape randomOval = new Ellipse2D.Double(randomMovingObj.x, randomMovingObj.y, this.objectSizeWidth,
					this.objectSizeHeight);
			if (controlledOval.intersects(randomOval.getBounds())) {
				this.showCollisionDialog = true;
				break;
			}
		}
	}

	/*
	 * Show the dialog box based on the object collision or the winning of the match
	 * */
	private void showDialogBox() {
		if (this.showCollisionDialog || this.showWiningDialog) {
			int dialogButtonOptions = JOptionPane.YES_NO_OPTION;
			Frame dialogboxFrame = new Frame();
			String title = this.showCollisionDialog ? "!!! Collision Detected !!!" : "!!! Winner !!!";
			String message = this.showCollisionDialog
					? "Your vehicle Collied with another vehicle. \n Do you want to restart the game?"
					: "You win the match. \n Do you want to restart the game?";

			int dialogBoxStatus = JOptionPane.showConfirmDialog(dialogboxFrame, message, title, dialogButtonOptions);

			if (dialogBoxStatus == 0) {
				dialogboxFrame.dispose();
				controlledMovingObj = new ControlledMovingObj(controlledObjStartingCoordinateX,
						controlledObjStartingCoordinateY, objectSizeWidth, objectSizeHeight, jumpedCoordinate);
				this.showCollisionDialog = false;
				this.showWiningDialog = false;
			} else {
				System.exit(1);
			}
		}
	}

	/*
	 * draw the 2D controlled object in this case it is circle or ellipse
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// draw the controlled object
		controlledMovingObj.draw(g);

		// draw the random objects
		for (RandomMovingObj randomMovingObj : randomMovingObjList) {
			randomMovingObj.draw(g);
		}

	}

	/*
	 * repaint the controlled object every 30ms based on user defined direction
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.showCollisionDialog) {
			repaint();
			for (RandomMovingObj randomMovingObj : randomMovingObjList) {
				randomMovingObj.run();
			}

			this.objectCollisionDetection();

			if (this.controlledMovingObj.y == 10) {
				this.showWiningDialog = true;
				this.showDialogBox();
			}

		} else {
			this.showCollisionDialog = true;
			this.showDialogBox();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		/*
		 * get the value of the key and call the corresponding method based on key press
		 */
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_UP) {
			controlledMovingObj.up();
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			controlledMovingObj.down();
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			controlledMovingObj.left();
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			controlledMovingObj.right();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
