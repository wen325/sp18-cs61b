package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import byog.Core.RandomUtils;

import java.util.Random;



public class MapTest {
	private static final int WIDTH = 80;
	private static final int HEIGHT = 30;
	static Random random = new Random();

	public static void main(String[] args){
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		int[][] digitalWorld = new int[WIDTH][HEIGHT];
		TETile[][] world = new TETile[WIDTH][HEIGHT];
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

		Helper.addTile(world, digitalWorld);
		ter.renderFrame(world);
	}
}
