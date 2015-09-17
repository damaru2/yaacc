package expression;

import misc.ValueAndIsConstant;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;

public class TBool extends Expression {

	public TBool (Boolean v) {
		this.v = v;
	}
	public void accept(VisitorInterface v){
		v.visitTBool(this);
	}
	public boolean boolVal() {return v;}
	private boolean v;
	public misc.ValueAndIsConstant ValueAndIsConstant(Visitor v) {
		return new ValueAndIsConstant (0, false);
	}

	public Type getType(Visitor v) {
		return v.getTBool();
	}

}
