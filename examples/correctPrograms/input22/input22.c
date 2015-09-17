//Ejemplo de sobrecarga de funciones

int f (){
	return 0;
}

int f (int a){
	return 1;
}

int f (int a, int b){
	return 2;
}
int f (bool b){
	if (b)
		return 100;
	else return 200;
}

void main(){
	int i, j, k, l;
	i= f();
	j = f(i);
	k = f(2, 3);
	l = f(true);
}
