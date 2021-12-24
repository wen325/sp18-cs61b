package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < n; i++) {
            randomString.append(CHARACTERS[rand.nextInt(25)]);
        }
        return randomString.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(Color.black);
        StdDraw.text(4, height - 2, "Round: " + round);
        if (gameOver) {
            StdDraw.text(width / 2, 5, s);
        }else if(playerTurn){
                StdDraw.text(width - 8, height - 2, ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)]);
                StdDraw.text(width / 2, height - 2, "Type!");
            StdDraw.text(width / 2, height / 2 - 2, s);
            } else {
                StdDraw.text(width / 2, height - 2, "Watch!");
                StdDraw.text(width / 2, height / 2, s);
            }
            StdDraw.show();

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        char[] chars = letters.toCharArray();
        String s;
        for (char aChar : chars) {
            try {
                TimeUnit.SECONDS.sleep(1 / 2);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
            s = String.valueOf(aChar);
            drawFrame(s);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }

    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder letters = new StringBuilder();
        char s;
        while (n > 0) {
                if(StdDraw.hasNextKeyTyped()){
                    n = n - 1;
                    s = StdDraw.nextKeyTyped();
                    letters.append(s);
                    drawFrame(String.valueOf(letters));
                }
        }
        return letters.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        StdDraw.setPenColor(Color.WHITE);
        round = 0;
        gameOver = false;
        String randomString;
        String inputString;
        while(!gameOver) {
            playerTurn = false;
            round += 1;
            drawFrame("Round" + round);
            randomString = generateRandomString(round);
            flashSequence(randomString);
            playerTurn = true;
            drawFrame("");
            inputString = solicitNCharsInput(round);
            if (!randomString.equals(inputString)) {
                gameOver = true;
            }
        }
        drawFrame("Game Over! You made it to round:" + round);
    }
}
