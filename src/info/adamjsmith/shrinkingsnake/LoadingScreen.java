package info.adamjsmith.shrinkingsnake;

import java.util.List;

import info.adamjsmith.framework.Game;
import info.adamjsmith.framework.Graphics;
import info.adamjsmith.framework.Screen;
import info.adamjsmith.framework.Graphics.PixmapFormat;
import info.adamjsmith.framework.Input.TouchEvent;

public class LoadingScreen extends Screen{
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		
		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.message = g.newPixmap("AlphaMessage.png", PixmapFormat.ARGB4444);
		Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
		Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
		Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
		Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
		Assets.headDown = g.newPixmap("head.png", PixmapFormat.ARGB4444);
		Assets.headLeft = g.newPixmap("head.png", PixmapFormat.ARGB4444);
		Assets.headUp = g.newPixmap("head.png", PixmapFormat.ARGB4444);
		Assets.body = g.newPixmap("body.png", PixmapFormat.ARGB4444);
		Assets.food = g.newPixmap("food.png", PixmapFormat.ARGB4444);
		Assets.head = g.newPixmap("head.png", PixmapFormat.ARGB4444);
		Assets.click = game.getAudio().newSound("click.ogg");
		Assets.gameOverSound = game.getAudio().newSound("gameover.ogg");
		Settings.load(game.getFileIO());
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.message = g.newPixmap("AlphaMessage.png", PixmapFormat.ARGB4444);
		g.drawPixmap(Assets.message, 0, 300);
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				game.setScreen(new MainMenuScreen(game));
			}
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
