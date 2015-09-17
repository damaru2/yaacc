package misc;

public class MutableInt {
	public MutableInt(){

	}
	public MutableInt(int i){
		this.i = i;
	}
	public void setInt (int i){
		this.i = i;
	}
	public int getInt (){
		return i;
	}
	public String toString(){
		return Integer.toString(i);
	}
	private int i;
}
