package statement;


import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.VisitorInterface;
import expression.Expression;

public class While extends LoopStatement{
	public While (Expression e, Statement s, Location loc){
		this.e = e;
		this.s = s;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitWhile(this);
	}
	public void setIsBoolean (boolean isBool){
		this.isBool = isBool;
	}
	public boolean isBoolean (){return isBool;}
	public Location getLocation (){ return loc;}
	public boolean hasReturn(){return s.hasReturn();}
	public Expression getE(){return e;}
	public Statement getS(){return s;}

	private Expression e;
	private Statement s;
	private boolean isBool;//This refers to the condition
	private Location loc;
}
