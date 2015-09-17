package misc;

public class ValueAndIsConstant {
	public ValueAndIsConstant (int value, boolean isConstant){
		this.value = value;
		this.isConstant = isConstant;
	}
	public int getValue(){
		return value;
	}
	public boolean isConstant(){
		return isConstant;
	}
	private int value;
	private boolean isConstant;
}
