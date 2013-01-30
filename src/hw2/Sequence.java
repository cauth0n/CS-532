package hw2;

import java.util.List;

public abstract class Sequence {
	protected List<Coefficient> sequence;

	public Sequence(List<Coefficient> sequence) {
		this.sequence = sequence;
	}

	public List<Coefficient> getSequence() {
		return sequence;
	}

	public void setIndex(int index, Coefficient value) {
		sequence.set(index, value);
	}

	public Coefficient getIndex(int index) {
		return sequence.get(index);
	}

	public void addCoefficientAtEnd(Coefficient toAdd) {
		sequence.add(toAdd);
	}
	
	public int getSize(){
		return sequence.size();
	}

}
