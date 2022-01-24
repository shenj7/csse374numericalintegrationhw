package part1;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {
	public static void main(String[] args) {

		Map<String, Function<Double, Double>> functionMap = new HashMap<>();
		functionMap.put("f(x)=x",   (x) -> x);
		functionMap.put("f(x)=x^2", (x) -> x * x);
		functionMap.put("f(x)=x^3", (x) -> x * x * x);
		functionMap.put("f(x)=x^4", (x) -> x * x * x * x);
		
		double lower_bound=-1;
		double upper_bound=1;
		double step_size=0.01;
		
		String[] functionTextInSortedOrder = functionMap.keySet().stream().sorted().toArray((size) -> new String[size]);
		
		for(String functionText : functionTextInSortedOrder) {
			Function<Double, Double> f_x = functionMap.get(functionText);
			Integrator trapInt = new CustomIntegrator(f_x, lower_bound, upper_bound, step_size, new int[] {1, 1}, 1, "trapezoid");
			Integrator simpInt = new CustomIntegrator(f_x, lower_bound, upper_bound, step_size, new int[] {1, 4, 1}, 2, "simpson");
			Integrator threeeightInt = new CustomIntegrator(f_x, lower_bound, upper_bound, step_size, new int[] {1, 3, 3, 1}, 3, "3/8");
			Integrator booleInt = new CustomIntegrator(f_x, lower_bound, upper_bound, step_size, new int[] {7, 32, 12, 32, 7}, 4, "boole");

			System.out.println("Definite integral of "+functionText+" from "+lower_bound+" to "+upper_bound+":");
			
			trapInt.integrate();
			simpInt.integrate();
			threeeightInt.integrate();
			booleInt.integrate();
			
			System.out.println();
		}
	}
}
