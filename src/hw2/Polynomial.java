package hw2;

import java.util.List;

public class Polynomial extends Sequence {

	public Polynomial(List<Coefficient> sequence) {
		super(sequence);
	}

	public Polynomial(Polynomial another) {
		this(another.getSequence());
	}

	public Polynomial(int size, List<Coefficient> sequence) {
		super(sequence);
		for (int i = 0; i < size; i++) {
			sequence.add(new Real());
		}
	}

}
