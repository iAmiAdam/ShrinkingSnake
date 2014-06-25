package info.adamjsmith.shrinkingsnake;

import info.adamjsmith.framework.Screen;
import info.adamjsmith.framework.impl.AndroidGame;

public class ShrinkingSnakeGame extends AndroidGame {
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}