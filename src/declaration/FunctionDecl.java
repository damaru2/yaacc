package declaration;

import java.util.LinkedList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import misc.MutableInt;
import program.ExternUnit;
import type.Type;
import visitor.FunctionHeader;
import visitor.VisitorInterface;
import code.Code;

public class FunctionDecl extends ExternUnit{
	public FunctionDecl (String id, List<VarDeclSimple> args, Code c, Type ret, Location loc){
		this.args = args;
		this.id = id;
		this.c = c;
		this.ret = ret;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){ v.visitFunctionDecl(this); };
	public String getIdentifier (){
		return id;
	}
	public Code getCode (){
		return c;
	}
	public Type getRet(){
		return ret;
	}
	public List<Type> getTypeList(){
		List<Type> ret = new LinkedList <Type> ();
		for(VarDeclSimple v : args)
			ret.add(v.getType());
		return ret;
	}
	public List<VarDeclSimple> getArgs(){return args;}
	public FunctionHeader getFunHeader(){return new FunctionHeader(id, this.getTypeList());}

	public MutableInt getFstInstr(){
		return fstInstr;
	}
	public void setFstInstr(int fstInstr ){
		this.fstInstr.setInt(fstInstr);
	}
	public Location getLocation (){
		return loc;
	}
	private List<VarDeclSimple> args;
	private String id;
	private Code c;
	private Type ret;
	private MutableInt fstInstr = new MutableInt();
	private Location loc;
}
