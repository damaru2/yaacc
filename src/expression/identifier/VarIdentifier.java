package expression.identifier;

import java_cup.runtime.ComplexSymbolFactory.Location;
import declaration.VarDeclSimple;
import errors.ErrorMessages;
import program.ExternUnit;
import type.Type;
import visitor.Visitor;
import visitor.VisitorInterface;


public class VarIdentifier extends Identifier {

	public VarIdentifier (String id, Location loc) {
		this.id = id;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitVarIdentifier(this);
	}
	public String getId (){
		return id;
	}
	public ExternUnit getRefToDecl(){return refToDecl;}
	public void setRefToDecl (ExternUnit e){this.refToDecl = e;}

	public Type getType(Visitor v) {
		VarDeclSimple var = v.getVariable(id);
		if (var == null)
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Varible " + id + " not declared.");
		return var.getType();
	}

	public Location getLocation (){
		return loc;
	}

	private String id;
	private ExternUnit refToDecl;
	private Location loc;

}
