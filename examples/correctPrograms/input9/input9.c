//Declaración, inicialización y uso de tipos structs compuestos
//es decir struct dentro de struct
struct asdf{
	int x;
	int y;
}

struct tipoEj{
	int a;
	asdf b;
	bool c;
}

void main(){
	tipoEj a;
	int i = 3;
	a.a = i;
	a.b.x = 4;
	a.b.y = 5;
	a.c = true;
	int j = a.b.x;
	bool k = a.c;
}
