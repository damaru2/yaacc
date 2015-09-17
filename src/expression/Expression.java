package expression;

import misc.ValueAndIsConstant;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;


public abstract class Expression {
	abstract public void accept(VisitorInterface v);
	abstract public ValueAndIsConstant ValueAndIsConstant (Visitor v);
	abstract public Type getType (Visitor v);
}

