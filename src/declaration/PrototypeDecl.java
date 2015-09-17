package declaration;

import java.util.LinkedList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import program.ExternUnit;
import type.Type;
import visitor.FunctionHeader;
import visitor.VisitorInterface;

public class PrototypeDecl extends ExternUnit {
	public PrototypeDecl (String id, Type ret, Location loc){
		this.args = new LinkedList<Type>();
		this.id = id;
		this.ret = ret;
		this.loc = loc;
	}
	// To push the main function onto the TreeMap of declarations
	public PrototypeDecl (String id, List<Type> args, Type ret){
		this.args = args;
		this.id = id;
		this.ret = ret;
	}
	public PrototypeDecl (String id, List<Type> args, Type ret, Location loc){
		this.args = args;
		this.id = id;
		this.ret = ret;
		this.loc = loc;
	}
	public String getIdentifier (){
		return id;
	}
	public List<Type> getList (){
		return args;
	}
	public Type getRet(){
		return ret;
	}
	public void setFunctionDecl(FunctionDecl f){this.f = f;}
	public FunctionDecl getFunctionDecl(){return f;}

	public FunctionHeader getFunHeader(){return new FunctionHeader(id, args);}

	public Location getLocation (){
		return loc;
	}

	public void accept(VisitorInterface v){ v.visitPrototypeDecl(this); };
	private List<Type> args;
	private Type ret; //tipo de retorno
	private String id;
	private FunctionDecl f;
	private Location loc;
}
