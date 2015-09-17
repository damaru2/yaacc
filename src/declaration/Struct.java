package declaration;

import java.util.List;
import java.util.TreeMap;

import java_cup.runtime.ComplexSymbolFactory.Location;
import program.ExternUnit;
import visitor.VisitorInterface;

public class Struct extends ExternUnit{
	public Struct (String id, List<VarDecl> decl, Location loc){
		this.declMap = new TreeMap<String,VarDeclSimple>();
		this.id = id;
		this.decl = decl;
		this.loc = loc;
	}
	public void accept(VisitorInterface v){
		v.visitStruct(this);
	}
	public List<VarDecl> getList(){
		return decl;
	}
	public String getId(){
		return id;
	}
	public VarDeclSimple getVarDeclSimple(String s){
		return declMap.get(s);
	}

	public void setDeclMap (TreeMap <String, VarDeclSimple>m){this.declMap = m;}

	public int getSize(){
		int i=0;
		for (VarDeclSimple v: declMap.values())
			i += v.getType().getSize();
		return i;
	}

	public Location getLocation (){
		return loc;
	}

	private List<VarDecl> decl;
	private TreeMap <String, VarDeclSimple> declMap;
	private String id;
	private Location loc;
}
