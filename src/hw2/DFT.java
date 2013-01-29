package hw2;

import java.util.List;

public class DFT {
	private List<Integer> y;

	public DFT(List<Integer> y) {
		this.y = y;
	}

	public List<Integer> getDft() {
		return y;
	}

	public void setIndex(int index, int value) {
		y.add(index, value);
	}
	public int getIndex(int index){
		return y.get(index);
	}

}
