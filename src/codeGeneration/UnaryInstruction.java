package codeGeneration;

import misc.MutableInt;

public class UnaryInstruction implements Instruction  {
	public UnaryInstruction(UnaryInstructionEnum e, boolean b){
		this.e=e;
		this.b = b;
	}
	public UnaryInstruction(UnaryInstructionEnum e, MutableInt opnd1){
		this.e=e;
		this.opnd1 = opnd1;
	}
	public UnaryInstruction(UnaryInstructionEnum e, int opnd1){
		this.e=e;
		this.opnd1 = new MutableInt (opnd1);
	}
	public String toString(){
		if (opnd1 != null)
			return e.toString() + " " + opnd1.toString();
		else if (b)
			return e.toString() + " " + "true";
		else
			return e.toString() + " " + "false";
	}
	private MutableInt opnd1;
	private UnaryInstructionEnum e;
	boolean b;
}
