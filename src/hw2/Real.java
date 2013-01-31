package hw2;

public class Real extends Coefficient {
	private int a;

	public Real() {

	}

	public Real(int a) {
		this.a = a;
	}

	@Override
	public Object getValue() {
		return a;
	}

	@Override
	public void setValue(Object value) {
		try{
			this.a = (int) value;
		}catch(Exception e){
			System.out.println("Error trying to set the value in Real");
		}
		
	}

}
