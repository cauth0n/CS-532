package hw2;

import java.util.ArrayList;
import java.util.List;

public class PolynomialMultiply {
	private Sequence a;
	private Sequence b;
	private int maxDegree;
	private int maxSize;
	private Sequence c;
	private Sequence y;

	public PolynomialMultiply() {
		a = new Polynomial(new ArrayList<Real>());
		b = new Polynomial(new ArrayList<Real>());
		c = new Polynomial(new ArrayList<Real>());
		y = new DFT(new ArrayList<Imaginary>());
		assign();

		print('a', a);
		print('b', b);
		// normal_polynomial_multiplication(a, b);
		y = iterative_fft(a);
		print('c', y);
	}

	public void assign() {
		a.addCoefficientAtEnd(new Real(0));
		a.addCoefficientAtEnd(new Real(0));
		a.addCoefficientAtEnd(new Real(0));
		a.addCoefficientAtEnd(new Real(0));
		a.addCoefficientAtEnd(new Real(0));
		a.addCoefficientAtEnd(new Real(0));
		a.addCoefficientAtEnd(new Real(0));
		a.addCoefficientAtEnd(new Real(0));

		b.addCoefficientAtEnd(new Real(0));
		b.addCoefficientAtEnd(new Real(0));
		b.addCoefficientAtEnd(new Real(0));
		b.addCoefficientAtEnd(new Real(0));
		if (a.getSize() > b.getSize()) {
			maxDegree = a.getSize();
		} else {
			maxDegree = b.getSize();
		}
		fill();
		if (a.getSize() > b.getSize()) {
			maxSize = a.getSize();
		} else {
			maxSize = b.getSize();
		}
	}

	public void fill() {
		for (int i = 0; i < (2 * maxDegree); i++) {
			c.addCoefficientAtEnd(0);
		}
		int aSize = a.getCoefficients().size();
		int bSize = b.getCoefficients().size();
		while (!isPowerOfTwo(aSize)) {
			a.addCoefficientAtEnd(0);
			aSize++;
		}
		while (!isPowerOfTwo(bSize) || bSize != aSize) {
			b.addCoefficientAtEnd(0);
			bSize++;
		}
	}

	public boolean isPowerOfTwo(int n) {
		boolean valid = false;
		if (Real.bitCount(n) == 1) {
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
		DFT A = new DFT(new ArrayList<Real>());
		a = new Polynomial(bit_reverse_copy(a));
		RootsOfUnityCircle unitCircle;
		int n = maxSize;
		for (int s = 1; s < lg(n); s++) {
			int m = (int) Math.pow(2, s);
			unitCircle = new RootsOfUnityCircle(m);
			for (int k = 0; k < n; k += m) {
				unitCircle.setPointer(1);
				for (int j = 0; j < (m / 2); j++) {
					int t = unitCircle.getValue() * A.getIndex(k + j + m / 2);
					int u = A.getIndex(k + j);
					A.setIndex(k + j, u + t);
					A.setIndex(k + j + (m / 2), u - t);
					unitCircle.incrementPointer();
				}
			}
		}
		return A;
	}

	public Polynomial bit_reverse_copy(Polynomial a) {
		Polynomial A = new Polynomial(maxSize, new ArrayList<Real>());
		for (int i = 0; i < maxSize; i++) {
			A.setCoefficient(bitFlip(i), a.getCoefficients().get(i));
		}
		return A;
	}

	public int bitFlip(int in) {
		String tempInString = Real.toBinaryString(in);
		String inString = "";
		while ((inString.length()) + (tempInString.length()) < lg(maxSize)) {
			inString += "0";
		}
		inString += tempInString;
		String rev = new StringBuffer(inString).reverse().toString();
		int reverse = Integer.parseInt(rev, 2);
		return reverse;
	}

	public void normal_polynomial_multiplication(Polynomial a, Polynomial b) {
		List<Coefficient> aValues = a.getSequence();
		List<Coefficient> bValues = b.getSequence();
		for (int j = 0; j <= (2 * maxDegree) - 2; j++) {
			Coefficient coefficientC = new Real(0);
			for (int k = 0; k <= j; k++) {
				coefficientC.setValue(aValues.get(k) * bValues.get(j - k));
			}
			c.addCoefficientAtEnd(coefficientC);
		}
	}

	public void print(char polyNumber, Sequence toPrint) {
		List<Coefficient> values = toPrint.getSequence();
		System.out.print(polyNumber + ": ");
		for (int i = 0; i < values.size(); i++) {
			System.out.print(values.get(i) + "x^" + i + " + ");
		}
		System.out.println();
	}
	
	
}
