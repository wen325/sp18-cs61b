package byog.Core;

public class Room {

	public Room(int[][] digitalWorld, Position p, int width, int height) {

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				digitalWorld[i + p.x][j + p.y] = 2;
			}
		}
	}
}


//
//			if (roomType.equals("start")){
//				doorNum = 1;
//			}else {
//				doorNum = RandomUtils.uniform(random, 1, maxDoor + 1);
//			}
//
//			door_position = new int[doorNum][2];
//
//			//** First, make the whole room are floors(2) */
//			for (int i = 0; i < width; i++){
//				for (int j = 0; j < height; j++){
//					digital_world[i + p.x][j + p.y] = 2;
//				}
//			}
//
//			//** Second, make the wall(1) of room */
//			for (int i = 0; i < width; i++) {
//				digital_world[i + p.x][p.y] = 1;
//				digital_world[i + p.x][height-1 + p.y] = 1;
//			}
//			for (int j = 0; j < height; j++) {
//				digital_world[p.x][j + p.y] = 1;
//				digital_world[width- 1 + p.x][j + p.y] = 1;
//			}
//			//** make doors of room, doors is also floor(2) */
//			int k = 0;
//			while (k < doorNum) {
//				int px = random.nextInt(width) + p.x;
//				int py = random.nextInt(height) + p.y;
//				if (digital_world[px][py] == 1){
//					if ((px == p.x && py == p.y) || (px == p.x && py == height - 1 + p.y) || (px == width -1 + p.x && py == p.y) || (px == width - 1 + p.x && py == height - 1 + p.y)){
//						//** the corner of room cannot be door */
//					}else{
//						digital_world[px][py] = 2;
//						k++;
//					}
//				}
//			}
//	}
//}
