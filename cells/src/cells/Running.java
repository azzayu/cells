package cells;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Running {

	public static String cornerCodeGenerator(int corner1, int corner2) {
		String corner1s = Integer.toString(corner1);
		String corner2s = Integer.toString(corner2);

		if (corner1s.length() == 2) {
			corner1s = "0" + corner1s;
		} else if (corner1s.length() == 1) {
			corner1s = "00" + corner1s;
		}
		if (corner2s.length() == 2) {
			corner2s = "0" + corner2s;
		} else if (corner2s.length() == 1) {
			corner2s = "00" + corner2s;
		}

		return corner1s + corner2s;
	}

	public static void main(String[] args) {

		int cellNumber = 1500;
		int offSetTotal = 6;
		Cell[] population = new Cell[cellNumber];
		int[] allX = new int[cellNumber];
		int[] allY = new int[cellNumber];
		boolean[] allTouched = new boolean[cellNumber];

		for (int i = 0; i < cellNumber; i++) {
			population[i] = new Cell(i);
			population[i].setPosition();
			allX[i] = population[i].giveX();
			allY[i] = population[i].giveY();
			allTouched[i] = population[i].giveTouched();
		}
		GUI window = new GUI("testing shizz", 1000, allX, allY, cellNumber, allTouched);
		for (int infinite = 0; infinite > -1; infinite = 0) {
			for (int i = 0; i < cellNumber; i++) {
				population[i].move();
				allX[i] = population[i].giveX();
				allY[i] = population[i].giveY();
				allTouched[i] = population[i].giveTouched();
			}
			window.refresh();
			try {
				TimeUnit.MILLISECONDS.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			boolean[] touched = new boolean[cellNumber];
			for (int i = 0; i < cellNumber; i++) {
				touched[i] = false;
			}

			Map<String, ArrayList<Integer>>[] allZonesCodes = new HashMap[offSetTotal];

			for (int i = 0; i < offSetTotal; i++) {
				allZonesCodes[i] = new HashMap<String, ArrayList<Integer>>();
			}

			for (int i = 0; i < cellNumber; i++) {
				for (int j = 0; j < offSetTotal; j++) {
					int xRow = (allX[i] - (allX[i] + j) % offSetTotal) / offSetTotal;
					int yRow = (allY[i] - (allY[i] + j) % offSetTotal) / offSetTotal;
					String zoneCode = cornerCodeGenerator(xRow, yRow);
					if (allZonesCodes[j].containsKey(zoneCode)) {
						allZonesCodes[j].get(zoneCode).add(i);

					} else {
						ArrayList<Integer> emptyList = new ArrayList<Integer>();
						allZonesCodes[j].put(zoneCode, (ArrayList<Integer>) emptyList.clone());
						allZonesCodes[j].get(zoneCode).add(i);
					}
				}
			}
			
			for (int i = 0; i < offSetTotal; i++) {
				Iterator<Entry<String, ArrayList<Integer>>> itr = allZonesCodes[i].entrySet().iterator();
				while (itr.hasNext()) {
					ArrayList<Integer> cellsInSquare = itr.next().getValue();
					if (cellsInSquare.size() > 1) {
						for (int j = 0; j < cellsInSquare.size(); j++) {
							touched[cellsInSquare.get(j)] = true;

						}
					}

				}

			}

			for (int i = 0; i < touched.length; i++) {
				population[i].collision(touched[i]);
			}
		}
	}
}
