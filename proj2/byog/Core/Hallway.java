package byog.Core;

public class Hallway {

	//** Use floors to connect two rooms' position by Hallways */
	public Hallway(int[][] digitalWorld, Position p1, Position p2) {
		if (p1.x == p2.x) {    // connect by vertical Hallway
			for (int i = Math.min(p1.y, p2.y); i < Math.max(p1.y, p2.y); i++) {
				digitalWorld[p1.x][i] = 2;
			}
		} else if (p1.y == p2.y) {  // connect by horizontal Hallway
			for (int i = Math.min(p1.x, p2.x); i < Math.max(p1.x, p2.x); i++) {
				digitalWorld[i][p1.y] = 2;
			}
		}else{
				for (int i = p1.x; i < p2.x; i++) {
					digitalWorld[i][p1.y] = 2;
				}
				for (int i = Math.min(p1.y, p2.y); i < Math.max(p1.y, p2.y); i++) {
					digitalWorld[p2.x][i] = 2;
				}
			}
		}

}


//	public Hallway(int[][] digital_world, Room last) {
//		String direction = Door_help.direction(digital_world, last.door_position[1]);
//		new Hallway(digital_world, last, direction);
//	}



//	public Hallway(int[][] digital_world, Room last, String direction){
//		int length = random.nextInt(10);
//		int px = last.door_position[1][0];
//		int py = last.door_position[1][1];
//		if (direction.equals("right")){
//			for (int i = px + 1; i < length; i++) {
//				digital_world[i][py] = 2;
//			}
//			for (int i = px + 1; i < length; i++) {
//				digital_world[i][py + 1] = 1;
//				digital_world[i][py - 1] = 1;
//			}
//
//		}
//
//	}
//
//
//
//
//
//}



