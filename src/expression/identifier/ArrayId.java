package expression.identifier;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;
import errors.ErrorMessages;
import expression.Expression;

public class ArrayId extends Identifier{

	public ArrayId(Expression lp, Expression exp, Location loc) {
		if (!(lp instanceof Identifier))
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Invalid access to a non-array variable. Left part is not an identifier.");
		this.loc = loc;
		this.lp = (Identifier) lp;
		this.exp = exp;
	}
	public void accept(VisitorInterface v){
		v.visitArrayId(this);
	}

	public Expression getExp(){
		return exp;
	}
	public Identifier getLp(){
		return lp;
	}

	public Type getType(Visitor v) {
		Type t = lp.getType(v);
		List<Integer> olddimensions = t.getDimensions();
		if(olddimensions.size()==0)
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Invalid access to a non-array variable.");
		Type texp = exp.getType(v);
		if(!texp.equals(v.getTInt()))
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"The array access index is not an integer.");
		Type newt = new Type(t.getTypeName(), loc);
		dimension = olddimensions.get(0);
		for(int i=1;i<olddimensions.size();++i)
			newt.pushDimension(olddimensions.get(i));
		if(t.getStruct()!= null)
			newt.setStruct(t.getStruct());
		multiplier = newt.getSize();
		return newt;
	}
	public int getMultiplier (){
		return multiplier;
	}
	public int getDimension(){
		return dimension;
	}

	public Location getLocation (){
		return loc;
	}

	private Identifier lp;
	private Expression exp;
	private int dimension;
	private int multiplier;
	private Location loc;

}
