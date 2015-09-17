//Paso por valor a una función de un struct compuesto

struct asdf{
	int x;
	int y;
}

struct tipoEj{
	int a;
	asdf b;
	bool c;
}

tipoEj b;

int f (tipoEj a){
	return a.a + a.b.x + a.b.y+b.a+b.b.x+b.b.y;
}

void main(){
	tipoEj a;
	int i = 30;
	a.a = i;
	a.b.x = 40;
	a.b.y = 50;
	b.a = 60;
	b.b.x = 70;
	b.b.y = 80;
	a.c = true;
	int j = f(a); //Devuelve 330
}
