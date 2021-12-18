package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.Random;

public class MapGenerator {


	public static TETile[][] Generator(int[][] digitalWorld , Random random){
		int WIDTH = digitalWorld.length;
		int HEIGHT = digitalWorld[0].length;
		TERenderer ter = new TERenderer();
//		ter.initialize(WIDTH, HEIGHT);

		TETile[][] world = new TETile[WIDTH][HEIGHT];
		int roomNum = RandomUtils.uniform(random, 10, 20);
		Position[] p = new Position[roomNum];
		int width;
		int height;
		for (int i = 0; i < roomNum; i++) {
			do {
				width = RandomUtils.uniform(random, 3, 6);
				height = RandomUtils.uniform(random, 3, 6);
				p[i] = new Position(RandomUtils.uniform(random, 5 , WIDTH - 3 * width), RandomUtils.uniform(random, 5 , HEIGHT - 3 * height));
			} while (Helper.overlap(digitalWorld, p[i], width, height));
			Room r1 = new Room(digitalWorld, p[i], width, height);
		}

		for (int i = 0; i < roomNum - 1; i++) {
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

		return world;
	}
}
