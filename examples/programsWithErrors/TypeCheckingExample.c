struct tipoEj{
	int a;
	bool b;
	int c [3];
}
int main(){
	//int i = -(2+3*4/5%6-7);   //Ok
	bool d;
	//bool b = (d&&i)+1; 		//Bad
	//1++; 						//Bad
	//int c[3];
	//c++; 						//Bad
	//c[1]++; 					//Ok
	tipoEj a;
	//--a.a; 					//Ok
	//--a.b; 					//Bad
	//--a.c[1]; 				//Ok
	//--a.c; 					//Bad
	return 0;
}
