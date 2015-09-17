package statement;

import visitor.VisitorInterface;
import code.CodeUnit;


public abstract class Statement extends CodeUnit{
	abstract public void accept(VisitorInterface v);
}
