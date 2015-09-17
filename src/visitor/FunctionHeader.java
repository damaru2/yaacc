package visitor;

import java.util.Iterator;
import java.util.List;

import type.Type;

public class FunctionHeader{
	public FunctionHeader(String id, List<Type> args){
		this.args=args;
		this.id = id;
	}

	public boolean equals(FunctionHeader fh){
		if(!id.equals(fh.getId()))
			return false;
		List<Type> args2 = fh.getArgs();
		if(args2.size() != args.size())
			return false;
		Iterator<Type>i1 =args.iterator();
		Iterator<Type>i2 =args2.iterator();
		while(i1.hasNext())
			if(!i1.next().equals(i2.next()))
				return false;
		return true;
	}



	public String getId(){return id;}
	public List<Type> getArgs(){return args;}

	private List<Type> args;
	private String id;
}
