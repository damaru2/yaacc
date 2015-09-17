package statement;

import visitor.VisitorInterface;


public class EmptyInstruction extends Statement{
	public void accept(VisitorInterface v){
		v.visitEmptyInstruction(this);
	}
	public boolean hasReturn(){return false;}
}
