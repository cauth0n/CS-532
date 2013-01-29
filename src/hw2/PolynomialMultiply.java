package hw2;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.complex.Complex;

public class PolynomialMultiply {
	private Polynomial a;
	private Polynomial b;
	private int degree;
	private Polynomial c;
	private DFT y;

	public PolynomialMultiply() {
		a = new Polynomial(new ArrayList<Integer>());
		b = new Polynomial(new ArrayList<Integer>());
		c = new Polynomial(new ArrayList<Integer>());
		y = new DFT(new ArrayList<Integer>());
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
		for (int i = n; i < (2 * n); i++) {
			a.addCoefficientAtEnd(0);
			b.addCoefficientAtEnd(0);
		}
		n = a.getCoefficients().size();

		while (!isPowerOfTwo(n)) {
			a.addCoefficientAtEnd(0);
			b.addCoefficientAtEnd(0);
			n = a.getCoefficients().size();
		}
	}

	public boolean isPowerOfTwo(int n) {
		boolean valid = false;
		if (Integer.bitCount(n) == 1) {
			valid = true;
		}
		return valid;
	}

	public void pointWiseMultiply() {

	}

	public int lg(int num) {
		return (int) (Math.log(num) / Math.log(2));
	}

	/*
	 * At this point we know that n is a power of 2. Thus, the log will ALWAYS
	 * return an int.
	 */
	public DFT iterative_fft(Polynomial a) {
		DFT A = new DFT(new ArrayList<Integer>());
		A = bit_reverse_copy(a);
		int n = a.getCoefficients().size();
		for (int s = 1; s < lg(n); s++) {
			int m = (int) Math.pow(2, s);
			Complex omega = new Complex(real, imaginary)
			for (int k = 0; k < n; k += m) {

				for (int j = 0; j < (m / 2); j++) {
					int t = 0;
					int u = A.getIndex(k + j);
					A.setIndex(k + j, u + t);
					A.setIndex(k + j + (m / 2), u - t);
					
				}
			}
		}
		return A;
	}

	public DFT bit_reverse_copy(Polynomial a) {

	}

	public DFT recursive_fft(Polynomial a) {
		int n = a.getCoefficients().size();
		if (n == 1) {
			return null;
		}

		int omega = 1;
		Polynomial aEven = new Polynomial(new ArrayList<Integer>());
		Polynomial aOdd = new Polynomial(new ArrayList<Integer>());

		/**
		 * First for loop for assigning evens and odds. Does not violate the
		 * nlgn time complexity because it is done in n time.
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

		for (int k = 0; k < n / 2; k++) { // left out the n/2-1 because of the <
											// (not a <=)
			y.setIndex(k, (yEven.getIndex(k) + omega * yOdd.getIndex(k)));
			y.setIndex(k + (n / 2),
					yEven.getIndex(k) - omega * yOdd.getIndex(k));
			// omega *=
		}

		return y;
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
