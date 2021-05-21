package tba;


import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame mainFrmae = new JFrame();
		MainPanel mainPanel = new MainPanel();
		mainFrmae.add(mainPanel);
		mainFrmae.setVisible(true);
		mainFrmae.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrmae.setTitle("Moving Vehicles (TBA)");
		mainFrmae.setSize(800, 800);
	}

}
