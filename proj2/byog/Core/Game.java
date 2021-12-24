package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static int[][] digitalWorld = new int[WIDTH][HEIGHT];
    public static final int hudheight = 4;

    public static long seed;
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.WHITE);
        Font smallFont = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(smallFont);

        Draw.menu();
        TETile[][] world = new TETile[0][];
        String info = "";

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                Random random;
                switch (c) {
                    case 'n':
                    case 'N':
                        seed = Draw.seedInput();
                        random = new Random(seed);
                        ter.initialize(WIDTH, HEIGHT + hudheight);
                        world = MapGenerator.Generator(digitalWorld, random);
                        ter.renderFrame(world);
                        Draw.Hud(WIDTH, HEIGHT + hudheight, info);
                        break;
                    case 'l':
                    case 'L':
                        ter.initialize(WIDTH, HEIGHT);
                        world = Helper.loadWorld();
                        ter.renderFrame(world);
                        digitalWorld = Helper.getDigitalWorld(world);
                        break;
                    case 'q':
                    case 'Q':
                        Helper.saveWorld(world);
                        System.exit(0);
                        break;
                    default:
                         Helper.playerMove(digitalWorld, c);
                         Helper.addTile(world, digitalWorld);
                         ter.renderFrame(world);
                         Draw.Hud(WIDTH, HEIGHT + hudheight, info);
                }
            }
            if(StdDraw.isMousePressed()){
                int mouseX = (int) StdDraw.mouseX();
                int mouseY = (int) StdDraw.mouseY();
                info = Draw.mousePressed(world, mouseX, mouseY);
                ter.renderFrame(world);
                Draw.Hud(WIDTH, HEIGHT + hudheight, info);
            }
        }
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
        char[] inputSeries = input.toCharArray();
        TETile[][]  world = new TETile[0][];
        for (int i = 0; i < inputSeries.length; i++) {
            switch (inputSeries[i]) {
                case 'n':
                case 'N':
                    long seed = 0;
                    while (inputSeries[i+1] != 's' && inputSeries[i+1] != 'S') {
                        seed = seed * 10 + Long.parseLong(String.valueOf(inputSeries[i+1]));
                        i++;
                    }
                    i++;
                    Random random = new Random(seed);
                    world = MapGenerator.Generator(digitalWorld, random);
                    break;
                case 'l':
                case 'L':
                    world = Helper.loadWorld();
                    digitalWorld = Helper.getDigitalWorld(world);
                    break;
                case 'q':
                case 'Q':
                    Helper.saveWorld(world);
//                    System.exit(0);
                    return world;
                case ':':
                    break;
                default:
                    Helper.playerMove(digitalWorld, inputSeries[i]);
                    Helper.addTile(world, digitalWorld);
//                    ter.renderFrame(world);
            }
        }
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);
        return world;
    }
}
