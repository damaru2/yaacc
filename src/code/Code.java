package code;

import java.util.LinkedList;
import java.util.List;

import visitor.VisitorInterface;

public class Code {
	public Code (){
		list = new LinkedList<CodeUnit> ();
	}
	public void push(CodeUnit c){
		list.add(c);
	}
	public List<CodeUnit> getList (){
		return list;
	}
	public void accept(VisitorInterface v){
		v.visitCode(this);
	}
	public boolean hasReturn(){
		for(CodeUnit cu : list)
			if(cu.hasReturn())
				return true;
		return false;
	}
	private List<CodeUnit> list;

}
