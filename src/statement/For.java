package statement;


import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.VisitorInterface;
import expression.Expression;
import expression.TBool;

public class For extends LoopStatement{
	public For (Expression assign, Expression cond, Expression increm, Statement s, Location loc){
		if (assign == null)
			this.assign = new EmptyInstruction();
		else
			this.assign = new ExpStatement(assign);

		if (cond == null)
			this.cond = new TBool(true);
		else
			this.cond = cond;

		if (increm == null)
			this.increm = new EmptyInstruction();
		else
			this.increm = new ExpStatement(increm);

		this.s = s;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitFor(this);
	}
	public Location getLocation (){ return loc;}
	public Statement getAssign(){return assign;}
	public Expression getCond(){return cond;}
	public Statement getIncrem(){return increm;}
	public Statement getS(){return s;}
	public boolean hasReturn(){return s.hasReturn();}
	public void setIsBoolean (boolean isBool){
		this.isBool = isBool;
	}
	public boolean isBoolean (){return isBool;}


	private Statement assign;
	private Expression cond;
	private Statement increm;
	private Statement s;
	private boolean isBool;//This refers to the condition
	private Location loc;
}
