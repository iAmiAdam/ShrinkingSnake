package info.adamjsmith.shrinkingsnake;

import java.util.List;

import android.graphics.Color;
import info.adamjsmith.framework.Game;
import info.adamjsmith.framework.Graphics;
import info.adamjsmith.framework.Input.TouchEvent;
import info.adamjsmith.framework.Pixmap;
import info.adamjsmith.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver
	}
	
	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";
	
	public GameScreen(Game game) {
		super(game);
		world = new World();
	}

	@Override
	public void update(float deltaTime) {
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if(state == GameState.Ready)
			updateReady(touchEvents);
		if(state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if(state == GameState.Paused)
			updatePaused(touchEvents);
		if(state == GameState.GameOver)
			updateGameOver(touchEvents);
	}
	
	private void updateReady(List < TouchEvent > touchEvents) {
		if(touchEvents.size() > 0)
			state = GameState.Running;
	}
	
	private void updateRunning(List < TouchEvent > touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x < 90 && event.y <86) {
					if(Settings.soundEnabled)
						Assets.click.play(1);
					state = GameState.Paused;
					return;
				}
			}
			if(event.type == TouchEvent.TOUCH_DOWN){
				if(event.x < 110 && event.y > 1160) {
					world.snake.turnLeft();
				}
				if(event.x > 610 && event.y > 1160) {
					world.snake.turnRight();
				}
			}
		}
		world.update(deltaTime);
		if(world.gameOver) {
			if(Settings.soundEnabled)
				Assets.gameOverSound.play(1);
			state = GameState.GameOver;
		}
		if(oldScore != world.score) {
			oldScore = world.score;
			score = "" + oldScore;
		}
	}
	
	private void updatePaused(List < TouchEvent > touchEvents) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x > 80 && event.x <= 240) {
					if(event.y > 100 && event.y <= 148) {
						if(Settings.soundEnabled)
							Assets.click.play(1);
						state = GameState.Running;
						return;
					}
					if(event.y > 148 && event.y < 196) {
						if(Settings.soundEnabled)
							Assets.click.play(1);
						game.setScreen(new MainMenuScreen(game));
						return;
					}
				}
			}
		}
	}
	
	private void updateGameOver(List < TouchEvent > touchEvents) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x >= 128 && event.x <= 192 && event.y >= 200 && event.y <= 264) {
					if(Settings.soundEnabled)
						Assets.click.play(1);
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0);
		drawWorld(world);
		if(state == GameState.Ready)
			drawReadyUI();
		if(state == GameState.Running)
			drawRunningUI();
		if(state == GameState.Paused)
			drawPausedUI();
		if(state == GameState.GameOver)
			drawGameOverUI();
		
		drawText(g, score, g.getWidth() / 2 - score.length()*100 / 2, g.getHeight() - 100);
	}
	
	private void drawWorld(World world) {
		Graphics g = game.getGraphics();
		Snake snake = world.snake;
		SnakePart head = snake.parts.get(0);
		Food food = world.food;
		
		Pixmap foodPixmap = null;
		if(food.type == Food.TYPE_1)
			foodPixmap = Assets.food;
		int x = food.x * 77;
		int y = food.y * 77;
		g.drawPixmap(foodPixmap, x, y);
	
		int len = snake.parts.size();
		for(int i = 1; i < len; i++) {
			SnakePart part = snake.parts.get(i);
			x = part.x * 77;
			y = part.y * 77;
			g.drawPixmap(Assets.body, x , y);
		}
		
		Pixmap headPixmap = null;
		if(snake.direction == Snake.UP)
			headPixmap = Assets.head;
		if(snake.direction == Snake.LEFT)
			headPixmap = Assets.head;
		if(snake.direction == Snake.DOWN)
			headPixmap = Assets.head;
		if(snake.direction == Snake.RIGHT)
			headPixmap = Assets.head;
		
		x = head.x * 77;
		y = head.y * 77;
		g.drawPixmap(headPixmap, x, y);
	}
	
	private void drawReadyUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.ready, 47, 100);
		g.drawLine(0, 1150, 720, 1150, Color.WHITE);
	}
	
	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.buttons, 0, 0, 5, 5, 110, 110);
		g.drawLine(0, 1150, 720, 1150, Color.WHITE);
		g.drawPixmap(Assets.buttons, 0, 1160, 0, 225, 110, 110);
		g.drawPixmap(Assets.buttons, 610, 1160, 115, 225, 110, 110);
	}
	
	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.pause, 80,  100);
		g.drawLine(0, 1150, 720, 1150, Color.WHITE);
	}
	
	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.gameOver, 0, 510);
		g.drawLine(0, 1150, 720, 1150, Color.WHITE);
	}
	
	public void drawText(Graphics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			
			if (character == ' ') {
				x += 20;
				continue;
			}
			
			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 991;
				srcWidth = 40;
			} else {
				srcX = (character - '0') * 100;
				srcWidth = 100;
			}
			
			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 83);
			x += srcWidth;
		}
	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;
		if (world.gameOver){
			Settings.addScore(world.score);
			Settings.save(game.getFileIO());
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
