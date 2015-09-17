package code;

import program.ExternUnit;
import visitor.VisitorInterface;

public abstract class CodeUnit extends ExternUnit {
	abstract public void accept(VisitorInterface v);
	abstract public boolean hasReturn();
}
