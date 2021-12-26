package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	//** use two grid to avoid backwash *//
	private WeightedQuickUnionUF grid, gridNoBottom;
	private boolean[][] openArray;
	private int N;
	private int virtualTop = 0;
	private int virtualBottom;
	private int count;

	// create N-by-N grid, with all sites initially blocked
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException();
		}else{
			grid = new WeightedQuickUnionUF(N * N + 2);
			gridNoBottom = new WeightedQuickUnionUF(N * N + 1);
			openArray = new boolean[N][N];
			virtualBottom = N * N + 1;
			this.N = N;
			count = 0;
		}
	}

	// open the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row > N || col > N) {
			throw new IndexOutOfBoundsException();
		}else{
			if (openArray[row][col]){
				return;
			}else{
				openArray[row][col] = true;
				count +=1;
			}
			if (row == 0) {
				grid.union(virtualTop, xyTo1D(row, col));
				gridNoBottom.union(virtualTop, xyTo1D(row, col));
			}
			if (row == N - 1) {
				grid.union(xyTo1D(row, col), virtualBottom);
			}
			if (row - 1 >= 0 && openArray[row - 1][col]) {
				grid.union(xyTo1D(row, col), xyTo1D(row - 1, col));
				gridNoBottom.union(xyTo1D(row, col), xyTo1D(row - 1, col));
			}
			if (row + 1 <= N - 1 && openArray[row + 1][col]) {
				grid.union(xyTo1D(row, col), xyTo1D(row + 1, col));
				gridNoBottom.union(xyTo1D(row, col), xyTo1D(row + 1, col));
			}
			if (col - 1 >= 0 && openArray[row][col - 1]) {
				grid.union(xyTo1D(row, col), xyTo1D(row, col - 1));
				gridNoBottom.union(xyTo1D(row, col), xyTo1D(row, col - 1));
			}
			if (col + 1 <= N - 1 && openArray[row][col + 1]) {
				grid.union(xyTo1D(row, col), xyTo1D(row, col + 1));
				gridNoBottom.union(xyTo1D(row, col), xyTo1D(row, col + 1));
			}
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row > N || col > N) {
			throw new IndexOutOfBoundsException();
		}else{
			return openArray[row][col];
		}
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row > N || col > N) {
			throw new IndexOutOfBoundsException();
		}else{
//			return grid.connected(xyTo1D(row, col), 0);
			return gridNoBottom.connected(xyTo1D(row, col), 0);   // avoid backwash
		}
	}

	// number of open sites
	public int numberOfOpenSites() {
		return count;
	}

	// does the system percolate?
	public boolean percolates() {
		return grid.connected(virtualTop, virtualBottom);
	}


	// covert 2D to 1D for Disjoint set /
	private int xyTo1D(int row, int col) {
		return row * N + col + 1 ;
	}

	// use for unit testing (not required)
	public static void main(String[] args) {

	}

}
