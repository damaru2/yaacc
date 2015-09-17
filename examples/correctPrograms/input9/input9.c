//Declaration, initialization and use of composite structs
//this is a struct inside of a struct
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
