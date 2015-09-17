//The compiler can continue the compilation proccess despite all these errors
//continuing with the analysis. Of course it doesn't generate code.

int g (); //Prototype g declared but the function was not declared.

int f (bool b){
	if (b)
		return 1;
	else{
		b = false; //There exists a branch in this function without a return.
	}
}


bool main(); //Main cannot be a protoype.

struct foo{
	int a;
}

struct foo{//Struct foo previously declared.
	bool q;
}

//The program must have a void main() function
bool main(){ //The main function must have the following format: void main().
	int i, j;

	void r[4];//An array of a void is not a valid type
	void w; //void is not a valid type for a variable.

	main(); //main function cannot be called from the code.
	break; //A break statement must be inside of a loop.
	continue; //A continue statement must be inside of a loop.
	for (i; i<3; i++); //First statement in a for must be an assignment

	foo a[4];
	if (a) //The condition in an if must be a boolean expression.
		j=1;

	while (a)//The condition in a while statement must be a boolean expression.
		j=1;

	return 0; //The returned value does not match the definition.
}
