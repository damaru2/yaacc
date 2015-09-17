package statement;

import code.Code;
import visitor.VisitorInterface;

public class Scope extends Statement{
	public Scope (Code c){
		this.c = c;
	}
	public void accept(VisitorInterface v){
		v.visitScope(this);
	}
	public Code getCode (){
		return c;
	}
	public boolean hasReturn(){return c.hasReturn();}
	private Code c;
}
