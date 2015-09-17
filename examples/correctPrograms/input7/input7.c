//Mutual recursive factorial


int factorial1(int n){
	if(n==0||n==1)return 1;
	else return n*factorial2(n-1);
}
int factorial2(int n){
	if(n==0||n==1)return 1;
	else return n*factorial1(n-1);
}
int factorial2(int);
void main(){
	int i = factorial1(5);
}
