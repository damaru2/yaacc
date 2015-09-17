package statement;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.VisitorInterface;



public class Continue extends Statement{
	public Continue (Location loc){
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitContinue(this);
	}

	public Location getLocation (){ return loc;}
	public LoopStatement getLoopStatement(){return s;}
	public void setStatement(LoopStatement s){this.s = s;}
	public boolean hasReturn(){return false;}
	private LoopStatement s;
	private Location loc;
}
