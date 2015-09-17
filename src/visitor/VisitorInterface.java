package visitor;


import program.Program;
import statement.*;
import type.*;
import code.Code;
import declaration.*;
import expression.Assignment;
import expression.EBin;
import expression.EUn;
import expression.TBool;
import expression.TInt;
import expression.identifier.*;



public interface VisitorInterface {
	//code
	public void visitCode (Code c);

	//declaration
	public void visitFunctionDecl (FunctionDecl f);
	public void visitPrototypeDecl (PrototypeDecl f);
	public void visitType (Type f);
	public void visitVarDecl (VarDecl f);
	public void visitVarDeclSimple (VarDeclSimple f);
		//struct
		public void visitStruct (Struct f);

	//expression
	public void visitEBin(EBin a);
	public void visitEUn(EUn a);
		//identifier
		public void visitArrayId (ArrayId a);
		public void visitFunctionCall (FunctionCall a);
		public void visitStructId (StructId a);
		public void visitVarIdentifier (VarIdentifier a);
		//misc
		public void visitAssignment (Assignment a);
		public void visitTBool (TBool a);
		public void visitTInt  (TInt a);

	//program
	public void visitProgram (Program a);

	//statement
	public void visitBreak  (Break a);
	public void visitContinue (Continue a);
	public void visitEmptyInstruction(EmptyInstruction emptyInstruction);
	public void visitExpStatement (ExpStatement a);
	public void visitFor (For a);
	public void visitIf (If a);
	public void visitReturn (Return a);
	public void visitScope (Scope a);
	public void visitWhile (While w);
}