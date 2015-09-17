//Use of an array as the argument of a function

int a[3][4];


int f (int[3][4] b){
	int res=0, i, j;
	for (i=0; i<3; i++)
		for (j=0; j<4; j++)
			res = res + b[i][j];
	return res;
}

void main(){
	int b[3][4];
	int i=10, j=400;
	for (i=0; i<3; i++)
		for (j=0; j<4; j++)
			b[i][j] = i*4+j;

	for (i=0; i<3; i++)
		for (j=0; j<4; j++)
			a[i][j] = b[i][j]*100;
	int k = f(b);
}
