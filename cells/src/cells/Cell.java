package cells;

import java.util.concurrent.ThreadLocalRandom;

public class Cell {

	private static char[] directions = { 'u', 'w', 'r', 's', 'd', 'e', 'l', 'm' };

	private int cellX;
	private int cellY;
	private int id;
	private int direction;
	private int directionChangeCounter = 20;
	private boolean touching = false;
	private int speed = 1;

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

	public boolean giveTouched() {
		return touching;
	}

	public void move() {

		if (directions[direction] == 'u') {
			cellY -= speed;
		} else if (directions[direction] == 'w') {
			cellY -= speed;
			cellX += speed;
		} else if (directions[direction] == 'r') {
			cellX += speed;
		} else if (directions[direction] == 's') {
			cellY += speed;
			cellX += speed;
		} else if (directions[direction] == 'd') {
			cellY += speed;
		} else if (directions[direction] == 'e') {
			cellY += speed;
			cellX -= speed;
		} else if (directions[direction] == 'l') {
			cellX -= speed;
		} else if (directions[direction] == 'm') {
			cellY -= speed;
			cellX -= speed;
		}

		if (cellX > 997) {
			cellX = 3;
		}
		if (cellY > 997) {
			cellY = 3;
		}
		if (cellX < 3) {
			cellX = 997;
		}
		if (cellY < 3) {
			cellY = 997;
		}

		if (speed > 1) {
			speed--;
		}

		directionChangeCounter -= 1;
		int shouldChangeDirection = ThreadLocalRandom.current().nextInt(0, directionChangeCounter);
		if (shouldChangeDirection == 0) {
			directionChangeCounter += 20;
			direction = randomDirection(direction);
		}

	}

	public void collision(boolean touched) {
		touching = touched;
		if (touching) {
			direction = (direction + 4) % 8;
			directionChangeCounter = 2;
			speed += 3;
		}
	}

}
