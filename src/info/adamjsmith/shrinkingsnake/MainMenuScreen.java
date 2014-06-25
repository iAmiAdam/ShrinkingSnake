package info.adamjsmith.shrinkingsnake;

import java.util.List;

import info.adamjsmith.framework.Game;
import info.adamjsmith.framework.Graphics;
import info.adamjsmith.framework.Input.TouchEvent;
import info.adamjsmith.framework.Screen;

public class MainMenuScreen extends Screen{
	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(inBounds(event, 0, g.getHeight() - 120, 120, 120)) {
					Settings.soundEnabled = !Settings.soundEnabled;
					if(Settings.soundEnabled)
						Assets.click.play(1);
				}
				if(inBounds(event, 230, 505,  260, 70) ) {
					game.setScreen(new GameScreen(game));
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if(inBounds(event, 0, 630, 720, 70) ) {
					game.setScreen(new HighscoreScreen(game));
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if(inBounds(event, 230, 750, 260, 70) ) {
					game.setScreen(new HelpScreen(game));
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}
		
	}
	
	public boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if(event.x > x && event.x < x + width && event.y > y && event.y < y + height - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.logo, 0, 20);
		g.drawPixmap(Assets.mainMenu, 0, 500);
		
		if(Settings.soundEnabled) 
			g.drawPixmap(Assets.buttons, 0, 1160, 5, 115, 110, 110);
		else 
			g.drawPixmap(Assets.buttons, 0, 1160, 115, 115, 110, 110);
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());		
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
