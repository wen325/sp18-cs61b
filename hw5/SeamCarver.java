import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.util.ArrayList;

public class SeamCarver {

	private int width;
	private int height;
	private Picture picture;

	public SeamCarver(Picture picture) {
		this.width = picture.width();
		this.height = picture.height();
		this.picture = new Picture(picture);

	}

	// current picture
	public Picture picture() {
		return this.picture;
	}

	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}


	// energy of pixel at column x and row y
	public double energy(int x, int y) {

		if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
			throw new IndexOutOfBoundsException();
		}

		x += width;
		y += height;  // avoid negative case

		Color rightRGB = picture.get((x + 1) % width, y % height);
		Color leftRGB = picture.get((x - 1) % width, y % height);
		Color bottomRGB = picture.get(x % width, (y + 1) % height);
		Color topRGB = picture.get(x % width, (y - 1) % height);

		double energyXRed = (rightRGB.getRed() - leftRGB.getRed()) * (rightRGB.getRed() - leftRGB.getRed());
		double energyXGreen = (rightRGB.getGreen() - leftRGB.getGreen()) * (rightRGB.getGreen() - leftRGB.getGreen());
		double energyXBlue = (rightRGB.getBlue() - leftRGB.getBlue()) * (rightRGB.getBlue() - leftRGB.getBlue());
		double energyX = energyXRed + energyXGreen + energyXBlue;

		double energyYRed = (bottomRGB.getRed() - topRGB.getRed()) * (bottomRGB.getRed() - topRGB.getRed());
		double energyYGreen = (bottomRGB.getGreen() - topRGB.getGreen()) * (bottomRGB.getGreen() - topRGB.getGreen());
		double energyYBlue = (bottomRGB.getBlue() - topRGB.getBlue()) * (bottomRGB.getBlue() - topRGB.getBlue());
		double energyY = energyYRed + energyYGreen + energyYBlue;

		return energyX + energyY;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		Picture transposepicture = new Picture(height, width);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				transposepicture.set(i, j, picture.get(j, i));
			}
		}
		return new SeamCarver(transposepicture).findVerticalSeam();
	}

	// sequence of indices for vertical seam. /
	public int[] findVerticalSeam() {
		double[][] energyMatrix = new double[height][width];

		// The top row energyMatrix /
		for (int j = 0; j < width; j++) {
			energyMatrix[0][j] = energy(j, 0);
		}

		// The lower rows energyMatrix /
		for (int i = 1; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (width == 1) {
					energyMatrix[i][j] = energy(j, i) + energyMatrix[i - 1][j];
				}else if (j == 0) {
					energyMatrix[i][j] = energy(j, i) + Math.min(energyMatrix[i - 1][j], energyMatrix[i - 1][j + 1]);
				} else if (j == width - 1) {
					energyMatrix[i][j] = energy(j, i) + Math.min(energyMatrix[i - 1][j], energyMatrix[i - 1][j - 1]);
				} else {
					energyMatrix[i][j] = energy(j, i) + Math.min(energyMatrix[i - 1][j], Math.min(energyMatrix[i - 1][j - 1], energyMatrix[i - 1][j + 1]));
				}
			}
		}

		// Return seamPath from Bottom to TOP /
		int[] seamPath = new int[height];
		int start = 0;
		int end = width;
		for (int i = height - 1; i >= 0; i--) {
			double minimumEnergy = energyMatrix[i][start];
			seamPath[i] = start;
			for (int j = start; j < end; j++) {
				if (energyMatrix[i][j] < minimumEnergy) {
					minimumEnergy = energyMatrix[i][j];
					seamPath[i] = j;
				}
			}
			if (seamPath[i] == 0) {
				start = 0;
				end = Math.min(width, 2);
			}else if(seamPath[i] == width - 1) {
				start = width - 2;
				end = width;
			}else{
				start = seamPath[i] - 1;
				end = seamPath[i] + 2;
			}

		}

//		System.out.println("Path root");
//		for (int j = 0; j < height; j++) {
//			for (int i = 0; i < width; i++) {
//				System.out.print(energyMatrix[i][j] + " ");
//			}
//			System.out.println("");
//		}

		return seamPath;
	}

	// remove horizontal seam from picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from picture
	public void removeVerticalSeam(int[] seam) {

	}

}