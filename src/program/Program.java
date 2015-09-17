package program;

import java.util.LinkedList;
import java.util.List;

import declaration.FunctionDecl;
import declaration.VarDecl;
import visitor.VisitorInterface;

public class Program {
	public Program (){
		list = new LinkedList<ExternUnit> ();
	}
	public void push (ExternUnit u){
		list.add(u);
	}
	public void accept(VisitorInterface v){
		v.visitProgram(this);
	}
	public List<ExternUnit> getList(){return list;}

	public void addGlobalVar(VarDecl v){globalVariables.add(v);}
	public void addFunDecl(FunctionDecl f){this.functionDeclaration.add(f);}
	public LinkedList<VarDecl> getGlobalVariables (){return globalVariables;}
	public LinkedList<FunctionDecl> getFunctionDecl (){return functionDeclaration;}

	private List<ExternUnit> list;
	private LinkedList<VarDecl> globalVariables = new LinkedList<VarDecl>();
	private LinkedList<FunctionDecl> functionDeclaration= new LinkedList<FunctionDecl>();
}
