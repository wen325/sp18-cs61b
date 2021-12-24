package byog.Core;

import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

public class Draw {


	public static void menu() {
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.text(0.5, 0.55, "New Game (N)");
		StdDraw.text(0.5, 0.50, "Load Game (L)");
		StdDraw.text(0.5, 0.45, "Quit (Q)");
		StdDraw.text(0.5, 0.40, "Setting (S)");
		StdDraw.show();
//		StdDraw.pause(100);
	}

	public static long seedInput() {


//			StdDraw.clear(StdDraw.BLACK);
//			StdDraw.text(0.5, 0.55, "Please enter seed, end with 'S'");
//			StdDraw.show();
			String seed = "";
			char key = 0;
			while (key != 'S' && key != 's') {
			StdDraw.clear(StdDraw.BLACK);
			StdDraw.text(0.5, 0.55, "Please enter seed, end with 'S'");
			StdDraw.text(0.5, 0.5, seed);
			StdDraw.show();
			if (!StdDraw.hasNextKeyTyped()) {
				continue;
			}
			key = StdDraw.nextKeyTyped();
			seed += String.valueOf(key);
		}
		return Long.parseLong(seed.replaceAll("[^0-9]",""));
	}

	public static void Hud(int width, int height, String info){
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.textLeft(1, height - 1, "*****");
		StdDraw.text(width/2, height - 1, info);
//		StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
		StdDraw.line(0, height - 2, width, height - 2);
		StdDraw.show();
	}
	
	public static String mousePressed(TETile[][] world, int mouseX, int mouseY){
		String info = "";
		switch (world[mouseX][mouseY].character()) {
			case ' ':
				info = "nothing";
				break;
			case '#':
				info = "wall";
				break;
			case '·':
				info = "floor";
				break;
			case '█':
				info = "locked door";
				break;
			case '@':
				info = "You";
				break;
		}
		return info;
	}
}
