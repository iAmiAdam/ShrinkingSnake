package info.adamjsmith.shrinkingsnake;

import java.util.Random;

public class World {
	static final int WORLD_WIDTH = 9;
	static final int WORLD_HEIGHT = 15;
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = 0.3f;
	static final float TICK_DECREMENT = 0.01f;
	
	public Snake snake;
	public Food food;
	public boolean gameOver = false;
	public int score = 0;
	
	boolean fields[][] = new boolean [WORLD_WIDTH][WORLD_HEIGHT];
	Random random = new Random();
	float tickTime = 0;
	float tick = TICK_INITIAL;
	
	public World() {
		snake = new Snake();
		placeFood();
	}
	
	private void placeFood() {
		for (int x = 0; x < WORLD_WIDTH; x++) {
			for (int y = 0; y < WORLD_HEIGHT; y++) {
				fields[x][y] = false;
			}
		}
		
		int len = snake.parts.size();
		for (int i = 0; i < len; i++) {
			SnakePart part = snake.parts.get(i);
			fields[part.x][part.y] = true;
		}
		
		int foodX = random.nextInt(WORLD_WIDTH);
		int foodY = random.nextInt(WORLD_HEIGHT);
		while(true) {
			if (fields[foodX][foodY] == false)
				break;
			foodX += 1;
			if(foodX >= WORLD_WIDTH) {
				foodX = 0;
				foodY += 1;
				if (foodY >= WORLD_HEIGHT) {
					foodY = 0;
				}
			}
		}
		food = new Food(foodX, foodY, random.nextInt(1));
	}
	
	public void update(float deltaTime) {
		if (gameOver)
			return;
		
		tickTime += deltaTime;
		
		while (tickTime > tick) {
			tickTime -= tick;
			snake.advance();
			if (snake.checkBitten()) {
				gameOver = true;
				return;
			}
			SnakePart head = snake.parts.get(0);
			if (head.x == food.x && head.y == food.y) {
				score += SCORE_INCREMENT;
				snake.eat();
				if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT) {
					gameOver = true;
					return;
				} else {
					placeFood();
				}
				
				if (score % 100 == 0 && tick - TICK_DECREMENT > 0) {
					tick -= TICK_DECREMENT;
				}
			}
		}
	}
	
	public int dimensions(String dimension) {
		if (dimension == "Height") {
			return WORLD_HEIGHT;
		} else {
			return WORLD_WIDTH;
		}
	}
}
