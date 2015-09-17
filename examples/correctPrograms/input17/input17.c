//Declaración, inicialización y uso de arrays locales y globales en un for

int a[3][4];

void main(){
	int b[3][4];
	int i=10, j=400;
	for (i=0; i<3; i++)
		for (j=0; j<4; j++)
			b[i][j] = i*4+j;

	for (i=0; i<3; i++)
		for (j=0; j<4; j++)
			a[i][j] = b[i][j]*100;
}
