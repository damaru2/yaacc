void main(){
	int i =5, j, k, l;

	while (i--){
		if (i == 1){//because of this break, at the end, 'i' will be 1.
			l= 30;
			break;
		}
		if (i == 2){
			k = 17;
			continue;
		}
		j = i; // j will be 3 because when i==2 continue is applied and in i==1 a break is executed

	}
	// Expected final state i = 1, j=3, k = 17, l=30
}
