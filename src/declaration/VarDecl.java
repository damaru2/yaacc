package declaration;

import java.util.LinkedList;
import java.util.List;

import type.Type;
import visitor.VisitorInterface;
import code.CodeUnit;


public class VarDecl  extends CodeUnit{
	public void push (VarDeclSimple v){
		l.add(v);
	}
	public Type getType(){
		return ((LinkedList<VarDeclSimple>)l).peek().getType();
	}
	public List<VarDeclSimple> getList(){
		return l;
	}
	public void accept(VisitorInterface v){ v.visitVarDecl(this); };
	private List<VarDeclSimple> l = new LinkedList<VarDeclSimple>();

	public boolean hasReturn() {return false;}
}
