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

			System.out.println("Definite integral of "+functionText+" from "+lower_bound+" to "+upper_bound+":");
			Integrator.integrate(f_x, lower_bound, upper_bound, step_size, "trapezoid");
			Integrator.integrate(f_x, lower_bound, upper_bound, step_size, "simpson");
			Integrator.integrate(f_x, lower_bound, upper_bound, step_size, "3/8");
			Integrator.integrate(f_x, lower_bound, upper_bound, step_size, "boole");
			
			System.out.println();
		}
	}
}
