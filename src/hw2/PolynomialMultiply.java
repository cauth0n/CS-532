package hw2;

import java.util.ArrayList;
import java.util.List;

public class PolynomialMultiply {
	private Polynomial a;
	private Polynomial b;
	private int degree;
	private Polynomial c;

	public PolynomialMultiply() {
		a = new Polynomial(new ArrayList<Integer>());
		b = new Polynomial(new ArrayList<Integer>());
		c = new Polynomial(new ArrayList<Integer>());
		assign();

		print('a', a);
		print('b', b);
		normal_polynomial_multiplication(a, b);
		print('c', c);
	}

	public void assign() {
		a.addCoefficientAtEnd(9);
		a.addCoefficientAtEnd(-10);
		a.addCoefficientAtEnd(7);
		a.addCoefficientAtEnd(6);
		b.addCoefficientAtEnd(-5);
		b.addCoefficientAtEnd(4);
		b.addCoefficientAtEnd(0);
		b.addCoefficientAtEnd(-2);
		degree = a.getCoefficients().size();
		fill();
	}

	public void fill() {
		int n = a.getCoefficients().size();
		for (int i = n; i <= (2 * n) ; i++) {
			a.addCoefficientAtEnd(0);
			b.addCoefficientAtEnd(0);
		}
	}

	public void pointWiseMultiply(){
		
	}

	public DFT recursive_fft(Polynomial a) {
		int n = a.getCoefficients().size();
		if (n == 1) {
			return a;
		}

		int sigma = 1;
		Polynomial aEven = new Polynomial(new ArrayList<Integer>());
		Polynomial aOdd = new Polynomial(new ArrayList<Integer>());

		/**
		 * First for loop for assigning evens and odds. Does not violate the nlgn time complexity because it is done in
		 * n time.
		 */
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				aEven.addCoefficientAtEnd(a.getCoefficients().get(i));
			} else {
				aOdd.addCoefficientAtEnd(a.getCoefficients().get(i));
			}
		}

		DFT yEven = new DFT(new ArrayList<Integer>());
		DFT yOdd = new DFT(new ArrayList<Integer>());
		yEven = recursive_fft(aEven);
		yOdd = recursive_fft(aOdd);

		for (int k = 0; k < n / 2; k++) {	// left out the n/2-1 because of the < (not a <=)

		}

		return null;
	}

	public void normal_polynomial_multiplication(Polynomial a, Polynomial b) {
		List<Integer> aValues = a.getCoefficients();
		List<Integer> bValues = b.getCoefficients();
		for (int j = 0; j <= (2 * degree) - 2; j++) {
			int coefficientC = 0;
			for (int k = 0; k <= j; k++) {
				coefficientC += (aValues.get(k) * bValues.get(j - k));
			}
			c.addCoefficientAtEnd(coefficientC);
		}
	}

	public void print(char polyNumber, Polynomial toPrint) {
		List<Integer> values = toPrint.getCoefficients();
		System.out.print(polyNumber + ": ");
		for (int i = 0; i < values.size(); i++) {
			System.out.print(values.get(i) + "x^" + i + " + ");
		}
		System.out.println();
	}
}
