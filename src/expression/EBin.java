package expression;

import java_cup.runtime.ComplexSymbolFactory.Location;
import misc.ValueAndIsConstant;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;
import errors.ErrorMessages;
import expression.enumeratedTypes.BinOperation;


public class EBin extends Expression{
	public EBin(Expression opnd1, Expression opnd2, BinOperation op, Location loc) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.op =op;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitEBin(this);
	}
	public Expression getOpnd1() {return opnd1;}
	public Expression getOpnd2() {return opnd2;}
	public BinOperation getOp (){
		return op;
	}
	public boolean t1IsBool(){return t1IsBool;}
	public boolean t2IsBool(){return t2IsBool;}
	public ValueAndIsConstant ValueAndIsConstant (Visitor v){
		if (!op.isIntOp(op)) return new ValueAndIsConstant(0, false);
		ValueAndIsConstant vc1 = opnd1.ValueAndIsConstant(v);
		if (!vc1.isConstant()) return new ValueAndIsConstant(0, false);
		ValueAndIsConstant vc2 = opnd2.ValueAndIsConstant(v);
		if (!vc2.isConstant()) return new ValueAndIsConstant(0, false);
		switch (op){
			case MULT :
				return new ValueAndIsConstant (vc1.getValue()*vc2.getValue(), true);
			case DIV :
				if(vc2.getValue() == 0)
					ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Division by zero in the definition of the dimensions of an array.");
				return new ValueAndIsConstant (vc1.getValue()/vc2.getValue(), true);
			case REM :
				return new ValueAndIsConstant (vc1.getValue()%vc2.getValue(), true);
			case ADD :
				return new ValueAndIsConstant (vc1.getValue()+vc2.getValue(), true);
			case SUBS :
				return new ValueAndIsConstant (vc1.getValue()-vc2.getValue(), true);
			default:
				return new ValueAndIsConstant(0, false);
		}
	}
	public Type getType(Visitor v) {
		Type t1 = opnd1.getType(v);
		if (op.ArgsAreInt(op)){
			if (!t1.equals(v.getTInt()))
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"First operand of this expression should be an int");
			if (!opnd2.getType(v).equals(v.getTInt()))
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Second operand of this expression should be an int");
			if (op.isIntOp(op))//MULT, DIV, REM, ADD, SUBS
				return v.getTInt();
			else //LT, LE, GT, GE
				return v.getTBool();
		}
		else if(op.equals(BinOperation.EQ) || op.equals(BinOperation.NEQ)){
			Type t2 = opnd2.getType(v);
			if (!(
					(t1.equals(v.getTInt()) && t2.equals(v.getTInt())) ||
					 (t1.equals(v.getTBool()) && t2.equals(v.getTBool()))
				)){
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"The operands of an equality or unequality must have the same type.");
			}
			return v.getTBool();
		}
		else{ //AND, OR
			if (!(t1.equals(v.getTInt()) || t1.equals(v.getTBool())))
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"First operand of this expression should be an int or a bool");
			t1IsBool = t1.equals(v.getTBool());
			Type t2 = opnd2.getType(v);
			if (!(t2.equals(v.getTInt()) || t2.equals(v.getTBool())))
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Second operand of this expression should be an int or a bool");
			t2IsBool = t2.equals(v.getTBool());
			return v.getTBool();
		}
	}

	private Expression opnd1;
	private Expression opnd2;
	private BinOperation op;
	private boolean t1IsBool=false;
	private boolean t2IsBool=false;
	private Location loc;


}
