package cells;

import java.util.concurrent.TimeUnit;

public class Running {

	public static void main(String[] args) {

		int cellNumber = 750;
		Cell[] population = new Cell[cellNumber];
		int[] allX = new int[cellNumber];
		int[] allY = new int[cellNumber];

		for (int i = 0; i < cellNumber; i++) {
			population[i] = new Cell(i);
			population[i].setPosition();
			allX[i] = population[i].giveX();
			allY[i] = population[i].giveY();
		}
		GUI window = new GUI("testing shizz", 1000, allX, allY, cellNumber);
		for (int ii = 0; ii > -1; ii++) {
			for (int i = 0; i < cellNumber; i++) {
				population[i].move();
				allX[i] = population[i].giveX();
				allY[i] = population[i].giveY();
			}
			window.refresh();
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		
		}
	}

}
