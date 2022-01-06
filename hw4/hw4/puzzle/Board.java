package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

	private int[][] tiles;
	private int N;
	private final int BLANK = 0;

	public Board(int[][] tiles) {
		this.N = tiles.length;
		this.tiles = new int[N][N];
		//* be immutable, arraycopy cannot apply to 2D array.
		for (int i = 0; i < N; i++) {
			System.arraycopy(tiles[i], 0, this.tiles[i], 0, N);
		}
	}

	public int tileAt(int i, int j) {
		if (i >= N || j >= N ) {
			throw new IndexOutOfBoundsException();
		}else{
			return tiles[i][j];
		}
	}

	public int size() {
		return N;
	}

	@Override
	public Iterable<WorldState> neighbors() {
		Queue<WorldState> neighbors = new Queue<>();
		int N = size();
		int xpos = -1;
		int ypos = -1;
		//* find BLANK position xpos, ypos;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tileAt(i, j) == BLANK) {
					xpos = i;
					ypos = j;
				}
			}
		}
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = tileAt(i, j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Math.abs(i - xpos) + Math.abs(j - ypos) == 1) {
					temp[xpos][ypos] = temp[i][j];
					temp[i][j] = BLANK;
					Board neighbor = new Board(temp);
					neighbors.enqueue(neighbor);
					temp[i][j] = temp[xpos][ypos];
					temp[xpos][ypos] = BLANK;
				}
			}
		}
		return neighbors;
	}

	public int hamming() {
		int wrongPosition = 0;
		int correctPosition = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tiles[i][j] != correctPosition) {
					wrongPosition += 1;
				}
				correctPosition +=1;
			}
		}
		return wrongPosition - 1;  //* the last position is 0, always wrong;
	}

	public int manhattan() {
		int distance = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tiles[i][j] == BLANK) {
					continue;
				}
				if (tiles[i][j] != i * N + j + 1) {
					int xpos = (tiles[i][j] - 1) / N;  //* correct row
					int ypos = (tiles[i][j] - 1) % N;  //* correct column
					distance += Math.abs(xpos - i) + Math.abs(ypos - j);
				}
			}
		}
		return distance;
	}

	public int estimatedDistanceToGoal() {
		return manhattan();
	}

	public boolean equals(Object y) {
		if (this == y) {
			return true;
		}
		if (y == null || getClass() !=y.getClass()) {
			return false;
		}

		Board board = (Board) y;
		if (this.N != board.N) {
			return false;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (this.tiles[i][j] != board.tiles[i][j]) {
					return  false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
