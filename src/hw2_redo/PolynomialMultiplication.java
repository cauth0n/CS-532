package hw2_redo;

public class PolynomialMultiplication {

	private final int size = 16;
	private Complex[] Ca;
	private Complex[] Cb;
	private int[] Ra;
	private int[] Rb;

	public PolynomialMultiplication() {
		Ca = new Complex[size];
		Cb = new Complex[size];
		Ra = new int[size];
		Rb = new int[size];
		iFill();
		realFill();
		int[] c = normal_multiply(Ra, Rb);
		Complex[] ans = dft_multipy();
		printComplex(ans);
		System.out.println();
		print(c);
	}

	public Complex[] dft_multipy() {
		Ca = fillComplex(Ca);
		Cb = fillComplex(Cb);
		Complex[] yA = recursive_fft(Ca);
		Complex[] yB = recursive_fft(Cb);
		Complex[] C = pointWiseMultiply(yA, yB);
		System.out.println("The pointwise vector of C (Product of pointwise A and B):");
		print(C);
		Complex[] ans = invert_fft(C);
		return ans;
	}

	public Complex[] invert_fft(Complex[] y) {
		int n = y.length;
		Complex[] a = new Complex[n];
		for (int i = 0; i < n; i++) {
			a[i] = y[i].interpolate();
		}
		a = recursive_fft(a);
		for (int i = 0; i < n; i++) {
			a[i] = a[i].interpolate();
		}
		for (int i = 0; i < n; i++) {
			a[i] = a[i].times(1.0 / n);
		}
		return a;
	}

	public Complex[] fillComplex(Complex[] in) {
		Complex[] a = new Complex[2 * in.length];

		for (int i = 0; i < in.length; i++) {
			a[i] = in[i];
		}
		for (int i = in.length; i < in.length * 2; i++) {
			a[i] = new Complex(0, 0);
		}
		return a;
	}

	public Complex[] recursive_fft(Complex[] a) {
		int n = a.length;
		if (n == 1) {
			return new Complex[] { a[0] };
		}
		Complex[] evens = new Complex[n / 2];
		Complex[] odds = new Complex[n / 2];
		for (int i = 0; i < n / 2; i++) {
			evens[i] = a[2 * i];
		}
		for (int i = 0; i < n / 2; i++) {
			odds[i] = a[2 * i + 1];
		}
		Complex[] y0 = recursive_fft(evens);
		Complex[] y1 = recursive_fft(odds);

		Complex[] y = new Complex[n];
		for (int k = 0; k < n / 2; k++) {
			double omega = -2 * k * Math.PI / n;
			Complex circle = new Complex(Math.cos(omega), Math.sin(omega));
			y[k] = y0[k].plus(circle.times(y1[k]));
			y[k + n / 2] = y0[k].minus(circle.times(y1[k]));
		}
		return y;
	}

	public Complex[] pointWiseMultiply(Complex[] a, Complex[] b) {
		int n = a.length;
		Complex[] c = new Complex[n];
		for (int i = 0; i < n; i++) {
			c[i] = a[i].times(b[i]);
		}
		return c;
	}

	public void realFill() {
		for (int i = 0; i < size; i++) {
			Ra[i] = i;
			Rb[i] = i;
		}
	}

	public void iFill() {
		for (int i = 0; i < size; i++) {
			Ca[i] = new Complex(i, 0);
			Cb[i] = new Complex(i, 0);
		}
	}

	public int[] normal_multiply(int[] a, int[] b) {
		int[] c = new int[size * 2];
		for (int j = 0; j < size; j++) {
			for (int k = 0; k < size; k++) {
				c[j + k] += a[j] * b[k];
			}
		}
		return c;
	}

	public int lg(int num) {
		return (int) (Math.log(num) / Math.log(2));
	}

	public void print(Complex[] in) {
		for (int i = 0; i < in.length; i++) {
			System.out.println(in[i]);
		}
		System.out.println();
	}

	public void printComplex(Complex[] in) {
		System.out
				.println("Answer from using complex multiplications. Note that the values are rounded due to double overflow/underflow.");
		for (int i = 0; i < in.length; i++) {
			System.out.print(Math.round(in[i].getReal()) + "x^" + i + " + ");
		}
		System.out.println();
	}

	public void print(int[] in) {
		System.out.println("Answer from using normal O(n^2) multiplication.");
		for (int i = 0; i < in.length; i++) {
			System.out.print(in[i] + "x^" + i + " + ");
		}
		System.out.println();
	}

}
