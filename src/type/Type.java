package type;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import declaration.Struct;
import visitor.VisitorInterface;
import expression.Expression;

public class Type {//¡¡¡Type is Gtipo!!!
	public Type (Location loc){
		this.loc = loc;
	}
	public Type (String typeName){
		this.typeName = typeName;
	}
	public Type (List<Expression> l, Location loc){
		this.argsArray = l;
		this.loc = loc;
	}
	public Type (String typeName, Location loc){
		this.typeName = typeName;
		this.argsArray = new LinkedList<Expression>();
		this.loc = loc;
	}
	public Type (String typeName, List<Expression> l, Location loc){
		this.typeName = typeName;
		this.argsArray = l;
		this.loc = loc;
	}


	public void setArgsArray(List<Expression> l){
		this.argsArray = l;
	}

	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	public void setStruct (Struct s){
		this.structDef = s;
	}
	public String getTypeName(){
		return typeName;
	}
	public List<Expression> getList(){
		return argsArray;
	}
	public List<Integer> getDimensions(){
		return dimensions;
	}
	public Struct getStruct(){
		return structDef;
	}

	public void pushDimension(int v){
		this.dimensions.add(new Integer(v));
	}

	public boolean equals(Type t){
		if(!t.getTypeName().equals(typeName))
			return false;
		List<Integer> l2 = t.getDimensions();
		if(l2.size() != dimensions.size())
			return false;
		Iterator<Integer>i1 =dimensions.iterator();
		Iterator<Integer>i2 =l2.iterator();
		while(i1.hasNext())
			if(!i1.next().equals(i2.next()))
				return false;
		return true;
	}
	public void accept(VisitorInterface v){ v.visitType(this); };

	public int getSize (){
		if (typeName.equals("void"))
			return 0;
		int i=1;
		for (Integer d: dimensions)
			i*=d.intValue();
		if (typeName.equals("int") || typeName.equals("bool"))
			return i;
		else
			return i*structDef.getSize();

	}

	public int getLastBound(){return ((LinkedList<Integer>)dimensions).getLast().intValue();}

	public Location getLocation (){
		return loc;
	}

	private String typeName;
	private Struct structDef;
	private List<Expression> argsArray;
	private List<Integer> dimensions = new LinkedList<Integer>();
	private Location loc;

}
