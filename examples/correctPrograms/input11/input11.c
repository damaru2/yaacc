//Use of global variables
//Result of a function as a parameter of another function
//Use, inside of a function, of a local variable with the same name 
//of a global variable

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
