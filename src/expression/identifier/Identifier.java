package expression.identifier;

import misc.ValueAndIsConstant;
import visitor.Visitor;
import visitor.VisitorInterface;
import expression.Expression;

public abstract class Identifier extends Expression{
	abstract public void accept(VisitorInterface v);
	public misc.ValueAndIsConstant ValueAndIsConstant(Visitor v) {
		//por diseño no se puede poner nada que tenga un identificador entre los corchetes
		return new ValueAndIsConstant(0, false);
	}
}
