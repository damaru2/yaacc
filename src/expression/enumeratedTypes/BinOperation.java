package expression.enumeratedTypes;

import codeGeneration.SimpleInstructionEnum;

public enum BinOperation {
	MULT,
	DIV,
	REM,
	ADD,
	SUBS,
	LT,
	LE,
	GT,
	GE,
	EQ,
	NEQ,
	AND,
	OR;
	public boolean isIntOp (BinOperation b){
		return b.ordinal() <= 4; //De 0 a 4 son operaciones que devuelven un entero
	}
	public boolean ArgsAreInt (BinOperation b){
		return b.ordinal() <= 8; //De 0 a 8 son operaciones cuyos argumentos son enteros
	}
	public SimpleInstructionEnum getCodeEnum (){
		switch (this){
		case MULT: return SimpleInstructionEnum.MUL;
		case DIV: return SimpleInstructionEnum.DIV;
		case ADD: return SimpleInstructionEnum.ADD;
		case SUBS: return SimpleInstructionEnum.SUB;
		case LT: return SimpleInstructionEnum.LES;
		case LE: return SimpleInstructionEnum.LEQ;
		case GT: return SimpleInstructionEnum.GRT;
		case GE: return SimpleInstructionEnum.GEQ;
		case EQ: return SimpleInstructionEnum.EQU;
		case NEQ:return SimpleInstructionEnum.NEQ;
		case AND:return SimpleInstructionEnum.AND;
		case OR: return SimpleInstructionEnum.OR;
		default: return null;
		}
	}
};
