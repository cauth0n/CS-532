package hw2;

import java.util.List;

public class Polynomial {
	private List<Integer> coefficients;

	public Polynomial(List<Integer> coefficients) {
		this.coefficients = coefficients;
	}

	public List<Integer> getCoefficients() {
		return coefficients;
	}

	public void addCoefficientAtEnd(int a) {
		coefficients.add(a);
	}

}
