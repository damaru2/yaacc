	package declaration;

import java.util.LinkedList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import program.ExternUnit;
import type.Type;
import visitor.VisitorInterface;
import expression.Expression;
import expression.identifier.Identifier;

//It extends from ExternUnit so that VarIdentifier can have a reference to its declaration
public  class VarDeclSimple extends ExternUnit{
	public VarDeclSimple(Identifier id, Location loc){
		t = new Type(loc);
		this.id = id;
		this.init = null;
		this.loc = loc;
	}
	public VarDeclSimple(Type t, Identifier id, Location loc){
		this.t = t;
		this.id = id;
		this.init = null;
		this.loc = loc;
	}
	public VarDeclSimple(Identifier id, Expression init, Location loc){
		this.t = new Type(loc);
		this.id = id;
		this.init= init;
		this.loc = loc;
	}
	public VarDeclSimple(Type t, Identifier id, Expression init, Location loc){
		this.t = t;
		this.id = id;
		this.init = init;
		this.loc = loc;
	}


	public void setType (Type t){
		this.t.setTypeName(t.getTypeName());
		if (this.t.getList() == null)
			this.t.setArgsArray(new LinkedList<Expression> ());
	}
	public void setListType (List<Expression> l){
		t.setArgsArray(l);
	}
	public Type getType (){
		return t;
	}
	public Identifier getIdentifier(){
		return id;
	}
	public Expression getInit(){
		return init;
	}
	public void setNestingDepth(int depth){
		this.nestingDepth = depth;
	}
	public int getNestingDepth (){
		return nestingDepth;
	}
	public boolean equals(VarDeclSimple v2){
		return t.equals(v2);
	}
	public void  setPosStack (int posStack){
		this.posStack = posStack;
	}
	public int getPosStack (){
		return posStack;
	}
	public Location getLocation (){
		return loc;
	}

	public void accept(VisitorInterface v){ v.visitVarDeclSimple(this); };
	private int posStack;
	private Type t;
	private Identifier id;
	private Expression init;
	private int nestingDepth;
	private Location loc;
}
