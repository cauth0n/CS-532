package hw2_redo;

public class Complex {
	private final double real;
	private final double imaginary;

	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public Complex(Complex another) {
		this(another.getReal(), another.getImaginary());
	}

	public double getReal() {
		return real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public Complex plus(Complex in) {
		double real = this.real + in.real;
		double imaginary = this.imaginary + in.imaginary;
		return new Complex(real, imaginary);
	}

	public Complex minus(Complex in) {
		double real = this.real - in.real;
		double imaginary = this.imaginary - in.imaginary;
		return new Complex(real, imaginary);
	}

	public Complex times(Complex in) {
		double real = (this.real * in.real) - (this.imaginary * in.imaginary);
		double imaginary = (this.real * in.imaginary) + (this.imaginary * in.real);
		return new Complex(real, imaginary);
	}
	
	public Complex times(double in){
		return new Complex(in * this.real, in * this.imaginary);
	}

	public Complex interpolate(){
		return new Complex(real, (-imaginary));
	}
	
	public String toString() {
		if (imaginary == 0) {
			return real + "";
		}
		if (real == 0) {
			return imaginary + "i";
		}
		if (imaginary < 0) {
			return real + "-" + (-imaginary) + "i";
		}
		return real + " + " + imaginary + "i";

	}

}
