//Recursive declaration of arrays and structs

//In this example we have a struct "tipoEj a" and inside an array 
//"asdf b[2]" and inside an array "tipoEj2 x" and inside the integer variable a.

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
