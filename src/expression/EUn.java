package expression;

import java_cup.runtime.ComplexSymbolFactory.Location;
import misc.ValueAndIsConstant;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;
import errors.ErrorMessages;
import expression.Expression;
import expression.enumeratedTypes.UnaryOperation;
import expression.identifier.*;

public class EUn extends Expression {
	public EUn(Expression opnd1, UnaryOperation op, Location loc) {
		 this.opnd1 = opnd1;
		 this.op = op;
		 this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitEUn(this);
	}
	public Expression getOpnd1() {
		 return opnd1;
	}
	public UnaryOperation getOp() {
		 return op;
	}
	public ValueAndIsConstant ValueAndIsConstant(Visitor v) {
		if (!opnd1.ValueAndIsConstant(v).isConstant()) return new ValueAndIsConstant(0, false);
		// The only admissible unary operation over a constant is the negation.
		if (op == UnaryOperation.NEG) return new ValueAndIsConstant (-opnd1.ValueAndIsConstant(v).getValue(), true);
		return new ValueAndIsConstant(0, false);
	}
	public Type getType(Visitor v) {
		if (op.equals(UnaryOperation.NEG)){
			if (!opnd1.getType(v).equals(v.getTInt()))
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Neg operation must receive an int as operand.");
			return v.getTInt();
		}
		else if (op.equals(UnaryOperation.NOT)){
			Type t1 = opnd1.getType(v);
			if (!t1.equals(v.getTInt()) && !t1.equals(v.getTBool()))
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Not operation '!' must receive an int or bool as operand.");
			isBool = t1.equals(v.getTBool());
			return v.getTBool();
		}
		else{
			if (!(	(opnd1 instanceof ArrayId) || //POSTINCR, PREINCR, POSTDECR, PREDECR must receive an ArrayId, StructId, VarIdentifier
					(opnd1 instanceof StructId) ||
					(opnd1 instanceof VarIdentifier)
				 ) || !opnd1.getType(v).equals(v.getTInt())
			   )
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Decrement or increment must have an integer variable as operand.");
			return v.getTInt();
		}
	}
	public boolean isBoolean (){return isBool;}

	private Expression opnd1;
	private UnaryOperation op;
	private boolean isBool = false;
	private Location loc;

}
