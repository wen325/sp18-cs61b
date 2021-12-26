package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	public double[] percolationThreshold;
	public int T;

	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T, PercolationFactory pf) {
		if (N <= 0 || T <= 0){
			throw new IllegalArgumentException();
		}
		this.T = T;
		percolationThreshold = new double[T];
		for (int i = 0; i < T; i++) {
			Percolation sample = pf.make(N);
			while (!sample.percolates()){
				int row = StdRandom.uniform(0, N);
				int col = StdRandom.uniform(0, N);
				sample.open(row, col);
			}
			double count = sample.numberOfOpenSites();
			percolationThreshold[i] = count / N / N;
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(percolationThreshold);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		if (T == 1) {
			return 0;
		}
		return StdStats.stddev(percolationThreshold);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLow() {
		return mean() - 1.96 * stddev() / Math.sqrt(T);
	}

	// high endpoint of 95% confidence interval
	public double confidenceHigh() {
		return mean() + 1.96 * stddev() / Math.sqrt(T);
	}
}
