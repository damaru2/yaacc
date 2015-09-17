package expression;

import java_cup.runtime.ComplexSymbolFactory.Location;
import misc.ValueAndIsConstant;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;
import errors.ErrorMessages;
import expression.identifier.FunctionCall;
import expression.identifier.Identifier;


public class Assignment extends Expression{
	public Assignment(Expression  id, Expression exp, Location loc) {
		if(!(id instanceof Identifier))
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Left part of assignment is not an identifier");
		if(id instanceof FunctionCall)
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Left part of assignment is not an identifier");
		this.id = (Identifier) id;
		this.exp = exp;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitAssignment(this);
	}
	public Expression getExp() {return exp;}
	public Identifier getId() {return id;}
	public misc.ValueAndIsConstant ValueAndIsConstant(Visitor v) {
		return new ValueAndIsConstant(0, false);
	}

	public Type getType(Visitor v){
		Type idType = id.getType(v);
		if (!idType.equals(exp.getType(v)))
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"The assignation was not correctly typed.");
		if (idType.getSize() > 1){
			if (idType.getDimensions().size()!=0)
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Structured types cannot be directly assigned.");
			else // It's an struct
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Arrays cannot be directly assigned.");
		}

		return idType;
	}

	private Identifier id;
	private Expression exp;
	private Location loc;

}
