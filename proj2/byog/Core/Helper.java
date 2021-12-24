package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.*;

public class Helper{

	//** to test whether the new room has overlap with exists digitalWorld */
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


	//** add Wall surround the floor. */
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

	//** add the yellow locked door */
	public static boolean addLockdoor(int[][] digitalWorld, int i, int j) {

		if (digitalWorld[i+1][j] == 2 || digitalWorld[i-1][j] == 2 || digitalWorld[i][j + 1] == 2 || digitalWorld[i][j - 1] == 2) {
			if ((digitalWorld[i+1][j] == 1 && digitalWorld[i-1][j] == 1) || (digitalWorld[i][j + 1] == 1 && digitalWorld[i][j - 1] == 1)) {
				digitalWorld[i][j] = 3;
				return true;
			}
		}
		return false;
	}

	//** add player */
	public static boolean addPlayer(int[][] digitalWorld, Position player) {

		if (digitalWorld[player.x][player.y] == 2){
			digitalWorld[player.x][player.y] = 4;
				return true;
			}
		return false;
	}

	//** move player */
	public static void playerMove(int[][] digitalWorld, char direction){

		int playerX = 0;
		int playerY = 0;
		for (int i = 0; i<digitalWorld.length; i++) {
			for (int j = 0; j < digitalWorld[0].length; j++) {
				if (digitalWorld[i][j] == 4){
					playerX = i;
					playerY = j;
				}
			}
		}

		if (!stuck(digitalWorld, playerX, playerY, direction)) {
			switch (direction) {
				case 'w':
				case 'W':
					digitalWorld[playerX][playerY + 1] = 4;
					break;
				case 's':
				case 'S':
					digitalWorld[playerX][playerY - 1] = 4;
					break;
				case 'a':
				case 'A':
					digitalWorld[playerX - 1][playerY] = 4;
					break;
				case 'd':
				case 'D':
					digitalWorld[playerX + 1][playerY] = 4;
					break;
			}
			digitalWorld[playerX][playerY] = 2;
		}
	}

	//** decide whether player can move along the direction or not */
	public static boolean stuck(int[][] digitalWorld, int playerX, int playerY, char direction){
		switch(direction){
			case 'w':
			case 'W':
				if (digitalWorld[playerX][playerY + 1] == 2) {
					return false;
				}
				break;
			case 's':
			case 'S':
				if (digitalWorld[playerX][playerY - 1] == 2) {
					return false;
				}
				break;
			case 'a':
			case 'A':
				if (digitalWorld[playerX - 1][playerY] == 2) {
					return false;
				}
				break;
			case 'd':
			case 'D':
				if (digitalWorld[playerX + 1][playerY] == 2) {
					return false;
				}
				break;
		}
		return true;
	}

	//** save the current world w */
	public static void saveWorld(TETile[][] world) {
		File f = new File("./phase2world.ser");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fs = new FileOutputStream(f);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(world);
			os.close();
		}  catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	//** load world from file */
	public static TETile[][] loadWorld() {
		File f = new File("./phase2world.ser");
		if (f.exists()) {
			try {
				FileInputStream fs = new FileInputStream(f);
				ObjectInputStream os = new ObjectInputStream(fs);
				TETile[][] loadWorld = (TETile[][]) os.readObject();
				os.close();
				return loadWorld;
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				System.exit(0);
			} catch (IOException e) {
				System.out.println(e);
				System.exit(0);
			} catch (ClassNotFoundException e) {
				System.out.println("class not found");
				System.exit(0);
			}
		}
		return null;
	}

	//** convert TETile[][] world to int[][] digital world; for loading game */

	public static int[][] getDigitalWorld(TETile[][] world){
		int[][] digitalWorld = new int[world.length][world[0].length];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				switch (world[i][j].character()){
					case ' ':
						digitalWorld[i][j] = 0;
						break;
					case '#':
						digitalWorld[i][j] = 1;
						break;
					case '·':
						digitalWorld[i][j] = 2;
						break;
					case '█':
						digitalWorld[i][j] = 3;
						break;
					case '@':
						digitalWorld[i][j] = 4;
						break;
				}
			}
		}
		return digitalWorld;
	}

	//** add tile */
	public static void addTile(TETile[][] world, int[][] digitalWorld) {
		for (int i = 0; i < digitalWorld.length; i++) {
			for (int j = 0; j < digitalWorld[i].length; j++){
				switch (digitalWorld[i][j]) {
					case 0: world[i][j] = Tileset.NOTHING;
						break;
					case 1: world[i][j] = Tileset.WALL;
						break;
					case 2: world[i][j] = Tileset.FLOOR;
						break;
					case 3: world[i][j] = Tileset.LOCKED_DOOR;
						break;
					case 4: world[i][j] = Tileset.PLAYER;
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
