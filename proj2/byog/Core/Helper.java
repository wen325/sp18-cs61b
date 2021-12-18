package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Helper {

	public static boolean overlap(int[][] digitalWorld, Position p, int width, int height){

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (digitalWorld[p.x + i][p.y + j] == 2){
					return true;
				}
			}
		}
		return false;
	}

	public static void addWall(int[][] digitalWorld) {
		for (int i = 0; i < digitalWorld.length; i++) {
			for (int j = 0; j < digitalWorld[0].length; j++) {
				if (digitalWorld[i][j] == 2){
					if(digitalWorld[i-1][j] == 0){
						digitalWorld[i-1][j] = 1;
					}
					if(digitalWorld[i+1][j] == 0){
						digitalWorld[i+1][j] = 1;
					}
					if(digitalWorld[i][j-1] == 0){
						digitalWorld[i][j-1] = 1;
					}
					if(digitalWorld[i][j+1] == 0){
						digitalWorld[i][j+1] = 1;
					}
				}
			}
		}
	}

	public static boolean addLockdoor(int[][] digitalWorld, int i, int j) {

		if (digitalWorld[i+1][j] == 2 || digitalWorld[i-1][j] == 2 || digitalWorld[i][j + 1] == 2 || digitalWorld[i][j - 1] == 2) {
			if ((digitalWorld[i+1][j] == 1 && digitalWorld[i-1][j] == 1) || (digitalWorld[i][j + 1] == 1 && digitalWorld[i][j - 1] == 1)) {
				digitalWorld[i][j] = 3;
				return true;
			}
		}
		return false;
	}

	public static void addTile(TETile[][] world, int[][] digital_world) {
		for (int i = 0; i < digital_world.length; i++) {
			for (int j = 0; j < digital_world[i].length; j++){
				switch (digital_world[i][j]) {
					case 0: world[i][j] = Tileset.NOTHING;
						break;
					case 1: world[i][j] = Tileset.WALL;
						break;
					case 2: world[i][j] = Tileset.FLOOR;
						break;
					case 3: world[i][j] = Tileset.LOCKED_DOOR;
						break;
				}
			}
		}
	}
}
//	public static String direction(int[][] digital_world, int[] door_position) {
//		int right = digital_world[door_position[0] - 1][door_position[1]];
//		int left = digital_world[door_position[0] + 1][door_position[1]];
//		int up = digital_world[door_position[0]][door_position[1] - 1];
//		int down = digital_world[door_position[0]][door_position[1] + 1];
//
//		if (right == 2){
//			return "right";
//		}else if (left == 2){
//			return "left";
//		}else if (up == 2) {
//			return "up";
//		}else {
//			return "down";
//		}
//	}
//}
