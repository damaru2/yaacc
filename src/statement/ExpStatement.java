package statement;

import visitor.VisitorInterface;
import expression.Expression;

public class ExpStatement extends Statement{
	public ExpStatement (Expression e){
		this.e = e;
	}
	public void accept(VisitorInterface v){
		v.visitExpStatement(this);
	}
	public Expression getE(){return e;}
	public boolean hasReturn(){return false;}
	private Expression e;
}
