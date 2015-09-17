//Uso de variables globales
//resultado de una función como paso de parámetros a otra función
//Uso dentro de la función f de una variable local con el mismo nombre que la global

int a;

int g (int b){
	return b+3;
}

int f (int a, int b){
	return 2*a+b;
}

void main(){
	int i = f (g(0), 4); // i = 10
	a = 3; // a = 3
	int j = a; // j = 3
}
