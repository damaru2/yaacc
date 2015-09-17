package visitor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import misc.MutableInt;
import program.Program;
import statement.Break;
import statement.Continue;
import statement.EmptyInstruction;
import statement.ExpStatement;
import statement.For;
import statement.If;
import statement.Return;
import statement.Scope;
import statement.While;
import type.Type;
import code.Code;
import code.CodeUnit;
import codeGeneration.BinaryInstruction;
import codeGeneration.BinaryInstructionEnum;
import codeGeneration.Instruction;
import codeGeneration.SimpleInstruction;
import codeGeneration.SimpleInstructionEnum;
import codeGeneration.UnaryInstruction;
import codeGeneration.UnaryInstructionEnum;
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
import expression.enumeratedTypes.BinOperation;
import expression.identifier.ArrayId;
import expression.identifier.FunctionCall;
import expression.identifier.Identifier;
import expression.identifier.StructId;
import expression.identifier.VarIdentifier;


public class VisitorCodeGeneration implements VisitorInterface {

	public VisitorCodeGeneration (Visitor v, String outputFileName){
		this.visitorAST = v;
		this.outputFileName = outputFileName;
	}

	public void visitCode(Code c) {
		for (CodeUnit cu: c.getList())
			cu.accept(this);
	}

	public void visitFunctionDecl(FunctionDecl f) {
		f.setFstInstr(codeSize+1);
		int mp = sp;
		sp = 5;
		for(VarDeclSimple v : f.getArgs())
			v.accept(this);
		f.getCode().accept(this);

		//If it's a void function we add a return at the end (so the programmer doesn't have to do it himself)
		if(f.getRet().getTypeName().equals("void")){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.RETP));
			++codeSize;
		}
		sp = mp;
	}

	public void visitPrototypeDecl(PrototypeDecl f) {
		//Empty
	}

	public void visitType(Type f) {
		//Empty
	}

	public void visitVarDecl(VarDecl f) {
		for (VarDeclSimple v: f.getList())
			v.accept(this);
	}

	public void visitVarDeclSimple(VarDeclSimple f) {
		Expression init = f.getInit();
		f.setPosStack(sp);
		if (init != null){
			init.accept(this);
			if((init instanceof Identifier) && !(init instanceof FunctionCall)){
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
				++codeSize;
			}

		}
		else{
			int size = f.getType().getSize();
			 //realSp := realSp+sizeOfExpression
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.SSP, sp+size));
			sp += size;
			++codeSize;
		}

	}

	public void visitStruct(Struct f) {
		//Empty
	}

	public void visitEBin(EBin a) {
		a.getOpnd1().accept(this);
		//Implying the position is absolute
		if((a.getOpnd1() instanceof Identifier) && !(a.getOpnd1() instanceof FunctionCall)){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
			++codeSize;
		}
		if(a.getOp().equals(BinOperation.AND) ||a.getOp().equals(BinOperation.OR)){
			if(!a.t1IsBool()){
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.NEQ));
				codeSize += 2;
			}
		}
		a.getOpnd2().accept(this);
		if((a.getOpnd2() instanceof Identifier) && !(a.getOpnd2() instanceof FunctionCall)){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
			++codeSize;
		}
		if(a.getOp().equals(BinOperation.AND) ||a.getOp().equals(BinOperation.OR)){
			if(!a.t2IsBool()){
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.NEQ));
				codeSize += 2;
			}
		}

		if (a.getOp().equals(BinOperation.REM)){
			instructions.add(new BinaryInstruction(BinaryInstructionEnum.LOD,0, sp-2));
			instructions.add(new BinaryInstruction(BinaryInstructionEnum.LOD,0, sp-1));
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.DIV));
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.MUL));
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.SUB));
			codeSize +=5;
		}
		else{
			instructions.add(new SimpleInstruction(a.getOp().getCodeEnum()));
			++codeSize;
		}
		--sp;
	}
	public void visitEUn(EUn a) {

		switch (a.getOp()){
			case NOT:
				a.getOpnd1().accept(this);
				if((a.getOpnd1() instanceof Identifier) && !(a.getOpnd1() instanceof FunctionCall)){
					instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
					++codeSize;
				}
				if(!a.isBoolean()){
					instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));
					instructions.add(new SimpleInstruction(SimpleInstructionEnum.NEQ));
					codeSize += 2;
				}
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.NOT));
				++codeSize;
				break;
			case NEG:
				a.getOpnd1().accept(this);
				if((a.getOpnd1() instanceof Identifier) && !(a.getOpnd1() instanceof FunctionCall)){
					instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
					++codeSize;
				}
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.NEG));
				++codeSize;
				break;
			case POSTINCR:
				a.getOpnd1().accept(this);
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new BinaryInstruction(BinaryInstructionEnum.STR, 0, sp-1));
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.INC, 1));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.STO));
				codeSize += 7;
				break;
			case PREINCR:
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));//This is an arbitrary value
				++codeSize;
				++sp;
				a.getOpnd1().accept(this);
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.INC, 1));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new BinaryInstruction(BinaryInstructionEnum.STR, 0, sp-2));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.STO));
				codeSize += 6;
				--sp;

				break;
			case POSTDECR:
				a.getOpnd1().accept(this);
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new BinaryInstruction(BinaryInstructionEnum.STR, 0, sp-1));
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.DEC, 1));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.STO));
				codeSize += 7;
				break;
			case PREDECR:
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));//This is an arbitrary value
				++codeSize;
				++sp;
				a.getOpnd1().accept(this);
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.DEC, 1));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
				instructions.add(new BinaryInstruction(BinaryInstructionEnum.STR, 0, sp-2));
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.STO));
				codeSize += 6;
				--sp;
			break;

		}
	}

	public void visitArrayId(ArrayId a) {
		a.getLp().accept(this);
		a.getExp().accept(this);
		if((a.getExp() instanceof Identifier) && !(a.getExp() instanceof FunctionCall)){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
			++codeSize;
		}
		instructions.add(new BinaryInstruction(BinaryInstructionEnum.CHK, 0, a.getDimension()-1));
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.IXA, a.getMultiplier()));
		codeSize += 2;
		--sp;
	}

	public void visitFunctionCall(FunctionCall a) {
		//Do not accept its lp
		int sizeRet;
		if (a.getId().equals("main"))
			sizeRet = 0;
		else
			sizeRet = a.getFunctionDecl().getRet().getSize();

		int sizeExpr = 0;
		MutableInt retAdr = new MutableInt();
		int mp;

		if(a.getId().equals("main"))
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.MST, 0));
		else
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.MST, 1));
		mp = sp;
		sp =5; //ret, sl, mp, ep, pc are under the position of sp
		++codeSize;
		Iterator<VarDeclSimple> itVar = a.getFunctionDecl().getArgs().iterator();
		for(Expression e : a.getList()){
			e.accept(this);
			int size = itVar.next().getType().getSize();
			if ((e instanceof Identifier) && !(e instanceof FunctionCall)){
				instructions.add(new UnaryInstruction(UnaryInstructionEnum.MOVS, size));
				++codeSize;
			}
			sizeExpr += size;
		}
		instructions.add(new BinaryInstruction(BinaryInstructionEnum.CUP, sizeExpr, a.getFunctionDecl().getFstInstr())); //PC, this will be overwritten by the cup instruction
		++codeSize;

		sp = mp+sizeRet;
		retAdr.setInt(codeSize+1);
	}

	public void visitStructId(StructId a) {
		a.getLp().accept(this);
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.INC, a.getOffset()));
		codeSize += 1;
	}

	public void visitVarIdentifier(VarIdentifier a) {
		VarDeclSimple v = (VarDeclSimple)a.getRefToDecl();
		instructions.add(new BinaryInstruction(BinaryInstructionEnum.LDA,v.getNestingDepth(), v.getPosStack()));
		++codeSize;
		++sp;
	}

	public void visitAssignment(Assignment a) {
		a.getId().accept(this);
		instructions.add(new SimpleInstruction(SimpleInstructionEnum.DPL));
		++codeSize;
		++sp;
		a.getExp().accept(this);

		if ((a.getExp() instanceof Identifier) && !(a.getExp() instanceof FunctionCall)){
			//if(a.getExp().getType(visitorAST).getSize() == 1){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
			++codeSize;
		}
		instructions.add(new SimpleInstruction(SimpleInstructionEnum.STO));
		instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));

		codeSize += 2;
		sp-=2;
	}

	public void visitTBool(TBool a) {
		if (a.boolVal())
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, true));
		else
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, false));
		++codeSize;
		++sp;
	}

	public void visitTInt(TInt a) {
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, a.getVal()));
		++codeSize;
		++sp;
	}

	public void visitProgram(Program a) {
		for(VarDecl v : a.getGlobalVariables())
			v.accept(this);
		// We have to do this or else the P-machine won't work
		if(a.getGlobalVariables().isEmpty()){
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.SSP, 1)); //realSp := realSp-sizeOfExpression
			++codeSize;
			++sp;
		}
		FunctionCall main = new FunctionCall ("main");
		main.setDeclarationOrPrototype(visitorAST.getFunction(new FunctionHeader(main.getId(), new LinkedList<Type>())));

		main.accept(this);
		instructions.add(new SimpleInstruction(SimpleInstructionEnum.STP));
		sp = 0;
		++codeSize;
		for(FunctionDecl f : a.getFunctionDecl())
			f.accept(this);

	}

	public void visitBreak(Break a) {
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.UJP, a.getLoopStatement().getBreak()));
		++codeSize;
	}

	public void visitContinue(Continue a) {
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.UJP, a.getLoopStatement().getContinue()));
		++codeSize;
	}

	public void visitEmptyInstruction(EmptyInstruction emptyInstruction) {
		//Empty
	}

	public void visitExpStatement(ExpStatement a) {
		int currentSp = sp;
		a.getE().accept(this);
		//sp-(sizeOfExpression) = sp-(currentSp-sp)
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.SSP, currentSp)); //realSp := realSp-sizeOfExpression
		++codeSize;
		sp = currentSp;
	}

	public void visitFor(For a) {
		a.getAssign().accept(this);
		MutableInt afterFor = new MutableInt();
		int fstExecutableInstFor =codeSize+1;
		a.getCond().accept(this);
		if((a.getCond() instanceof Identifier) && !(a.getCond() instanceof FunctionCall)){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
			++codeSize;
		}
		if(!a.isBoolean()){
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.NEQ));
			codeSize += 2;
		}
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.FJP, afterFor));
		++codeSize;
		--sp;
		a.getS().accept(this);
		a.setContinue(codeSize+1);
		a.getIncrem().accept(this);
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.UJP, fstExecutableInstFor));
		++codeSize;
		afterFor.setInt(codeSize+1);
		a.setBreak(codeSize+1);
	}

	public void visitIf(If a) {
		MutableInt afterIfLabel = new MutableInt();
		a.getE().accept(this);
		if((a.getE() instanceof Identifier) && !(a.getE() instanceof FunctionCall)){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
			++codeSize;
		}
		if(!a.isBoolean()){
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.NEQ));
			codeSize += 2;
		}
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.FJP, afterIfLabel));
		++codeSize;
		--sp;
		a.getS1().accept(this);
		if (a.getS2() != null){
			MutableInt afterElseLabel = new MutableInt();
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.UJP, afterElseLabel));
			++codeSize;
			afterIfLabel.setInt(codeSize+1);
			a.getS2().accept(this);
			afterElseLabel.setInt(codeSize+1);
		}
		else
			afterIfLabel.setInt(codeSize+1);
	}

	public void visitReturn(Return a) {
		if (a.getE() != null){
			a.getE().accept(this);
			if((a.getE() instanceof Identifier) && !(a.getE() instanceof FunctionCall)){
				instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
				++codeSize;
			}
			//Depth 0 is your current MP
			instructions.add(new BinaryInstruction(BinaryInstructionEnum.STR, 0, 0));
			++codeSize;
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.RETF));
			++codeSize;
		}
		else{
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.RETP));
			++codeSize;
		}

	}

	public void visitScope(Scope a) {
		a.getCode().accept(this);	}

	public void visitWhile(While w) {
		w.setContinue(codeSize+1);
		w.getE().accept(this);
		if((w.getE() instanceof Identifier) && !(w.getE() instanceof FunctionCall)){
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.IND));
			++codeSize;
		}
		if(!w.isBoolean()){
			instructions.add(new UnaryInstruction(UnaryInstructionEnum.LDC, 0));
			instructions.add(new SimpleInstruction(SimpleInstructionEnum.NEQ));
			codeSize += 2;
		}
		MutableInt afterWhile = new MutableInt();
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.FJP, afterWhile));
		++codeSize;
		--sp;
		w.getS().accept(this);
		instructions.add(new UnaryInstruction(UnaryInstructionEnum.UJP, w.getContinue()));
		++codeSize;
		afterWhile.setInt(codeSize+1);
		w.setBreak(codeSize+1);
	}
	public void printCode(){
		int line = 0;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outputFileName);
		} catch (FileNotFoundException e) {
			System.out.println("FICHOERRRROROROOROOO!!!!!");
		}
		if (ErrorMessages.getErrors())
			System.exit(1);
		for (Instruction i: instructions){
			writer.println("{" + line + "} " + i.toString().toLowerCase() + ";");
			System.out.println((line++) + ": " + i.toString().toLowerCase() + ";");
		}
		writer.close();
	}
	private int sp = 0, codeSize=-1;
	private List<Instruction> instructions = new LinkedList<Instruction>();
	private String outputFileName;
	private Visitor visitorAST;
}
