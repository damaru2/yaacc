//La asignación es una expresión, como tal devuelve un valor y se puede
//asignar de manera encadenada (asociatividad por la derecha), pero no
//sólo eso ¡se puede operar con el resultado de la asignación!

void main(){
	int i, j, k;
	i = j = 8+ (k=3*5);
	j=30;
}

