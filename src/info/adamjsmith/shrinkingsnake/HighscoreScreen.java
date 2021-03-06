package info.adamjsmith.shrinkingsnake;

import java.util.List;

import info.adamjsmith.framework.Game;
import info.adamjsmith.framework.Graphics;
import info.adamjsmith.framework.Screen;
import info.adamjsmith.framework.Input.TouchEvent;

public class HighscoreScreen extends Screen {
	String lines[] =  new String[5];
	
	public HighscoreScreen(Game game) {
		super(game);
		for (int i = 0; i < 5; i++) {
			lines[i] =  "" + i + 1 + ". " + Settings.highscores[i];
		}
	}

	@Override
	public void update(float deltaTime) {
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < 90 && event.y > 1193) {
					if(Settings.soundEnabled)
						Assets.click.play(1);
					game.setScreen(new MainMenuScreen(game));
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.mainMenu, 0, 20, 0, 125, 720, 70);
		
		int y = 300;
		for (int i = 0; i < 5; i++) {
			drawText(g, lines[i], 40, y);
			y += 100;
		}
		g.drawPixmap(Assets.buttons, 0, 1160, 0, 225, 110, 110);
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
			
			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 100);
			x += srcWidth;
		}
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
