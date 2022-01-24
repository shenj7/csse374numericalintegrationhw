package part1;

import java.util.function.Function;

public class CustomAreaUnderCurve extends Integrator {

	public CustomAreaUnderCurve(Function<Double, Double> f, double lowerBound, double upperBound, double stepSize,
			int[] distribution, int stepSizeModifier, String methodName) {
		super(f, lowerBound, upperBound, stepSize, distribution, stepSizeModifier, methodName);
	}

	@Override
	double integrateOneStep(double x_i) {
		double total = 0;
		double newStep = stepSize/stepSizeModifier;
		double distTotal = 0;
		for (int x = 0; x <= stepSizeModifier; x++) {
			total = total + f.apply(x_i + x*newStep)*distribution[x];
			if (wantArea()) {
				distTotal = distTotal + (distribution[x]>0?distribution[x]:-distribution[x]);
			}
		}
		return stepSize*total/distTotal;
	}

	@Override
	public boolean wantArea() {
		return true;
	}
}
