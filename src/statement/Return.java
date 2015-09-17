package statement;


import java_cup.runtime.ComplexSymbolFactory.Location;
import declaration.FunctionDecl;
import visitor.VisitorInterface;
import expression.Expression;

public class Return extends Statement{
	public Return (Expression e, Location loc){
		this.e = e;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitReturn(this);
	}
	public Location getLocation (){ return loc;}
	public Expression getE(){return e;}
	public FunctionDecl getF(){return f;}
	public void setF(FunctionDecl f){this.f = f;}
	public boolean hasReturn(){return true;}
	private Expression e;
	private FunctionDecl f;
	private Location loc;
}
