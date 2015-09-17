package visitor;

import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

import declaration.VarDeclSimple;

public class IdTableVar {


	public VarDeclSimple getVarDeclSimple (String s){
		VarDeclSimple ret;
		for(TreeMap<String, VarDeclSimple> h : variables){
			ret = h.get(s);
			if (ret != null)
				return ret;
		}

		return null;
	}

	public TreeMap<String, VarDeclSimple> getCurrentScope(){return ((LinkedList<TreeMap<String, VarDeclSimple>>) variables).getFirst();}

	public void pushVariable (String s, VarDeclSimple i){
		((LinkedList<TreeMap<String, VarDeclSimple>>) variables).getFirst().put(s, i);
	}

	public boolean isInCurrentScope (String s ){
		return ((LinkedList<TreeMap<String,VarDeclSimple>>) variables).getFirst().get(s) != null;
	}

	public void createNewScope(){
		((LinkedList<TreeMap<String, VarDeclSimple>>) variables).addFirst(new TreeMap<String,VarDeclSimple>());
	}
	public void deleteScope(){
		((LinkedList<TreeMap<String, VarDeclSimple>>) variables).removeFirst();
	}
	public int getDepth (){
		return variables.size();
	}
	private List<TreeMap<String, VarDeclSimple>> variables = new LinkedList<TreeMap<String, VarDeclSimple>>();
}
