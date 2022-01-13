package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator{
	private int period;
	private int state;
	private double accer;

	public AcceleratingSawToothGenerator(int period, double accer) {
		state = 0;
		this.period = period;
		this.accer = accer;
	}

	public double next() {
		state = (state + 1);
		double var =  (double) (state % period) / (double) (period);
		if (state % period == 0) {
			period = (int) ((double) period * accer);
			state = 0;
		}
		return normalize(var);
	}

	public double normalize(double var) {
		return var * 2 - 1;
	}

}
