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
		a = new Polynomial(new ArrayList<Coefficient>());
		b = new Polynomial(new ArrayList<Coefficient>());
		c = new Polynomial(new ArrayList<Coefficient>());
		y = new DFT(new ArrayList<Coefficient>());
		assign();

		print('a', a);
		print('b', b);
		normal_polynomial_multiplication(a, b);
		// y = iterative_fft(a);
		print('c', c);
	}

	public void assign() {
		a.addCoefficientAtEnd(new Real(1));
		a.addCoefficientAtEnd(new Real(2));
		a.addCoefficientAtEnd(new Real(3));
		a.addCoefficientAtEnd(new Real(2));
		// a.addCoefficientAtEnd(new Real(0));
		// a.addCoefficientAtEnd(new Real(0));
		// a.addCoefficientAtEnd(new Real(0));
		// a.addCoefficientAtEnd(new Real(0));

		b.addCoefficientAtEnd(new Real(1));
		b.addCoefficientAtEnd(new Real(2));
		b.addCoefficientAtEnd(new Real(3));
		 b.addCoefficientAtEnd(new Real(2));
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
			// c.addCoefficientAtEnd(new Real(0));
		}
		int aSize = a.getSize();
		int bSize = b.getSize();
		while (!isPowerOfTwo(aSize)) {
			a.addCoefficientAtEnd(new Real(0));
			aSize++;
		}
		while (!isPowerOfTwo(bSize) || bSize != aSize) {
			b.addCoefficientAtEnd(new Real(0));
			bSize++;
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
	 * At this point we know that n is a power of 2. Thus, the log will ALWAYS return an int.
	 */
	public Sequence iterative_fft(Sequence a) {
		Sequence A = new DFT(new ArrayList<Coefficient>());
		A = bit_reverse_copy(a);
		RootsOfUnityCircle unitCircle;
		int n = maxSize;
		for (int s = 1; s < lg(n); s++) {
			int m = (int) Math.pow(2, s);
			unitCircle = new RootsOfUnityCircle(m);
			for (int k = 0; k < n; k += m) {
				unitCircle.setPointer(1);
				for (int j = 0; j < (m / 2); j++) {
					int t = unitCircle.getValue() * (int) A.getIndex(k + j + m / 2).getValue();
					int u = (int) A.getIndex(k + j).getValue();
					A.setIndex(k + j, new Real(u + t));
					A.setIndex(k + j + (m / 2), new Real(u - t));
					unitCircle.incrementPointer();
				}
			}
		}
		return A;
	}

	public Sequence bit_reverse_copy(Sequence a) {
		Sequence A = new Polynomial(maxSize, new ArrayList<Coefficient>());
		for (int i = 0; i < maxSize; i++) {
			A.setIndex(bitFlip(i), a.getSequence().get(i));
		}
		return A;
	}

	public int bitFlip(int in) {
		String tempInString = Integer.toBinaryString(in);
		String inString = "";
		while ((inString.length()) + (tempInString.length()) < lg(maxSize)) {
			inString += "0";
		}
		inString += tempInString;
		String rev = new StringBuffer(inString).reverse().toString();
		int reverse = Integer.parseInt(rev, 2);
		return reverse;
	}

	public void normal_polynomial_multiplication(Sequence a, Sequence b) {
		List<Coefficient> aValues = a.getSequence();
		List<Coefficient> bValues = b.getSequence();
		for (int j = 0; j < (2 * (maxDegree)) - 2; j++) {
			int val = 0;
			for (int k = 0; k <= j; k++) {
				val += (int) aValues.get(k).getValue() * (int) bValues.get(j - k).getValue();
			}
			c.addCoefficientAtEnd(new Real(val));
		}
	}

	public void print(char polyNumber, Sequence toPrint) {
		List<Coefficient> values = toPrint.getSequence();
		System.out.print(polyNumber + ": ");
		for (int i = 0; i < values.size(); i++) {
			System.out.print(values.get(i).getValue() + "x^" + i + " + ");
		}
		System.out.println();
	}

}
