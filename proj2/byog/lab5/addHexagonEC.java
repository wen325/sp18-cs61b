package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class addHexagonEC {

	private static final int WIDTH = 28;
	private static final int HEIGHT = 30;
	private static final long SEED = 2873123;
	private static final Random RANDOM = new Random(SEED);

	public static class Position{
		public int x;
		public int y;
		public Position(int _x, int _y){
			x = _x;
			y = _y;
		}
	}

	private static TETile randomTile() {
		int tileNum = RANDOM.nextInt(3);
		switch (tileNum) {
			case 0: return Tileset.GRASS;
			case 1: return Tileset.FLOWER;
			case 2: return Tileset.MOUNTAIN;
			default: return Tileset.NOTHING;
		}
	}

	public static void addHexagon(TETile[][] world, int i, Position p, TETile t) {
		for(int j = 0; j < i; j++) {
			for (int k = i - 1 - j; k < i*2 - 1 + j; k++) {
				world[k+p.x][j+p.y] = t;
			}
		}
		for(int j = i; j < i * 2; j++) {
			for (int k = -i + j; k < i * 4 - j -2; k++) {
				world[k+p.x][j+p.y] = t;
			}
		}
	}

	public static void drawRandomVerticalHexes(TETile[][] world, Position p, int number){
		while (number > 0) {
			addHexagon(world, 3, p, randomTile());
			p.y +=6;
			number -= 1;
		}
	}

	public static void main(String[] args) {
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);
		TETile[][] world = new TETile[WIDTH][HEIGHT];
		for (int x = 0; x < WIDTH; x += 1) {
			for (int y = 0; y < HEIGHT; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}


		int[] number = new int[]{3, 4, 5, 4, 3};
		int[] start_x = new int[]{0, 5, 10, 15, 20};
		int[] start_y = new int[]{6, 3, 0, 3, 6};
		Position p = new Position(0,6);
		for (int i = 0; i < 5; i++) {
			p.x = start_x[i];
			p.y = start_y[i];
			drawRandomVerticalHexes(world, p, number[i]);
		}
		ter.renderFrame(world);
	}
}
