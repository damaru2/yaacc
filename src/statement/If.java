package statement;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.VisitorInterface;
import expression.Expression;

public class If extends Statement{
	public If (Expression e, Statement s1, Statement s2, Location loc){
		this.e = e;
		this.s1 = s1;
		this.s2 = s2;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitIf(this);
	}
	public Expression getE(){return e;}
	public Statement getS1(){return s1;}
	public Statement getS2(){return s2;}
	public boolean hasReturn(){
		if(s2==null)return s1.hasReturn();
		else return s1.hasReturn() && s2.hasReturn();
	}
	public void setIsBoolean (boolean isBool){
		this.isBool = isBool;
	}
	public boolean isBoolean (){return isBool;}
	public Location getLocation (){ return loc;}

	private Expression e;
	private Statement s1;
	private Statement s2;
	private boolean isBool;//This refers to the condition
	private Location loc;
}
