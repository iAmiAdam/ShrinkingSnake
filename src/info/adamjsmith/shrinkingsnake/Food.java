package info.adamjsmith.shrinkingsnake;

public class Food {
	public static final int TYPE_1 = 0;
	public int x, y;
	public int type;
	
	public Food(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type=type;
	}
}
