package expression;

import misc.ValueAndIsConstant;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;

public class TInt extends Expression {

	public TInt (String v) {
		this.v = Integer.valueOf(v).intValue();
	}
	public void accept(VisitorInterface v){
		v.visitTInt(this);
	}
	public int getVal() {return v;}
	public misc.ValueAndIsConstant ValueAndIsConstant(Visitor v) {
		return new ValueAndIsConstant (this.v, true);
	}
	public Type getType(Visitor v){
		return v.getTInt();
	}
	private int v;
}
