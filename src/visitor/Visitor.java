package visitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

import java_cup.runtime.ComplexSymbolFactory.Location;
import misc.ValueAndIsConstant;
import program.ExternUnit;
import program.Program;
import statement.Break;
import statement.Continue;
import statement.EmptyInstruction;
import statement.ExpStatement;
import statement.For;
import statement.If;
import statement.LoopStatement;
import statement.Return;
import statement.Scope;
import statement.While;
import type.Type;
import code.Code;
import code.CodeUnit;
import declaration.FunctionDecl;
import declaration.PrototypeDecl;
import declaration.Struct;
import declaration.VarDecl;
import declaration.VarDeclSimple;
import errors.ErrorMessages;
import expression.Assignment;
import expression.EBin;
import expression.EUn;
import expression.Expression;
import expression.TBool;
import expression.TInt;
import expression.identifier.ArrayId;
import expression.identifier.FunctionCall;
import expression.identifier.StructId;
import expression.identifier.VarIdentifier;


public class Visitor implements VisitorInterface {
	public Visitor (String outputFileName){
		this.outputFileName = outputFileName;
	}

	public void visitCode(Code c) {
		for(CodeUnit l: c.getList()){
			l.accept(this);
		}
	}

	public void visitFunctionDecl(FunctionDecl f) {
		f.getRet().accept(this);
		Location loc = f.getLocation();
		if (f.getRet().getSize() > 1)
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"A function cannot return structured types nor arrays.");

