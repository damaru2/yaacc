package codeGeneration;

public class SimpleInstruction implements Instruction {

	public SimpleInstruction(SimpleInstructionEnum e){
		this.e=e;
	}

	public String toString(){
		return e.toString();
	}
	private SimpleInstructionEnum e;
}
