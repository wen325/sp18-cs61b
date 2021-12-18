package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GameTest {
	TERenderer ter = new TERenderer();
	/* Feel free to change the width and height. */
	public static final int WIDTH = 80;
	public static final int HEIGHT = 30;

	/**
	 * Method used for playing a fresh game. The game should start from the main menu.
	 */
	public void playWithKeyboard() {
	}

	/**
	 * Method used for autograding and testing the game code. The input string will be a series
	 * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
	 * behave exactly as if the user typed these characters into the game after playing
	 * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
	 * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
	 * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
	 * should save, and thus if we then called playWithInputString with the string "l", we'd expect
	 * to get the exact same world back again, since this corresponds to loading the saved game.
	 * @param input the input string to feed to your program
	 * @return the 2D TETile[][] representing the state of the world
	 */
	public TETile[][] playWithInputString(String input) {
		// TODO: Fill out this method to run the game using the input passed in,
		// and return a 2D tile representation of the world that would have been
		// drawn if the same inputs had been given to playWithKeyboard().
		int k = 1;
		StringBuilder inputSEED = new StringBuilder();
		char[] inputChar = input.toCharArray();
		while (inputChar[k] != 'S') {
			inputSEED.append(inputChar[k]);
			k++;
		}

		long SEED = Long.parseLong(inputSEED.toString());

		Random random = new Random(SEED);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);


		int[][] digitalWorld = new int[WIDTH][HEIGHT];
		TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
		int Roomnum = RandomUtils.uniform(random, 10, 20);
		Position[] p = new Position[Roomnum];
		int width;
		int height;
		for (int i = 0; i < Roomnum; i++) {
			do {
				width = RandomUtils.uniform(random, 3, 6);
				height = RandomUtils.uniform(random, 3, 6);
				p[i] = new Position(RandomUtils.uniform(random, 5 , WIDTH - 3 * width), RandomUtils.uniform(random, 5 , HEIGHT - 3 * height));
			} while (Helper.overlap(digitalWorld, p[i], width, height));
			Room r1 = new Room(digitalWorld, p[i], width, height);
		}

		for (int i = 0; i < Roomnum - 1; i++) {
			Hallway h1 = new Hallway(digitalWorld, p[i], p[i + 1]);
		}

		Helper.addWall(digitalWorld);
		int doorX;
		int doorY;
		boolean Lockdoor;
		do {
			doorX = RandomUtils.uniform(random, 1, WIDTH - 1);
			doorY = RandomUtils.uniform(random, 1, HEIGHT - 1);
			Lockdoor = !Helper.addLockdoor(digitalWorld, doorX, doorY);
		}while (Lockdoor);

		Helper.addTile(finalWorldFrame, digitalWorld);
		ter.renderFrame(finalWorldFrame);

		return finalWorldFrame;
	}
}
