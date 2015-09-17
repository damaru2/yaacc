//Declaración, inicialización y uso de tipos structs simples

struct tipoEj{
	int a;
	int b;
	bool c;
}

void main(){
	tipoEj a;
	int i = 3;
	a.a = i;
	a.b = 4;
	a.c = true;
	int j = a.b;
}
