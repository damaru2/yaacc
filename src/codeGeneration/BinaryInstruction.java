package codeGeneration;

import misc.MutableInt;

public class BinaryInstruction implements Instruction {

	public BinaryInstruction(BinaryInstructionEnum e, int opnd1, int opnd2){
		this.e=e;
		this.opnd1 = opnd1;
		this.opnd2 = new MutableInt(opnd2);
	}
	public BinaryInstruction(BinaryInstructionEnum e, int opnd1, MutableInt opnd2){
		this.e=e;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	public String toString(){
		return e.toString() + " " + Integer.toString(opnd1) + " " + opnd2.toString();
	}
	private int opnd1;
	private MutableInt opnd2;
	private BinaryInstructionEnum e;
}
