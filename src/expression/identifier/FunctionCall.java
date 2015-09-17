package expression.identifier;

import java.util.LinkedList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import program.ExternUnit;
import declaration.FunctionDecl;
import declaration.PrototypeDecl;
import type.Type;
import visitor.FunctionHeader;
import visitor.Visitor;
import visitor.VisitorInterface;
import errors.ErrorMessages;
import expression.Expression;

public class FunctionCall extends Identifier{
	public FunctionCall(String id, Location loc) {
		 this.id = id;
		 this.list= new LinkedList<Expression>();
		 this.loc = loc;
	 }
	//this constructor will only be used to create a main call
	//in order to have a start point to generate code
	public FunctionCall(String id) {
		 this.id = id;
		 this.list= new LinkedList<Expression>();
	 }
	public FunctionCall(String id, List<Expression> list, Location loc) {
		 this.id = id;
		 this.list=list;
		 this.loc = loc;
	 }
	public void accept(VisitorInterface v){
		v.visitFunctionCall(this);
	}
	public List<Expression> getList() {return list;}
	public String getId(){return id;}

	public Type getType(Visitor v) {
		List<Type> lt = new LinkedList<Type>();
		for(Expression e : list)
			lt.add(e.getType(v));
		if (id.equals("main"))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"main function cannot be called from the code.");
		declarationOrPrototype=v.getFunction(new FunctionHeader(id, lt));
		if(declarationOrPrototype == null)
			ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Function " + id + " called but not declared.");
		if(declarationOrPrototype instanceof PrototypeDecl)
			return ((PrototypeDecl) declarationOrPrototype).getRet();
		else // declarationOrPrototype instanceof FunctionDecl
			return ((FunctionDecl) declarationOrPrototype).getRet();
	}

	public FunctionDecl getFunctionDecl(){
		if (declarationOrPrototype instanceof PrototypeDecl)
			return ((PrototypeDecl)declarationOrPrototype).getFunctionDecl();
		return (FunctionDecl)declarationOrPrototype;
	}
	public void setDeclarationOrPrototype (ExternUnit eu){
		declarationOrPrototype = eu;
	}

	private String id;
	private List<Expression> list;
	private ExternUnit declarationOrPrototype; //We assign it when we call getType.
	private Location loc;

}
