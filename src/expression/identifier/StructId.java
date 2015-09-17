package expression.identifier;

import java_cup.runtime.ComplexSymbolFactory.Location;
import declaration.Struct;
import declaration.VarDeclSimple;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;
import errors.ErrorMessages;
import expression.Expression;

public class StructId extends Identifier{

	public StructId(Expression lp, String id, Location loc) {
		if (!(lp instanceof Identifier))
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Illegal struct access. Left part is not an identifier.");
		this.lp = (Identifier) lp;
		this.id = id;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitStructId(this);
	}

	public Type getType(Visitor v) {
		Struct s = lp.getType(v).getStruct();
		if(s == null)
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Invalid access to a non-struct variable.");
		VarDeclSimple var = s.getVarDeclSimple(id);
		if(var == null)
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"The struct " + s.getId() + " does not have the field " + id + " .");
		offset = var.getPosStack();
		return var.getType();
	}
	public int getOffset (){
		return offset;
	}
	public Location getLocation (){
		return loc;
	}

	public Identifier getLp (){return lp;}
	public String getId (){return id;}
	private Identifier lp;
	private String id;
	private int offset;
	private Location loc;
}
