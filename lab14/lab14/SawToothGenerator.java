package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {

	private int period;
	private int state;

	public SawToothGenerator(int period) {
		state = 0;
		this.period = period;
	}

	public double next() {
		state = (state + 1);
		double var = (double) (state % period) / period;
		return normalize(var);
	}

	public double normalize(double var) {
		return var * 2 - 1;
	}

}
