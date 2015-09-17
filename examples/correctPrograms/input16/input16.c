//Declaration, initialization and use of arrays mixing local and global ones.

int a[3];

void main(){
	int b[3];
	int i=10, j=400;
	b[0]= i;
	b[1]=j;
	b[2]= 3000;
	a[0] = b[0]+1;
	a[1] = b[1] +1;
	a[2] = b[2] +1;
}
