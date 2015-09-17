struct tipoEj{
	int a;
	bool b;
	int c [3];
}
int main(){
	//int i = -(2+3*4/5%6-7);   //Ok
	bool d;
	//bool b = (d&&i)+1; 		//Mal
	//1++; 						//Mal
	//int c[3];
	//c++; 						//Mal
	//c[1]++; 					//Ok
	tipoEj a;
	//--a.a; 					//Ok
	//--a.b; 					//Mal
	//--a.c[1]; 				//Ok
	//--a.c; 					//Mal
	return 0;
}
