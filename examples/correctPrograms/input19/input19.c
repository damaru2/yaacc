//Se pueden declarar arrays y structs recursivamente unos dentro de otros
//sin límite

//En el ejemplo, tenemos un struct tipoEj a con un array de asdf b[2] con un array de tipoEj2 x con la variable entera a.

struct tipoEj2{
	int a;
	int b;
}

struct asdf{
	tipoEj2 x[3];
	int y;
}

struct tipoEj{
	int a;
	asdf b[2];
	bool c;
}

void main(){
	tipoEj a;
	a.b[0].x[2].a = 15;
}
