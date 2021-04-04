package cells;

import java.util.concurrent.ThreadLocalRandom;

public class Cell {

	private static char[] directions = { 'u', 'w', 'r', 's', 'd', 'e', 'l', 'm' };

	private int cellX;
	private int cellY;
	private int id;
	private int direction;
	private int directionChangeCounter = 20;

	public Cell(int inId) {
		id = inId;
	}

	public void setPosition() {
		cellX = ThreadLocalRandom.current().nextInt(10, 1000);
		cellY = ThreadLocalRandom.current().nextInt(10, 1000);
		int initialDirectionSelector = ThreadLocalRandom.current().nextInt(0, 8);
		direction = initialDirectionSelector;
	}

	private int randomDirection(int currentDirection) {
		int[] addToDirection = { 1, 2, 6, 7 };
		int changeDirectionSelector = ThreadLocalRandom.current().nextInt(0, 4);
		int futureDirection = (currentDirection + addToDirection[changeDirectionSelector]) % 8;
		return futureDirection;
	}

	public int giveX() {
		return cellX;
	}

	public int giveY() {
		return cellY;
	}

	public void move() {

		if (directions[direction] == 'u') {
			cellY -= 1;
		} else if (directions[direction] == 'w') {
			cellY -= 1;
			cellX += 1;
		} else if (directions[direction] == 'r') {
			cellX += 1;
		} else if (directions[direction] == 's') {
			cellY += 1;
			cellX += 1;
		} else if (directions[direction] == 'd') {
			cellY += 1;
		} else if (directions[direction] == 'e') {
			cellY += 1;
			cellX -= 1;
		} else if (directions[direction] == 'l') {
			cellX -= 1;
		} else if (directions[direction] == 'm') {
			cellY -= 1;
			cellX -= 1;
		}

		if (cellX > 1000) {
			cellX = 0;
		} else if (cellY > 1000) {
			cellY = 0;
		} else if (cellX < 0) {
			cellX = 1000;
		} else if (cellX < 0) {
			cellX = 1000;
		}

		directionChangeCounter -= 1;
		int shouldChangeDirection = ThreadLocalRandom.current().nextInt(0, directionChangeCounter);
		if (shouldChangeDirection == 0) {
			directionChangeCounter = 20;
			direction = randomDirection(direction);
		}

	}

}