		if (f.getIdentifier().equals("main") && (!f.getRet().equals(tVoid) || f.getArgs().size()!=0))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"The main function must have the following format: void main().");

		variables.createNewScope();
		for (VarDeclSimple l : f.getArgs())
			l.accept(this);

		FunctionHeader fh = f.getFunHeader();
		ExternUnit fun = functions.get(fh);
		if (fun == null) // If it hadn't been declared, we add it to the map
			functions.put(fh, f);
		else if(fun instanceof PrototypeDecl){
			if(!f.getRet().equals(((PrototypeDecl) fun).getRet()))
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Prototype return type and function return type do not match.");
			else{ // If everything is correct we update the prototype declaration
				functions.put(fh,f);
				((PrototypeDecl) fun).setFunctionDecl(f);
			}
		}
		else{ // fun instanceof FunctionDeclaration
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"The function " + fh.getId() + " has already been declared.");
		}

		this.currentFunction = f; //Pal return
		if(!f.getRet().equals(tVoid) && !f.getCode().hasReturn())
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"There exists a branch in this function without a return.");
		f.getCode().accept(this);

		this.currentFunction = null;

		variables.deleteScope();
	}

	public void visitPrototypeDecl(PrototypeDecl f) {
		f.getRet().accept(this);
		Location loc = f.getLocation();
		if (f.getRet().getSize() > 1)
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"A function cannot return structured types nor arrays.");
		if (f.getIdentifier().equals("main"))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Main cannot have a protoype.");

		for (Type l : f.getList())
			l.accept(this);
		FunctionHeader fh = f.getFunHeader();
		ExternUnit e = functions.get(fh);
		if(e != null)
			if(e instanceof FunctionDecl)
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Function " + f.getIdentifier() +" declared prior to its prototype.");
			else{ // e instanceof PrototypeDecl
				if (!((PrototypeDecl)e).getIdentifier().equals("main"))
					ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+
					"Two equivalent prototypes (lines " + ((PrototypeDecl)e).getLocation().getLine() + ", " + loc.getColumn() + "respectively) "
					+ "associated to the function have been declared.");
			}
		else
			functions.put(fh, f);
	}

	public void visitType(Type f) {
		String name = f.getTypeName();
		Location loc = f.getLocation();
		if (!(name.equals("int") || name.equals("bool") || name.equals("void"))){
			Struct s = structs.get(name);
			if (s == null)
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Type " + name + " has not been declared");
			else{
				f.setStruct(s);
			}
		}
		if (name.equals("void") && f.getList().size()!=0)
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"An array of a void is not a valid type");

		for(Expression e : f.getList()){
			ValueAndIsConstant v = e.ValueAndIsConstant(this);
			if (!v.isConstant())
				ErrorMessages.printErr(loc.getLine()+":"+loc.getColumn()+":"+"Non-constant dimension.");
			else if(v.getValue()<=0)
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Array dimension is " + v.getValue() + ". It must be greater than zero.");
			else
				f.pushDimension(v.getValue());
		}
	}

	public void visitVarDecl(VarDecl f) {
		for (VarDeclSimple v: f.getList())
			v.accept(this);
	}

	public void visitVarDeclSimple(VarDeclSimple f) {
		f.getType().accept(this);
		Location loc = f.getLocation();
		if (f.getType().getTypeName().equals("void"))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"void is not a valid type for a variable.");

		String s = ((VarIdentifier)f.getIdentifier()).getId();
		if (structs.get(s)!= null)
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Variable " + s + " corresponds to a struct type name.");
		else if (variables.isInCurrentScope(s))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Variable " + s + " already defined in this scope.");
		else{
			variables.pushVariable(s, f);
			if(variables.getDepth()==1)
				f.setNestingDepth(1);
			else
				f.setNestingDepth(0);
		}

		//We do not accept the identifier in a variable declaration
		// since we do not have to link this variable to its declaration in its own declaration.
		//f.getIdentifier().accept(this);
		if (f.getInit()!= null)
			if(!f.getType().equals(f.getInit().getType(this)))
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Variable initialization does not match the type of the variable.");
			else
				f.getInit().accept(this);
	}

	public void visitStruct(Struct f) {
		Location loc = f.getLocation();
		if(structs.get(f.getId())!=null)
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Struct " + f.getId() + " previously declared.");
		else if(variables.isInCurrentScope(f.getId()))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Struct identifier " + f.getId() + " already used in a global variable declaration.");
		else{
			variables.createNewScope();
			int offset =0;
			for(VarDecl var : f.getList())
				for(VarDeclSimple vs : var.getList()){
					vs.setPosStack(offset);
					vs.accept(this);
					offset += vs.getType().getSize();
				}
			f.setDeclMap(variables.getCurrentScope());
			structs.put(f.getId(), f);

			variables.deleteScope();
		}
	}

	public void visitEBin(EBin a) {
		a.getOpnd1().accept(this);
		a.getOpnd2().accept(this);
	}

	public void visitEUn(EUn a) {
		a.getOpnd1().accept(this);
	}

	public void visitArrayId(ArrayId a) {
		a.getExp().accept(this);
		a.getLp().accept(this);
	}

	public void visitFunctionCall(FunctionCall a) {
		for (Expression e: a.getList())
			e.accept(this);
	}

	public void visitStructId(StructId a) {
		a.getLp().accept(this);
	}

	public void visitVarIdentifier(VarIdentifier a) {
		VarDeclSimple v = variables.getVarDeclSimple(a.getId());
		if (v == null){
			Location loc = a.getLocation();
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+"'" + a.getId() + "' undeclared (first use in this function).");
		}
		else
			a.setRefToDecl(v);
	}

	public void visitAssignment(Assignment a) {
		a.getId().accept(this);
		a.getExp().accept(this);
	}

	public void visitTBool(TBool a) {
	}

	public void visitTInt(TInt a) {
	}

	public void visitProgram(Program a) {
		List<Type> lt = new LinkedList<Type>();
		Location loc;
		functions.put(new FunctionHeader("main", lt), new PrototypeDecl("main", lt, tVoid));
		variables.createNewScope();
		for(ExternUnit eu : a.getList()){
			eu.accept(this);
			if (eu instanceof VarDecl)
				a.addGlobalVar((VarDecl)eu);
			else if(eu instanceof FunctionDecl)
				a.addFunDecl((FunctionDecl)eu);
		}
		for (Entry<FunctionHeader, ExternUnit> en : functions.entrySet())
			if (! (en.getValue() instanceof FunctionDecl)){
				//If it is not a FunctionDecl, it is a PrototypeDecl
				//I add this just in case. If the structure of the program is changed,
				//you will be warned
				if (!(en.getValue() instanceof PrototypeDecl)){
					System.err.println("You have tried to cast to a PrototypeDecl");
					System.exit(1);
				}
				loc = ((PrototypeDecl)en.getValue()).getLocation();
				if (en.getKey().getId().equals("main"))
					ErrorMessages.printWarning("The program must have a void main() function");
				else
					ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Prototype " + en.getKey().getId() + " declared but the function was not declared.");
			}
		variables.deleteScope();
		if (!ErrorMessages.getErrors()){
			VisitorCodeGeneration visitorCode = new VisitorCodeGeneration(this, outputFileName);
			a.accept(visitorCode);
			visitorCode.printCode();
		}

	}

	public void visitBreak(Break a) {
		if (loops.isEmpty()){
			Location loc = a.getLocation();
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Break statement not within loop.");
		}
		else
			a.setStatement(loops.peek());
	}

	public void visitContinue(Continue a) {
		if (loops.isEmpty()){
			Location loc = a.getLocation();
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Continue statement not within loop.");
		}
		else
			a.setStatement(loops.peek());
	}
	public void visitEmptyInstruction(EmptyInstruction emptyInstruction){
	}

	public void visitExpStatement(ExpStatement a) {
		a.getE().getType(this); //Checks the coherence of the expression itself
		a.getE().accept(this);
	}

	public void visitFor(For a) {
		Type t;
		Location loc = a .getLocation();
		if(a.getAssign() instanceof ExpStatement){
			ExpStatement e = (ExpStatement)a.getAssign();
			if( e.getE() instanceof Assignment){
				e.getE().getType(this); //Checks the coherence of the expression itself
				e.getE().accept(this);
			}
			else
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"First statement in a for must be an assignment");
		}
		t=a.getCond().getType(this);
		if(!(t.equals(tInt) || t.equals(tBool)))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"Second statement in a for must be a boolean expression.");
		a.setIsBoolean(t.equals(tBool));
		a.getCond().accept(this);

		if(a.getIncrem() instanceof ExpStatement){
			((ExpStatement)a.getIncrem()).getE().getType(this); //Checks the coherence of the expression itself
			((ExpStatement)a.getIncrem()).getE().accept(this);
		}
		loops.push(a);
		a.getS().accept(this);
		loops.pop();
	}

	public void visitIf(If a) {
		Type t;
		t=a.getE().getType(this);
		if(!(t.equals(tInt) || t.equals(tBool))){
			Location loc = a.getLocation();
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"The condition in an if must be a boolean expression.");
		}
		a.setIsBoolean(t.equals(tBool));
		a.getE().accept(this);
		a.getS1().accept(this);
		if(a.getS2() != null)
			a.getS2().accept(this);
	}

	public void visitReturn(Return a) {
		Location loc = a.getLocation();
		a.setF(this.currentFunction);
		if(a.getE() == null){
			if (!this.currentFunction.getRet().equals(tVoid))
				ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"The returned value does not match the definition.");
		}
		else if(!a.getE().getType(this).equals(this.currentFunction.getRet()))
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"The returned value type does not match the definition.");
		else
			a.getE().accept(this);

	}
	public void visitScope(Scope a) {
		variables.createNewScope();

		a.getCode().accept(this);
		variables.deleteScope();
	}
	public void visitWhile(While w) {
		Type t;
		t=w.getE().getType(this);
		if(!(t.equals(tInt) || t.equals(tBool))){
			Location loc = w.getLocation();
			ErrorMessages.printWarning(loc.getLine()+":"+loc.getColumn()+":"+"The condition in a while statement must be a boolean expression");
		}
		w.setIsBoolean(t.equals(tBool));
		w.getE().accept(this);
		loops.push(w);
		w.getS().accept(this);
		loops.pop();
	}

	public IdTableVar getIdTableVar (){return variables;}

	public ExternUnit getFunction (FunctionHeader fh){return functions.get(fh);}
	public VarDeclSimple getVariable (String id){return variables.getVarDeclSimple(id);}
	public Type getTInt(){return tInt;}
	public Type getTBool(){return tBool;}
	public Type getTVoid(){return tVoid;}

	private IdTableVar variables = new IdTableVar();
	private TreeMap <FunctionHeader, ExternUnit> functions = new TreeMap <FunctionHeader, ExternUnit> (new comparatorFunctionHeader());
	private TreeMap <String, Struct> structs = new TreeMap<String,Struct>();
	private FunctionDecl currentFunction = null;
	private Stack<LoopStatement> loops = new Stack<LoopStatement>();
	private String outputFileName;

	private static Type tInt = new Type("int");
	private static Type tBool = new Type("bool");
	private static Type tVoid = new Type("void");



}
