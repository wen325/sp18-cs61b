package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator{

		private int period;
		private int state;

		public StrangeBitwiseGenerator(int period) {
			state = 0;
			this.period = period;
		}

		public double next() {
			state = (state + 1);
//			int weirdState = state & (state >>> 3) % period;
			int weirdState = state & (state >> 3) & (state >> 8) % period;
			double var = (double) (weirdState % period) / period;
			return normalize(var);
		}

		public double normalize(double var) {
			return var * 2 - 1;
		}

}
