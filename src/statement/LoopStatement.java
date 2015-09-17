package statement;

import misc.MutableInt;

public abstract class LoopStatement extends Statement {
	public void setContinue (int i){
		continueInst.setInt(i);
	}
	public void setBreak(int i){
		breakInst.setInt(i);
	}
	public MutableInt getBreak(){
		return breakInst;
	}
	public MutableInt getContinue(){
		return continueInst;
	}
	private MutableInt continueInst=new MutableInt();
	private MutableInt breakInst = new MutableInt();
}
