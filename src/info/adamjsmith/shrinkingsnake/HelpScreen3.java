package info.adamjsmith.shrinkingsnake;

import java.util.List;

import info.adamjsmith.framework.Game;
import info.adamjsmith.framework.Graphics;
import info.adamjsmith.framework.Input.TouchEvent;
import info.adamjsmith.framework.Screen;

public class HelpScreen3 extends Screen {
	public HelpScreen3(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x > 630 && event.y > 1193) {
					game.setScreen(new MainMenuScreen(game));
					if(Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.help3, 64, 100);
		g.drawPixmap(Assets.buttons, 630, 1193, 0, 172, 90, 172);	
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
