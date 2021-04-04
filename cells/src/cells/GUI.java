package cells;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class GUI {

	private int bound = 0;
	private int[] allX, allY;
	public panel my_panel;
	JFrame frame = new JFrame();

	public GUI(String name, int input_bound, int[] xCoords, int[] yCoords,int cellNumber) {
		JFrame frame = new JFrame(name);
		bound = input_bound;

		my_panel = new panel(xCoords, yCoords,cellNumber);

		frame.setSize(input_bound,input_bound);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(my_panel);

		frame.setVisible(true);
	}

	public void refresh() {
		my_panel.revalidate();
		my_panel.repaint();
	}

}

class panel extends JPanel {
	
	private int cellNumber;
	private int[] allX, allY;
	

	public panel(int[] xCoords, int[] yCoords,int populationNumber) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		allX = xCoords;
		allY = yCoords;
		cellNumber=populationNumber;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < cellNumber; i++) {
			g.setColor(Color.BLACK);
			g.fillRect(allX[i] - 1, allY[i] - 2, 5, 5);
		}
	}

}
