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
				if(inBounds(event, 0, g.getHeight() - 87, 90, 87)) {
					Settings.soundEnabled = !Settings.soundEnabled;
					if(Settings.soundEnabled)
						Assets.click.play(1);
				}
				if(inBounds(event, 280, 264, 164, 46) ) {
					game.setScreen(new GameScreen(game));
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if(inBounds(event, 136, 377, 454, 46) ) {
					game.setScreen(new HighscoreScreen(game));
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if(inBounds(event, 277, 478, 168, 45) ) {
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
		g.drawPixmap(Assets.logo, 49, 88);
		g.drawPixmap(Assets.mainMenu, 137, 264);
		
		if(Settings.soundEnabled) 
			g.drawPixmap(Assets.buttons, 0, 1193, 0, 0, 90, 87);
		else 
			g.drawPixmap(Assets.buttons, 0, 1193, 90, 0, 90, 87);
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
