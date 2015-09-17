package visitor;

import java.util.Comparator;
import java.util.Iterator;

import type.Type;

public class comparatorFunctionHeader implements Comparator<FunctionHeader>{

	public int compare(FunctionHeader o1, FunctionHeader o2) {
		int a = o1.getId().compareTo(o2.getId());
		if(a!=0)
			return a;
		a = o1.getArgs().size() - o2.getArgs().size();
		if(a!=0)
			return a;
		Iterator<Type>i1 =o1.getArgs().iterator();
		Iterator<Type>i2 =o2.getArgs().iterator();
		while(i1.hasNext()){
			Type t1 = i1.next();
			Type t2 = i2.next();
			a = t1.getTypeName().compareTo(t2.getTypeName());
			if (a!= 0)
				return a;
			a = t1.getDimensions().size() - t2.getDimensions().size();
			if (a!= 0)
				return a;
			Iterator<Integer> j1 =t1.getDimensions().iterator();
			Iterator<Integer> j2 =t2.getDimensions().iterator();
			while(j1.hasNext()){
				a = Integer.compare(j1.next(), j2.next());
				if (a!=0)
					return a;
			}

		}
		return 0;
	}


}
